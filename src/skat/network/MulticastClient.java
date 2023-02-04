package skat.network;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;

public class MulticastClient implements IMulticastClient, Runnable {

    protected MulticastSocket socket;
    protected byte[] buffer;
    LinkedList<String> serverAddresses;

    public MulticastClient() {
        try {
            socket = new MulticastSocket(18081);
        } catch(IOException e) {
            e.printStackTrace();
        }
        buffer = new byte[14];
        serverAddresses = new LinkedList<>();
    }

    @Override
    public void run() {
        try {
            NetworkInterface nif = NetworkInterface.getByInetAddress(InetAddress.getByName(InetAddress.getLocalHost()
                    .getHostAddress()));
            InetSocketAddress isa = new InetSocketAddress("230.0.0.1", 18081);
            socket.joinGroup(isa, nif);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while(socket.isConnected()) {
                socket.receive(packet);
                synchronized(serverAddresses) {
                    serverAddresses.add(new String(packet.getData(), 0, packet.getLength()));
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public LinkedList<String> getAvailableServers() {
        synchronized(serverAddresses) {
            LinkedList<String> addresses = new LinkedList<>(serverAddresses);
            serverAddresses.clear();
            return addresses;
        }
    }

    @Override
    public void closeMulicastClient() {
        socket.close();
    }
}

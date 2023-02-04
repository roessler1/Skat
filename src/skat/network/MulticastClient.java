package skat.network;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class MulticastClient implements IMulticastClient, Runnable {

    protected MulticastSocket socket;
    protected byte[] buffer;
    Queue<String> serverAddresses;

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
    public Queue<String> getAvailableServers() {
        synchronized(serverAddresses) {
            return serverAddresses;
        }
    }

    @Override
    public void closeMulicastClient() {
        socket.close();
    }
}

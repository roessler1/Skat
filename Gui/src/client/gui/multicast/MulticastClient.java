package client.gui.multicast;

import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MulticastClient implements IMulticastClient {

    protected MulticastSocket socket;
    protected byte[] buffer;
    ListView<Label> serverAddresses;
    ExecutorService executor;
    Runnable runnable = this::run;

    public MulticastClient() {
        try {
            socket = new MulticastSocket(18081);
        } catch(IOException e) {
            e.printStackTrace();
        }
        buffer = new byte[14];
        serverAddresses = new ListView<>();
        executor = Executors.newSingleThreadExecutor();
        executor.execute(runnable);
    }

    public void run() {
        try {
            NetworkInterface nif = NetworkInterface.getByInetAddress(InetAddress.getByName(InetAddress.getLocalHost()
                    .getHostAddress()));
            InetSocketAddress isa = new InetSocketAddress("230.0.0.1", 18081);
            socket.joinGroup(isa, nif);
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while(socket.isConnected()) {
                socket.receive(packet);
                Label label = new Label(new String(packet.getData(), 0, packet.getLength()));
                synchronized(serverAddresses) {
                    serverAddresses.getItems().add(label);
                }
            }
            socket.leaveGroup(isa, nif);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SortedList<Label> getAvailableServers() {
        synchronized(serverAddresses) {
            SortedList<Label> addresses = serverAddresses.getItems().sorted();
            serverAddresses.getItems().clear();
            return addresses;
        }
    }

    @Override
    public void closeMulicastClient() {
        socket.close();
    }
}

package client.gui.multicast;

import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MulticastClient implements IMulticastClient {

    protected MulticastSocket socket;
    protected byte[] buffer;
    final LinkedList<Label> serverAddresses;
    ExecutorService executor;
    Runnable runnable = this::run;
    private boolean open;

    public MulticastClient() {
        open = true;
        try {
            socket = new MulticastSocket(18081);
        } catch(IOException e) {
            e.printStackTrace();
        }
        buffer = new byte[14];
        serverAddresses = new LinkedList<>();
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
            while(open) {
                socket.receive(packet);
                Label label = new Label();
                label.setText(new String(packet.getData(), 0, packet.getLength()));
                serverAddresses.add(label);
            }
            socket.leaveGroup(isa, nif);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public SortedList<Label> getAvailableServers() {
        synchronized(serverAddresses) {
            SortedList<Label> addresses = FXCollections.observableList((LinkedList<Label>) serverAddresses.clone()).sorted();
            serverAddresses.clear();
            return addresses;
        }
    }

    @Override
    public void closeMulicastClient() {
        open = false;
        socket.close();
    }
}

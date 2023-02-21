package server.multicast;

import skat.log.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class MulticastServer implements IMulticastServer {

    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buffer;
    ScheduledExecutorService send;

    public MulticastServer() {
        try {
            socket = new DatagramSocket();
            String message = InetAddress.getLocalHost().getHostAddress();
            group = InetAddress.getByName("230.0.0.1");
            buffer = message.getBytes();
            send = Executors.newScheduledThreadPool(1);
            Runnable multicast = this::sendAddress;
            send.scheduleAtFixedRate(multicast, 0, 1000, TimeUnit.MILLISECONDS);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void sendAddress() {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 18081);
        try {
            socket.send(packet);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void stopMulticast() {
        send.shutdownNow();
        send.close();
        socket.close();
    }
}

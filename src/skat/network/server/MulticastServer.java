package skat.network.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MulticastServer {
    ScheduledExecutorService send;
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buffer;
    TimerTask multicast = new TimerTask() {
        @Override
        public void run() {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 18081);
            try {
                socket.send(packet);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    };

    public MulticastServer() {
        try {
            socket = new DatagramSocket();
            String message = InetAddress.getLocalHost().getHostAddress();
            group = InetAddress.getByName("230.0.0.1");
            buffer = message.getBytes();
            send = Executors.newScheduledThreadPool(1);
            send.scheduleAtFixedRate(multicast, 0, 1000, TimeUnit.MILLISECONDS);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void stopMulticast() {
        send.shutdownNow();
        socket.close();
    }
}

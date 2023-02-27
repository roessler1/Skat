package server.multicast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

public class MulticastServerTest {

    @Test
    void testSendMessage() {
        IMulticastServer server = new MulticastServer();
        try {
            byte[] buffer = new byte[14];
            MulticastSocket socket = new MulticastSocket(18081);
            NetworkInterface nif = NetworkInterface.getByInetAddress(InetAddress.getByName(InetAddress.getLocalHost()
                    .getHostAddress()));
            InetSocketAddress isa = new InetSocketAddress("230.0.0.1", 18081);
            socket.joinGroup(isa, nif);
            String message = null;
            for(int i = 0; i < 3; i++) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("InetAddress from Server: " + message);
            }
            socket.leaveGroup(isa, nif);
            socket.close();
            server.stopMulticast();
            Assertions.assertEquals(InetAddress.getLocalHost().getHostAddress(), message);
        } catch(IOException e) {
            Assertions.fail(e.getMessage());
        }
    }
}

package server;

import server.logic.ServerLogic;
import server.multicast.IMulticastServer;
import server.multicast.MulticastServer;
import server.network.ServerIncoming;
import server.network.ServerOutgoing;
import skat.log.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class Server extends Thread {

    private ServerOutgoing[] outgoings;
    private ServerIncoming[] incomings;
    private ArrayList<Socket> sockets;
    private ExecutorService executor;
    private ServerSocketChannel socket;

    public Server() {
        sockets = new ArrayList<>();
        outgoings = new ServerOutgoing[3];
        incomings = new ServerIncoming[3];
        try {
            socket = ServerSocketChannel.open();
            socket.bind(new InetSocketAddress(18081));
            socket.configureBlocking(false);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void establishConnection(Socket socket, byte playerId, ServerLogic logic) {
        sockets.add(socket);
        try {
            outgoings[playerId] = new ServerOutgoing(socket.getOutputStream(), playerId);
            incomings[playerId] = new ServerIncoming(socket.getInputStream(), playerId, logic);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void observeInputs() {
        while(sockets.size() == 3 && sockets.get(0).isConnected() && sockets.get(1).isConnected() && sockets.get(2).isConnected()) {
            for(byte i = 0; i < sockets.size(); i++) {
                    if(incomings[i].available()) {
                    incomings[i].read();
                }
            }
        }
        for(Socket socket : sockets) {
            try {
                socket.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        sockets = null;
        incomings = null;
        outgoings = null;
    }

    @Override
    public void run() {
        ServerLogic logic = new ServerLogic(this);
        IMulticastServer multicast = new MulticastServer();
        for(byte i = 0; i < 3; i++) {
            SocketChannel sc = null;
            while(sc == null && socket.isOpen()) {
                try {
                    sc = socket.accept();
                    if(incomings[1] != null && incomings[1].available()) {
                        sockets.remove(1);
                        incomings[1] = null;
                        outgoings[1] = null;
                        i--;
                    }
                } catch(IOException e) {
                    Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
                }
            }
            if(socket.isOpen())
                establishConnection(sc.socket(), i, logic);
        }
        multicast.stopMulticast();
        executor = Executors.newSingleThreadExecutor();
        Runnable loop = this::observeInputs;
        executor.execute(loop);
        try {
            if(socket.isOpen())
                socket.close();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void closeConnection() {
        for(byte i = 0; i < 3; i++) {
            getClient(i).sendClosing();
        }
        closeServer();
    }

    public void closeServer() {
        if(this.isAlive()) {
            this.interrupt();
            for(byte i = 0; i < 3; i++) {
                if(getClient(i) != null)
                    getClient(i).sendClosing();
            }
        }
        try {
            if(socket.isOpen())
                socket.close();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public ServerOutgoing getClient(byte playerId) {
        return outgoings[playerId];
    }
}

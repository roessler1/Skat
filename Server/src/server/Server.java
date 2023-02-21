package server;

import server.network.ServerIncoming;
import server.logic.ServerLogic;
import server.network.ServerOutgoing;
import server.multicast.IMulticastServer;
import server.multicast.MulticastServer;
import skat.log.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class Server extends Thread {

    private ServerOutgoing[] outgoings;
    private ServerIncoming[] incomings;
    private Socket[] sockets;
    private ExecutorService executor;

    public Server() {
        sockets = new Socket[3];
        outgoings = new ServerOutgoing[3];
        incomings = new ServerIncoming[3];
    }

    private void establishConnection(Socket socket, byte playerId, ServerLogic logic) {
        sockets[playerId] = socket;
        try {
            outgoings[playerId] = new ServerOutgoing(socket.getOutputStream(), playerId);
            incomings[playerId] = new ServerIncoming(socket.getInputStream(), playerId, logic);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void observeInputs() {
        while(sockets[0].isConnected() && sockets[1].isConnected() && sockets[2].isConnected()) {
            for(byte i = 0; i < sockets.length; i++) {
                if(incomings[i].available()) {
                    incomings[i].read();
                }
            }
        }
        for(Socket socket:sockets) {
            try {
                socket.close();
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        }
        sockets = null;
        incomings = null;
        outgoings = null;
    }

    @Override
    public void run() {
        ServerSocket socket = null;
        try {
            socket = new ServerSocket(18081);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
        ServerLogic logic = new ServerLogic(this);
        IMulticastServer multicast = new MulticastServer();
        for(byte i = 0; i < 3; i++) {
            try {
                establishConnection(socket.accept(), i, logic);
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        }
        executor = Executors.newSingleThreadExecutor();
        Runnable loop = this::observeInputs;
        executor.execute(loop);
    }

    public void closeServer() {
        try {
            sockets[0].close();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public ServerOutgoing getClient(byte playerId) {
        return outgoings[playerId];
    }
}

package skat.logic;

import skat.log.Log;
import skat.network.ClientIncoming;
import skat.network.ClientOutgoing;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class LocalEvents {

    static LocalEvents localEvents;
    private Socket socket;
    private boolean errorOccurred;
    private ClientIncoming incoming;
    private ClientOutgoing outgoing;

    private LocalEvents() {
        localEvents = this;
    }

    public static LocalEvents getInstance() {
        if(localEvents == null) {
            new LocalEvents();
        }
        return localEvents;
    }

    public void closeConnection() {
        if(!incoming.isUp()) {
            incoming.closeInput();
        } else {
            outgoing.closeOutput();
            try {
                socket.close();
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }

    public void buildConnection(String address) {
        try {
            socket = new Socket(address, 18081);
            outgoing = new ClientOutgoing(socket.getOutputStream());
            incoming = new ClientIncoming(socket.getInputStream());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            setErrorOccurred();
        }
        if(errorOccurred) {
            return;
        }
        //TODO -> close selection panel
        //TODO -> open card panel
    }

    public void joinGame() {
        //TODO -> start multicast client
        //TODO -> open selection panel to display available servers
    }

    public void hostGame() {
        //TODO -> start server and wait for incoming connections, open multicast server
    }

    public void exit() {
        //TODO -> stop graphic rendering
        System.exit(0);
    }

    public void setErrorOccurred() {
        errorOccurred = true;
    }
}

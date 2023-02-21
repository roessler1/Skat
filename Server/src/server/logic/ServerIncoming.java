package server.logic;

import skat.log.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.logging.Level;

public class ServerIncoming {

    private ObjectInputStream in;
    private byte playerId;
    private ServerLogic logic;

    public ServerIncoming(InputStream in, byte playerId, ServerLogic logic) {
        this.playerId = playerId;
        this.logic = logic;
        try {
            this.in = new ObjectInputStream(new BufferedInputStream(in));
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void resolve(byte id) {
        switch(id) {
            //TODO -> add cases
        }
    }

    public boolean available() {
        try {
            return in.available() > 0;
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    public void read() {
        try {
            resolve(in.readByte());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

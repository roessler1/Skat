package skat.network;

import skat.log.Log;
import skat.logic.LocalEvents;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class ClientOutgoing {

    private ObjectOutputStream out;

    public ClientOutgoing(OutputStream out) {
        try {
            this.out = new ObjectOutputStream(new BufferedOutputStream(out));
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            LocalEvents.getInstance().setErrorOccurred();
        }
    }

    public void closeOutput() {
        try {
            out.close();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

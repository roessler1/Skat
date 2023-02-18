package skat.network;

import skat.cards.Card;
import skat.log.Log;
import skat.LogicEvents;

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
            LogicEvents.getInstance().setErrorOccurred();
        }
    }

    public void sendSkat(Card[] skat) {
        try {
            out.writeByte(2);
            out.flush();
            out.writeObject(skat);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void playCard(Card card) {
        try {
            out.writeByte(1);
            out.flush();
            out.writeObject(card);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
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

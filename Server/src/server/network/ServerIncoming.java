package server.network;

import server.logic.ServerLogic;
import skat.cards.Card;
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
        if(playerId == 2)
            logic.startNextRound();
    }

    private void resolve(byte id) {
        switch(id) {
            case 1 -> receivePlayedCard();
            case 2 -> receiveSkat();
            case 3 -> receiveGameId();
            case 4 -> receivePriceStage();
            case 5 -> receiveBidAnswer();
            case 6 -> logic.openGameCards();
        }
    }

    private void receiveBidAnswer() {
        try {
            logic.answerBid(in.readBoolean());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void receivePriceStage() {
        try {
            logic.setPriceStage(in.readByte());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void receiveGameId() {
        try {
            logic.setGameId(in.readByte());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void receiveSkat() {
        try {
            logic.setSkat((Card[]) in.readObject());
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage());
        }
    }

    private void receivePlayedCard() {
        try {
            logic.playCard((Card) in.readObject(), playerId);
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
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

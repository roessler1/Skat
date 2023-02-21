package server.logic;

import skat.cards.Card;
import skat.log.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;

public class ServerOutgoing {

    private ObjectOutputStream out;

    public ServerOutgoing(OutputStream out, byte playerId) {
        try {
            this.out = new ObjectOutputStream(new BufferedOutputStream(out));
            this.out.flush();
            this.out.writeByte(6);
            this.out.flush();
            this.out.writeByte(playerId);
            this.out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendRetrying(Card[] cards, byte gameId) {
        try {
            out.writeByte(13);
            out.flush();
            out.writeObject(cards);
            out.flush();
            out.writeByte(gameId);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendClosing() {
        try {
            out.writeByte(12);
            out.flush();
            out.close();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendSinglePlayer(byte singlePlayer) {
        try {
            out.writeByte(11);
            out.flush();
            out.writeByte(singlePlayer);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendPriceStage(byte priceStage) {
        try {
            out.writeByte(10);
            out.flush();
            out.writeByte(priceStage);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendGame(byte gameId) {
        try {
            out.writeByte(9);
            out.flush();
            out.writeByte(gameId);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendTurn() {
        try {
            out.writeByte(8);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendBid(short bid) {
        try {
            out.writeByte(7);
            out.flush();
            out.writeShort(bid);
            out.flush();
        } catch(IOException e){
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendPlayerPoints(byte[] playerPoints) {
        try {
            out.writeByte(5);
            out.flush();
            out.writeObject(playerPoints);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendOpenGameCards(String[] openCards) {
        try {
            out.writeByte(4);
            out.flush();
            out.writeObject(openCards);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendSkat(Card[] skat) {
        try {
            out.writeByte(3);
            out.flush();
            out.writeObject(skat);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendHand(Card[] hand) {
        try {
            out.writeByte(1);
            out.flush();
            out.writeObject(hand);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void sendPlayedCards(String[] playedCards) {
        try {
            out.writeByte(2);
            out.flush();
            out.writeObject(playedCards);
            out.flush();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

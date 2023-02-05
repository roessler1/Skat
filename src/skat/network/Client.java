package skat.network;

import skat.memory.Card;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class Client implements IClient {

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Client(Socket socket) {
        this.socket = socket;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.flush();
            in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Queue<Byte> getUpdates() {
        Queue<Byte> updates = null;
        try {
            out.writeObject("g1");
            out.flush();
            updates = (LinkedList<Byte>) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return updates;
    }

    @Override
    public Card[] getHand() {
        Card[] hand = null;
        try {
            out.writeObject("g2");
            out.flush();
            hand = (Card[]) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return hand;
    }

    @Override
    public short getCurrentBid() {
        short currentBid = 0;
        try {
            out.writeObject("g3");
            out.flush();
            currentBid = in.readShort();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return currentBid;
    }

    @Override
    public void sendBidAnswer(boolean bidAnswer) {
        try {
            out.writeObject("s3");
            out.flush();
            out.writeBoolean(bidAnswer);
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Card[] getSkat() {
        Card[] skat = null;
        try {
            out.writeObject("g4");
            out.flush();
            skat = (Card[]) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return skat;
    }

    @Override
    public void sendSkat(Card[] skat) {
        try {
            out.writeObject("s4");
            out.flush();
            out.writeObject(skat);
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte getTrumpId() {
        byte trumpId = 0;
        try {
            out.writeObject("g5");
            out.flush();
            trumpId = in.readByte();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return trumpId;
    }

    @Override
    public void sendTrumpId(byte trumpId) {
        try {
            out.writeObject("s5");
            out.flush();
            out.writeByte(trumpId);
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte getSpecialGame() {
        byte specialGame = -1;
        try {
            out.writeObject("g6");
            out.flush();
            specialGame = in.readByte();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return specialGame;
    }

    @Override
    public void sendSpecialGame(byte specialGameId) {
        try {
            out.writeObject("s6");
            out.flush();
            out.writeByte(specialGameId);
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getOpenCards() {
        String[] openCards = null;
        try {
            out.writeObject("g7");
            out.flush();
            openCards = (String[]) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return openCards;
    }

    @Override
    public Card[] getPlayedCards() {
        Card[] playedCards = null;
        try {
            out.writeObject("g8");
            out.flush();
            playedCards = (Card[]) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return playedCards;
    }

    @Override
    public void sendPlayedCard(Card playedCard) {
        try {
            out.writeObject("s8");
            out.flush();
            out.writeObject(playedCard);
            out.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public short[] getResults() {
        short[] results = null;
        try {
            out.writeObject("g9");
            out.flush();
            results = (short[]) in.readObject();
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void finishGame() {
        try {
            out.writeObject("s9");
            out.flush();
            in.close();
            out.close();
            socket.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

/*
        Codes:
            "g":
                1: updates holen
                2: eigenes Spielblatt holen
                3: aktuellen reizwert erfragen
                4: skat erfragen
                5: spiel erfragen
                6: spielzusatz erfragen
                7: offenes blatt des eizelspielers erfragen
                8: ausgespielte karten erfragen
                9: (zwischen-)ergebnis erfragen
            "s":
                3: reizwert bestätigen/ablehnen
                4: skat senden
                5: spiel ansagen
                6: spielzusatz
                8: auszuspielende karte senden
                9: beendet die verbindung und das spiel
     */

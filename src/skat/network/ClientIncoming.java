package skat.network;

import skat.log.Log;
import skat.logic.CardLogic;
import skat.memory.Card;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ClientIncoming {

    private ObjectInputStream in;
    private ExecutorService executor;
    private boolean isUp;
    private CardLogic cardLogic;
    private Runnable update = this::update;

    public ClientIncoming(InputStream in) {
        try {
            this.in = new ObjectInputStream(new BufferedInputStream(in));
        } catch(IOException e) {
            Log.getLogger().severe(e.getMessage());
        }
        isUp = true;
        cardLogic = CardLogic.getCardLogic();
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.execute(update);
    }

    private void update() {
        while(isUp) {
            try {
                byte input = in.readByte();
                resolveUpdate(input);
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    private void resolveUpdate(byte updateId) {
        switch(updateId) {
            case 1 -> updateHand();
            case 2 -> updatePlayedCards();
            case 3 -> updateSkat();
            case 4 -> updateOpenGameCards();
        }
    }

    private void updateOpenGameCards() {
        try {
            String[] openCards = (String[]) in.readObject();
            //TODO -> create open game cards panel
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateSkat() {
        try {
            cardLogic.setSkat((Card[]) in.readObject());
            //TODO -> create skat panel
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updatePlayedCards() {
        try {
            Card[] skat = (Card[]) in.readObject();
            cardLogic.setPlayedCards(skat);
            //TODO -> update open game cards
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateHand() {
        try {
            cardLogic.addCardsToHand((Card[]) in.readObject());
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void closeInput() {
        try {
            in.close();
            isUp = false;
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}

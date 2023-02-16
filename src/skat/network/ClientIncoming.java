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
        do {
            try {
                byte input = in.readByte();
                resolveUpdate(input);
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        } while(isUp);
    }

    private void resolveUpdate(byte updateId) {
        switch(updateId) {
            case 1 -> updateHand();
            case 2 -> updatePlayedCards();
            case 3 -> updateSkat();
            case 4 -> updateOpenGameCards();
            case 5 -> updatePlayerPoints();
            case 6 -> updateId();
            case 7 -> updateBid();
            case 8 -> updateTurn();
            case 9 -> updateGame();
            case 10 -> updateGameAddition();
            case 11 -> updateSinglePlayerId();
            case 12 -> closeInput();
        }
    }

    private void updateSinglePlayerId() {
        try {
            byte singlePlayer = in.readByte();
            //TODO -> displaying single player
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateGameAddition() {
        try {
            String msg = switch(in.readByte()) {
                case 2 -> "Hand";
                case 3 -> "Schneider";
                case 4 -> "Schwarz";
                case 5 -> "Ouvert";
                default -> "";
            };
            //TODO -> display game addition together with the game announcement
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateGame() {
        try {
            byte game = in.readByte();
            cardLogic.setGameId(game);
            //TODO -> announce game
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateTurn() {
        try {
            boolean turn = in.readBoolean();
            //TODO -> set turn
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateBid() {
        try {
            short bid = in.readShort();
            //TODO -> create bid panel
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateId() {
        try {
            byte id = in.readByte();
            //TODO -> set own id
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updatePlayerPoints() {
        try {
            byte[] points = (byte[]) in.readObject();
            //TODO -> open result panel
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
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
            isUp = false;
            in.close();
            cardLogic = null;
            CardLogic.deleteCardLogic();
            //TODO -> close network output and socket, change to main menu
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
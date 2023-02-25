package client.logic.network;

import client.gui.pane_controller.GuiController;
import skat.log.Log;
import client.logic.logic.CardLogic;
import skat.cards.Card;
import client.logic.LogicEvents;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ClientIncoming {

    private ObjectInputStream in;
    private ExecutorService executor;
    private boolean isUp;
    private CardLogic cardLogic;
    private byte singlePlayer;

    public ClientIncoming(InputStream in, CardLogic cardLogic) {
        try {
            this.in = new ObjectInputStream(new BufferedInputStream(in));
        } catch(IOException e) {
            Log.getLogger().severe(e.getMessage());
            LogicEvents.getInstance().setErrorOccurred();
            return;
        }
        isUp = true;
        this.cardLogic = cardLogic;
        executor = Executors.newSingleThreadScheduledExecutor();
        Runnable update = this::update;
        executor.execute(update);
    }

    private void update() {
        do {
            try {
                if(in.available() > 0) {
                    byte input = in.readByte();
                    resolveUpdate(input);
                }
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
            case 8 -> LogicEvents.getInstance().setTurn();
            case 9 -> updateGame();
            case 10 -> updatePriceStage();
            case 11 -> updateSinglePlayerId();
            case 12 -> closeInput();
            case 13 -> retryPlayCard();
        }
    }

    private void retryPlayCard() {
        try {
            Card card = (Card) in.readObject();
            cardLogic.addCardsToHand(new ArrayList<>(Arrays.asList(card)));
            cardLogic.setGameId(in.readByte());
            LogicEvents.getInstance().setTurn();
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateSinglePlayerId() {
        try {
            byte singlePlayer = in.readByte();
            LogicEvents.getInstance().getInformation().setSoloPlayerColumn(singlePlayer);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updatePriceStage() {
        try {
            byte profitLevel = in.readByte();
            LogicEvents.getInstance().getInformation().setWinTierColumn(profitLevel);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateGame() {
        try {
            byte game = in.readByte();
            cardLogic.setGameId(game);
            GuiController.getInstance().loadPlayedCards();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateBid() {
        try {
            LogicEvents.getInstance().setBid(in.readShort());
            GuiController.getInstance().loadBidPanel();
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateId() {
        try {
            LogicEvents.getInstance().setPlayerId(in.readByte());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updatePlayerPoints() {
        try {
            LogicEvents.getInstance().setPlayerPoints((short[]) in.readObject());
            GuiController.getInstance().loadResults();
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
            GuiController.getInstance().loadBidPanel();
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updatePlayedCards() {
        try {
            String[] playedCards = (String[]) in.readObject();
            GuiController.getInstance().addPlayedCard(playedCards);
            //TODO -> update open game cards
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void updateHand() {
        try {
            cardLogic.addCardsToHand((ArrayList<Card>) in.readObject());
            //TODO -> reset others cards
            /*GuiController.getInstance().getRightPlayer().resetCards();
            GuiController.getInstance().getLeftPlayer().resetCards();*/
        } catch(IOException | ClassNotFoundException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void closeInput() {
        isUp = false;
        cardLogic = null;
        LogicEvents.getInstance().closeConnection();
    }

    public boolean isUp() {
        return isUp;
    }
}

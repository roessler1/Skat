package client.logic.logic;

import client.gui.pane_controller.GuiController;
import client.gui.pane_events.SkatEvents;
import client.logic.LogicEvents;
import skat.cards.Card;
import client.logic.memory.Hand;
import client.logic.memory.IHand;

import java.util.ArrayList;
import java.util.Arrays;

public class CardLogic {

    private final IHand hand;
    private Card[] skat;
    private boolean handGame;
    private SkatEvents skatEvents;

    public CardLogic() {
        hand = new Hand();
        handGame = true;
    }

    public String[] getOpenGameCards() {
        return hand.getCardsUrls();
    }

    public void insertSkat() {
        addCardsToHand(new ArrayList<>(Arrays.asList(skat)));
        handGame = false;
        for(byte i = 0; i < skat.length; i++)
            skat[i] = null;
        GuiController.getInstance().loadSkatPane();
    }

    public Card[] getSkat() {
        return skat;
    }

    public void setSkat(Card[] skat) {
        this.skat = skat;
    }

    public void putToSkat(String cardUrl) {
        if(skat[0] == null) {
            skat[0] = hand.removeByUrl(cardUrl);
            skatEvents.pushCard(skat[0].getUrl());
        }
        else if(skat[1] == null) {
            skat[1] = hand.removeByUrl(cardUrl);
            skatEvents.pushCard(skat[1].getUrl());
        }
        else addCardsToHand(hand.getCards());
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public Card getCardByUrl(String cardUrl) {
        return hand.removeByUrl(cardUrl);
    }

    public void addCardsToHand(ArrayList<Card> cards) {
        hand.addCards(cards);
        GuiController.getInstance().addCards(hand.getCardsUrls());
        if (cards.size() != 1) {
            GuiController.getInstance().unloadCenterPane();
        }
    }

    public void setGameId(byte gameId) {
        LogicEvents.getInstance().getInformation().setGameTypeColumn(gameId);
        hand.sortCards(gameId);
    }

    public boolean hasSkat() {
        return skat != null;
    }

    public boolean isHandGame() {
        boolean handGame = this.handGame;
        this.handGame = true;
        return handGame;
    }
    public void setSkatEvents(SkatEvents events) {
        skatEvents = events;
    }

    public void clearHand() {
        hand.emptyHand();
    }
}

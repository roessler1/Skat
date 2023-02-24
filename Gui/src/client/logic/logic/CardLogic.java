package client.logic.logic;

import client.gui.pane_controller.GuiController;
import skat.cards.Card;
import client.logic.memory.Hand;
import client.logic.memory.IHand;

import java.util.ArrayList;
import java.util.Arrays;

public class CardLogic {

    private final IHand hand;
    private Card[] skat;
    private boolean handGame;

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
        //TODO -> update skat panel
    }

    public Card[] getSkat() {
        return skat;
    }

    public void setSkat(Card[] skat) {
        this.skat = skat;
        //TODO -> create skat panel
    }

    public void putToSkat(String cardUrl) {
        if(skat[0] == null) {
            skat[0] = hand.removeByUrl(cardUrl);
            //TODO -> update graphic
        }
        if(skat[1] == null) {
            skat[1] = hand.removeByUrl(cardUrl);
            //TODO -> update graphic
        }
    }

    public int getHandSize() {
        return hand.getSize();
    }

    public Card getCardByUrl(String cardUrl) {
        return hand.getCard(cardUrl);
    }

    public void addCardsToHand(ArrayList<Card> cards) {
        hand.addCards(cards);
        GuiController.getInstance().addCards(hand.getCardsUrls());
    }

    public void setGameId(byte gameId) {
        //TODO -> displaying game
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
}

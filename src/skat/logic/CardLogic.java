package skat.logic;

import skat.gui.GraphicController;
import skat.memory.*;

import java.util.ArrayList;

public class CardLogic {

    static CardLogic cardLogic;
    private IHand hand;
    private IPlayedCards playedCards;
    private byte gameId;

    private CardLogic() {
        cardLogic = this;
        hand = new Hand();
        playedCards = new PlayedCards();
        gameId = 0;
    }

    public static CardLogic getCardLogic() {
        if(cardLogic == null) {
            new CardLogic();
        }
        return cardLogic;
    }

    public static void deleteCardLogic() {
        cardLogic = null;
    }

    public void addCardsToHand(Card[] cards) {
        hand.addCards(cards);
        addHandGraphic();
    }

    public void setPlayedCards(Card[] playedCards) {
        this.playedCards.setPlayedCards(playedCards);
        String[] cardUrls = new String[playedCards.length];
        for(byte i = 0; i < playedCards.length; i++) {
            cardUrls[i] = playedCards[i].getUrl();
        }
        GraphicController.getInstance().addPlayedCardsGraphic(cardUrls);
    }

    private void addHandGraphic() {
        ArrayList<Card> handCards = hand.getCards();
        String[] cardUrls = new String[handCards.size()];
        for(byte i = 0; i < handCards.size(); i++) {
            cardUrls[i] = handCards.get(i).getUrl();
        }
        GraphicController.getInstance().addHandGraphic(cardUrls);
    }

    public void setSkat(Card[] skat) {
        hand.addCards(skat);
        addHandGraphic();
    }

    public void setGameId(byte gameId) {
        this.gameId = gameId;
        hand.sortCards(gameId);
    }
}

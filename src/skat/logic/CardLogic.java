package skat.logic;

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

    public static CardLogic getInstance() {
        if(cardLogic == null) {
            new CardLogic();
        }
        return cardLogic;
    }

    public static void deleteInstance() {
        cardLogic = null;
    }

    public void addCardsToHand(Card[] cards) {
        hand.addCards(cards);
        addHandGraphic();
    }

    public void setPlayedCards(Card[] playedCards) {
        this.playedCards.setPlayedCards(playedCards);
        //TODO -> update Graphic
    }

    private void addHandGraphic() {
        ArrayList<Card> handCards = hand.getCards();
        //TODO -> update Graphic
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

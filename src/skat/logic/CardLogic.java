package skat.logic;

import skat.memory.*;

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
        //TODO -> update Graphic
    }

    public void setPlayedCards(Card[] playedCards) {
        this.playedCards.setPlayedCards(playedCards);
        //TODO -> update Graphic
    }

    public void setSkat(Card[] skat) {
        hand.addCards(skat);
        //TODO -> update Graphic
    }

    public void setGameId(byte gameId) {
        this.gameId = gameId;
        hand.sortCards(gameId);
    }
}

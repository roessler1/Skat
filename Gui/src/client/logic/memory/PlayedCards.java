package client.logic.memory;

import client.logic.cards.Card;

public class PlayedCards implements IPlayedCards {

    private Card[] playedCards;

    public PlayedCards() {
        playedCards = new Card[3];
    }

    @Override
    public void setPlayedCards(Card[] playedCards) {
        this.playedCards = playedCards;
    }
}

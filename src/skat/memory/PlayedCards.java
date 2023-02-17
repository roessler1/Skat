package skat.memory;

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

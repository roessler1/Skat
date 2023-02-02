package skat.memory;

public interface IPlayedCards {

    void setPlayedCards(Card[] playedCards);
    Card getFirstPlayedCard();
    void addPlayedCard(Card card);
    boolean isComplete();
    Card[] removePlayedCards();
    String[] getPlayedCardsUrl();
}

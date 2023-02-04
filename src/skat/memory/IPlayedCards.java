package skat.memory;

public interface IPlayedCards {

    Card[] getPlayedCards();

    void setPlayedCards(Card[] playedCards);

    Card getFirstPlayedCard();

    void addPlayedCard(Card card);

    boolean isComplete();

    boolean isEmpty();

    void removePlayedCards();

    String[] getPlayedCardsUrl();
}

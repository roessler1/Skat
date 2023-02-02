package skat.memory;

public interface IHand {

    void addCards(Card[] cards);

    String[] getGraphicsUrl();

    void emptyHand();

    Card removeCard(String cardUrl);

    Card getCard(String cardUrl);

    void sortHand(byte game);
}

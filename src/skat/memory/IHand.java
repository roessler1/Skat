package skat.memory;

public interface IHand {

    void addCards(Card[] cards);

    boolean hasTrumpCard(byte trumpId);

    boolean hasCard(byte colorId);

    String[] getGraphicsUrl();

    void emptyHand();

    void removeCard(String cardUrl);

    Card getCard(String cardUrl);

    void sortHand(byte game);
}

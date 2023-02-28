package client.logic.memory;

import skat.cards.Card;

public interface IHand {

    void addCards(Card[] cards);

    Card getCard(String cardUrl);

    Card[] getCards();

    String[] getCardsUrls();

    int getSize();

    Card removeByUrl(String cardUrl);

    void sortCards(byte gameId);

    void emptyHand();
}

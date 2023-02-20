package client.logic.memory;

import client.logic.cards.Card;

import java.util.ArrayList;

public interface IHand {

    void addCards(Card[] cards);

    Card getCard(String cardUrl);

    ArrayList<Card> getCards();

    String[] getCardsUrls();

    int getSize();

    Card removeByUrl(String cardUrl);

    void sortCards(byte gameId);
}

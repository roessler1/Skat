package skat.memory;

import skat.cards.Card;

import java.util.ArrayList;

public interface IHand {

    void addCards(Card[] cards);

    Card getCard(String cardUrl);

    ArrayList<Card> getCards();

    int getSize();

    Card removeByUrl(String cardUrl);

    void sortCards(byte gameId);
}

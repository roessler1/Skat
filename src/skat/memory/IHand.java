package skat.memory;

import java.util.ArrayList;

public interface IHand {

    void addCards(Card[] cards);

    ArrayList<Card> getCards();

    void sortCards(byte gameId);
}

package skat.memory;

import skat.logic.CardSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand implements IHand {

    private ArrayList<Card> hand;
    private CardSort sort;

    public Hand() {
        hand = new ArrayList<>();
        sort = new CardSort();
    }

    @Override
    public void addCards(Card[] cards) {
        hand.addAll(Arrays.asList(cards));
        sort.sort(hand, (byte) 24);
    }

    @Override
    public ArrayList<Card> getCards() {
        return hand;
    }

    @Override
    public void sortCards(byte gameId) {
        sort.sort(hand, gameId);
    }
}

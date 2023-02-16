package skat.memory;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand implements IHand {

    private ArrayList<Card> hand;

    public Hand() {
        hand = new ArrayList<>();
    }

    @Override
    public void addCards(Card[] cards) {
        hand.addAll(Arrays.asList(cards));
    }
}

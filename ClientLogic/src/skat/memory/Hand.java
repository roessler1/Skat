package skat.memory;

import skat.cards.Card;
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
        hand = sort.sort(hand, (byte) 24);
    }

    @Override
    public Card getCard(String cardUrl) {
        for(Card card:hand) {
            if(card.getUrl().equals(cardUrl)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Card> getCards() {
        return hand;
    }

    @Override
    public int getSize() {
        return hand.size();
    }

    @Override
    public Card removeByUrl(String cardUrl) {
        Card card = getCard(cardUrl);
        hand.remove(card);
        return card;
    }

    @Override
    public void sortCards(byte gameId) {
        hand = sort.sort(hand, gameId);
    }
}

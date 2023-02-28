package client.logic.memory;

import skat.cards.Card;
import skat.compare.CardSort;

import java.util.ArrayList;
import java.util.Arrays;

public class Hand implements IHand {

    private ArrayList<Card> hand;
    private final CardSort sort;

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
    public Card[] getCards() {
        return (Card[]) hand.toArray();
    }

    @Override
    public String[] getCardsUrls() {
        String[] cardsUrls = new String[hand.size()];
        for(byte i = 0; i < hand.size(); i++) {
            cardsUrls[i] = hand.get(i).getUrl();
        }
        return cardsUrls;
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

    @Override
    public void emptyHand() {
        hand.clear();
    }
}

package skat.logic;

import skat.memory.*;

public class CardLogic {

    private IHand hand;
    private IPlayedCards playedCards;
    private byte trumpId;
    private boolean myTurn;

    public CardLogic() {
        hand = new Hand();
        playedCards = new PlayedCards();
        myTurn = false;
    }

    protected void addCards(Card[] cards) {
        hand.addCards(cards);
        //TODO -> Grafik updaten
    }

    protected void emptyHand() {
        hand.emptyHand();
        //TODO -> Grafik updaten
    }

    protected void setTrump(byte trumpId) {
        this.trumpId = trumpId;
        switch(trumpId) {
            case 35, 46, 59 -> hand.sortHand((byte) 23);
            default -> hand.sortHand(trumpId);
        }
    }

    protected Card[] getPlayedCards() {
        return playedCards.getPlayedCards();
    }

    protected void setPlayedCards(Card[] cards) {
        playedCards.setPlayedCards(cards);
        //TODO -> grafik updaten
    }

    protected void playCard(String cardUrl) {
        if(!myTurn) {
            return;
        }
        Card card = hand.getCard(cardUrl);
        if(playedCards.isEmpty()) {
            playedCards.addPlayedCard(card);
            hand.removeCard(cardUrl);
            myTurn = false;
            //TODO -> server und grafik updaten
            return;
        }
        Card comparison = playedCards.getFirstPlayedCard();
        if(checkPlayable(card, comparison)) {
            playedCards.addPlayedCard(card);
            hand.removeCard(cardUrl);
            myTurn = false;
            //TODO -> server und grafik updaten
        }
    }

    private boolean checkPlayable(Card card, Card comparison) {
        if(comparison.getValue() == 2 || comparison.getColor() == trumpId) {
            return compareByTrump(card, comparison);
        } else {
            return compareByColor(card, comparison);
        }
    }

    private boolean compareByTrump(Card card, Card comparison) {
        switch(trumpId) {
            case 23, 35, 46, 59 -> {
                return compareByColor(card, comparison);
            }
        }
        if(card.getColor() == trumpId || card.getValue() == 2)
            return true;
        return !hand.hasTrumpCard(trumpId);
    }

    private boolean compareByColor(Card card, Card comparison) {
        if(card.getColor() == comparison.getColor()) {
            return true;
        }
        return !hand.hasCard(comparison.getColor());
    }

    private void setMyTurn() {
        if(playedCards.isComplete()) {
            playedCards.removePlayedCards();
            //TODO -> Grafik updaten
        }
        myTurn = true;
    }

    private byte getTrumpId() {
        return trumpId;
    }
}

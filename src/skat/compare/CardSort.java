package skat.compare;

import skat.cards.Card;

import java.util.ArrayList;

public class CardSort {

    public ArrayList<Card> sort(ArrayList<Card> cards, byte game) {
        for(int i = 1; i < cards.size(); i++) {
            Card temp = cards.get(i);
            int j = i;
            while((j > 0) && (compareByGame(cards.get(j - 1), temp, game) == 1)) {
                cards.set(j, cards.get(j - 1));
                j--;
            }
            cards.set(j, temp);
        }
        return cards;
    }

    public byte compareByGame(Card card1, Card card2, byte game) {
        switch(game) {
            case 9 -> {
                return compareDiamonds(card1, card2);
            }
            case 10 -> {
                return compareHearts(card1, card2);
            }
            case 11 -> {
                return compareSpades(card1, card2);
            }
            case 12 -> {
                return compareClubs(card1, card2);
            }
            case 24 -> {
                return compareGrand(card1, card2);
            }
            case 23, 35, 46, 59 -> {
                return compareNull(card1, card2);
            }
            default -> {
                return 0;
            }
        }
    }

    private byte compareDiamonds(Card card1, Card card2) {
        byte result = compareGrand(card1, card2);
        if(card2.getColor() == 9 && card1.getValue() != 2) {
            if(card1.getColor() > 9)
                result = 1;
        }
        if(card1.getColor() == 9 && card2.getValue() != 2) {
            if(card2.getColor() > 9)
                result = -1;
        }
        return result;
    }

    private byte compareHearts(Card card1, Card card2) {
        byte result = compareGrand(card1, card2);
        if(card2.getColor() == 10 && card1.getValue() != 2) {
            if(card1.getColor() > 10)
                result = 1;
        }
        if(card1.getColor() == 10 && card2.getValue() != 2) {
            if(card2.getColor() > 10)
                result = -1;
        }
        return result;
    }

    private byte compareSpades(Card card1, Card card2) {
        byte result = compareGrand(card1, card2);
        if(card2.getColor() == 11 && card1.getValue() != 2) {
            if(card1.getColor() > 11)
                result = 1;
        }
        if(card1.getColor() == 11 && card2.getValue() != 2) {
            if(card2.getColor() > 11)
                result = -1;
        }
        return result;
    }

    private byte compareClubs(Card card1, Card card2) {
        byte result = compareGrand(card1, card2);
        if(card2.getColor() == 12 && card1.getValue() != 2) {
            if(card1.getColor() > 12)
                result = 1;
        }
        if(card1.getColor() == 12 && card2.getColor() != 2) {
            if(card2.getColor() > 12)
                result = -1;
        }
        return result;
    }

    private byte compareGrand(Card card1, Card card2) {
        if(card1.getValue() == 2 && card2.getValue() == 2) {
            if(card1.getColor() > card2.getColor())
                return -1;
            if(card1.getColor() < card2.getColor())
                return 1;
        }
        if(card1.getValue() == 2)
            return -1;
        if(card2.getValue() == 2)
            return 1;
        if(card1.getColor() == card2.getColor()) {
            if(card1.getValue() > card2.getValue())
                return -1;
            if(card1.getValue() < card2.getValue())
                return 1;
        }
        if(card1.getColor() > card2.getColor())
            return -1;
        if(card1.getColor() < card2.getColor())
            return 1;
        return 0;
    }

    private byte compareNull(Card card1, Card card2) {
        if(card1.getId() > card2.getId()) {
            return 1;
        }
        if(card1.getId() < card2.getId()) {
            return -1;
        }
        return 0;
    }
}

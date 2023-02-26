package server.logic;

import skat.cards.Card;
import skat.compare.CardSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class CardPartLogic {

    private ArrayList<Card> cards;
    private ArrayList<Card>[] hands;
    private Card[] playedCards;
    private Card[] skat;
    private Random random;
    private byte gameId;

    protected CardPartLogic() {
        cards = new ArrayList<>();
        hands = new ArrayList[3];
        playedCards = new Card[3];
        skat = new Card[2];
        random = new Random();
    }

    protected void shuffleCards() {
        if(cards.size() != 32) {
            cards.clear();
            cards.addAll(Arrays.asList(Card.values()));
        }
        while(!cards.isEmpty()) {
            if(cards.size() == 23) {
                skat[0] = cards.remove(random.nextInt(0, cards.size()));
                skat[1] = cards.remove(random.nextInt(0, cards.size()));
            }
            for(byte i = 0; i < 3; i++) {
                hands[i].add(cards.remove(random.nextInt(0, cards.size())));
            }
        }
    }

    protected boolean isPlayable(Card card, byte playerId) {
        if(cards.contains(card) || !hands[playerId].contains(card))
            return false;
        if(playedCards[0] == null)
            return true;
        byte game = switch(gameId) {
            case 23, 35, 46, 59 -> 23;
            default -> gameId;
        };
        if(game != 23 && (playedCards[0].getColor() == gameId || playedCards[0].getValue() == 2))
            return checkTrump(card, playerId);
        else
            return checkColor(card,playerId);
    }

    private boolean checkColor(Card card, byte playerId) {
        if(card.getColor() == playedCards[0].getColor())
            return true;
        for(Card reference:hands[playerId]) {
            if(reference.getColor() == playedCards[0].getColor()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkTrump(Card card, byte playerId) {
        if(card.getValue() == 2 || card.getColor() == gameId)
            return true;
        for(Card reference:hands[playerId]) {
            if(reference.getColor() == gameId || reference.getValue() == 2) {
                return false;
            }
        }
        return true;
    }

    protected void setSkat(Card[] skat, byte playerId) {
        if(!Arrays.equals(this.skat, skat)) {
            hands[playerId].addAll(Arrays.asList(this.skat));
            hands[playerId].remove(skat[0]);
            hands[playerId].remove(skat[1]);
        }
        CardSort sort = new CardSort();
        hands[playerId] = sort.sort(hands[playerId], gameId);
        cards.addAll(Arrays.asList(skat));
    }

    protected Card[] getSkat() {
        return skat;
    }

    protected void setGameId(byte gameId) {
        this.gameId = gameId;
    }

    protected void emptyPlayedCards() {
        for(int i = 0; i < 3; i++) {
            playedCards[i] = null;
        }
    }

    protected void playCard(Card card, byte playerId) {
        cards.add(card);
        byte i = 0;
        while(playedCards[i] != null) {
            i++;
        }
        playedCards[i] = card;
        hands[playerId].remove(card);
    }

    protected Card[] getPlayedCards() {
        return playedCards;
    }

    protected ArrayList<Card> getHand(byte playerId) {
        return hands[playerId];
    }

    protected boolean isPlayedCardsFull() {
        return playedCards[2] != null;
    }

    protected boolean isComplete() {
        return cards.size() == 32;
    }

    protected String[] getHandGraphics(byte singlePlayer) {
        String[] hand = new String[10];
        for(byte i = 0; i < 10; i++) {
            hand[i] = hands[singlePlayer].get(i).getUrl();
        }
        return hand;
    }
}

package skat.compare;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skat.cards.Card;

import java.util.ArrayList;

class CardSortTest {

    private ArrayList<Card> cards;
    private static CardSort cardSort;

    @BeforeAll
    static void setUpClass() {
        cardSort = new CardSort();
    }

    @BeforeEach
    void setUp() {
        cards = new ArrayList<>();
        cards.add(Card.C_SEVEN);
        cards.add(Card.D_KING);
        cards.add(Card.C_ACE);
        cards.add(Card.D_SEVEN);
        cards.add(Card.S_SEVEN);
        cards.add(Card.S_ACE);
        cards.add(Card.D_JACK);
        cards.add(Card.H_JACK);
        cards.add(Card.H_TEN);
        cards.add(Card.H_QUEEN);
    }

    @Test
    void sortLikeGrand() {
        cards = cardSort.sort(cards, (byte) 24);
        Assertions.assertEquals(19, cards.get(0).getId());
        Assertions.assertEquals(27, cards.get(1).getId());
        Assertions.assertEquals(0, cards.get(2).getId());
        Assertions.assertEquals(7, cards.get(3).getId());
        Assertions.assertEquals(8, cards.get(4).getId());
        Assertions.assertEquals(15, cards.get(5).getId());
        Assertions.assertEquals(20, cards.get(6).getId());
        Assertions.assertEquals(18, cards.get(7).getId());
        Assertions.assertEquals(25, cards.get(8).getId());
        Assertions.assertEquals(31, cards.get(9).getId());
    }

    @Test
    void sortLikeNull() {
        cards = cardSort.sort(cards, (byte) 23);
        Assertions.assertEquals(0, cards.get(0).getId());
        Assertions.assertEquals(7, cards.get(1).getId());
        Assertions.assertEquals(8, cards.get(2).getId());
        Assertions.assertEquals(15, cards.get(3).getId());
        Assertions.assertEquals(18, cards.get(4).getId());
        Assertions.assertEquals(19, cards.get(5).getId());
        Assertions.assertEquals(20, cards.get(6).getId());
        Assertions.assertEquals(25, cards.get(7).getId());
        Assertions.assertEquals(27, cards.get(8).getId());
        Assertions.assertEquals(31, cards.get(9).getId());
    }

    @Test
    void sortLikeClubs() {
        cards = cardSort.sort(cards, (byte) 12);
        Assertions.assertEquals(19, cards.get(0).getId());
        Assertions.assertEquals(27, cards.get(1).getId());
        Assertions.assertEquals(0, cards.get(2).getId());
        Assertions.assertEquals(7, cards.get(3).getId());
        Assertions.assertEquals(8, cards.get(4).getId());
        Assertions.assertEquals(15, cards.get(5).getId());
        Assertions.assertEquals(20, cards.get(6).getId());
        Assertions.assertEquals(18, cards.get(7).getId());
        Assertions.assertEquals(25, cards.get(8).getId());
        Assertions.assertEquals(31, cards.get(9).getId());
    }

    @Test
    void sortLikeSpades() {
        cards = cardSort.sort(cards, (byte) 11);
        Assertions.assertEquals(19, cards.get(0).getId());
        Assertions.assertEquals(27, cards.get(1).getId());
        Assertions.assertEquals(8, cards.get(2).getId());
        Assertions.assertEquals(15, cards.get(3).getId());
        Assertions.assertEquals(0, cards.get(4).getId());
        Assertions.assertEquals(7, cards.get(5).getId());
        Assertions.assertEquals(20, cards.get(6).getId());
        Assertions.assertEquals(18, cards.get(7).getId());
        Assertions.assertEquals(25, cards.get(8).getId());
        Assertions.assertEquals(31, cards.get(9).getId());
    }

    @Test
    void sortLikeHearts() {
        cards = cardSort.sort(cards, (byte) 10);
        Assertions.assertEquals(19, cards.get(0).getId());
        Assertions.assertEquals(27, cards.get(1).getId());
        Assertions.assertEquals(20, cards.get(2).getId());
        Assertions.assertEquals(18, cards.get(3).getId());
        Assertions.assertEquals(0, cards.get(4).getId());
        Assertions.assertEquals(7, cards.get(5).getId());
        Assertions.assertEquals(8, cards.get(6).getId());
        Assertions.assertEquals(15, cards.get(7).getId());
        Assertions.assertEquals(25, cards.get(8).getId());
        Assertions.assertEquals(31, cards.get(9).getId());
    }

    @Test
    void sortLikeDiamonds() {
        cards = cardSort.sort(cards, (byte) 9);
        Assertions.assertEquals(19, cards.get(0).getId());
        Assertions.assertEquals(27, cards.get(1).getId());
        Assertions.assertEquals(25, cards.get(2).getId());
        Assertions.assertEquals(31, cards.get(3).getId());
        Assertions.assertEquals(0, cards.get(4).getId());
        Assertions.assertEquals(7, cards.get(5).getId());
        Assertions.assertEquals(8, cards.get(6).getId());
        Assertions.assertEquals(15, cards.get(7).getId());
        Assertions.assertEquals(20, cards.get(8).getId());
        Assertions.assertEquals(18, cards.get(9).getId());
    }
}
package skat.memory;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HandTest {

    private static Card[] cards;
    private static IHand hand;

    @BeforeAll
    static void setUpClass() {
        cards = new Card[4];
        hand = new Hand();
        cards[0] = Card.S_ACE;
        cards[1] = Card.C_SEVEN;
        cards[2] = Card.D_JACK;
        cards[3] = Card.H_TEN;
    }

    @Test
    @Order(1)
    void addCardsTest() {
        hand.addCards(cards);
        Assertions.assertEquals(cards[0], hand.getCard(cards[0].getUrl()));
        Assertions.assertEquals(cards[1], hand.getCard(cards[1].getUrl()));
        Assertions.assertEquals(cards[2], hand.getCard(cards[2].getUrl()));
        Assertions.assertEquals(cards[3], hand.getCard(cards[3].getUrl()));
    }

    @Test
    @Order(2)
    void getGraphicsUrlTest() {
        String[] graphicsUrl = hand.getGraphicsUrl();
        Assertions.assertEquals(cards[2].getUrl(), graphicsUrl[0]);
        Assertions.assertEquals(cards[1].getUrl(), graphicsUrl[1]);
        Assertions.assertEquals(cards[0].getUrl(), graphicsUrl[2]);
        Assertions.assertEquals(cards[3].getUrl(), graphicsUrl[3]);
    }

    @Test
    @Order(3)
    void removeCardTest() {
        hand.removeCard(cards[2].getUrl());
        Assertions.assertNull(hand.getCard(cards[2].getUrl()));
        Assertions.assertNotNull(hand.getCard(cards[0].getUrl()));
    }

    @Test
    @Order(4)
    void emptyHand() {
        hand.emptyHand();
        Assertions.assertNull(hand.getCard(cards[0].getUrl()));
        Assertions.assertNull(hand.getCard(cards[1].getUrl()));
        Assertions.assertNull(hand.getCard(cards[3].getUrl()));
    }
}

package skat.memory;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PlayedCardsTest {

    private static Card[] cards;
    private static IPlayedCards playedCards;

    @BeforeAll
    static void setUpClass() {
        cards = new Card[3];
        cards[0] = Card.C_ACE;
        cards[1] = Card.H_QUEEN;
        cards[2] = Card.D_NINE;
        playedCards = new PlayedCards();
    }

    @Test
    @Order(1)
    void addPlayedCardTest() {
        playedCards.addPlayedCard(cards[0]);
        Assertions.assertEquals(cards[0], playedCards.getFirstPlayedCard());
    }

    @Test
    @Order(2)
    void getPlayedCardsUrlTest() {
        playedCards.setPlayedCards(cards);
        String[] graphics = playedCards.getPlayedCardsUrl();
        for(byte i = 0; i < cards.length; i++) {
            Assertions.assertEquals(cards[i].getUrl(), graphics[i]);
        }
    }

    @Test
    @Order(3)
    void isCompleteTest() {
        Assertions.assertTrue(playedCards.isComplete());
    }

    @Test
    @Order(4)
    void removePlayedCardsTest() {
        Card[] testCards = playedCards.removePlayedCards();
        Assertions.assertNull(playedCards.getFirstPlayedCard());
        for(byte i = 0; i < cards.length; i++) {
            Assertions.assertEquals(cards[i], testCards[i]);
        }
    }
}

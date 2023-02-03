package skat.memory;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OpenCardsTest {

    private static IOpenCards openCards;
    private static String[] cards;

    @BeforeAll
    static void setUpClass() {
        cards = new String[4];
        cards[0] = Card.S_KING.getUrl();
        cards[1] = Card.C_TEN.getUrl();
        cards[2] = Card.D_JACK.getUrl();
        cards[3] = Card.H_EIGHT.getUrl();
        openCards = new OpenCards(cards);
    }

    @Test
    @Order(1)
    void getOpenCards() {
        Assertions.assertEquals(cards.length, openCards.getSize());
        ArrayList<String> testCards = openCards.getOpenCards();
        for(byte i = 0; i < cards.length; i++) {
            Assertions.assertEquals(cards[i], testCards.get(i));
        }
    }

    @Test
    @Order(2)
    void removeCardTest() {
        int size = openCards.getSize();
        openCards.removeCard(cards[0]);
        Assertions.assertEquals(size - 1, openCards.getSize());
    }

    @Test
    void emptyOpenCardsTest() {
        openCards.emptyOpenCards();
        Assertions.assertEquals(0, openCards.getSize());
    }
}

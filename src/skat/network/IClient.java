package skat.network;

import skat.memory.Card;

import java.util.Queue;

public interface IClient {

    Queue<Byte> getUpdates();

    Card[] getHand();

    short getCurrentBid();

    void sendBidAnswer(boolean bidAnswer);

    Card[] getSkat();

    void sendSkat(Card[] skat);

    byte getTrumpId();

    void sendTrumpId(byte trumpId);

    byte getSpecialGame();

    void sendSpecialGame(byte specialGameId);

    String[] getOpenCards();

    Card[] getPlayedCards();

    void sendPlayedCard(Card playedCard);

    short[] getResults();

    void finishGame();
}

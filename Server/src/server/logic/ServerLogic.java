package server.logic;

import server.Server;
import skat.cards.Card;
import skat.compare.CardSort;

public class ServerLogic {

    private Server server;
    private byte speaking;
    private byte listening;
    private byte giving;
    private BidPartLogic bidLogic;
    private CardPartLogic cardLogic;
    private PointsLogic pointsLogic;
    private byte outPlayed;
    private byte singlePlayer;

    public ServerLogic(Server server) {
        this.server = server;
        giving = 2;
        listening = 0;
        speaking = 1;
        bidLogic = new BidPartLogic();
        cardLogic = new CardPartLogic();
        pointsLogic = new PointsLogic();
        singlePlayer = -1;
    }

    public void playCard(Card card, byte playerId) {
        if(cardLogic.isPlayable(card, playerId)) {
            cardLogic.playCard(card, playerId);
            server.getClient((byte) 0).sendPlayedCards(cardLogic.getPlayedCards());
            server.getClient((byte) 1).sendPlayedCards(cardLogic.getPlayedCards());
            server.getClient((byte) 2).sendPlayedCards(cardLogic.getPlayedCards());
            if(card.getColor() == pointsLogic.getGameId() || card.getValue() == 2)
                pointsLogic.addPeak(card);
            if(cardLogic.isPlayedCardsFull()) {
                calculatePoints();
                cardLogic.emptyPlayedCards();
                if(cardLogic.isComplete()) {
                    //TODO -> calculate results
                    return;
                }
                server.getClient(outPlayed).sendTurn();
                server.getClient((byte) 0).sendPlayedCards(cardLogic.getPlayedCards());
                server.getClient((byte) 1).sendPlayedCards(cardLogic.getPlayedCards());
                server.getClient((byte) 2).sendPlayedCards(cardLogic.getPlayedCards());
            }
            if(playerId == 2) {
                server.getClient((byte) 0).sendPlayedCards(cardLogic.getPlayedCards());
            } else {
                server.getClient((byte) (playerId+1)).sendPlayedCards(cardLogic.getPlayedCards());
            }
        } else
            server.getClient(playerId).sendRetrying(card, pointsLogic.getGameId());
    }

    private void rotateRound() {
        byte temp = giving;
        giving = listening;
        listening = speaking;
        speaking = temp;
    }

    public void startNextRound() {
        rotateRound();
        outPlayed = listening;
        cardLogic.shuffleCards();
        for(byte i = 0; i < 3; i++) {
            server.getClient(i).sendHand(cardLogic.getHand(i));
        }
        //TODO -> send speaking player first bid
    }

    private void calculatePoints() {
        Card[] currentPlayed = cardLogic.getPlayedCards();
        while(outPlayed > 0) {
            Card temp = currentPlayed[0];
            currentPlayed[0] = currentPlayed[2];
            currentPlayed[1] = currentPlayed[0];
            currentPlayed[2] = currentPlayed[1];
            outPlayed--;
        }
        CardSort sort = new CardSort();
        byte points = currentPlayed[0].getValue();
        for(byte i = 1; i < 3; i++) {
            if(sort.compareByGame(currentPlayed[outPlayed], currentPlayed[i], pointsLogic.getGameId()) == 1)
                outPlayed = i;
                points += currentPlayed[i].getValue();
        }
        if(outPlayed == singlePlayer) {
            if(pointsLogic.getGameId() == 23 || pointsLogic.getGameId() == 35 || pointsLogic.getGameId() == 46 || pointsLogic.getGameId() == 59)
                pointsLogic.giveUp(singlePlayer);
            pointsLogic.addSinglePoints(points);
        } else {
            pointsLogic.addDoublePoints(points);
        }
    }

    public void setSkat(Card[] skat) {
        pointsLogic.addSinglePoints(skat[0].getValue());
        pointsLogic.addSinglePoints(skat[1].getValue());
        cardLogic.setSkat(skat, singlePlayer);

    }

    public void setGameId(byte gameId) {
        pointsLogic.setGameId(gameId);
        cardLogic.setGameId(gameId);
        server.getClient((byte) 0).sendGame(gameId);
        server.getClient((byte) 1).sendGame(gameId);
        server.getClient((byte) 2).sendGame(gameId);
    }

    public void setPriceStage(byte priceStage) {
        pointsLogic.setPriceStage(priceStage);
        if(priceStage > 1) {
            server.getClient((byte) 0).sendPriceStage(priceStage);
            server.getClient((byte) 1).sendPriceStage(priceStage);
            server.getClient((byte) 2).sendPriceStage(priceStage);
        }
        server.getClient(outPlayed).sendTurn();
    }

    public void answerBid(boolean answer) {
        //TODO -> add bid functionality
    }

    public void openGameCards() {
        String[] hand = cardLogic.getHandGraphics(singlePlayer);
        for(byte i = 0; i < 3; i++) {
            if(i == singlePlayer) {
                continue;
            }
            server.getClient(i).sendOpenGameCards(hand);
        }
    }
}

package server.logic;

import server.Server;
import skat.cards.Card;
import skat.compare.CardSort;

public class ServerLogic {

    private Server server;
    private BidPartLogic bidLogic;
    private CardPartLogic cardLogic;
    private PointsLogic pointsLogic;
    private byte outPlayed;
    private byte singlePlayer;

    public ServerLogic(Server server) {
        this.server = server;
        bidLogic = new BidPartLogic(server, this);
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

    public void startNextRound() {
        bidLogic.resetBids();
        outPlayed = bidLogic.getListening();
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
            currentPlayed[2] = currentPlayed[1];
            currentPlayed[1] = temp;
            outPlayed--;
        }
        CardSort sort = new CardSort();
        byte points = currentPlayed[0].getValue();
        for(byte i = 1; i < 3; i++) {
            if(sort.compareByGame(currentPlayed[outPlayed], currentPlayed[i], pointsLogic.getGameId()) == 1) {
                outPlayed = i;
                points += currentPlayed[i].getValue();
            }
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

    public void answerBid(boolean answer, byte playerId) {
        if(!answer)
            bidLogic.pass(playerId);
        else if(bidLogic.isAccepted())
            bidLogic.nextBid();
        else
            bidLogic.acceptBid();
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

    protected void setSinglePlayer(byte playerId) {
        singlePlayer = playerId;
        pointsLogic.setBid(bidLogic.getCurrentBid());
        for(byte i = 0; i < 3; i++) {
            if(i == singlePlayer) {
                server.getClient(i).sendSkat(cardLogic.getSkat());
            } else {
                server.getClient(i).sendSinglePlayer(singlePlayer);
            }
        }
    }
}

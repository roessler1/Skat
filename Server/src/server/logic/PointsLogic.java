package server.logic;

import skat.cards.Card;

import java.util.ArrayList;

public class PointsLogic {

    private byte gameId;
    private byte priceStage;
    private byte singlePoints;
    private byte doublePoints;
    private short[] points;
    private ArrayList<Byte> peaks;

    protected PointsLogic() {
        gameId = 0;
        priceStage = 0;
        singlePoints = 0;
        doublePoints = 0;
        points = new short[3];
        peaks = new ArrayList<>();
    }

    protected void nextRound() {
        gameId = 0;
        priceStage = 1;
        singlePoints = 0;
        doublePoints = 0;
        peaks.clear();
    }

    protected void calculateResults(byte singlePlayer) {
        boolean isZeroGame = switch(gameId) {
            case 23, 35, 46, 59: yield true;
            default: yield false;
        };
        if(isZeroGame) {
            points[singlePlayer] += gameId;
        }
        if(singlePoints == 120 && !isZeroGame) {
            priceStage++;
        }
        if(singlePoints > 90 && !isZeroGame) {
            priceStage++;
        }
        priceStage += calculateStage();
        if(singlePoints > doublePoints) {
            points[singlePlayer] += (priceStage*gameId);
        } else {
            points[singlePlayer] += ((priceStage*gameId)*-2);
        }
    }

    protected short[] getPoints() {
        return points;
    }

    protected void addDoublePoints(byte points) {
        doublePoints += points;
    }

    protected void addSinglePoints(byte points) {
        singlePoints += points;
    }

    protected byte getGameId() {
        return gameId;
    }

    protected void setGameId(byte gameId) {
        this.gameId = gameId;
    }

    protected void setPriceStage(byte priceStage) {
        this.priceStage = priceStage;
    }

    protected byte calculateStage() {
        if(peaks.contains((byte) 1)) {
            byte max = 1;
            while(peaks.contains((byte) (max+1))) {
                max++;
            }
            return max;
        } else {
            byte min = 5;
            for(byte peak:peaks) {
                if(min > peak)
                    min = peak;
            }
            min--;
            return min;
        }
    }

    protected void giveUp(byte singlePlayer) {
        points[singlePlayer] += (gameId*-2);
    }

    protected void addPeak(Card card) {
        byte value = card.getValue();
        byte color = card.getColor();
        if(value == 2) {
            switch(color) {
                case 12 -> peaks.add((byte) 1);
                case 11 -> peaks.add((byte) 2);
                case 10 -> peaks.add((byte) 3);
                case 9 -> peaks.add((byte) 4);
            }
        } else {
            if(value == 0) {
                switch(card.getId()%8) {
                    case 7 -> peaks.add((byte) 11);
                    case 6 -> peaks.add((byte) 10);
                    case 5 -> peaks.add((byte) 9);
                }
            } else {
                switch(value) {
                    case 11 -> peaks.add((byte) 5);
                    case 10 -> peaks.add((byte) 6);
                    case 4 -> peaks.add((byte) 7);
                    case 3 -> peaks.add((byte) 8);
                }
            }
        }
    }
}

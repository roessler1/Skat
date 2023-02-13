package skat.logic.server;

import skat.memory.server.Bid;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ServerBidding {

    private Queue<Bid> bids;
    private short currentBid;
    private boolean bidAccepted;
    private LinkedList<Byte> biddingPlayers;

    public ServerBidding() {
        bids = new LinkedList<>(Arrays.asList(Bid.values()));
        currentBid = bids.remove().getValue();
        biddingPlayers = new LinkedList<>(Arrays.asList((byte) 0, (byte) 1, (byte) 2));
    }

    public void passBidding(byte playerId) {
        biddingPlayers.remove(Byte.valueOf(playerId));
    }

    public void acceptBid() {
        bidAccepted = true;
    }

    public boolean isBidAccepted() {
        return bidAccepted;
    }

    public void nextBid() {
        bidAccepted = false;
        currentBid = bids.remove().getValue();
    }

    public short getBid() {
        return currentBid;
    }

    public int numberOfBidingPlayers() {
        return biddingPlayers.size();
    }

    public boolean isBidding(byte playerId) {
        return biddingPlayers.contains(playerId);
    }
}

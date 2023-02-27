package server.logic;

import server.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BidPartLogic {

    private Queue<Bid> bids;
    private short currentBid;
    private ArrayList<Byte> biddingPlayers;
    private byte listening;
    private byte speaking;
    private byte giving;
    private Server server;
    private boolean bidAccepted;
    private ServerLogic logic;

    public BidPartLogic(Server server, ServerLogic logic) {
        this.logic = logic;
        bids = new LinkedList<>();
        biddingPlayers = new ArrayList<>();
        giving = 2;
        listening = 0;
        speaking = 1;
        bidAccepted = false;
        this.server = server;
    }

    protected void resetBids() {
        bids.clear();
        bids.addAll(Arrays.asList(Bid.values()));
        biddingPlayers.clear();
        biddingPlayers.add((byte) 0);
        biddingPlayers.add((byte) 1);
        biddingPlayers.add((byte) 2);
        bidAccepted = false;
        rotateRound();
    }

    protected byte getListening() {
        return listening;
    }

    protected short getCurrentBid() {
        return currentBid;
    }

    private void rotateRound() {
        byte temp = giving;
        giving = listening;
        listening = speaking;
        speaking = temp;
    }

    protected void pass(byte playerId) {
        biddingPlayers.remove(Byte.valueOf(playerId));
        if(biddingPlayers.size() == 1) {
            if(currentBid == 18)
                server.getClient(listening).sendBid(currentBid);
            else
                logic.setSinglePlayer(biddingPlayers.get(0));
        } else {
            server.getClient(giving).sendBid(currentBid);
        }
    }

    protected void nextBid() {
        currentBid = bids.remove().getBid();
        bidAccepted = false;
        if(biddingPlayers.contains(speaking)) {
            server.getClient(speaking).sendBid(currentBid);
        } else {
            server.getClient(giving).sendBid(currentBid);
        }
    }

    protected void acceptBid() {
        bidAccepted = true;
        if(biddingPlayers.size() == 1) {
            logic.setSinglePlayer(biddingPlayers.get(0));
            return;
        }
        if(biddingPlayers.contains(listening)) {
            server.getClient(listening).sendBid(currentBid);
        } else {
            server.getClient(giving).sendBid(currentBid);
        }
    }

    protected boolean isAccepted() {
        return bidAccepted;
    }
}

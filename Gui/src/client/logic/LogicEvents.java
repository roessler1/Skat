package client.logic;

import client.gui.pane_controller.GuiController;
import server.Server;
import skat.log.Log;
import client.logic.logic.CardLogic;
import client.logic.network.ClientIncoming;
import client.logic.network.ClientOutgoing;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;

public class LogicEvents {

    static LogicEvents logicEvents;
    private Socket socket;
    private boolean errorOccurred;
    private ClientIncoming incoming;
    private ClientOutgoing outgoing;
    private CardLogic cardLogic;
    private boolean turn;
    private short bid;
    private Server server;

    private LogicEvents() {
        logicEvents = this;
        cardLogic = new CardLogic();
        socket = new Socket();
        try {
            socket.setReuseAddress(true);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static LogicEvents getInstance() {
        if(logicEvents == null) {
            new LogicEvents();
        }
        return logicEvents;
    }

    public void sendOpenGameCards() {
        outgoing.sendOpenGameCards();
    }

    public void sendBidAnswer(boolean answer) {
        outgoing.sendBidAnswer(answer);
    }

    public void sendPriceStage(byte priceStage) {
        outgoing.sendPriceStage(priceStage);
    }

    public void sendGameId(byte gameId) {
        outgoing.sendGameId(gameId);
    }

    public void insertSkat() {
        cardLogic.insertSkat();
    }

    public void sendSkat() {
        if(cardLogic.getHandSize() == 10) {
            outgoing.sendSkat(cardLogic.getSkat());
        }
    }

    public void playCard(String cardUrl) {
        if(cardLogic.getHandSize() > 10) {
            cardLogic.putToSkat(cardUrl);
        }
        if(!turn)
            return;
        outgoing.playCard(cardLogic.getCardByUrl(cardUrl));
        turn = false;
    }

    public void closeConnection() {
        if(!incoming.isUp()) {
            incoming.closeInput();
        } else {
            outgoing.closeOutput();
            if(server != null) {
                server.closeServer();
                server = null;
            }
        }

    }

    public void buildConnection(String address) {
        try {
            socket.connect(new InetSocketAddress(address, 18081));
            outgoing = new ClientOutgoing(socket.getOutputStream());
            incoming = new ClientIncoming(socket.getInputStream(), cardLogic);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            setErrorOccurred();
        }
        if(errorOccurred) {
            return;
        }
        GuiController.getInstance().loadWaiting();
        GuiController.getInstance().loadCardPane();
    }

    public void startServer() {
        server = new Server();
        server.start();
        try {
            buildConnection(InetAddress.getLocalHost().getHostAddress());
        } catch(UnknownHostException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setErrorOccurred() {
        errorOccurred = true;
    }

    public void setTurn() {
        turn = true;
    }

    public void setBid(short bid) {
        this.bid = bid;
    }

    public short getBid() {
        return bid;
    }

    public void playNextRound() {
        outgoing.playNextRound();
    }

    public static void deleteInstance() {
        logicEvents.closeConnection();
        logicEvents = null;
    }

    public boolean isBiddingOver() {
        return cardLogic.hasSkat();
    }
}

package skat;

import skat.log.Log;
import skat.logic.CardLogic;
import skat.network.ClientIncoming;
import skat.network.ClientOutgoing;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;

public class LogicEvents {

    static LogicEvents logicEvents;
    private Socket socket;
    private boolean errorOccurred;
    private ClientIncoming incoming;
    private ClientOutgoing outgoing;
    private CardLogic cardLogic;
    private boolean turn;

    private LogicEvents() {
        logicEvents = this;
        cardLogic = new CardLogic();
    }

    public static LogicEvents getInstance() {
        if(logicEvents == null) {
            new LogicEvents();
        }
        return logicEvents;
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
            try {
                socket.close();
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        }

    }

    public void buildConnection(String address) {
        try {
            socket = new Socket(address, 18081);
            outgoing = new ClientOutgoing(socket.getOutputStream());
            incoming = new ClientIncoming(socket.getInputStream(), cardLogic);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            setErrorOccurred();
        }
        if(errorOccurred) {
            return;
        }
        //TODO -> close selection panel
        //TODO -> open card panel
    }

    public void setErrorOccurred() {
        errorOccurred = true;
    }

    public void setTurn() {
        turn = true;
    }
}

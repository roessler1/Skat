package client.gui.pane_events;

import client.logic.LogicEvents;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationEvents implements Initializable {

    @FXML
    Label singlePlayer;
    @FXML
    Label gameType;
    @FXML
    Label winTier;
    @FXML
    Label isTurn;

    public void setSoloPlayerColumn(byte soloPlayer) {
        Platform.runLater(() -> {
            byte playerId = LogicEvents.getInstance().getPlayerId();
            if(playerId == soloPlayer) {
                singlePlayer.setText("Du");
            } else if(playerId + 1 == soloPlayer || (playerId == 2 && soloPlayer == 0)) {
                singlePlayer.setText("Links");
            } else singlePlayer.setText("Rechts");
        });
    }

    public void setGameTypeColumn(byte gameId) {
        Platform.runLater(() -> {
            switch(gameId) {
                case 23, 35, 46, 59 -> gameType.setText("Null");
                case 12 -> gameType.setText("Eichel");
                case 11 -> gameType.setText("Blatt");
                case 10 -> gameType.setText("Herz");
                case 9 -> gameType.setText("Schellen");
                case 24 -> gameType.setText("Grand");
            }
        });
    }

    public void setWinTierColumn(byte profitLevel) {
        Platform.runLater(() -> {
            switch(profitLevel) {
                case 1 -> winTier.setText("Normal");
                case 2 -> winTier.setText("Handspiel");
                case 3 -> winTier.setText("Schneider");
                case 4 -> winTier.setText("Schwarz");
                case 5 -> winTier.setText("Ouvert");
            }
            setIsTurnColumn(false);
        });
    }

    public void setIsTurnColumn(boolean turn) {
        Platform.runLater(() -> {
            if(turn)
                isTurn.setText("Ja");
            else
                isTurn.setText("Nein");
        });
    }

    public void emptyInformation() {
        singlePlayer.setText("");
        gameType.setText("");
        winTier.setText("");
        isTurn.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LogicEvents.getInstance().setInformation(this);
    }
}

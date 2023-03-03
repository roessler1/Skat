package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsEvents implements Initializable {

    @FXML
    Text player1;
    @FXML
    Label player1Points;
    @FXML
    Text player2;
    @FXML
    Label player2Points;
    @FXML
    Text player3;
    @FXML
    Label player3Points;

    @FXML
    protected void onNextRoundButtonClicked() {
        LogicEvents.getInstance().playNextRound();
        GuiController.getInstance().loadWaiting();
    }

    @FXML
    protected void onBackButtonClicked() {
        LogicEvents.getInstance().closeConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        byte playerId = LogicEvents.getInstance().getPlayerId();
        if(playerId == 0) {
            player1.setText("Du\t\t\t\t\t");
            player2.setText("Links\t\t\t\t");
            player3.setText("Rechts\t\t\t\t");
        }
        if(playerId == 1) {
            player1.setText("Rechts\t\t\t\t");
            player2.setText("Du\t\t\t\t\t");
            player3.setText("Links\t\t\t\t");
        }
        if(playerId == 2) {
            player1.setText("Links\t\t\t\t");
            player2.setText("Rechts\t\t\t\t");
            player3.setText("Du\t\t\t\t\t");
        }
        short[] playerPoints = LogicEvents.getInstance().getPlayerPoints();
        player1Points.setText(String.valueOf(playerPoints[0]));
        player2Points.setText(String.valueOf(playerPoints[1]));
        player3Points.setText(String.valueOf(playerPoints[2]));
    }
}

package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsEvents implements Initializable {

    @FXML
    Text player1;
    @FXML
    Text player1Points;
    @FXML
    Text player2;
    @FXML
    Text player2Points;
    @FXML
    Text player3;
    @FXML
    Text player3Points;

    @FXML
    protected void onNextRoundButtonClicked() {
        LogicEvents.getInstance().playNextRound();
        GuiController.getInstance().loadWaiting();
    }

    @FXML
    protected void onBackButtonClicked() {
        LogicEvents.deleteInstance();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        byte playerId = LogicEvents.getInstance().getPlayerId();
        if(playerId == 0) {
            player1.setText("Du\t\t");
            player2.setText("Links\t\t");
            player3.setText("Rechts\t\t");
        }
        if(playerId == 1) {
            player1.setText("Rechts\t\t");
            player2.setText("Du\t\t");
            player3.setText("Links\t\t");
        }
        if(playerId == 2) {
            player1.setText("Links\t\t");
            player2.setText("Rechts\t\t");
            player3.setText("Du\t\t");
        }
        short[] playerPoints = LogicEvents.getInstance().getPlayerPoints();
        String points1 = String.valueOf(playerPoints[0]);
        String points2 = String.valueOf(playerPoints[1]);
        String points3 = String.valueOf(playerPoints[2]);
        player1Points.setText(points1);
        player1Points.setText(points2);
        player1Points.setText(points3);
    }
}

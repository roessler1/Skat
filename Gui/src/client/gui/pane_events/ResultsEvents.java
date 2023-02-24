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
        // TODO -> Spieler Namen
    }
}

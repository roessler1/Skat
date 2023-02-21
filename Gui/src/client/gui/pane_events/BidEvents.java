package client.gui.pane_events;

import client.logic.LogicEvents;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class BidEvents implements Initializable {

    @FXML
    private Button bid;

    @FXML
    protected void onBidButtonClicked() {
        LogicEvents.getInstance().sendBidAnswer(true);
    }

    @FXML
    protected void onPassButtonClicked() {
        LogicEvents.getInstance().sendBidAnswer(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        bid.setText(String.valueOf(LogicEvents.getInstance().getBid()));
    }
}

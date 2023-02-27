package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
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
    private Button pass;

    @FXML
    protected void onBidButtonClicked() {
        LogicEvents.getInstance().sendBidAnswer(true);
        GuiController.getInstance().unloadCenterPane();
    }

    @FXML
    protected void onPassButtonClicked() {
        LogicEvents.getInstance().sendBidAnswer(false);
        GuiController.getInstance().unloadCenterPane();
    }

    @FXML
    protected void onPickupButtonClicked() {
        LogicEvents.getInstance().insertSkat();
    }

    @FXML
    protected  void onHandgameButtonClicked() {
        LogicEvents.getInstance().sendSkat();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LogicEvents.getInstance().isBiddingOver()) {
            bid.setOnMouseClicked(e -> onHandgameButtonClicked());
            pass.setOnMouseClicked(e -> onPickupButtonClicked());
            bid.setText("Handspiel");
            pass.setText("Skat aufnehmen");
            bid.setPrefWidth(110);
            pass.setPrefWidth(110);
        } else {
            bid.setOnMouseClicked(e -> onBidButtonClicked());
            pass.setOnMouseClicked(e -> onPassButtonClicked());
            bid.setText(String.valueOf(LogicEvents.getInstance().getBid()));
            pass.setText("Passen");
        }
    }
}

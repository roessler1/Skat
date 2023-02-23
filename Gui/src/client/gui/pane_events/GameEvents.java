package client.gui.pane_events;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class GameEvents implements Initializable {

    @FXML
    ToggleGroup gameId;
    @FXML
    RadioButton normal;
    @FXML
    RadioButton three;
    @FXML
    RadioButton four;
    @FXML
    RadioButton five;
    @FXML
    protected void onConfirmButtonClicked() {
        RadioButton selectedRadioButton = (RadioButton) gameId.getSelectedToggle();
        byte game = Byte.parseByte(selectedRadioButton.getId());
        // TODO -> Confirm Button
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(0 == 0/* TODO -> Handspiel? */) {
            normal.setText("Handspiel");
        } else {
            normal.setText("Normales Spiel");
            three.setDisable(true);
            four.setDisable(true);
            five.setDisable(true);
        }
    }
}

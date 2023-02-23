package client.gui.pane_events;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class GameEvents {

    @FXML
    ToggleGroup gameId;

    @FXML
    protected void onConfirmButtonClicked() {
        RadioButton selectedRadioButton = (RadioButton) gameId.getSelectedToggle();
        byte game = Byte.parseByte(selectedRadioButton.getId());
        // TODO -> Confirm Button
    }
}

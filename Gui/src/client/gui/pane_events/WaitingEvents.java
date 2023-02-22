package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;

public class WaitingEvents {

    @FXML
    protected void onBackButtonClicked() {
        LogicEvents.deleteInstance();
        GuiController.getInstance().loadMainMenu();
    }
}

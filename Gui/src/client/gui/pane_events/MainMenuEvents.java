package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;

public class MainMenuEvents {

    @FXML
    protected void onHostButtonClicked() {
        LogicEvents.getInstance().startServer();
    }

    @FXML
    protected void onJoinButtonClicked() {
        GuiController.getInstance().loadServerSelection();
    }

    @FXML
    protected void onExitButtonClicked() {
        System.exit(0);
    }
}

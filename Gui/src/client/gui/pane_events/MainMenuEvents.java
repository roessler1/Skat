package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.application.Platform;
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
        Platform.exit();
        System.exit(0);
    }
}

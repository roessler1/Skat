package gui.pane_events;

import javafx.fxml.FXML;

public class MainMenuEvents {

    @FXML
    protected void onHostButtonClicked() {
        //TODO -> start server
    }

    @FXML
    protected void onJoinButtonClicked() {
        //TODO -> change to server selection pane
    }

    @FXML
    protected void onExitButtonClicked() {
        System.exit(0);
    }
}

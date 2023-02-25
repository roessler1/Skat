package client.gui.pane_events;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class InformationEvents implements Initializable {
    @FXML
    TableColumn soloPlayer;
    @FXML
    TableColumn gameType;
    @FXML
    TableColumn winTier;

    protected void setSoloPlayerColumn() {
        // TODO -> Wert für Column hinzufügen(information-view)
    }

    protected void setGameTypeColumn() {
        // TODO -> Wert für Column hinzufügen(information-view)
    }

    protected void setWinTierColumn() {
        // TODO -> Wert für Column hinzufügen(information-view)
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSoloPlayerColumn();
        setGameTypeColumn();
        setWinTierColumn();
    }
}

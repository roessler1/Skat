package client.gui.pane_events;

import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    ToggleGroup priceStaging;
    boolean isHand;
    @FXML
    RadioButton twentythree;

    @FXML
    protected void onConfirmButtonClicked() {
        RadioButton selectedGameButton = (RadioButton) gameId.getSelectedToggle();
        byte game;
        if(selectedGameButton.getId().equals(twentythree.getId()))
            game = 23;
        else
            game = Byte.parseByte(selectedGameButton.getId());
        RadioButton selectedStageButton = (RadioButton) priceStaging.getSelectedToggle();
        byte profitLevel = switch(selectedStageButton.getId()) {
            case "five" -> 5;
            case "four" -> 4;
            case "three" -> 3;
            case "two" -> 2;
            case "one" -> 1;
            default -> 0;
        };
        if(game == 23) {
            if(profitLevel == 5 && isHand)
                game = 59;
            else if(profitLevel == 5)
                game = 46;
            else if(isHand)
                game = 35;
        }
        LogicEvents.getInstance().sendGameId(game);
        LogicEvents.getInstance().sendPriceStage(profitLevel);
        if(profitLevel == 5)
            LogicEvents.getInstance().sendOpenGameCards();
        GuiController.getInstance().unloadCenterPane();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LogicEvents.getInstance().isHandGame()) {
            normal.setText("Handspiel");
            normal.setId("two");
            twentythree.selectedProperty().addListener((observableValue, wasSelected, isSelected) -> {
                if(isSelected) {
                    three.setDisable(true);
                    four.setDisable(true);
                    normal.setSelected(true);
                } else {
                    three.setDisable(false);
                    four.setDisable(false);
                }
            });

        } else {
            normal.setText("Normales Spiel");
            three.setDisable(true);
            four.setDisable(true);
            five.setDisable(true);
            normal.setId("one");
            twentythree.selectedProperty().addListener((observableValue, wasSelected, isSelected) -> {
                if(isSelected)
                    five.setDisable(false);
                else {
                    five.setDisable(true);
                    normal.setSelected(true);
                }
            });
        }
    }
}

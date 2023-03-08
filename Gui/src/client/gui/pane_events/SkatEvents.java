package client.gui.pane_events;

import client.gui.Gui;
import client.gui.pane_controller.GuiController;
import client.logic.LogicEvents;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import skat.log.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class SkatEvents implements Initializable {

    @FXML
    Label card1;
    @FXML
    Label card2;
    private double cardWidth;
    private double cardHeight;

    @FXML
    protected void onReselectButtonClicked() {
        LogicEvents.getInstance().insertSkat();
        card1.setGraphic(null);
        card2.setGraphic(null);
    }

    @FXML
    protected void onPushSkatButtonClicked() {
        LogicEvents.getInstance().sendSkat();
    }

    public void pushCard(String url) {
        ImageView view = null;
        try {
            Image img = new Image(Gui.class.getResource(url).openStream(), cardWidth, cardHeight, true, false);
            view = new ImageView(img);
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }

        if (card1.getGraphic() == null) {
            card1.setGraphic(view);
        } else if (card2.getGraphic() == null){
            card2.setGraphic(view);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LogicEvents.getInstance().setSkatEvents(this);
        cardWidth = GuiController.getInstance().getWidth();
        cardHeight = GuiController.getInstance().getHeight();
        card1.setPrefWidth(cardWidth);
        card1.setPrefHeight(cardHeight);
        card2.setPrefWidth(cardWidth);
        card2.setPrefHeight(cardHeight);
    }
}

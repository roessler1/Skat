package client.gui.panes;

import client.gui.Gui;
import client.gui.pane_controller.GuiController;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class OpenCardsPane extends VBox {

    ArrayList<ImageView> labels;

    public OpenCardsPane(String[] cardsUrls, double screenWidth, double screenHeight) {
        labels = new ArrayList<>();
        double width = GuiController.getInstance().getWidth() *0.65;
        double height = GuiController.getInstance().getHeight()*0.65;
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setStyle("-fx-background-color: #349746");
        this.setSpacing(-(height/(5/3.0)));
        Label label;
        for(String url:cardsUrls) {
            ImageView view = new ImageView(new Image(Gui.class.getResourceAsStream(url), width, height, true, false));
            labels.add(view);
            label = new Label();
            label.setGraphic(view);
            this.getChildren().add(label);
        }
    }

    public void removeCard(String[] playedCards) {
        byte pos = 0;
        while(pos < 3 && playedCards[pos] != null) {
            pos++;
        }
        pos--;
        for(ImageView label:labels) {
            if(label.getImage().getUrl().equals(playedCards[pos])) {
                this.getChildren().remove(pos);
            }
        }
    }
}

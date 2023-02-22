package client.gui.panes;

import client.logic.LogicEvents;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.ArrayList;

public class CardPane extends HBox {

    ArrayList<Label> labels = new ArrayList<>();
    double width;
    double height;

    public CardPane(double screenWidth, double screenHeight) {
        width = 190*(screenWidth*100/1920)/100;
        height = (294*(screenHeight*100/1080)/100)/2;
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #349746");
        this.setSpacing(-(width/2));
        this.setWidth(screenWidth);
        this.setHeight(height);
    }

    public void addCards(String[] cardsUrls) {
        Label label = new Label();
        for(String card:cardsUrls) {
            label.setAlignment(Pos.TOP_LEFT);
            ImageView view = new ImageView(new Image(card, width, height, true, false));
            label.setGraphic(view);
            label.setOnMouseClicked(e -> {
                LogicEvents.getInstance().playCard(card);
                labels.remove(label);
            });
            labels.add(label);
            this.getChildren().add(label);
        }
    }
}

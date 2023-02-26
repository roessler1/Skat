package client.gui.panes;

import client.gui.Gui;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import skat.log.Log;

import java.io.IOException;
import java.util.logging.Level;

public class PlayedCardPane extends StackPane {

    Label[] labels;
    double width;
    double height;

    public PlayedCardPane(double screenWidth, double screenHeight) {
        width = (190*(screenWidth*100/1920)/100)*0.75;
        height = (294*(screenHeight*100/1080)/100)*0.75;
        this.setStyle("-fx-background-color: #349746");
        labels = new Label[3];
        for(byte i = 0; i < labels.length; i++) {
            labels[i] = new Label();
            this.getChildren().add(labels[i]);
        }
        labels[0].setPadding(new Insets(0,0,screenHeight*0.1,screenHeight*0.05));
        labels[1].setPadding(new Insets(0,0,screenWidth*0.07,0));
        labels[2].setPadding(new Insets(0,screenHeight*0.05,screenHeight*0.1,0));
    }

    public void addPlayedCards(String[] cards) {
        for(byte i = 0; i < cards.length; i++) {
            ImageView view = null;
            try {
                Image img = new Image(Gui.class.getResource(cards[i]).openStream(), width, height , true, true);
                view = new ImageView(img);
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
            labels[i].setGraphic(view);
        }
        labels[0].setStyle("-fx-rotate: -45");
        labels[2].setStyle("-fx-rotate: 45");
    }
}

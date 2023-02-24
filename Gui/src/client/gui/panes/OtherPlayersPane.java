package client.gui.panes;

import client.gui.Gui;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import skat.log.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class OtherPlayersPane extends VBox {

    ArrayList<ImageView> labels = new ArrayList<>();
    double width;
    double height;
    boolean open;
    boolean left;

    public OtherPlayersPane(double screenWidth, double screenHeight, boolean left) {
        width = (190*(screenWidth*100/1920)/100)*0.65;
        height = (294*(screenHeight*100/1080)/100)*0.65;
        this.setSpacing(-(width/2));
        this.setStyle("-fx-background-color: #349746");
        this.setAlignment(Pos.CENTER);
    }

    public void addCards(String[] cardsUrls) {
        labels.clear();
        this.getChildren().clear();
        open = true;
        Label label = new Label();
        for(String card:cardsUrls) {
            ImageView view = null;
            try {
                view = new ImageView(new Image(Gui.class.getResource(card).openStream(), width, height, true, false));
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
            label.setGraphic(view);
            if(left) {
                label.setAlignment(Pos.TOP_RIGHT);
                label.setStyle("-fx-rotate: -90");
            } else {
                label.setAlignment(Pos.TOP_LEFT);
                label.setStyle("-fx-rotate: 90");
            }
            labels.add(view);
            this.getChildren().add(label);
        }
    }

    public void resetCards() {
        labels.clear();
        this.getChildren().clear();
        open = false;
        Label label = new Label();
        for(byte i = 0; i < 10; i++) {
            ImageView view = null;
            try {
                view = new ImageView(new Image(Gui.class.getResource("/assets/texture/card_back.png").openStream(), width, height, false, false));
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
            label.setGraphic(view);
            if(left) {
                label.setAlignment(Pos.TOP_RIGHT);
                label.setStyle("-fx-rotate: -90");
            } else {
                label.setAlignment(Pos.TOP_LEFT);
                label.setStyle("-fx-rotate: 90");
            }
            labels.add(view);
            this.getChildren().add(label);
        }
    }

    public void removeCard(String[] playedCards) {
        if(!open) {
            this.getChildren().remove(0);
            return;
        }
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

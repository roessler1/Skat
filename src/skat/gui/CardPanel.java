package skat.gui;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private int width;
    private int height;

    public CardPanel(int screenWidth, int screenHeight) {
        width = (((190 * 100) / 1920) * screenWidth) / 100;
        height = ((((294 * 100) / 1080) * screenHeight) / 100) / 2;

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, (0 - (width / 2)), 0);
        this.setLayout(flowLayout);
    }

    public void addCards(String[] images) {
        for(String image : images) {
            this.add(new CardLabel(image, width, height));
        }
    }

    public void deleteCard(CardLabel cardLabel) {
        this.remove(cardLabel);
    }

    public void emptyHand() {
        this.removeAll();
    }
}

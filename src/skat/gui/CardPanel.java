package skat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CardPanel extends JPanel {
    public CardPanel(int screenWidth, int screenHeight, String[] images) {
        int width = (((190 * 100) / 1920) * screenWidth) / 100;
        int height = ((((294 * 100) / 1080) * screenHeight) / 100) / 2;

        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, (0-(width /2)), 0);
        this.setLayout(flowLayout);
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

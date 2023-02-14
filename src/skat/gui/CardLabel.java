package skat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardLabel extends JLabel {
    public CardLabel(String img, int width, int height) {
        Image image = new ImageIcon(getClass().getClassLoader().getResource(img)).getImage();
        image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(image));
        this.setVerticalAlignment(SwingConstants.NORTH);
        this.setPreferredSize(new Dimension(width, height));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO -> Funktionalität von Kartenauswahl
            }
        });
    }
}

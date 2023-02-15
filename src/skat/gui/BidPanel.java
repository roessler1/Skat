package skat.gui;

import javax.swing.*;
import java.awt.*;

public class BidPanel extends JPanel {
    private JButton[] buttons = new JButton[2];

    public BidPanel(short value) {
        buttons[0] = new JButton(String.valueOf(value));
        buttons[1] = new JButton("Passen");
        createPanelDesign();
        activateBidButtons();
    }

    public BidPanel() {
        buttons[0] = new JButton("Skat aufheben");
        buttons[1] = new JButton("Handspiel");
        createPanelDesign();
        activateSkatButtons();
    }

    private void createPanelDesign() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setOpaque(false);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 25, 0, 0);
        for (byte i = 0; i < buttons.length; i++) {
            gridBagConstraints.gridx = i;
            buttons[i].setPreferredSize(new Dimension(100, 40));
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttons[i].setFont(buttons[i].getFont().deriveFont(1, 14.0f));
            buttons[i].setForeground(Color.black);
            buttons[i].setBackground(Color.white);
            buttons[i].setBorder(BorderFactory.createCompoundBorder());
            this.add(buttons[i]);
        }
    }

    private void activateBidButtons() {
        // TODO -> Buttons aktivieren
    }

    private void activateSkatButtons() {
        // TODO -> Buttons aktivieren
    }
}

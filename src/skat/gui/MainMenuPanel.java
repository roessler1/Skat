package skat.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuPanel extends JPanel {
    private JButton[] buttons = new JButton[3];

    public MainMenuPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setOpaque(false);

        buttons[0] = new JButton("Beitreten");
        buttons[1] = new JButton("Hosten");
        buttons[2] = new JButton("Spiel verlassen");

        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.insets = new Insets(25, 0, 0, 0);
        for (byte i = 0; i < buttons.length; i++) {
            gridBagConstraints.gridy = i;
            buttons[i].setPreferredSize(new Dimension(100, 40));
            buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttons[i].setFont(buttons[i].getFont().deriveFont(1, 14.0f));
            buttons[i].setForeground(Color.black);
            buttons[i].setBackground(Color.white);
            buttons[i].setBorder(BorderFactory.createCompoundBorder());
            this.add(buttons[i]);
        }

        activateButtons();
    }

    private void activateButtons() {
        // TODO -> Buttons aktivieren
    }
}

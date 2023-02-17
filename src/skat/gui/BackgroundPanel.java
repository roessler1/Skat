package skat.gui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {

    private JPanel centerPanel;
    private JPanel southPanel;
    private Image backgroundImage;

    protected BackgroundPanel(int width, int height) {
        super(new BorderLayout());
        backgroundImage = new ImageIcon(getClass().getClassLoader().getResource("assets/texture/backgroundImage.jpg")).getImage();
        backgroundImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);

        validate();
        repaint();
    }

    protected void loadIntoSouthPanel(JPanel panel) {
        southPanel = panel;
        this.add(southPanel, BorderLayout.SOUTH);
    }

    protected void unloadSouthPanel() {
        this.remove(southPanel);
    }

    protected void loadIntoCenterPanel(JPanel panel) {
        centerPanel = panel;
        this.add(centerPanel, BorderLayout.CENTER);
    }

    protected void unloadCenterPanel() {
        this.remove(centerPanel);
    }

    protected void render() {
        centerPanel.repaint();
        southPanel.repaint();
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        backgroundImage.getGraphics().drawImage(backgroundImage, 0, 0, this);
    }
}

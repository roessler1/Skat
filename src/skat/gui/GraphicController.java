package skat.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GraphicController {

    static GraphicController gui;
    private JFrame frame;
    Timer render = new Timer(40, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            render();
        }
    });
    private CardPanel cardPanel;

    private GraphicController() {
        gui = this;
        frame = new JFrame();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        loadMainMenu();
        render.start();
    }

    public static GraphicController getInstance() {
        if(gui == null) {
            new GraphicController();
        }
        return gui;
    }

    private void render() {
        //TODO -> repaint every panel
        frame.repaint();
    }

    public void addPlayedCardsGraphic(String[] cardUrls) {
        //TODO -> updating played cards in played cards panel
    }

    public void addHandGraphic(String[] cardUrls) {
        if(cardPanel == null) {
            cardPanel = new CardPanel(frame.getWidth(), frame.getHeight());
            //TODO -> adding card panel to background panel
        }
        cardPanel.addCards(cardUrls);
    }

    public void loadSkatPanel() {
        //TODO -> creating skat panel
    }

    public void loadMainMenu() {
        //TODO -> creating main menu panel
    }
}

package client.gui.pane_controller;

import client.gui.Gui;
import client.gui.panes.CardPane;
import client.gui.panes.ServerSelectionPane;
import javafx.fxml.FXMLLoader;
import skat.log.Log;

import java.io.IOException;
import java.util.logging.Level;

public class GuiController {

    private static GuiController guiController;
    private Gui gui;
    private CardPane cardPane;

    private GuiController(Gui gui) {
        guiController = this;
        this.gui = gui;
    }

    public void loadMainMenu() {
        gui.getBorderPane().setBottom(null);
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/game-view.fxml"));
        try {
            gui.getBorderPane().setCenter(fxmlLoader.load());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void loadCardPane() {
        cardPane = new CardPane(gui.getBorderPane().getWidth(), gui.getBorderPane().getHeight());
        gui.getBorderPane().setBottom(cardPane);
    }

    public void addCards(String[] cardsUrls) {
        cardPane.addCards(cardsUrls);
    }

    public void loadServerSelection() {
        gui.getBorderPane().setCenter(new ServerSelectionPane(gui.getBorderPane().getWidth(),
                gui.getBorderPane().getHeight()));
    }

    public void unloadCenterPane() {
        gui.getBorderPane().setCenter(null);
    }

    public void loadBidPanel() {
        FXMLLoader loader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/bid-view.fxml"));
        try {
            gui.getBorderPane().setCenter(loader.load());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void loadWaiting() {
        FXMLLoader loader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/waiting-view.fxml"));
        try {
            gui.getBorderPane().setCenter(loader.load());
        } catch(IOException e) {
            Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public static void loadGuiController(Gui gui) {
        if(guiController == null)
            new GuiController(gui);
    }

    public static GuiController getInstance() {
        return guiController;
    }
}

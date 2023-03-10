package client.gui.pane_controller;

import client.gui.Gui;
import client.gui.panes.CardPane;
import client.gui.panes.OpenCardsPane;
import client.gui.panes.PlayedCardPane;
import client.gui.panes.ServerSelectionPane;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import skat.log.Log;

import java.io.IOException;
import java.util.logging.Level;

public class GuiController {

    private static GuiController guiController;
    private final Gui gui;
    private CardPane cardPane;
    private PlayedCardPane playedCardPane;
    private OpenCardsPane openCards;
    private final double cardWidth;
    private final double cardHeight;

    private GuiController(Gui gui) {
        guiController = this;
        this.gui = gui;
        cardWidth = Screen.getPrimary().getBounds().getWidth()*0.6/13*2;
        cardHeight = cardWidth*(294.0/190.0);
    }

    public void loadMainMenu() {
        Platform.runLater(() -> {
            gui.getBorderPane().setBottom(null);
            gui.getBorderPane().setRight(null);
            gui.getBorderPane().setTop(null);
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/main-menu-view.fxml"));
            try {
                gui.getBorderPane().setCenter(fxmlLoader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadInformation() {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/information-view.fxml"));
            try {
                gui.getBorderPane().setTop(fxmlLoader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadPlayedCards() {
        Platform.runLater(() -> {
            playedCardPane = new PlayedCardPane(gui.getBorderPane().getWidth(), gui.getBorderPane().getHeight());
            gui.getBorderPane().setCenter(playedCardPane);
        });
    }

    public void addPlayedCard(String[] cards) {
        Platform.runLater(() -> {
            playedCardPane.addPlayedCards(cards);
            removeOpenCard(cards);
        });
    }

    public void loadGameSelection() {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/game-view.fxml"));
            try {
                gui.getBorderPane().setCenter(fxmlLoader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadResults() {
        Platform.runLater(() -> {
            if(openCards != null) {
                gui.getBorderPane().setRight(null);
                openCards = null;
            }
            cardPane.emptyHand();
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/results-view.fxml"));
            try {
                gui.getBorderPane().setCenter(fxmlLoader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadCardPane() {
        Platform.runLater(() -> {
            cardPane = new CardPane(gui.getBorderPane().getWidth(), gui.getBorderPane().getHeight());
            gui.getBorderPane().setBottom(cardPane);
        });
    }

    public void addCards(String[] cardsUrls) {
        Platform.runLater(() -> cardPane.addCards(cardsUrls));
    }

    public void loadSkatPane() {
        Platform.runLater(() -> {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/skat-view.fxml"));
            try {
                gui.getBorderPane().setCenter(fxmlLoader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadServerSelection() {
        Platform.runLater(() -> gui.getBorderPane().setCenter(new ServerSelectionPane(gui.getBorderPane().getWidth(),
                gui.getBorderPane().getHeight())));
    }

    public void unloadCenterPane() {
        Platform.runLater(() -> gui.getBorderPane().setCenter(null));
    }

    public void loadBidPanel() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/bid-view.fxml"));
            try {
                gui.getBorderPane().setCenter(loader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadWaiting() {
        Platform.runLater(() -> {
            FXMLLoader loader = new FXMLLoader(Gui.class.getResource("/client/gui/panes/waiting-view.fxml"));
            try {
                gui.getBorderPane().setCenter(loader.load());
            } catch(IOException e) {
                Log.getLogger().log(Level.SEVERE, e.getMessage(), e);
            }
        });
    }

    public void loadOpenGameCards(String[] urls) {
        Platform.runLater(() -> {
            openCards = new OpenCardsPane(urls, gui.getBorderPane().getWidth(), gui.getBorderPane().getHeight());
            gui.getBorderPane().setRight(openCards);
        });
    }

    private void removeOpenCard(String[] playedCards) {
        Platform.runLater(() -> {
            if(openCards != null) {
                openCards.removeCard(playedCards);
            }
        });
    }

    public static void loadGuiController(Gui gui) {
        if(guiController == null)
            new GuiController(gui);
    }

    public double getWidth() {
        return cardWidth;
    }

    public double getHeight() {
        return cardHeight;
    }

    public static GuiController getInstance() {
        return guiController;
    }

    public Gui getGui() {
        return gui;
    }
}

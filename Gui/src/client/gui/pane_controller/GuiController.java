package client.gui.pane_controller;

import client.gui.Gui;
import client.gui.panes.ServerSelectionPane;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class GuiController {

    private static GuiController guiController;
    private Gui gui;

    private GuiController(Gui gui) {
        guiController = this;
        this.gui = gui;
    }

    public void loadMainMenu() {
        FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/gui/panes/main-menu-view.fxml"));
        try {
            gui.getBorderPane().setCenter(fxmlLoader.load());
        } catch(IOException e) {

        }
    }

    public void loadServerSelection() {
        gui.getBorderPane().setCenter(new ServerSelectionPane(gui.getBorderPane().getWidth(),
                gui.getBorderPane().getHeight()));
    }

    public static void loadGuiController(Gui gui) {
        if(guiController == null)
            new GuiController(gui);
    }

    public static GuiController getInstance() {
        return guiController;
    }
}

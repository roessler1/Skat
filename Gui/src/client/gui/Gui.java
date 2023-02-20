package client.gui;

import client.gui.pane_controller.GuiController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Gui extends Application {

    private BorderPane borderPane;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        GuiController.loadGuiController(this);
        borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        scene.setFill(Color.rgb(52, 151, 70));
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Skat");
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        GuiController.getInstance().loadMainMenu();
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public void startGame() {
        launch();
    }
}

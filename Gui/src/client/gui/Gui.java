package client.gui;

import client.gui.pane_controller.GuiController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private BorderPane borderPane;
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        borderPane = new BorderPane();
        borderPane.setStyle("-fx-background-color: #349746");
        Scene scene = new Scene(borderPane);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Skat");
        this.primaryStage.setFullScreen(true);
        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.setOnCloseRequest(windowEvent -> {
            Platform.exit();
            System.exit(0);
        });
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
        GuiController.loadGuiController(this);
        GuiController.getInstance().loadMainMenu();
    }

    public BorderPane getBorderPane() {
        return borderPane;
    }

    public static void main(String[] args) {
        launch();
    }
}

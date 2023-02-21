package client.gui.panes;

import client.logic.LogicEvents;
import client.gui.multicast.IMulticastClient;
import client.gui.multicast.MulticastClient;
import client.gui.pane_controller.GuiController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ServerSelectionPane extends StackPane {

    IMulticastClient client;
    ListView<Label> addresses;
    ScheduledExecutorService executorService;
    Runnable updateList = this::update;

    public ServerSelectionPane(double width, double height) {
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #349746");
        BorderPane bpane = new BorderPane();
        bpane.setMaxSize(width*0.4, height*0.4);
        bpane.setStyle("-fx-background-color: #b9b9b9");
        bpane.setPadding(new Insets(5,5,5,5));

        addresses = new ListView<>();
        addresses.setStyle("-fx-background-color: #ffffff");
        bpane.setCenter(addresses);
        client = new MulticastClient();

        Button back = new Button("Zurück");
        back.setPrefSize(80, 35);
        back.setOnMouseClicked(e -> {
            client.closeMulicastClient();
            GuiController.getInstance().loadMainMenu();
        });
        HBox box = new HBox(back);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        box.setPadding(new Insets(5,5,5,5));
        bpane.setBottom(box);

        this.getChildren().add(bpane);
        executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(updateList, 0, 1000, TimeUnit.MILLISECONDS);
    }

    private void update() {
        addresses.getItems().clear();
        addresses.getItems().addAll(client.getAvailableServers());
        for(Label label:addresses.getItems()) {
            label.setOnMouseClicked(e -> {
                client.closeMulicastClient();
                executorService.shutdownNow();
                executorService.close();
                LogicEvents.getInstance().buildConnection(label.getText());
                //TODO -> change pane
            });
        }
    }
}

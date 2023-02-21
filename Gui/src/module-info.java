module Gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    opens client.gui to javafx.fxml;
    opens client.gui.pane_controller to javafx.fxml;
    opens client.gui.pane_events to javafx.fxml;
    opens client.gui.panes;
    exports client.gui;
}
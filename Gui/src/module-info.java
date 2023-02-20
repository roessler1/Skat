module Gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens gui to javafx.fxml;
    opens gui.pane_controller to javafx.fxml;
    opens gui.pane_events to javafx.fxml;
    exports gui;
}
module com.example.skat {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.skat to javafx.fxml;
    exports com.example.skat;
}
module com.example.pm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pm to javafx.fxml;
    exports com.example.pm;
}
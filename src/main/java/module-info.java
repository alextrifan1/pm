module com.example.pm {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.example.pm to javafx.fxml;
    exports com.example.pm;
}
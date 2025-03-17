module com.example.reasturant {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.reasturant to javafx.fxml;
    exports com.example.reasturant;
}
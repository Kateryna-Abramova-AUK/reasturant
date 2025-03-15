module com.example.reasturant {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reasturant to javafx.fxml;
    exports com.example.reasturant;
}
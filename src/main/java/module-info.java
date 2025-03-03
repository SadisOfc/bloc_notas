module com.example.notitas {
    requires javafx.controls;
    requires java.sql;
    requires javafx.fxml;


    opens com.example.notitas to javafx.fxml;
    exports com.example.notitas;
    exports com.example.notitas.model;
    opens com.example.notitas.model to javafx.fxml;
    exports com.example.notitas.controller;
    opens com.example.notitas.controller to javafx.fxml;
    exports com.example.notitas.database;
    opens com.example.notitas.database to javafx.fxml;
}
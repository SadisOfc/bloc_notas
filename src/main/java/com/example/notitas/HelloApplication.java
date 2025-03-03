package com.example.notitas;

import com.example.notitas.controller.MainController;
import com.example.notitas.database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.sql.SQLException;
import java.sql.Connection;

import javafx.stage.Stage;

import java.io.IOException;
//C:\Users\rirfe\AppData\Local\SceneBuilder
public class HelloApplication extends Application {
    private MainController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/notitas/views/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        controller = fxmlLoader.getController();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        try(Connection conn = DatabaseConnection.getConnection()){
            if(conn !=null){
                System.out.println("Conexión Exitosa");
            }else{
                System.out.println("No fue posible conectarse a la base de datos");
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        stage.setOnCloseRequest(event->{
            controller.actualizarArchivo();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
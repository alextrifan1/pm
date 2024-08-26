package com.example.pm;

import Domain.Entity;
import Domain.Piesa;
import Repository.ExceptionRepository;
import Repository.IRepository;
import Repository.MRepository;
import Repository.SQLRepository;
import Service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ExceptionRepository {
        IRepository<Piesa> repository = new SQLRepository("radio.sqlite");
        Service service = new Service(repository);

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        com.example.pm.HelloController hc = new com.example.pm.HelloController(service);
        fxmlLoader.setController(hc);

        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
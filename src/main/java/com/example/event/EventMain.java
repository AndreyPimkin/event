package com.example.event;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class EventMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(EventMain.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.getIcons().add(new Image("file:src/main/resources/com/example/event/picture/icon.png"));
        stage.setTitle("Event");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
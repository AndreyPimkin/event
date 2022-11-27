package com.example.event;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.DatabaseHandler;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class BigEvent {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageEvent;

    DatabaseHandler dbHandler = new DatabaseHandler();
    private String number;

    @FXML
    void initialize() {
        ResultSet resSet = dbHandler.getID();
        try {
            if(resSet.next()){
                try {
                    number = resSet.getString("event_id");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        imageEvent.setImage(new Image("file:src/main/resources/com/example/event/picture/"+number+".png"));
    }

}

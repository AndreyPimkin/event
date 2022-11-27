package com.example.event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class Organizator {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button events;

    @FXML
    private ImageView image;

    @FXML
    private Button jurys;

    @FXML
    private Label label;

    @FXML
    private Button members;

    @FXML
    private Button myProf;

    LocalDate dateLD;
    Date date;
    String formattedDate;

    @FXML
    void initialize() {
        myProf.setOnAction(actionEvent -> {
            openModal("/com/example/event/profileOrg.fxml", "My profile");
        });
        image.setImage(new Image("file:src/main/resources/com/example/event/picture/org/"+Authorization.imageUser));
        LocalDateTime dateTime = LocalDateTime.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        if(dateTime.getHour() <= 11 &&  dateTime.getHour() >= 6)
            label.setText("Доброе утро, "
                    +Authorization.nameUser.substring(Authorization.nameUser.indexOf(' ') + 1));
        else if (dateTime.getHour() > 11 &&  dateTime.getHour() <= 18){
            label.setText("Добрый день, "
                    +Authorization.nameUser.substring(Authorization.nameUser.indexOf(' ') + 1));}
        else if (dateTime.getHour() > 18){
            label.setText("Добрый вечер, "
                    +Authorization.nameUser.substring(Authorization.nameUser.indexOf(' ') + 1));}


        events.setOnAction(actionEvent -> {
            openModal("/com/example/event/addEvent.fxml", "Event");
        });

    }

    private void openModal(String path, String title) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(path));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene((new Scene(root)));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image("file:src/main/resources/com/example/event/picture/icon.png"));
        stage.setTitle(title);
        stage.showAndWait();
    }


}

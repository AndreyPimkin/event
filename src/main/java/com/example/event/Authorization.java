package com.example.event;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import server.DatabaseHandler;
import server.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Authorization{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private Button open;

    @FXML
    private Button close;

    @FXML
    private PasswordField password;

    @FXML
    private Label text;

    public static String nameUser;


    @FXML
    void initialize() {
        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            String passwordText = password.getText().trim();
            if (!loginText.equals("") && !passwordText.equals("")) {
                try {
                    loginUser(loginText, passwordText);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                text.setText("Пустые данные");

            }
        });

        close.setOnAction(actionEvent -> close.getScene().getWindow().hide());
    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setEmail(loginText);
        user.setPassword(passwordText);
        ResultSet resultAuto = dbHandler.getUserLoginAndPassword(user);
        ResultSet resultRole = dbHandler.getRoles(user);
        ResultSet resultName= dbHandler.getName(user);
        if(resultAuto.next() && resultRole.next() && resultName.next()){
            int userRole = resultRole.getInt("RoleID");
            nameUser = resultName.getString("Firstname");
            if(userRole == 1) open("/com/example/avia/Admin.fxml");
            else open("/com/example/avia/User.fxml");
        }
        else text.setText("Такой пользователь не существует");
    }

    private void open(String path) {
        open.getScene().getWindow().hide();
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
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.ico"));
        stage.setTitle("AMONIC Airlines Automation System");
        stage.show();
    }



}

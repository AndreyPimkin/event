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
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.DatabaseHandler;
import server.User;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

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

    public static String role;
    public static String roleID;

    private int score = 0;

    @FXML
    void initialize() {
        if (score == 3){
            open.setDisable(true);
            timerCode();
        }
        open.setDisable(false);
        open.setOnAction(actionEvent -> {
            if (Captcha.check == 1){
                openWindow("/com/example/event/"+ role +".fxml");
            }
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
                score++;

            }
        });

        close.setOnAction(actionEvent -> close.getScene().getWindow().hide());
    }

    private void loginUser(String loginText, String passwordText) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setEmail(loginText);
        user.setPassword(passwordText);

        ResultSet resultJury = dbHandler.getJury(user);
        ResultSet resultMember = dbHandler.getMember(user);
        ResultSet resultOrg = dbHandler.getOrganizator(user);
        ResultSet resultMod = dbHandler.getModerator(user);
        ResultSet resultName;


        if(resultJury .next()){
            role = "jury";
            roleID = "jury_id";
            resultName = dbHandler.getName(user);
            nameUser = resultName.getString("full_name");
            openModal("/com/example/event/captcha.fxml");

        }
        else if(resultMember.next()){
            role = "member";
            roleID = "member_id";
            resultName = dbHandler.getName(user);
            nameUser = resultName.getString("full_name");
            openModal("/com/example/event/captcha.fxml");
        }
        else if(resultOrg.next()){
            role = "organizator";
            roleID = "organizator_id";
            resultName = dbHandler.getName(user);
            nameUser = resultName.getString("full_name");
            openModal("/com/example/event/captcha.fxml");

        }
        else if(resultMod.next()){
            role = "moderator";
            roleID = "moderator_id";
            resultName = dbHandler.getName(user);
            nameUser = resultName.getString("full_name");
            openModal("/com/example/event/captcha.fxml");

        }
        else{
            text.setText("Такой пользователь не существует");
            score++;
        }
    }


    private void openModal(String path) {
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
        stage.setTitle("captcha");
        stage.showAndWait();
    }

    private void openWindow(String path) {
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
        stage.getIcons().add(new Image("file:src/main/resources/picture/icon.png"));
        stage.setTitle(role);
        stage.show();
    }

    Timer timer = new Timer();
    private void timerCode(){
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            int i = 10;
            public void run() {
                System.out.println(i);
                i--;
                if (i < 0) {
                    open.setDisable(false);
                    timer.cancel();
                }

            }
        }, 0, 1000);
    }
}



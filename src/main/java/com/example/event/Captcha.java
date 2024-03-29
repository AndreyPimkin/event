package com.example.event;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Captcha {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label four;

    @FXML
    private TextField login;

    @FXML
    private Label one;

    @FXML
    private Button open;

    @FXML
    private Label text;

    @FXML
    private Label three;

    @FXML
    private Label two;

    private  String oneC;
    private  String twoC;
    private  String threeC;
    private  String fourC;

    public static int check = 0;


    @FXML
    void initialize() {
        Random random =  new Random();
        oneC = String.valueOf(random.nextInt(0, 9));
        twoC = String.valueOf(random.nextInt(0, 9));
        threeC = String.valueOf(random.nextInt(0, 9));
        fourC = String.valueOf(random.nextInt(0, 9));

        one.setText(oneC);
        two.setText(twoC);
        three.setText(threeC);
        four.setText(fourC);

        open.setOnAction(actionEvent -> {
            String loginText = login.getText().trim();
            if (!loginText.equals("")) {
                if (loginText.equals(oneC+twoC+threeC+fourC )){
                    check = 1;
                    open.getScene().getWindow().hide();
                }
                else{
                    oneC = String.valueOf(random.nextInt(0, 9));
                    twoC = String.valueOf(random.nextInt(0, 9));
                    threeC = String.valueOf(random.nextInt(0, 9));
                    fourC = String.valueOf(random.nextInt(0, 9));
                    one.setText(oneC);
                    two.setText(twoC);
                    three.setText(threeC);
                    four.setText(fourC);
                    text.setText("Неверно");
                }

            }

            else {
                text.setText("Пустые данные");
            }
        });

    }



}

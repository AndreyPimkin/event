package com.example.event;

import POJO.Org;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import server.DatabaseHandler;
import server.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileOrg {


    @FXML
    private TextField birthday;

    @FXML
    private RadioButton changePassword;

    @FXML
    private TextField country;

    @FXML
    private TextField email;

    @FXML
    private TextField fullName;

    @FXML
    private TextField idNumber;

    @FXML
    private ImageView image;

    @FXML
    private TextField male;

    @FXML
    private RadioButton muskPassword;

    @FXML
    private Button noButton;

    @FXML
    private TextField numberPhone;

    @FXML
    private Button okButton;

    @FXML
    private TextField passwordFour;

    @FXML
    private PasswordField passwordOne;

    @FXML
    private TextField passwordThree;

    @FXML
    private PasswordField passwordTwo;

    ResultSet resultOrg;
    DatabaseHandler dbHandler = new DatabaseHandler();
    private String passwordOneText;
    private String passwordTwoText;
    private String fullNameText;
    private String maleText;
    private String birthdayText;
    private String countryText;
    private String numberText;
    private String emailText;
    Org org;
    User user;
    @FXML
    void initialize() {
        passwordThree.setVisible(false);
        passwordFour.setVisible(false);
        image.setImage(new Image("file:src/main/resources/com/example/event/picture/org/"+Authorization.imageUser));
        user = new User();
        user.setEmail(Authorization.ID);
        user.setPassword(Authorization.passwordUser);
        resultOrg = dbHandler.getOrganizatorFull(user);

        try {
            if(resultOrg.next()){
                fullName.setText(resultOrg.getString("full_name"));
                male.setText(resultOrg.getString("male"));
                birthday.setText(resultOrg.getString("birthday"));
                idNumber.setText(resultOrg.getString("organizator_id"));
                country.setText(resultOrg.getString("name") + "(" + resultOrg.getString("code") +")");
                numberPhone.setText(resultOrg.getString("phone"));
                email.setText(resultOrg.getString("mail"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        okButton.setOnAction(actionEvent -> {

        });

        noButton.setOnAction(actionEvent -> {
            resultOrg = dbHandler.getOrganizatorFull(user);
            passwordOne.clear();
            passwordTwo.clear();
            passwordThree.clear();
            passwordFour.clear();
            try {
                if(resultOrg.next()){
                    fullName.setText(resultOrg.getString("full_name"));
                    male.setText(resultOrg.getString("male"));
                    birthday.setText(resultOrg.getString("birthday"));
                    idNumber.setText(resultOrg.getString("organizator_id"));
                    country.setText(resultOrg.getString("name") + "(" + resultOrg.getString("code") +")");
                    numberPhone.setText(resultOrg.getString("phone"));
                    email.setText(resultOrg.getString("mail"));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        });

        muskPassword.setOnAction(actionEvent -> {
            if(muskPassword.isSelected()){
                passwordOne.setVisible(false);
                passwordTwo.setVisible(false);
                passwordThree.setVisible(true);
                passwordFour.setVisible(true);
            }
            else {
                passwordOne.setVisible(true);
                passwordTwo.setVisible(true);
                passwordThree.setVisible(false);
                passwordFour.setVisible(false);
            }
        });

        okButton.setOnAction(actionEvent -> {
            if(!changePassword.isSelected()){
                fullNameText = fullName.getText().trim();
                maleText = male.getText().trim();
                birthdayText = birthday.getText().trim();
                countryText = country.getText().trim();
                numberText = numberPhone.getText().trim();
                emailText = email.getText().trim();
                try {
                    loginUser();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {

                if(muskPassword.isSelected()){
                    passwordOneText = passwordThree.getText().trim();
                    passwordTwoText = passwordFour.getText().trim();
                }
                else {
                    passwordOneText = passwordOne.getText().trim();
                    passwordTwoText = passwordTwo.getText().trim();
                }
                if(passwordOneText.equals(passwordTwoText)){
                    try {
                        loginUser();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        });


    }

    private void loginUser() throws SQLException {


        if (changePassword.isSelected()){
            org = new Org();
            org.setPasswordOneText(passwordOneText);
            dbHandler.changePassword(org);
            Authorization.passwordUser = passwordOneText;
        }
        else if(!changePassword.isSelected()){
            org = new Org();
            org.setFullNameText(fullNameText);
            org.setMaleText(maleText);
            org.setBirthdayText(birthdayText);
            org.setCountryText(countryText);
            org.setNumberText(numberText);
            org.setEmailText(emailText);
            dbHandler.changePerson(org);
        }
        user = new User();
        user.setEmail(Authorization.ID);
        user.setPassword(Authorization.passwordUser);
        resultOrg = dbHandler.getOrganizatorFull(user);

        try {
            if(resultOrg.next()){
                fullName.setText(resultOrg.getString("full_name"));
                male.setText(resultOrg.getString("male"));
                birthday.setText(resultOrg.getString("birthday"));
                idNumber.setText(resultOrg.getString("organizator_id"));
                country.setText(resultOrg.getString("name") + "(" + resultOrg.getString("code") +")");
                numberPhone.setText(resultOrg.getString("phone"));
                email.setText(resultOrg.getString("mail"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

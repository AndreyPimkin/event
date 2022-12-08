package com.example.event;

import POJO.ForEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;

import server.DatabaseHandler;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AddEvent {

    @FXML
    private Button addButton;

    @FXML
    private ComboBox<String> city;

    @FXML
    private Button csv;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private Button doska;

    @FXML
    private TextField event;



    @FXML
    private ComboBox<String> juryOne;

    @FXML
    private ComboBox<String> juryThree;

    @FXML
    private ComboBox<String> juryTwo;

    @FXML
    private ComboBox<String> moderator;

    @FXML
    private TextField nameOne;

    @FXML
    private TextField nameThree;

    @FXML
    private TextField dayOne;

    @FXML
    private TextField dayThree;

    @FXML
    private TextField dayTwo;

    @FXML
    private TextField nameTwo;

    @FXML
    private TextField number;

    @FXML
    private Button okButton;

    @FXML
    private TextField timeOne;

    @FXML
    private TextField timeThree;

    @FXML
    private TextField timeTwo;

    @FXML
    private Label text;

    @FXML
    private ComboBox<String> winner;


    private String selectCity;
    private String selectModerator;
    private String selectWinner;

    private ArrayList<String> listJuryOne = new ArrayList<>();
    private ArrayList<String> listJuryTwo = new ArrayList<>();
    private ArrayList<String> listJuryThree = new ArrayList<>();

    DatabaseHandler dbHandler = new DatabaseHandler();


    ForEvent forEvent = new ForEvent();
    LocalDate dateOne;
    LocalDate dateTwo;
    ResultSet rs;

    @FXML
    void initialize() {
        try {
            initCity();
            initModerator();
            initWinner();
            initJury();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        city.setOnAction(this::getCity);
        moderator.setOnAction(this::getModerator);
        winner.setOnAction(this::getWinner);
        juryOne.setOnAction(this::getJuryOne);
        juryTwo.setOnAction(this::getJuryTwo);
        juryThree.setOnAction(this::getJuryThree);

        addButton.setOnAction(actionEvent -> {
            forEvent = new ForEvent();
            forEvent.setName(event.getText());
            dateOne = dateFrom.getValue();
            dateTwo = dateTo.getValue();
            rs =  dbHandler.getEvent(forEvent);
            try {
                if(!rs.next()){
                    forEvent = new ForEvent();
                    forEvent.setName(event.getText());
                    forEvent.setDateFrom(String.valueOf(dateOne));
                    forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                    forEvent.setCityID(selectCity.substring(selectCity.indexOf(" ") + 1));
                    dbHandler.addEvent(forEvent);
                    text.setStyle("-fx-text-fill:  black");
                    text.setText("Событие добавлена");
                }
                else{
                    forEvent = new ForEvent();
                    forEvent.setNumber(number.getText() + "a");
                    forEvent.setName(event.getText());
                    forEvent.setDateFrom(String.valueOf(dateFrom.getValue()));
                    forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                    forEvent.setActivity(nameOne.getText());
                    forEvent.setDayActivity(dayOne.getText());
                    forEvent.setTimeFrom(timeOne.getText());
                    forEvent.setModerator(selectModerator);
                    forEvent.setJury1(listJuryOne.get(0));
                    forEvent.setJury2(listJuryOne.get(1));
                    forEvent.setJury3(listJuryOne.get(2));
                    forEvent.setJury4(listJuryOne.get(3));
                    forEvent.setJury5(listJuryOne.get(4));
                    if(selectWinner != null){
                        forEvent.setWinner(selectWinner);
                    }
                    else {
                        forEvent.setWinner("");
                    }
                    dbHandler.addAct(forEvent);

                    forEvent = new ForEvent();
                    forEvent.setNumber(number.getText() + "b");
                    forEvent.setName(event.getText());
                    forEvent.setDateFrom(String.valueOf(dateFrom.getValue()));
                    forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                    forEvent.setActivity(nameTwo.getText());
                    forEvent.setDayActivity(dayTwo.getText());
                    forEvent.setTimeFrom(timeTwo.getText());
                    forEvent.setModerator(selectModerator);
                    forEvent.setJury1(listJuryTwo.get(0));
                    forEvent.setJury2(listJuryTwo.get(1));
                    forEvent.setJury3(listJuryTwo.get(2));
                    forEvent.setJury4(listJuryTwo.get(3));
                    forEvent.setJury5(listJuryTwo.get(4));
                    forEvent.setWinner(" ");
                    dbHandler.addAct(forEvent);

                    forEvent = new ForEvent();
                    forEvent.setNumber(number.getText() + "c");
                    forEvent.setName(event.getText());
                    forEvent.setDateFrom(String.valueOf(dateFrom.getValue()));
                    forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                    forEvent.setActivity(nameThree.getText());
                    forEvent.setDayActivity(dayThree.getText());
                    forEvent.setTimeFrom(timeThree.getText());
                    forEvent.setModerator(selectModerator);
                    forEvent.setJury1(listJuryThree.get(0));
                    forEvent.setJury2(listJuryThree.get(1));
                    forEvent.setJury3(listJuryThree.get(2));
                    forEvent.setJury4(listJuryThree.get(3));
                    forEvent.setJury5(listJuryThree.get(4));
                    forEvent.setWinner(" ");
                    dbHandler.addAct(forEvent);

                    text.setStyle("-fx-text-fill:  black");
                    text.setText("Активноть добавлена");


                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        });



        okButton.setOnAction(actionEvent -> {
            okButton.getScene().getWindow().hide();
        });


    }

    private void initCity() throws SQLException {
        ResultSet rs = dbHandler.getCityEvent();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            city.getItems().addAll(rs.getString("name") + " " +  rs.getString("city_id"));
        }
    }

    private void initModerator() throws SQLException {
        ResultSet rs = dbHandler.getModeratorEvent();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            moderator.getItems().addAll(rs.getString("full_name"));
        }
    }

    private void initWinner() throws SQLException {
        ResultSet rs = dbHandler.getMemberEvent();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            winner.getItems().addAll(rs.getString("full_name"));
        }
    }

    private void initJury() throws SQLException {
        ResultSet rs = dbHandler.getJuryEvent();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            juryOne.getItems().addAll(rs.getString("full_name"));
            juryTwo.getItems().addAll(rs.getString("full_name"));
            juryThree.getItems().addAll(rs.getString("full_name"));
        }
    }


    private void getCity(ActionEvent actionEvent) {
        selectCity = String.valueOf(city.getValue());
    }

    private void getModerator(ActionEvent actionEvent) {
        selectModerator = String.valueOf(moderator.getValue());
    }

    private void getWinner(ActionEvent actionEvent) {
        selectWinner = String.valueOf(winner.getValue());
    }

    private void getJuryOne(ActionEvent actionEvent) {
        if(listJuryOne.size() > 4){
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        }
        else {
            listJuryOne.add(juryOne.getValue());
        }

    }

    private void getJuryTwo(ActionEvent actionEvent) {
        if(listJuryTwo.size() > 4){
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        }
        else {
            listJuryTwo.add(juryTwo.getValue());
        }

    }
    private void getJuryThree(ActionEvent actionEvent) {
        if(listJuryThree.size() > 4){
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        }
        else {
            listJuryThree.add(juryThree.getValue());
        }
    }






}

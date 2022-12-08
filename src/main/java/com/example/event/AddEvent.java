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
            city.getItems().addAll(rs.getString("name") + " " + rs.getString("city_id"));
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
        if (listJuryOne.size() > 4) {
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        } else {
            listJuryOne.add(juryOne.getValue());
        }

    }

    private void getJuryTwo(ActionEvent actionEvent) {
        if (listJuryTwo.size() > 4) {
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        } else {
            listJuryTwo.add(juryTwo.getValue());
        }

    }

    private void getJuryThree(ActionEvent actionEvent) {
        if (listJuryThree.size() > 4) {
            text.setStyle("-fx-text-fill:  red");
            text.setText("Выберите 5 жюри");
        } else {
            listJuryThree.add(juryThree.getValue());
        }
    }


    public void addEv() {
        String eventName = event.getText();
        LocalDate dateOne = dateFrom.getValue();
        LocalDate dateTwo = dateTo.getValue();
        String selCity = selectCity;
        String numberEvent = number.getText();
        String nameOneAct = nameOne.getText();
        String nameTwoAct = nameTwo.getText();
        String nameThreeAct = nameThree.getText();
        String dayOneAct = dayOne.getText();
        String dayTwoAct = dayTwo.getText();
        String dayThreeAct = dayThree.getText();
        String timeOneAct = timeOne.getText();
        String timeTwoAct = timeTwo.getText();
        String timeThreeAct = timeThree.getText();
        String selMod = selectModerator;

        add(eventName, dateOne, dateTwo, selCity, numberEvent, nameOneAct, nameTwoAct, nameThreeAct, dayOneAct,
                dayTwoAct, dayThreeAct, timeOneAct, timeTwoAct, timeThreeAct, selMod);
    }

    public String add(String eventName, LocalDate dateOne, LocalDate dateTwo, String selCity, String numberEvent,
                      String nameOneAct, String nameTwoAct, String nameThreeAct,
                      String dayOneAct, String dayTwoAct, String dayThreeAct,
                      String timeOneAct, String timeTwoAct, String timeThreeAct, String selMod) {
        forEvent = new ForEvent();
        if (!eventName.equals("")) {
            forEvent.setName(eventName);
            rs = dbHandler.getEvent(forEvent);
            try {
                if (dateOne != null && dateTwo != null) {
                    if (!selCity.equals("")) {
                        if (!rs.next()) {
                            forEvent = new ForEvent();
                            forEvent.setName(eventName);
                            forEvent.setDateFrom(String.valueOf(dateOne));
                            forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                            forEvent.setCityID(selCity.substring(selCity.indexOf(" ") + 1));
                            dbHandler.addEvent(forEvent);
                          //  text.setStyle("-fx-text-fill:  black");
                          //  text.setText("Мероприятие добавлено");
                            return "Мероприятие добавлено";
                        } else {
                            if (!numberEvent.equals("")) {
                                if (!nameOneAct.equals("") && !nameTwoAct.equals("") && !nameThreeAct.equals("")) {
                                    if (!dayOneAct.equals("") && !dayTwoAct.equals("") && !dayThreeAct.equals("")) {
                                        if (!timeOneAct.equals("")) {
                                            if(!timeTwoAct.equals("")){
                                                if(!timeThreeAct.equals("")){
                                                    if (!selMod.equals("")) {
                                                        forEvent = new ForEvent();
                                                        forEvent.setNumber(numberEvent + "a");
                                                        forEvent.setName(eventName);
                                                        forEvent.setDateFrom(String.valueOf(dateOne));
                                                        forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                                                        forEvent.setActivity(nameOneAct);
                                                        forEvent.setDayActivity(dayOneAct);
                                                        forEvent.setTimeFrom(timeOneAct);
                                                        forEvent.setModerator(selMod);
                                                        forEvent.setJury1(listJuryOne.get(0));
                                                        forEvent.setJury2(listJuryOne.get(1));
                                                        forEvent.setJury3(listJuryOne.get(2));
                                                        forEvent.setJury4(listJuryOne.get(3));
                                                        forEvent.setJury5(listJuryOne.get(4));
                                                        if (selectWinner != null) {
                                                            forEvent.setWinner(selectWinner);
                                                        } else {
                                                            forEvent.setWinner("");
                                                        }
                                                        dbHandler.addAct(forEvent);

                                                        forEvent = new ForEvent();
                                                        forEvent.setNumber(numberEvent + "b");
                                                        forEvent.setName(eventName);
                                                        forEvent.setDateFrom(String.valueOf(dateOne));
                                                        forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                                                        forEvent.setActivity(nameTwoAct);
                                                        forEvent.setDayActivity(dayTwoAct);
                                                        forEvent.setTimeFrom(timeTwoAct);
                                                        forEvent.setModerator(selMod);
                                                        forEvent.setJury1(listJuryOne.get(0));
                                                        forEvent.setJury2(listJuryOne.get(1));
                                                        forEvent.setJury3(listJuryOne.get(2));
                                                        forEvent.setJury4(listJuryOne.get(3));
                                                        forEvent.setJury5(listJuryOne.get(4));
                                                        forEvent.setWinner(" ");
                                                        dbHandler.addAct(forEvent);

                                                        forEvent = new ForEvent();
                                                        forEvent.setNumber(numberEvent + "c");
                                                        forEvent.setName(eventName);
                                                        forEvent.setDateFrom(String.valueOf(dateOne));
                                                        forEvent.setDaysEvent(String.valueOf((ChronoUnit.DAYS.between(dateOne, dateTwo) + 1)));
                                                        forEvent.setActivity(nameThreeAct);
                                                        forEvent.setDayActivity(dayThreeAct);
                                                        forEvent.setTimeFrom(timeThreeAct);
                                                        forEvent.setModerator(selMod);
                                                        forEvent.setJury1(listJuryOne.get(0));
                                                        forEvent.setJury2(listJuryOne.get(1));
                                                        forEvent.setJury3(listJuryOne.get(2));
                                                        forEvent.setJury4(listJuryOne.get(3));
                                                        forEvent.setJury5(listJuryOne.get(4));
                                                        forEvent.setWinner(" ");
                                                        dbHandler.addAct(forEvent);

                                                      //  text.setStyle("-fx-text-fill:  black");
                                                       // text.setText("Активноть добавлена");
                                                        return "Активность добавлена";

                                                    } else {
                                                      //  text.setText("Модератор не выбран");
                                                        return "Модератор не выбран";
                                                    }

                                                }
                                                else{
                                                 //   text.setText("Время третьей активности не выбрана");
                                                    return "Время третьей активности не выбрано";
                                                }

                                            }
                                            else{
                                              //  text.setText("Время второй активности не выбрана");
                                                return "Время второй активности не выбрано";
                                            }
                                        } else {
                                           // text.setText("Время первой активности не выбрана");
                                            return "Время первой активности не выбрано";
                                        }

                                    } else {
                                      //  text.setText("День активности не выбрана");
                                        return "День активности не выбран";
                                    }

                                } else {
                                   // text.setText("Имя активности не выбрана");
                                    return "Имя активности не выбрано";
                                }

                            } else {
                               // text.setText("Номер не выбран");
                                return "Номер не выбран";
                            }

                        }
                    } else {
                        //text.setText("Вы не выбрали город");
                        return "Вы не выбрали город";
                    }
                } else {
                  //  text.setText("Вы не выбрали дату");
                    return "Вы не выбрали дату";
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
           // text.setText("Вы не выбрали мероприятие");
            return "Вы не выбрали мероприятие";
        }
    }
}

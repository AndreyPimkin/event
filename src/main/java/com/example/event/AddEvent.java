package com.example.event;

import POJO.ForEvent;
import POJO.MainTable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import server.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEvent {

    @FXML
    private ComboBox<String> city;

    @FXML
    private Button csv;

    @FXML
    private ComboBox<?> direction;

    @FXML
    private Button doska;

    @FXML
    private ComboBox<String> event;

    @FXML
    private DatePicker fromDate;


    @FXML
    private ComboBox<String> juryOne;

    @FXML
    private ComboBox<String> juryThree;

    @FXML
    private ComboBox<String> juryTwo;

    @FXML
    private TextField nameOne;

    @FXML
    private TextField nameThree;

    @FXML
    private TextField fromTime;

    @FXML
    private TextField day;

    @FXML
    private TextField nameTwo;

    @FXML
    private Button okButton;

    @FXML
    private TextField  timeOne;

    @FXML
    private TextField  timeTwo;

    @FXML
    private TextField  timeThree;

    private String selectedEvent;

    DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    void initialize() {





        okButton.setOnAction(actionEvent -> {
            ForEvent forEvent = new ForEvent();
            forEvent.setNumber("11a");
            forEvent.setName();
            forEvent.setDaysEvent(day.getText());
            forEvent.setActivity(nameOne.getText());
            forEvent.setDayActivity(timeOne.getText());
            forEvent.setTimeFrom(fromTime.getText());
            forEvent.setModerator();
        });


    }

    private void initEvent() throws SQLException {
        ResultSet rs = dbHandler.getEventTable();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            event.setItems(rs.getString(1));
        }
    }

}

package com.example.event;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import POJO.MainTable;
import server.DatabaseHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button authorization_button;

    @FXML
    private TableColumn<MainTable, String> cityColumn;

    @FXML
    private TableColumn<MainTable, Date> dataColumn;

    @FXML
    private TableColumn<MainTable, Integer> dayColumn;

    @FXML
    private TableColumn<MainTable, String> eventColumn;

    @FXML
    private TableView<MainTable> eventTable;

    @FXML
    private Label labelText;

    DatabaseHandler dbHandler = new DatabaseHandler();
    private final ObservableList<MainTable> eventList = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            initEvent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        authorization_button.setOnAction(actionEvent -> {
            openAuthorization("/com/example/event/Authorization.fxml");
        });
        labelText.setText("Добрый день!");
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("eventColumn"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataColumn"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("dayColumn"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityColumn"));
        eventTable.setItems(eventList);


    }

    private void openAuthorization(String path) {
        authorization_button.getScene().getWindow().hide();
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
        stage.setTitle("Code");
        stage.show();
    }

    private void initEvent() throws SQLException {
        ResultSet rs = dbHandler.getEventTable();
        while (true) {
            assert rs != null;
            if (!rs.next()) break;
            eventList.add(new MainTable(rs.getString("event"), rs.getDate("date"),
                    rs.getInt("days"), rs.getString("name")));
        }
    }


}

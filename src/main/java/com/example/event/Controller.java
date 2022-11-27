package com.example.event;

import POJO.MainTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import server.DatabaseHandler;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

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
    public static Object val;




    @FXML
    void initialize() {
        try {
            initEvent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        authorization_button.setOnAction(actionEvent -> {
            openAuthorization("/com/example/event/authorization.fxml");
        });
        labelText.setText("Добрый день!");
        eventColumn.setCellValueFactory(new PropertyValueFactory<>("eventColumn"));
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("dataColumn"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("dayColumn"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("cityColumn"));
        eventTable.setItems(eventList);

    /*    eventTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if(eventTable.getSelectionModel().getSelectedItem() != null)
                {
                    TableView.TableViewSelectionModel<MainTable> selectionModel = eventTable.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    val = tablePosition.getTableColumn().getCellData(newValue);

                    openModal("/com/example/event/bigEvent.fxml", String.valueOf(val));

                }
            }
        });*/


        eventTable.setRowFactory(tv -> {
            TableRow<MainTable> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {
                    MainTable clickedRow = row.getItem();
                    val = clickedRow.getEventColumn();
                    openModal("/com/example/event/bigEvent.fxml", String.valueOf(val));
                }
            });
            return row ;
        });


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
        stage.getIcons().add(new Image("file:src/main/resources/com/example/event/picture/icon.png"));
        stage.setTitle("Login");
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
        stage.getIcons().add(new Image("file:src/main/resources/com/example/event/picture/icon.png"));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.showAndWait();
    }


}

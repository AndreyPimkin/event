module com.example.event {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.junit.jupiter.api;


    opens com.example.event to javafx.fxml;
    exports com.example.event;

    exports POJO;
    opens POJO to javafx.fxml;
    exports server;
    opens server to javafx.fxml;


}
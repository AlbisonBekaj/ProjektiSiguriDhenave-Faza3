module com.example.projekti3faza3siguridhenave {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens Application to javafx.fxml;
    opens controllers to javafx.fxml;

    exports Application;
    exports controllers to javafx.fxml;
}
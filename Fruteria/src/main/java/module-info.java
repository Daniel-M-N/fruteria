module com.example.fruteria {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fruteria to javafx.fxml;
    exports com.example.fruteria;
}
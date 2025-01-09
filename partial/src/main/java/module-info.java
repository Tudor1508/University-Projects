module com.example.partial {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.partial to javafx.fxml;
    exports com.example.partial;
}
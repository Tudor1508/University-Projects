module com.example.colo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.xerial.sqlitejdbc;


    opens com.example.colo to javafx.fxml;
    exports com.example.colo;
    exports com.example.colo.Domain;
    opens com.example.colo.Domain to javafx.fxml;
    exports com.example.colo.Repo;
    opens com.example.colo.Repo to javafx.fxml;
    exports com.example.colo.Services;
    opens com.example.colo.Services to javafx.fxml;
}
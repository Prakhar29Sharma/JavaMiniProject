module com.example.miniproject_healthcare_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.miniproject_healthcare_system to javafx.fxml;
    exports com.example.miniproject_healthcare_system;
}

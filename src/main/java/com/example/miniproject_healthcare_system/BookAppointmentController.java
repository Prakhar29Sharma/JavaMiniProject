package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BookAppointmentController {

    private Scene scene;
    private Stage stage;

    public void switchToAppointments(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("appointments.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

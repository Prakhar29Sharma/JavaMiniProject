package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientRegController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TextField city;

    @FXML
    private TextField cityArea;

    @FXML
    private Label confirmationLabel;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private ChoiceBox<String> genderChoice;

    @FXML
    private TextField lname;

    @FXML
    private TextField phno;

    @FXML
    private Button registerButton;


    public void switchToPatientRec(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pat_recs.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void onRegisterButtonClick(ActionEvent event) {
        registerButton.setOnAction(e -> {
            String first_name = fname.getText();
            String last_name = lname.getText();
            String phone_num = phno.getText();
            String email_ = email.getText();
            String city_ = city.getId();
            String gender = (String) genderChoice.getValue();

        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderChoice.getItems().addAll("male", "female", "other");
    }
}
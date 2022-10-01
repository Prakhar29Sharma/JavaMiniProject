package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class UserRegistrationController {
    @FXML
    private Button RegisterButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label warningLabel1, warningLabel2, warningLabel3;


    @FXML
    void onButtonClick(ActionEvent event) throws IOException {
        RegisterButton.setOnAction(e -> {
            String user = usernameField.getText();
            if(user.equals("")){
                warningLabel1.setText("username field cannot be null!");
                warningLabel2.setText("");
                warningLabel3.setText("");
            }
            try {

                if(JavaDatabaseConnector.validateUser(user) == 0) {
                    warningLabel1.setText("user already exist!");
                    warningLabel2.setText("");
                    warningLabel3.setText("");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}

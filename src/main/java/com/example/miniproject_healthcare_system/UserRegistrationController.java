package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private Label warningLabel1, warningLabel2, warningLabel3, warningLabel4;

    @FXML
    private Label finalValidationLabel;

    private Stage stage;
    private Scene scene;

    int validateEmailFormat(String email) {
        char[] Array = email.toCharArray();
        int countAtSign = 0;
        int countFullStop = 0;
        for(char c : Array) {
            if(c == '@') {
                countAtSign+=1;
            }
            if(c == '.') {
                countFullStop += 1;
            }
        }
        if(countAtSign > 1 || countFullStop > 1) {
            return 1;
        } else {
            return 0;
        }
    }

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onButtonClick(ActionEvent event) throws IOException {
        RegisterButton.setOnAction(e -> {
            String user = usernameField.getText();
            String email = emailField.getText();
            String pass = passwordField.getText();
            String confirmPass = confirmPasswordField.getText();

            if(!user.equals("") && !email.equals("") && !pass.equals("") && !confirmPass.equals("")){
                try {

                    if(JavaDatabaseConnector.validateUser(user) == 1) {
                        if(validateEmailFormat(email) == 0) {
                            if(pass.equals(confirmPass)) {
                                JavaDatabaseConnector.InsertUser(user, email, confirmPass);
                                finalValidationLabel.setText("Registered Successfully!");
                            } else {
                                warningLabel3.setText("password did not match!");
                            }
                        } else if (validateEmailFormat(email) == 1) {
                            warningLabel2.setText("Invalid email id!");
                        }
                    } else {

                        if (JavaDatabaseConnector.validateUser(user) == 0) {
                            warningLabel1.setText("User already exist!");
                        }
                        if (validateEmailFormat(email) == 1) {
                            warningLabel2.setText("Invalid email id!");
                        }
                        if (!pass.equals(confirmPass)) {
                            warningLabel3.setText("password did not match!");
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            } else {
                if(user.equals("")) {
                    warningLabel1.setText("please enter username");
                }
                if(email.equals("")) {
                    warningLabel2.setText("please enter email");
                }
                if(pass.equals("")) {
                    warningLabel4.setText("please enter password");
                }
                if(confirmPass.equals("")) {
                    warningLabel3.setText("please enter confirm password");
                }
            }

        });
    }
}

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
import java.util.regex.Pattern;

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


    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    // function for validation of email
    public static boolean validateEmail(String email) {
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

            Pattern pat = Pattern.compile(emailRegex);
            if (email == null)
                return false;
            return pat.matcher(email).matches();
    }

    // checks strength of a password
    public static int passwordStrength(String pass) {
        int len = pass.length();
        // we can count number of small case, uppercase, special char and numbers to calc strength
        if(len < 8) {
            // weak password
            return 0;
        } else {
            // strong password
            return 1;
        }
    }

    // function for actions when register button is clicked
    @FXML
    void onButtonClick(ActionEvent event) throws IOException {
        RegisterButton.setOnAction(e -> {
            // getting values
            String user = usernameField.getText();
            String email = emailField.getText();
            String pass = passwordField.getText();
            String confirmPass = confirmPasswordField.getText();

            // validation before inserting record into database
            if(!user.equals("") && !email.equals("") && !pass.equals("") && !confirmPass.equals("")){
                try {

                    boolean isValidEmail = validateEmail(email);

                    if(JavaDatabaseConnector.validateUser(user) == 1) {
                        if(isValidEmail == true) {
                            if(pass.equals(confirmPass)) {
                                JavaDatabaseConnector.InsertUser(user, email, confirmPass);
                                finalValidationLabel.setText("Registered Successfully!");
                            } else {
                                warningLabel3.setText("password did not match!");
                            }
                        } else if (isValidEmail == false) {
                            warningLabel2.setText("Invalid email id!");
                        }
                    } else {

                        if (JavaDatabaseConnector.validateUser(user) == 0) {
                            warningLabel1.setText("User already exist!");
                        }
                        if (validateEmail(email) == false) {
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
                } else {
                    warningLabel1.setText("");
                }
                if(email.equals("")) {
                    warningLabel2.setText("please enter email");
                } else {
                    if (validateEmail(email) == false) {
                        warningLabel2.setText("Invalid email id!");
                    } else {
                        warningLabel2.setText("");
                    }
                }
                if(pass.equals("")) {
                    warningLabel4.setText("please enter password");
                } else {
                    warningLabel4.setText("");
                }
                if(confirmPass.equals("")) {
                    warningLabel3.setText("please enter confirm password");
                } else {
                    warningLabel3.setText("");
                }
            }

        });
    }
}

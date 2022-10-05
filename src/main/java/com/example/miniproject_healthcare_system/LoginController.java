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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginController {
    private Stage stage;
    private Scene scene;

    /*
    private Stage stage;
    private Scene scene;


    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BaseScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
    */

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button button;

    @FXML
    private Label warningLabel1;

    @FXML
    private Label warningLabel2;

    String usernameText, passwordText;

    @FXML
    void switchToUserRegistration(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("UserRegistration.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void onButtonClick(ActionEvent event) throws IOException {
        button.setOnAction(e -> {
            usernameText = username.getText();
            passwordText = password.getText();
            if(usernameText == "" && passwordText == "") {
                warningLabel1.setText("please enter username!");
                warningLabel2.setText("please enter password!");
            } else if(usernameText == ""){
                warningLabel1.setText("please enter username!");
                warningLabel2.setText("");
            } else if(passwordText == "") {
                warningLabel2.setText("please enter password!");
                warningLabel1.setText("");
            } else {
                warningLabel2.setText("");
                warningLabel1.setText("");
                // Check if username is in database
                // if username is there then check for password match
                // if password matches then allow access to dashboard

                try {
                    if(JavaDatabaseConnector.validateUser(usernameText) == 1) {
                        warningLabel1.setText("user not found!");
                        warningLabel2.setText("");
                    } else {
                        if(JavaDatabaseConnector.validatePassword(usernameText, passwordText) == 1) {
                            warningLabel1.setText("");
                            warningLabel2.setText("invalid username or password!");
                        } else {
                            System.out.println("Access Granted!");
                            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            scene = new Scene(fxmlLoader.load());
                            stage.setScene(scene);
                            stage.show();
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
    }
}

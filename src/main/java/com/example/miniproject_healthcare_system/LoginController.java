package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
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
    void onButtonClick() throws IOException {
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
                            warningLabel2.setText("invalid password!");
                        } else {
                            System.out.println("Access Granted!");
                        }
                    }
                } catch (Exception ex) {
                    System.out.println(ex);
                }

            }

        });
    }
}

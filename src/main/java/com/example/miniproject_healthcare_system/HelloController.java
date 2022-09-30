package com.example.miniproject_healthcare_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    /*
    private Stage stage;
    private Scene scene;

    public void switchToHome(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BaseScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("SignUp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLogin(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
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
            if(usernameText == "" && passwordText == ""){
                warningLabel1.setText("please enter username!");
                warningLabel2.setText("please enter password!");
            }
            if(usernameText == "" && passwordText != ""){
                warningLabel1.setText("please enter username!");
            }
            if(passwordText == "" && usernameText != ""){
                warningLabel2.setText("please enter password!");
            }
            if(usernameText!="" && passwordText!=""){
                System.out.println("username : " + usernameText);
                System.out.println("password : " + passwordText);
                warningLabel1.setText("");
                warningLabel2.setText("");

            }



        });
    }
}
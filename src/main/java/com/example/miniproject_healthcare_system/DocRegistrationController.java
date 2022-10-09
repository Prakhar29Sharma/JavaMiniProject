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

public class DocRegistrationController {

    @FXML
    private TextField city;

    @FXML
    private Label confirmationLabel;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField qualification;

    @FXML
    private TextField phno;

    @FXML
    private TextField specialization;

    @FXML
    private Button RegisterButton;

    private Stage stage;
    private Scene scene;

    public void switchToDoctors(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("doctors.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void onRegister(ActionEvent event) throws IOException {
        RegisterButton.setOnAction(e -> {
            String first = fname.getText();
            String last = lname.getText();
            String spec = specialization.getText();
            String qual = qualification.getText();
            String cityAd = city.getText();
            String phone = phno.getText();

            if(first!="" && last!="" && spec!="" && qual!="" && cityAd!="" && phone!="") {
                try {
                    JavaDatabaseConnector.insertDoctor(first, last, qual, spec, phone, cityAd);
                    confirmationLabel.setText("Added Doctor Successfully!");
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            } else {
                if(first == ""){

                } else {

                }
                if(last == "") {

                } else {

                }
                if(spec == "") {

                } else {

                }

                if(qual == "") {

                } else {

                }

                if(cityAd == "") {

                } else {

                }

                if(phone == "") {

                } else {

                }
            }


        });
    }


}

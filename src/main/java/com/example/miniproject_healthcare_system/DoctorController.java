package com.example.miniproject_healthcare_system;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    @FXML
    private TableColumn<doctors, String> city;

    @FXML
    private TableView doctorTable;

    @FXML
    private TableColumn<doctors, String> fname;

    @FXML
    private TableColumn<doctors, Integer> id;

    @FXML
    private TableColumn<doctors, String> lname;

    @FXML
    private TableColumn<doctors, String> phno;

    @FXML
    private TableColumn<doctors, String> qualification;

    @FXML
    private TableColumn<doctors, String> specialization;

    ObservableList<doctors> listM;

    public void initialize(URL url, ResourceBundle rb) {
        id.setCellValueFactory(new PropertyValueFactory<doctors, Integer>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<doctors, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<doctors, String>("lname"));
        phno.setCellValueFactory(new PropertyValueFactory<doctors, String>("phno"));
        qualification.setCellValueFactory(new PropertyValueFactory<doctors, String>("qualification"));
        specialization.setCellValueFactory(new PropertyValueFactory<doctors, String>("specialization"));
        city.setCellValueFactory(new PropertyValueFactory<doctors, String>("city"));


        listM = JavaDatabaseConnector.getDataDoctor();
        doctorTable.setItems(listM);

    }





    private Stage stage;
    private Scene scene;

    public void switchToLogin(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDash(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDoctors(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("doctors.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPatientRec(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pat_recs.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppointments(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("appointments.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSlots(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("slots.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

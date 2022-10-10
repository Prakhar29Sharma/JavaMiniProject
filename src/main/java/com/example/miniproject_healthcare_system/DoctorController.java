package com.example.miniproject_healthcare_system;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;
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

    @FXML
    Label dateLabel;
    @FXML
    Label timeLabel;
    @FXML
    Label userLabel;

    @FXML
    private Button searchButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField idField;

    @FXML
    private TextField searchID;

    @FXML
    private TextField searchSpec;

    Timeline timeline;

    ObservableList<doctors> listM = JavaDatabaseConnector.getDataDoctor();

    public void initialize(URL url, ResourceBundle rb) {
        try {
            time();
            userLabel.setText("Hello, " + DashboardController.getUsername());
        } catch (Exception e) {
            System.out.println(e);
        }

        id.setCellValueFactory(new PropertyValueFactory<doctors, Integer>("id"));
        fname.setCellValueFactory(new PropertyValueFactory<doctors, String>("fname"));
        lname.setCellValueFactory(new PropertyValueFactory<doctors, String>("lname"));
        phno.setCellValueFactory(new PropertyValueFactory<doctors, String>("phno"));
        qualification.setCellValueFactory(new PropertyValueFactory<doctors, String>("qualification"));
        specialization.setCellValueFactory(new PropertyValueFactory<doctors, String>("specialization"));
        city.setCellValueFactory(new PropertyValueFactory<doctors, String>("city"));

        doctorTable.setItems(listM);
    }


    public void time() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            LocalDateTime date1 = LocalDateTime.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String formatDate = date1.format(format);
            DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formatTime1 = date1.format(formatTime);
            dateLabel.setText(formatDate);
            timeLabel.setText(formatTime1);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void onSearch(ActionEvent event) {
        searchButton.setOnAction(e -> {
            int id = 0;

            if(searchID.getText().equals("")){
                System.out.println("search id field empty");
            } else {
                id = Integer.parseInt(searchID.getText());
            }

            String spec = searchSpec.getText();
            if(searchID.getText() == "" && spec == "") {
                listM = JavaDatabaseConnector.getDataDoctor();
                doctorTable.setItems(listM);
            } else {
                if(searchID.getText()!="" && spec == "") {
                    listM = JavaDatabaseConnector.getDoctorByID(id);
                    doctorTable.setItems(listM);
                } else if(searchID.getText() == "" && spec == "") {
                    listM = JavaDatabaseConnector.getDoctorBySpecialization(spec);
                    doctorTable.setItems(listM);
                } else {
                    listM = JavaDatabaseConnector.getDoctorBySpecialization(spec);
                    doctorTable.setItems(listM);
                }
            }
        });
    }

    public void onDelete(ActionEvent event) {
        removeButton.setOnAction(e -> {
            String id = idField.getText();
            if(id.equals("")) {
                System.out.println("id Field is empty!");
            } else {
                try {
                    JavaDatabaseConnector.deleteDoctor(id);
                    listM = JavaDatabaseConnector.getDataDoctor();
                    doctorTable.setItems(listM);
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        });
    }

    private Stage stage;
    private Scene scene;

    @FXML
    private Button addDoc;

    public void switchToLogin(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDocRegistration(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add_doc.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDash(ActionEvent event) throws IOException {
        timeline.stop();
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
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pat_recs.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAppointments(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("appointments.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToSlots(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("slots.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    public void switchToAddDoc(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add_doc.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}

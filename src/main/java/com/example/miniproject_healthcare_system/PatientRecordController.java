package com.example.miniproject_healthcare_system;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PatientRecordController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    private Button addPatientButton;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private TableColumn<patients, String> city;

    @FXML
    private Label dateLabel;

    @FXML
    private TableColumn<patients, String> email;

    @FXML
    private TableColumn<patients, String> gender;

    @FXML
    private TableColumn<patients, Integer> patientID;

    @FXML
    private TableColumn<patients, String> patientName;

    @FXML
    private TableView<patients> patientTable;

    @FXML
    private TableColumn<patients, String> phoneNum;

    @FXML
    private Button searchButton;

    @FXML
    private TextField searchField;

    @FXML
    private Label timeLabel;

    @FXML
    private Label userLabel;

    Timeline timeline;

    ObservableList<patients> listM = JavaDatabaseConnector.getDataPatients();


    public void switchToLogin(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
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
        timeline.stop();
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

    public void switchToPatientReg(ActionEvent event) throws IOException {
        timeline.stop();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("pat_reg.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            time();
            userLabel.setText("Hello, " + DashboardController.getUsername());
        } catch (Exception e) {
            System.out.println(e);
        }

        patientID.setCellValueFactory(new PropertyValueFactory<patients, Integer>("patient_id"));
        patientName.setCellValueFactory(new PropertyValueFactory<patients, String>("Name"));
        phoneNum.setCellValueFactory(new PropertyValueFactory<patients, String>("ph_no"));
        email.setCellValueFactory(new PropertyValueFactory<patients, String>("email"));
        city.setCellValueFactory(new PropertyValueFactory<patients, String>("city"));
        gender.setCellValueFactory(new PropertyValueFactory<patients, String>("gender"));

        patientTable.setItems(listM);

    }
}

package com.example.miniproject_healthcare_system;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SlotsController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML
    Label timeLabel;

    @FXML
    Label dateLabel;

    @FXML
    Label showDateLabel;

    @FXML
    Label showTimeLabel;


    @FXML
    Label userLabel;

    @FXML
    private ChoiceBox<String> doctorIDs;

    @FXML
    private ChoiceBox<String> timeSlots;

    @FXML
    private Label doctorName;

    @FXML
    private Button show;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button addSlotButton;

    Timeline timeline;

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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("slots.fxml"));
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


    public void setDoctorName() throws SQLException {
        if(doctorIDs.getValue().equals("")) {
            System.out.println("doctor id not specified");
        } else {
            int id = Integer.parseInt(doctorIDs.getValue());
            String name = JavaDatabaseConnector.getDoctorNameByID(id);
            doctorName.setText(name);
        }
    }

    public void setDateLabel() {
        LocalDate date = datePicker.getValue();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        showDateLabel.setText(date.format(dateTimeFormatter));
    }

    public void setTimeSlotLabel() {
        String timeSlot = timeSlots.getValue();
        showTimeLabel.setText(timeSlot);
    }

    /*
    public void setPatientName() throws SQLException {
        if(patientIDs.getValue().equals("")) {
            System.out.println("patient id not specified");
        } else {
            int id = Integer.parseInt(patientIDs.getValue());
            String name = JavaDatabaseConnector.getPatientNameByID(id);
            patientName.setText(name);
        }
    }
     */

    //patientIDs.setItems(JavaDatabaseConnector.getPatientIDs());


    public void onShowClick(ActionEvent event) {
        show.setOnAction(e -> {
            try {
                setDoctorName();
                setDateLabel();
                setTimeSlotLabel();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    public void onAddSlots(ActionEvent event) {
        addSlotButton.setOnAction(e -> {
            // insert query
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            time();
            doctorIDs.setItems(JavaDatabaseConnector.getDoctorIDs());
            timeSlots.setItems(JavaDatabaseConnector.getTimeSlots());
            userLabel.setText("Hello, " + DashboardController.getUsername());
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

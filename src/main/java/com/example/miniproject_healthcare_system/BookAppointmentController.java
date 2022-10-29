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
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BookAppointmentController implements Initializable {


    @FXML
    private TableView<timeslot> availSlotsTable;

    @FXML
    private Button bookAppointmentButton;

    @FXML
    private Label confirmationLabel;

    @FXML
    private TableColumn<timeslot, String> dateColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private TableColumn<timeslot, String> doctorIDColumn;

    @FXML
    private ChoiceBox<String> patientIDChoiceBox;

    @FXML
    private Label patientNameLabel;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Button showButton;

    @FXML
    private TableColumn<timeslot, Integer> slotID;

    @FXML
    private ChoiceBox<String> slotIDChoiceBox;

    @FXML
    private TableColumn<timeslot, String> timeColumn;

    @FXML
    private Label timeLabel;

    @FXML
    private Label userLabel;

    Timeline timeline;

    private Scene scene;
    private Stage stage;


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

    // shows patient name when show button is clicked
    public void show() throws SQLException {
        String patientName;
        int patientID = Integer.parseInt(patientIDChoiceBox.getValue());
        patientName = JavaDatabaseConnector.getPatientNameByID(patientID);
        patientNameLabel.setText(patientName);
    }

    // adds data to database when appointment is booked
    public void bookSlot(ActionEvent event) {
        bookAppointmentButton.setOnAction(e -> {
            String slotID = slotIDChoiceBox.getValue();
            String patientID = patientIDChoiceBox.getValue();
            String reason = reasonTextArea.getText();

            if(!slotID.equals("") && !patientID.equals("") && !reason.equals("")) {
                try {
                    JavaDatabaseConnector.insertAppointment(Integer.parseInt(slotID), Integer.parseInt(patientID), reason);
                    confirmationLabel.setText("Appointment Booked!");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
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

    ObservableList<timeslot> listM = JavaDatabaseConnector.getAvailableSlot();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        time();
        userLabel.setText("Hello, " + DashboardController.getUsername());
        try {
            slotIDChoiceBox.setItems(JavaDatabaseConnector.getSlotID());
            patientIDChoiceBox.setItems(JavaDatabaseConnector.getPatientIDs());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        slotID.setCellValueFactory(new PropertyValueFactory<timeslot, Integer>("slot_id"));
        doctorIDColumn.setCellValueFactory(new PropertyValueFactory<timeslot, String>("doctor_id"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<timeslot, String>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<timeslot, String>("time"));

        availSlotsTable.setItems(listM);
    }
}

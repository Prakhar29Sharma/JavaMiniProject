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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;


public class AppointmentsController implements Initializable {

    private Stage stage;
    private Scene scene;

    @FXML
    private TableColumn<appointment, Integer> AppIDColumn;

    @FXML
    private TableColumn<appointment, String> appointmentStatusColumn;

    @FXML
    private TableView<appointment> appointment_table;

    @FXML
    private TextField apptID;

    @FXML
    private TextField apptID2;

    @FXML
    private Button changeAppointmentStatusButton;

    @FXML
    private TableColumn<appointment, String> dateColumn;

    @FXML
    private Label dateLabel;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableColumn<appointment, String> doctorNameColumn;

    @FXML
    private TableColumn<appointment, String> patientNameColumn;

    @FXML
    private Button paymentButton;

    @FXML
    private TableColumn<appointment, String> paymentStatusColumn;

    @FXML
    private TableColumn<appointment, String> reasonColumn;

    @FXML
    private Button searchButton;

    @FXML
    private ChoiceBox<String> doctorCheckBox;

    @FXML
    private TableColumn<appointment, String> timeColumn;

    @FXML
    private Label timeLabel;

    @FXML
    private Label userLabel;


    Timeline timeline;


    public void onSearch(ActionEvent event) {
        LocalDate localDate = datePicker.getValue();
        String doctorId , date ;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = localDate.format(dateTimeFormatter);
        doctorId = doctorCheckBox.getValue();

        if(doctorId!=null && date.equals("")) {
            listM = JavaDatabaseConnector.getAppointmentDetailsByDoctorId(Integer.parseInt(doctorId));
            appointment_table.setItems(listM);
        }
        if(doctorId==null && date.equals("")) {
            listM = JavaDatabaseConnector.getAppointmentDetails();
            appointment_table.setItems(listM);
        }
        if(!date.equals("") && doctorId == null) {
            listM = JavaDatabaseConnector.getAppointmentDetailsByDate(date);
            appointment_table.setItems(listM);
        }


    }

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

    ObservableList<appointment> listM = JavaDatabaseConnector.getAppointmentDetails();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            time();
            userLabel.setText("Hello, " + DashboardController.getUsername());
            doctorCheckBox.setItems(JavaDatabaseConnector.getDoctorIDs());
            // int appointmentId, String doctorName, String patientName, String date, String time, String paymentStatus, String appointmentStatus, String reason
            AppIDColumn.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("appointmentId"));
            doctorNameColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("doctorName"));
            patientNameColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("patientName"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("date"));
            timeColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("time"));
            paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("paymentStatus"));
            appointmentStatusColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("appointmentStatus"));
            reasonColumn.setCellValueFactory(new PropertyValueFactory<appointment, String>("reason"));
            appointment_table.setItems(listM);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

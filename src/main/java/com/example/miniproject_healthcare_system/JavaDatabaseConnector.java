package com.example.miniproject_healthcare_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class JavaDatabaseConnector {
    /* Establishing Connection */
    static String url = "jdbc:mysql://localhost:3306/ams";
    static String user = "sqluser";
    static String dbpassword = "SQLuser123";
    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(url, user, dbpassword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /* database connection code ends here */

    /* validateUser(): checks in database if user already exist */
    /* helpful to avoid users with same username or maintains uniqueness of username */
    static int validateUser(String username) throws SQLException {

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from receptionist");
        while(resultSet.next()) {
            String user = resultSet.getString("username");
            if(username.equals(user)){
                return 0;
            }
        }
        return 1;
    }


    /* validatePassword(): */
    static int validatePassword(String username, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM receptionist;");
        while(resultSet.next()) {
            String user = resultSet.getString("username");
            if(username.equals(user)) {
                String pass = resultSet.getString("password");
                if(password.equals(pass)){
                    return 0;
                }
            }
        }
        return 1;
    }

    /* InsertUser() -> inserts user data into database */
    static void InsertUser(String username, String email, String password) throws SQLException {

        String query = "INSERT INTO `ams`.`receptionist` (`username`,`email`,`password`) VALUES ('" + username + "','" + email + "','" + password + "')";
        System.out.println("query : " + query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if (status!=0){
            System.out.println("Inserted User Data Successfully!");
        }
    }

    /* insertDoctor() -> inserted doctor into database */
    static void insertDoctor(String fname, String lname, String qualification, String specialization, String phno, String city) throws SQLException {
        String query = "INSERT INTO `ams`.`doctor` (`firstName`,`lastName`,`qualification`,`specialization`,`phone_no`,`city`,`active`) VALUES ('"+ fname + "', '" + lname + "', '" + qualification + "', '" + specialization + "', '" + phno +"', '" + city + "', " + 1 + ");";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Doctors Data Inserted Successfully!");
        }
    }

    /* insert patient details into database */
    static void insertPatient(String fname, String lname, String phone_num, String email, String city, String gender) throws SQLException {
        String query = "INSERT INTO `ams`.`patient` (`firstName`, `lastName`, `phone_no`, `email`, `city`, `gender`) VALUES ('"+fname+"', '"+ lname + "', '"+ phone_num + "', '" + email + "', '" + city + "','" + gender + "');";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Patient Data Inserted Successfully!");
        }

    }

    /* insert available slots into database */
    static void insertSlot(String time, String date, int doctor_id) throws SQLException {
        String query = "INSERT INTO `ams`.`available_slots` (`time`, `date`, `doctor_id`) VALUES ('" + time + "','" + date + "'," + doctor_id + ");";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Slot Added Successfully!");
        }
    }

    /* insert data into appointment table */
    static void insertAppointment(int slot_id, int patient_id, String reason) throws SQLException {
        String query = "SELECT available_slots.doc_schedule_id,available_slots.doctor_id, available_slots.time, available_slots.date FROM ams.available_slots WHERE available_slots.doc_schedule_id = " + slot_id + ";";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String doctorId = null, date = null, time = null;
        while(resultSet.next()) {
            doctorId = resultSet.getString("doctor_id");
            date = resultSet.getString("date");
            time = resultSet.getString("time");
        }

        String insertQuery = "INSERT INTO `ams`.`appointment` ( `doctor_id`, `patient_id`, `time`, `date`, `reason_for_appointment`) VALUES (" + Integer.parseInt(doctorId) + ", " + patient_id + ", '" + time + "', '" + date + "', '" + reason + "');";
        System.out.println(insertQuery);
        PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Inserted Appointment Data Successfully!");
        }

        String updateQuery = "UPDATE `ams`.`available_slots` SET `available_slots`.`appointment_status` = 1 WHERE `available_slots`.`doc_schedule_id` = "+ slot_id +";";
        System.out.println(updateQuery);
        PreparedStatement preparedStatement1 = connection.prepareStatement(updateQuery);
        int status1 = preparedStatement1.executeUpdate();
        if(status1!=0) {
            System.out.println("Made Booked Slot Unavailable!");
        }
    }

    /* deleteDoctor : method for removing a doctor from table view by setting active to 0 */

    static void deleteDoctor(String id) throws SQLException {
        int doc_id = Integer.parseInt(id);
        String query = "UPDATE `ams`.`doctor` SET `active` = 0 WHERE `doctor_id` = " + doc_id + ";";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Doctor Deleted Successfully!");
        }
    }

    /* getTotalDoc() : counts total active doctors present in database */

    public static String getTotalDoc() throws SQLException {
        String query = "SELECT COUNT(doctor_id) AS 'count' FROM ams.doctor WHERE active = 1;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String count = null;
        while(resultSet.next()) {
            count = resultSet.getString("count");
        }
        return count;
    }

    /* getAppointmentCount()  */
    public static String getAppointmentCount() throws SQLException {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(dateTimeFormatter);
        String query = "SELECT COUNT(appointment_id) AS 'count' FROM ams.appointment WHERE appointment.date = '" + date + "';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String count = null;
        while(resultSet.next()) {
            count = resultSet.getString("count");
        }
        return count;
    }

    /* getSlotsCount() */

    public static String getSlotsCount() throws SQLException {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = localDate.format(dateTimeFormatter);
        String query = "SELECT COUNT(doc_schedule_id) AS 'count' FROM ams.available_slots WHERE available_slots.date = '" + date + "';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String count = null;
        while(resultSet.next()) {
            count = resultSet.getString("count");
        }
        return count;
    }

    /* counts total patient present in database */
    public static String getTotalPatients() throws SQLException {
        String query = "SELECT COUNT(patient_id) AS 'count' FROM ams.patient;";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String count = null;
        while(resultSet.next()) {
            count = resultSet.getString("count");
        }
        return count;
    }

    /* get doctor details for the table view */

    public static ObservableList<doctors> getDataDoctor() {
        ObservableList<doctors> list = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT `doctor`.`doctor_id`, `doctor`.`firstName`, `doctor`.`lastName`, `doctor`.`qualification`, `doctor`.`specialization`, `doctor`.`phone_no`, `doctor`.`city` FROM `ams`.`doctor` WHERE `doctor`.`active` = 1;");
            while(rs.next()) {
                // int id, String fname, String lname, String qualification, String specialization, String phno, String city
                list.add(new doctors(Integer.parseInt(rs.getString("doctor_id")), rs.getString("firstName"), rs.getString("lastName"), rs.getString("qualification"), rs.getString("specialization"), rs.getString("phone_no"), rs.getString("city")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* get patient details for patient table view */
    public static ObservableList<patients> getDataPatients() {
        ObservableList<patients> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT `patient`.`patient_id`, `patient`.`firstName`, `patient`.`lastName`, `patient`.`phone_no`, `patient`.`email`, `patient`.`city`, `patient`.`gender` FROM `ams`.`patient`;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                //int patient_id;
                //String firstName, lastName,phone_o,email,city,cityArea,gender;
                list.add(new patients(Integer.parseInt(rs.getString("patient_id")), rs.getString("firstName") + " " + rs.getString("lastName"), rs.getString("phone_no"), rs.getString("email"), rs.getString("city"), "", rs.getString("gender")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* get booked appointment details from database */
    public static ObservableList<appointment> getAppointmentDetails() {
        ObservableList<appointment> list = FXCollections.observableArrayList();
        try {
            String query = "select appointment.appointment_id, doctor.firstName as dname, patient.firstName as pname, appointment.date, appointment.time, appointment.payment_status, appointment.appointment_status, appointment.reason_for_appointment from appointment, doctor, patient where doctor.doctor_id = appointment.doctor_id and patient.patient_id = appointment.patient_id;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                list.add(new appointment(Integer.parseInt(resultSet.getString("appointment_id")), resultSet.getString("dname"), resultSet.getString("pname"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getString("payment_status"), resultSet.getString("appointment_status"), resultSet.getString("reason_for_appointment")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /* get booked appointment details from database filtered upon date */
    public static ObservableList<appointment> getAppointmentDetailsByDate(String date) {
        ObservableList<appointment> list = FXCollections.observableArrayList();
        try {
            String query = "select appointment.appointment_id, doctor.firstName as dname, patient.firstName as pname, appointment.date, appointment.time, appointment.payment_status, appointment.appointment_status, appointment.reason_for_appointment from appointment, doctor, patient where doctor.doctor_id = appointment.doctor_id and patient.patient_id = appointment.patient_id and appointment.date = '"+ date +"';";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                list.add(new appointment(Integer.parseInt(resultSet.getString("appointment_id")), resultSet.getString("dname"), resultSet.getString("pname"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getString("payment_status"), resultSet.getString("appointment_status"), resultSet.getString("reason_for_appointment")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /* set payment status to done */
    public static void setPaymentStatus(int app_id) throws SQLException {
        String query = "UPDATE `ams`.`appointment` SET `appointment`.`payment_status` = \"DONE\" WHERE `appointment_id` = "+  app_id + ";";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Payment Status Changed Successfully!");
        }
    }

    /* setting appointment status to done */
    public static void setAppointmentStatus(int app_id) throws SQLException {
        String query = "UPDATE `ams`.`appointment` SET `appointment`.`appointment_status` = \"DONE\" WHERE `appointment_id` = "+  app_id + ";";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Appointment Status Changed Successfully!");
        }
    }

    /* get booked appointment details from database filtered upon doctor_id */
    public static ObservableList<appointment> getAppointmentDetailsByDoctorId(int doctor_id) {
        ObservableList<appointment> list = FXCollections.observableArrayList();
        try {
            String query = "select appointment.appointment_id, doctor.firstName as dname, patient.firstName as pname, appointment.date, appointment.time, appointment.payment_status, appointment.appointment_status, appointment.reason_for_appointment from appointment, doctor, patient where doctor.doctor_id = appointment.doctor_id and patient.patient_id = appointment.patient_id and appointment.doctor_id = "+ doctor_id +";";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()) {
                list.add(new appointment(Integer.parseInt(resultSet.getString("appointment_id")), resultSet.getString("dname"), resultSet.getString("pname"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getString("payment_status"), resultSet.getString("appointment_status"), resultSet.getString("reason_for_appointment")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


    /* return list of time slots from database */
    public static ObservableList<timeslot> getAvailableSlot() {
        ObservableList<timeslot> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT `available_slots`.`doc_schedule_id`,`available_slots`.`time`, `available_slots`.`date`, `available_slots`.`doctor_id` FROM `ams`.`available_slots` WHERE `available_slots`.`appointment_status` = 0;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                list.add(new timeslot(Integer.parseInt(rs.getString("doc_schedule_id")),rs.getString("date"), rs.getString("time"), Integer.parseInt(rs.getString("doctor_id"))));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }



    /* filters patient by patient id and used in table view search */
    public static ObservableList<patients> getDataPatientsByID(int id) {
        ObservableList<patients> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT `patient`.`patient_id`, `patient`.`firstName`, `patient`.`lastName`, `patient`.`phone_no`, `patient`.`email`, `patient`.`city`, `patient`.`gender` FROM `ams`.`patient` WHERE `patient`.`patient_id` = "+ id + " ;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                //int patient_id;
                //String firstName, lastName,phone_o,email,city,cityArea,gender;
                list.add(new patients(Integer.parseInt(rs.getString("patient_id")), rs.getString("firstName") + " " + rs.getString("lastName"), rs.getString("phone_no"), rs.getString("email"), rs.getString("city"), "", rs.getString("gender")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* filters patients by city and used in table view search */
    public static ObservableList<patients> getDataPatientsByCity(String city) {
        ObservableList<patients> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT `patient`.`patient_id`, `patient`.`firstName`, `patient`.`lastName`, `patient`.`phone_no`, `patient`.`email`, `patient`.`city`, `patient`.`gender` FROM `ams`.`patient` WHERE `patient`.`city` = '"+ city + "' ;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                //int patient_id;
                //String firstName, lastName,phone_o,email,city,cityArea,gender;
                list.add(new patients(Integer.parseInt(rs.getString("patient_id")), rs.getString("firstName") + " " + rs.getString("lastName"), rs.getString("phone_no"), rs.getString("email"), rs.getString("city"), "", rs.getString("gender")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* filters patient by gender and used in table view search */
    public static ObservableList<patients> getDataPatientsByGender(String gender) {
        ObservableList<patients> list = FXCollections.observableArrayList();
        try {
            String query = "SELECT `patient`.`patient_id`, `patient`.`firstName`, `patient`.`lastName`, `patient`.`phone_no`, `patient`.`email`, `patient`.`city`, `patient`.`gender` FROM `ams`.`patient` WHERE `patient`.`gender` = '"+ gender + "' ;";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()) {
                //int patient_id;
                //String firstName, lastName,phone_o,email,city,cityArea,gender;
                list.add(new patients(Integer.parseInt(rs.getString("patient_id")), rs.getString("firstName") + " " + rs.getString("lastName"), rs.getString("phone_no"), rs.getString("email"), rs.getString("city"), "", rs.getString("gender")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* filters doctors by specialization and used in table view search */
    public static ObservableList<doctors> getDoctorBySpecialization(String spec) {
        ObservableList<doctors> list = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT `doctor`.`doctor_id`, `doctor`.`firstName`, `doctor`.`lastName`, `doctor`.`qualification`, `doctor`.`specialization`, `doctor`.`phone_no`, `doctor`.`city` FROM `ams`.`doctor` WHERE `doctor`.`active` = 1 AND `doctor`.`specialization` = '" + spec + "';");
            while(rs.next()) {
                // int id, String fname, String lname, String qualification, String specialization, String phno, String city
                list.add(new doctors(Integer.parseInt(rs.getString("doctor_id")), rs.getString("firstName"), rs.getString("lastName"), rs.getString("qualification"), rs.getString("specialization"), rs.getString("phone_no"), rs.getString("city")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* filters doctors by id and used in table view search */
    public static ObservableList<doctors> getDoctorByID(int id) {
        ObservableList<doctors> list = FXCollections.observableArrayList();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT `doctor`.`doctor_id`, `doctor`.`firstName`, `doctor`.`lastName`, `doctor`.`qualification`, `doctor`.`specialization`, `doctor`.`phone_no`, `doctor`.`city` FROM `ams`.`doctor` WHERE `doctor`.`active` = 1 AND `doctor`.`doctor_id` = '"+id+"';");
            while(rs.next()) {
                // int id, String fname, String lname, String qualification, String specialization, String phno, String city
                list.add(new doctors(Integer.parseInt(rs.getString("doctor_id")), rs.getString("firstName"), rs.getString("lastName"), rs.getString("qualification"), rs.getString("specialization"), rs.getString("phone_no"), rs.getString("city")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    /* returns list of doctors id who are active (used for choice box) */
    public static ObservableList<String> getDoctorIDs() throws SQLException{
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `doctor`.`doctor_id` FROM `ams`.`doctor` WHERE `doctor`.`active` = 1;");
        ObservableList data = FXCollections.observableArrayList();
        while(resultSet.next()) {
            data.add(resultSet.getString("doctor_id"));
        }
        return data;
    }

    /* return list of slot id from available slots */
    public static ObservableList<String> getSlotID() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `available_slots`.`doc_schedule_id` FROM ams.available_slots WHERE appointment_status = 0;");
        ObservableList data = FXCollections.observableArrayList();
        while(resultSet.next()) {
            data.add(resultSet.getString("doc_schedule_id"));
        }
        return data;
    }

    /* returns list of patient id from patient table (used for choice box) */
    public static ObservableList<String> getPatientIDs() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `patient`.`patient_id` FROM `ams`.`patient`");
        ObservableList data = FXCollections.observableArrayList();
        while(resultSet.next()) {
            data.add(resultSet.getString("patient_id"));
        }
        return data;
    }

    /* returns list of possible time slots from database (used for choice box) */
    public static ObservableList<String> getTimeSlots() throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `time_slots`.`time_slot` FROM `ams`.`time_slots`;");
        ObservableList data = FXCollections.observableArrayList();
        while(resultSet.next()) {
            data.add(resultSet.getString("time_slot"));
        }
        return data;
    }

    /* gets doctor name by doctor id */
    public static String getDoctorNameByID(int id) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `doctor`.`firstName` FROM `ams`.`doctor` WHERE `doctor`.`active` = 1 AND `doctor`.`doctor_id` = " + id + ";");
        String name = "";
        while (resultSet.next()) {
            name = resultSet.getString("firstName");
        }
        return name;
    }

    /* gets patient name by patient id */
    public static String getPatientNameByID(int id) throws SQLException {
        ResultSet resultSet = connection.createStatement().executeQuery("SELECT `patient`.`firstName` FROM ams.patient WHERE `patient`.`patient_id` = "+ id +";");
        String name = "";
        while (resultSet.next()) {
            name = resultSet.getString("firstName");
        }
        return name;
    }


}

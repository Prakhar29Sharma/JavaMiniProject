package com.example.miniproject_healthcare_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

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

    static void InsertUser(String username, String email, String password) throws SQLException {

        String query = "INSERT INTO `ams`.`receptionist` (`username`,`email`,`password`) VALUES ('" + username + "','" + email + "','" + password + "')";
        System.out.println("query : " + query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if (status!=0){
            System.out.println("Inserted User Data Successfully!");
        }
    }

    static void insertDoctor(String fname, String lname, String qualification, String specialization, String phno, String city) throws SQLException {
        String query = "INSERT INTO `ams`.`doctor` (`firstName`,`lastName`,`qualification`,`specialization`,`phone_no`,`city`,`active`) VALUES ('"+ fname + "', '" + lname + "', '" + qualification + "', '" + specialization + "', '" + phno +"', '" + city + "', " + 1 + ");";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Doctors Data Inserted Successfully!");
        }
    }

    static void insertPatient(String fname, String lname, String phone_num, String email, String city,String cityArea, String gender) throws SQLException {
        String query = "INSERT INTO `ams`.`patient` (`firstName`, `lastName`, `phone_no`, `email`, `city`, `cityArea`, `gender`) VALUES ('"+fname+"', '"+ lname + "', '"+ phone_num + "', '" + email + "', '" + city + "', '"+ cityArea + "','" + gender + "');";
        System.out.println(query);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if(status!=0) {
            System.out.println("Patient Data Inserted Successfully!");
        }

    }

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


}

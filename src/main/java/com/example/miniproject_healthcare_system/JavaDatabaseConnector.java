package com.example.miniproject_healthcare_system;

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
        String query = "INSERT INTO `ams`.`receptionist` (`username`,`email`,`password`) VALUES (" + username + "," + email + "," + password + ");";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int status = preparedStatement.executeUpdate();
        if (status!=0){
            System.out.println("Inserted data!");
        }
    }
}

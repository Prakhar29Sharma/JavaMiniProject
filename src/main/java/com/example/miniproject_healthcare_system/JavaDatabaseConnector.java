package com.example.miniproject_healthcare_system;

import java.sql.*;

class JavaDatabaseConnector {
    /* Establishing Connection */
    static String url = "jdbc:mysql://localhost:3306/users";
    static String user = "root";
    static String dbpassword = "5492";
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
        ResultSet resultSet = statement.executeQuery("select * from user");
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
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user;");
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
}

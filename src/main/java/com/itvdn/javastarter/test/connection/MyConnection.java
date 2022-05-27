package com.itvdn.javastarter.test.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String url = "jdbc:mysql://localhost:3306/carsshop";
    private static final String userName = "root";
    private static final String password = "root";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

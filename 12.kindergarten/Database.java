package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements AutoCloseable {
    private Connection connection;

    public Database() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/kindergarten";
        String username = "root";
        String password = "123456789";
        connection = DriverManager.getConnection(url, username, password);
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}

package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Utility class to establish connections to the PostgreSQL database.
 */
public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/vet_clinic";
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "password"; 

    // testing function
    public static void main(String[] args) {
    try {
        getConnection();
        System.out.println("Success! Connected to PostgreSQL database.");
    } catch (Exception e) {
        System.out.println("Connection failed!");
        e.printStackTrace();
    }
}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Critical: Failed to connect to the PostgreSQL database.", e);
        }
    }
}


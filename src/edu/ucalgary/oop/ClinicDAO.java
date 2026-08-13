package edu.ucalgary.oop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object to pull summary telemetry data or initialize the systems.
 */
public class ClinicDAO {

    /**
     * Helper method to verify record counts across specific tables at startup.
     */
    public int getRecordCount(String tableName) {
        // Simple validation whitelist to protect database integrity
        if (!tableName.equals("staff") && !tableName.equals("owners") && 
            !tableName.equals("pets") && !tableName.equals("appointments")) {
            throw new IllegalArgumentException("Invalid table name specified.");
        }

        String sql = "SELECT COUNT(*) FROM " + tableName;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error: Failed to fetch record metrics from table: " + tableName, e);
        }
        return 0;
    }
}

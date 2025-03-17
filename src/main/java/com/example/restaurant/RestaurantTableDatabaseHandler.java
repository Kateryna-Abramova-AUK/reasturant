package com.example.restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantTableDatabaseHandler {
    private Connection connection;

    public RestaurantTableDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertRestaurantTable(int tableNumber, int seatingCapacity, String status) throws SQLException {
        String sql = "INSERT INTO RestaurantTable (TableNumber, SeatingCapacity, Status) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tableNumber);
            stmt.setInt(2, seatingCapacity);
            stmt.setString(3, status);
            stmt.executeUpdate();
        }
    }

    public void updateRestaurantTable(int tableID, int tableNumber, int seatingCapacity, String status) throws SQLException {
        String sql = "UPDATE RestaurantTable SET TableNumber = ?, SeatingCapacity = ?, Status = ? WHERE TableID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tableNumber);
            stmt.setInt(2, seatingCapacity);
            stmt.setString(3, status);
            stmt.setInt(4, tableID);
            stmt.executeUpdate();
        }
    }

    public void deleteRestaurantTable(int tableID) throws SQLException {
        String sql = "DELETE FROM RestaurantTable WHERE TableID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, tableID);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllRestaurantTables() throws SQLException {
        List<String> tables = new ArrayList<>();
        String sql = "SELECT * FROM RestaurantTable";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tables.add(rs.getInt("TableID") + ": Table " + rs.getInt("TableNumber") + " - " + rs.getString("Status"));
            }
        }
        return tables;
    }
}


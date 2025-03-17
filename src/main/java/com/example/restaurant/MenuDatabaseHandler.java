package com.example.restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDatabaseHandler {
    private Connection connection;

    public MenuDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertMenuItem(String item, String description, String category, double price) throws SQLException {
        String sql = "INSERT INTO Menu (Item, Description, Category, Price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setDouble(4, price);
            stmt.executeUpdate();
        }
    }

    public void updateMenuItem(int menuID, String item, String description, String category, double price) throws SQLException {
        String sql = "UPDATE Menu SET Item = ?, Description = ?, Category = ?, Price = ? WHERE MenuID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item);
            stmt.setString(2, description);
            stmt.setString(3, category);
            stmt.setDouble(4, price);
            stmt.setInt(5, menuID);
            stmt.executeUpdate();
        }
    }

    public void deleteMenuItem(int menuID) throws SQLException {
        String sql = "DELETE FROM Menu WHERE MenuID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuID);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllMenuItems() throws SQLException {
        List<String> menuItems = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                menuItems.add(rs.getInt("MenuID") + ": " + rs.getString("Item") + " - " + rs.getString("Category"));
            }
        }
        return menuItems;
    }
}


package com.example.restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerOrderDatabaseHandler {
    private Connection connection;

    public CustomerOrderDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertCustomerOrder(int customerID, int employeeID, int tableID, double totalAmount, String status, Date orderDate) throws SQLException {
        String sql = "INSERT INTO CustomerOrder (CustomerID, EmployeeID, TableID, TotalAmount, Status, OrderDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            stmt.setInt(2, employeeID);
            stmt.setInt(3, tableID);
            stmt.setDouble(4, totalAmount);
            stmt.setString(5, status);
            stmt.setDate(6, orderDate);
            stmt.executeUpdate();
        }
    }
    public void updateOrderStatus(int menuID, String item, String description, String category, double price) throws SQLException {
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

    public void deleteOrder(int menuID) throws SQLException {
        String sql = "DELETE FROM Menu WHERE MenuID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, menuID);
            stmt.executeUpdate();
        }
    }
    public List<String> getAllCustomerOrders() throws SQLException {
        List<String> orders = new ArrayList<>();
        String sql = "SELECT * FROM CustomerOrder";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(rs.getInt("OrderID") + ": Customer " + rs.getInt("CustomerID") + " - Table " + rs.getInt("TableID"));
            }
        }
        return orders;
    }
}


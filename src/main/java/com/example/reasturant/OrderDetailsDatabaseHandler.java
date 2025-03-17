package com.example.reasturant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDatabaseHandler {
    private Connection connection;

    public OrderDetailsDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertOrderDetails(int orderID, int menuID, double subtotal, int quantity) throws SQLException {
        String sql = "INSERT INTO OrderDetails (OrderID, MenuID, Subtotal, Quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderID);
            stmt.setInt(2, menuID);
            stmt.setDouble(3, subtotal);
            stmt.setInt(4, quantity);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllOrderDetails() throws SQLException {
        List<String> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetails";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orderDetails.add(rs.getInt("OrderID") + ": Menu " + rs.getInt("MenuID") + " - " + rs.getInt("Quantity"));
            }
        }
        return orderDetails;
    }


}


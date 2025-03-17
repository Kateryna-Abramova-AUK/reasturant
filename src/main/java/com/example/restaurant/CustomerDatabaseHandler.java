package com.example.restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDatabaseHandler {
    private Connection connection;

    public CustomerDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertCustomer(String email, String phoneNumber, String address, String firstName, String surname) throws SQLException {
        String sql = "INSERT INTO Customer (Email, PhoneNumber, Address, FirstName, Surname) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, address);
            stmt.setString(4, firstName);
            stmt.setString(5, surname);
            stmt.executeUpdate();
        }
    }

    public void updateCustomer(int customerID, String email, String phoneNumber, String address, String firstName, String surname) throws SQLException {
        String sql = "UPDATE Customer SET Email = ?, PhoneNumber = ?, Address = ?, FirstName = ?, Surname = ? WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, address);
            stmt.setString(4, firstName);
            stmt.setString(5, surname);
            stmt.setInt(6, customerID);
            stmt.executeUpdate();
        }
    }

    public void deleteCustomer(int customerID) throws SQLException {
        String sql = "DELETE FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            stmt.executeUpdate();
        }
    }

    public void getCustomerById(int customerID) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE CustomerID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Customer ID: " + rs.getInt("CustomerID"));
                System.out.println("Email: " + rs.getString("Email"));
                System.out.println("Phone: " + rs.getString("PhoneNumber"));
                System.out.println("Address: " + rs.getString("Address"));
                System.out.println("First Name: " + rs.getString("FirstName"));
                System.out.println("Surname: " + rs.getString("Surname"));
            }
        }
    }

    public List<String> getAllCustomers() throws SQLException {
        List<String> customers = new ArrayList<>();
        String sql = "SELECT * FROM Customer";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(rs.getInt("CustomerID") + ": " + rs.getString("FirstName") + " " + rs.getString("Surname"));
            }
        }
        return customers;
    }
}


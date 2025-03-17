package com.example.restaurant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDatabaseHandler {
    private Connection connection;

    public EmployeeDatabaseHandler(Connection connection) {
        this.connection = connection;
    }

    public void insertEmployee(String hireDate, String phoneNumber, String role, String fullName) throws SQLException {
        String sql = "INSERT INTO Employee (HireDate, PhoneNumber, Role, FullName) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hireDate);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, role);
            stmt.setString(4, fullName);
            stmt.executeUpdate();
        }
    }

    public void updateEmployee(int employeeID, String hireDate, String phoneNumber, String role, String fullName) throws SQLException {
        String sql = "UPDATE Employee SET HireDate = ?, PhoneNumber = ?, Role = ?, FullName = ? WHERE EmployeeID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, hireDate);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, role);
            stmt.setString(4, fullName);
            stmt.setInt(5, employeeID);
            stmt.executeUpdate();
        }
    }

    public void deleteEmployee(int employeeID) throws SQLException {
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.executeUpdate();
        }
    }

    public List<String> getAllEmployees() throws SQLException {
        List<String> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(rs.getInt("EmployeeID") + ": " + rs.getString("FullName") + " - " + rs.getString("Role"));
            }
        }
        return employees;
    }
}


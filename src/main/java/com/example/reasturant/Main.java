package com.example.reasturant;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main extends Application {
    CustomerOrderDatabaseHandler orderHandler;
    Connection connection;
    MenuDatabaseHandler menuHandler;
    CustomerDatabaseHandler customerHandler;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_project_abramova_ovcharenko_maltsev", "root", "D#bmsql2025");
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        menuHandler = new MenuDatabaseHandler(connection);
        customerHandler = new CustomerDatabaseHandler(connection);
        customerHandler = new CustomerDatabaseHandler(connection);

        Label customerLabel = new Label("Customer Management:");
        Label menuLabel = new Label("Menu Management:");
        Label orderLabel = new Label("Order Management:");
        Label employeeLabel = new Label("Employee Management:");
        Label tableLabel = new Label("Table Management:");

        String labelStyle = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 5px 0;";
        for (Label lbl : new Label[]{customerLabel, menuLabel, orderLabel, employeeLabel, tableLabel}) {
            lbl.setStyle(labelStyle);
        }

        Button addCustomerBtn = new Button("Add Customer");
        Button updateCustomerBtn = new Button("Update Customer");
        Button deleteCustomerBtn = new Button("Delete Customer");
        Button addMenuItemBtn = new Button("Add Menu Item");
        Button updateMenuItemBtn = new Button("Update Menu Item");
        Button deleteMenuItemBtn = new Button("Delete Menu Item");
        Button addOrderBtn = new Button("Add Order");
        Button updateOrderBtn = new Button("Update Order");
        Button deleteOrderBtn = new Button("Delete Order");
        Button addEmployeeBtn = new Button("Add Employee");
        Button updateEmployeeBtn = new Button("Update Employee");
        Button deleteEmployeeBtn = new Button("Delete Employee");
        Button addTableBtn = new Button("Add Table");
        Button updateTableBtn = new Button("Update");
        Button deleteTableBtn = new Button("Delete Table");
        Button viewMenuBtn = new Button("View Menu");

        addCustomerBtn.setOnAction(e -> addCustomer());
        updateCustomerBtn.setOnAction(e -> updateCustomer());
        deleteCustomerBtn.setOnAction(e -> deleteCustomer());
        addMenuItemBtn.setOnAction(e -> addMenuItem());
        updateMenuItemBtn.setOnAction(e -> updateMenuItem());
        deleteMenuItemBtn.setOnAction(e -> deleteMenuItem());
        addOrderBtn.setOnAction(e -> addOrder());
        updateOrderBtn.setOnAction(e -> updateOrder());
        deleteOrderBtn.setOnAction(e -> deleteOrder());
        addEmployeeBtn.setOnAction(e -> addEmployee());
        updateEmployeeBtn.setOnAction(e -> updateEmployee());
        deleteEmployeeBtn.setOnAction(e -> deleteEmployee());
        addTableBtn.setOnAction(e -> addTable());
        updateTableBtn.setOnAction(e -> updateTable());
        deleteTableBtn.setOnAction(e -> deleteTable());
        viewMenuBtn.setOnAction(e -> viewMenu());

        String buttonStyle = "-fx-padding: 8px 12px; -fx-border-radius: 5px; -fx-background-radius: 5px;";
        String hoverStyle = "-fx-padding: 8px 12px; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-background-color: #788894;";

        for (Button btn : new Button[]{addCustomerBtn, updateCustomerBtn, deleteCustomerBtn,
                addMenuItemBtn, updateMenuItemBtn, deleteMenuItemBtn,
                addOrderBtn, updateOrderBtn, deleteOrderBtn,
                addEmployeeBtn, updateEmployeeBtn, deleteEmployeeBtn,
                addTableBtn, updateTableBtn, deleteTableBtn, viewMenuBtn}) {
            btn.setStyle(buttonStyle);
            btn.setOnMouseEntered(e -> btn.setStyle(hoverStyle));
            btn.setOnMouseExited(e -> btn.setStyle(buttonStyle));
        }

        HBox customerBox = new HBox(15, addCustomerBtn, updateCustomerBtn, deleteCustomerBtn);
        HBox menuBox = new HBox(15, addMenuItemBtn, updateMenuItemBtn, deleteMenuItemBtn);
        HBox orderBox = new HBox(15, addOrderBtn, updateOrderBtn, deleteOrderBtn);
        HBox employeeBox = new HBox(15, addEmployeeBtn, updateEmployeeBtn, deleteEmployeeBtn);
        HBox tableBox = new HBox(15, addTableBtn, updateTableBtn, deleteTableBtn);

        customerBox.setAlignment(Pos.BASELINE_LEFT);
        menuBox.setAlignment(Pos.BASELINE_LEFT);
        orderBox.setAlignment(Pos.BASELINE_LEFT);
        employeeBox.setAlignment(Pos.BASELINE_LEFT);
        tableBox.setAlignment(Pos.BASELINE_LEFT);

        VBox customerSection = new VBox(5, customerLabel, customerBox);
        VBox menuSection = new VBox(5, menuLabel, menuBox);
        VBox orderSection = new VBox(5, orderLabel, orderBox);
        VBox employeeSection = new VBox(5, employeeLabel, employeeBox);
        VBox tableSection = new VBox(5, tableLabel, tableBox);

        VBox root = new VBox(15,viewMenuBtn, customerSection, menuSection, orderSection, employeeSection, tableSection);
        root.setStyle("-fx-padding: 15px; -fx-background-color: #f4f4f4;");
        root.setAlignment(Pos.TOP_LEFT);

        Scene scene = new Scene(root, 600, 550);
        primaryStage.setTitle("Restaurant Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    private void addCustomer() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");
        Button submit = new Button("Add Customer");
        submit.setOnAction(e -> {
            try {
                customerHandler.insertCustomer(emailField.getText(), phoneField.getText(), addressField.getText(), firstNameField.getText(), surnameField.getText());
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(emailField, phoneField, addressField, firstNameField, surnameField, submit);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void addMenuItem() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField itemField = new TextField();
        itemField.setPromptText("Item Name");
        TextField descField = new TextField();
        descField.setPromptText("Description");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        Button submit = new Button("Add Item");
        submit.setOnAction(e -> {
            try {
                menuHandler.insertMenuItem(itemField.getText(), descField.getText(), categoryField.getText(), Double.parseDouble(priceField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(itemField, descField, categoryField, priceField, submit);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }
    private void updateMenuItem() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField menuIDField = new TextField();
        menuIDField.setPromptText("Menu ID");
        TextField itemField = new TextField();
        itemField.setPromptText("Item Name");
        TextField descField = new TextField();
        descField.setPromptText("Description");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        Button submit = new Button("Update Item");
        submit.setOnAction(e -> {
            try {
                menuHandler.updateMenuItem(Integer.parseInt(menuIDField.getText()), itemField.getText(), descField.getText(), categoryField.getText(), Double.parseDouble(priceField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(menuIDField, itemField, descField, categoryField, priceField, submit);
        Scene scene = new Scene(layout, 300, 250);
        stage.setScene(scene);
        stage.show();
    }

    private void deleteMenuItem() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField menuIDField = new TextField();
        menuIDField.setPromptText("Menu ID");
        Button submit = new Button("Delete Item");
        submit.setOnAction(e -> {
            try {
                menuHandler.deleteMenuItem(Integer.parseInt(menuIDField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(menuIDField, submit);
        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void updateCustomer() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Customer ID");
        TextField emailField = new TextField();
        emailField.setPromptText("Email");
        TextField phoneField = new TextField();
        phoneField.setPromptText("Phone Number");
        TextField addressField = new TextField();
        addressField.setPromptText("Address");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        TextField surnameField = new TextField();
        surnameField.setPromptText("Surname");
        Button submit = new Button("Update Customer");
        submit.setOnAction(e -> {
            try {
                customerHandler.updateCustomer(
                        Integer.parseInt(customerIDField.getText()),
                        emailField.getText(),
                        phoneField.getText(),
                        addressField.getText(),
                        firstNameField.getText(),
                        surnameField.getText()
                );
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(customerIDField, emailField, phoneField, addressField, firstNameField, surnameField, submit);
        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void deleteCustomer() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Customer ID");
        Button submit = new Button("Delete Customer");
        submit.setOnAction(e -> {
            try {
                customerHandler.deleteCustomer(Integer.parseInt(customerIDField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(customerIDField, submit);
        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.show();
    }

    private void addOrder() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField customerIDField = new TextField();
        customerIDField.setPromptText("Customer ID");
        TextField employeeIDField = new TextField();
        employeeIDField.setPromptText("Employee ID");
        TextField tableIDField = new TextField();
        tableIDField.setPromptText("Table ID");
        TextField totalAmountField = new TextField();
        totalAmountField.setPromptText("Total Amount");
        TextField statusField = new TextField();
        statusField.setPromptText("Status");
        TextField orderDateField = new TextField();
        orderDateField.setPromptText("Order Date (YYYY-MM-DD)");
        Button submit = new Button("Add Order");
        submit.setOnAction(e -> {
            try {
                orderHandler.insertCustomerOrder(Integer.parseInt(customerIDField.getText()), Integer.parseInt(employeeIDField.getText()), Integer.parseInt(tableIDField.getText()), Double.parseDouble(totalAmountField.getText()), statusField.getText(), java.sql.Date.valueOf(orderDateField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(customerIDField, employeeIDField, tableIDField, totalAmountField, statusField, orderDateField, submit);
        Scene scene = new Scene(layout, 300, 350);
        stage.setScene(scene);
        stage.show();
    }

    private void updateOrder() {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField menuIDField = new TextField();
        menuIDField.setPromptText("Menu ID");
        TextField itemField = new TextField();
        itemField.setPromptText("Item");
        TextField descriptionField = new TextField();
        descriptionField.setPromptText("Description");
        TextField categoryField = new TextField();
        categoryField.setPromptText("Category");
        TextField priceField = new TextField();
        priceField.setPromptText("Price");
        Button submit = new Button("Update Order");
        submit.setOnAction(e -> {
            try {
                orderHandler.updateOrderStatus(Integer.parseInt(menuIDField.getText()), itemField.getText(), descriptionField.getText(), categoryField.getText(), Double.parseDouble(priceField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(menuIDField, itemField, descriptionField, categoryField, priceField, submit);
        Scene scene = new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.show();
    }

    private void deleteOrder () {
        Stage stage = new Stage();
        VBox layout = new VBox(10);
        TextField menuIDField = new TextField();
        menuIDField.setPromptText("Menu ID");
        Button submit = new Button("Delete Order");
        submit.setOnAction(e -> {
            try {
                orderHandler.deleteOrder(Integer.parseInt(menuIDField.getText()));
                stage.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        layout.getChildren().addAll(menuIDField, submit);
        Scene scene = new Scene(layout, 300, 150);
        stage.setScene(scene);
        stage.show();
        }
    private void deleteTable() {
    }

    private void updateTable() {

    }

    private void addTable() {

    }

    private void deleteEmployee() {

    }

    private void updateEmployee() {

    }

    private void addEmployee() {

    }
        private void viewMenu () {
            Stage stage = new Stage();
            VBox layout = new VBox(10);
            ListView<String> menuList = new ListView<>();
            try {
                menuList.getItems().addAll(menuHandler.getAllMenuItems());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            layout.getChildren().add(menuList);
            Scene scene = new Scene(layout, 300, 400);
            stage.setScene(scene);
            stage.show();
    }
}


package fr.esisar.px.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPanel extends JPanel {
	
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel statusLabel;

    public LoginPanel() {
        setLayout(new GridLayout(4, 1, 10, 10));

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        statusLabel = new JLabel("", JLabel.CENTER);

        add(new JLabel("Username:", JLabel.CENTER));
        add(usernameField);
        add(new JLabel("Password:", JLabel.CENTER));
        add(passwordField);
        add(loginButton);
        add(statusLabel);

        loginButton.addActionListener(e -> attemptLogin());
    }

    private void attemptLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (checkCredentials(username, password)) {
            statusLabel.setText("Login successful!");
            // Switch to GamePanel or main UI here
        } else {
            statusLabel.setText("Invalid username or password.");
        }
    }

    private boolean checkCredentials(String username, String password) {
        // Example with SQLite
        String url = "jdbc:sqlite:users.db"; // make sure this file exists and has a table
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password); // for production, use hashed passwords!
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // returns true if a row is found
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupingsystem;

/**
 *
 * @author Aarya
 */

import javax.swing.*;
import java.sql.*;

public abstract class User {
    protected String id; // PRN for Student, Mentor ID for Mentor
    protected String name;
    protected String email;
    protected String role;

    public User(String id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Common validation for login
    public boolean validateLoginFields() {
        return !id.isEmpty() && !email.isEmpty();
    }

    // Common validation for signup
    public boolean validateSignupFields() {
        return !id.isEmpty() && !name.isEmpty() && !email.isEmpty();
    }

    // Abstract method to get login query
    public abstract String getLoginQuery();

    // Abstract method to get signup query
    public abstract String getSignupQuery();

    // Abstract method to add role-specific UI components
    public abstract void addSpecificFields(JPanel panel, int yPosition);

    // Abstract method to validate role-specific fields
    public abstract boolean validateSpecificFields();

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    void updateFields(String name, String id, String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    String validateIdFormatAndUniqueness(Conn c) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

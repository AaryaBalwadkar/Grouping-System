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
import java.awt.*;
import java.sql.*;

public class Mentor extends User {
    private String field;
    private Choice cfield;

    public Mentor(String mentorId, String name, String email, String field) {
        super(mentorId, name, email, "mentor");
        this.field = field;
    }

    @Override
    public String getLoginQuery() {
        return "SELECT * FROM faculty WHERE email = '" + email + "' AND mentor_id = '" + id + "'";
    }

    @Override
    public String getSignupQuery() {
        int field_id = switch (field) {
            case "Computer Science" -> 1;
            case "Artificial Intelligence" -> 2;
            case "Cybersecurity" -> 3;
            default -> 4;
        };
        return "INSERT INTO faculty VALUES('" + id + "','" + field_id + "','" + name + "','" + email + "')";
    }

    @Override
    public void addSpecificFields(JPanel panel, int yPosition) {
        // Field
        JLabel lbfield = new JLabel("Field");
        lbfield.setBounds(100, yPosition - 20, 100, 25);
        lbfield.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lbfield);

        cfield = new Choice();
        cfield.setBounds(100, yPosition, 350, 40);
        cfield.setFont(new Font("Arial", Font.PLAIN, 20));
        cfield.add("Select Field");
        cfield.add("Computer Science");
        cfield.add("Artificial Intelligence");
        cfield.add("Cybersecurity");
        cfield.add("Web Development");
        panel.add(cfield);
    }

    @Override
    public boolean validateSpecificFields() {
        if (cfield == null) {
            return false;
        }
        field = cfield.getSelectedItem();
        return !field.equals("Select Field");
    }

    // Update fields from UI inputs
    public void updateFields(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Validate Mentor ID format and uniqueness
    @Override
    public String validateIdFormatAndUniqueness(Conn conn) throws SQLException {
        if (!id.matches("\\d{3}")) {
            return "Mentor ID must be a three-digit number (e.g., 101).";
        }
        try {
            int idNum = Integer.parseInt(id);
            if (idNum < 101) {
                return "Mentor ID must be 101 or greater.";
            }
        } catch (NumberFormatException e) {
            return "Mentor ID must be a valid number.";
        }
        String query = "SELECT COUNT(*) FROM faculty WHERE mentor_id = '" + id + "'";
        ResultSet rs = conn.s.executeQuery(query);
        if (rs.next() && rs.getInt(1) > 0) {
            return "Mentor ID '" + id + "' already exists.";
        }
        return null; // Valid ID
    }
}
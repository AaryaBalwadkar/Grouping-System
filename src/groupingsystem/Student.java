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

public class Student extends User {
    private String branch;
    private String gender;
    private String year;
    private Choice cbranch, cgender, cyear;

    public Student(String prn, String name, String email, String branch, String gender, String year) {
        super(prn, name, email, "student");
        this.branch = branch;
        this.gender = gender;
        this.year = year;
    }

    @Override
    public String getLoginQuery() {
        return "SELECT * FROM student WHERE email = '" + email + "' AND prn = '" + id + "'";
    }

    @Override
    public String getSignupQuery() {
        int yearInt = Integer.parseInt(year);
        return "INSERT INTO student VALUES('" + id + "','" + name + "','" + email + "','" + branch + "','" + gender + "','" + yearInt + "',NULL)";
    }

    @Override
    public void addSpecificFields(JPanel panel, int yPosition) {
        // Branch
        JLabel lbbranch = new JLabel("Branch");
        lbbranch.setBounds(100, yPosition - 20, 100, 25);
        lbbranch.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lbbranch);

        cbranch = new Choice();
        cbranch.setBounds(100, yPosition, 350, 40);
        cbranch.setFont(new Font("Arial", Font.PLAIN, 20));
        cbranch.add("Select Branch");
        cbranch.add("Computer");
        cbranch.add("IT");
        cbranch.add("Electronics");
        cbranch.add("Mechanical");
        panel.add(cbranch);
        yPosition += 60;

        // Gender
        JLabel lbgender = new JLabel("Gender");
        lbgender.setBounds(100, yPosition - 20, 100, 25);
        lbgender.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lbgender);

        cgender = new Choice();
        cgender.setBounds(100, yPosition, 350, 40);
        cgender.setFont(new Font("Arial", Font.PLAIN, 20));
        cgender.add("Select Gender");
        cgender.add("Male");
        cgender.add("Female");
        panel.add(cgender);
        yPosition += 60;

        // Year
        JLabel lbyear = new JLabel("Year");
        lbyear.setBounds(100, yPosition - 20, 100, 25);
        lbyear.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lbyear);

        cyear = new Choice();
        cyear.setBounds(100, yPosition, 350, 40);
        cyear.setFont(new Font("Arial", Font.PLAIN, 20));
        cyear.add("Select Year");
        cyear.add("1");
        cyear.add("2");
        cyear.add("3");
        cyear.add("4");
        panel.add(cyear);
    }

    @Override
    public boolean validateSpecificFields() {
        if (cbranch == null || cgender == null || cyear == null) {
            return false;
        }
        branch = cbranch.getSelectedItem();
        gender = cgender.getSelectedItem();
        year = cyear.getSelectedItem();
        return !branch.equals("Select Branch") && !gender.equals("Select Gender") && !year.equals("Select Year");
    }

    // Update fields from UI inputs
    public void updateFields(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Validate PRN format and uniqueness
    @Override
    public String validateIdFormatAndUniqueness(Conn conn) throws SQLException {
        if (!id.matches("PRN\\d{3}")) {
            return "PRN must be in the format 'PRN' followed by three digits (e.g., PRN001).";
        }
        String query = "SELECT COUNT(*) FROM student WHERE prn = '" + id + "'";
        ResultSet rs = conn.s.executeQuery(query);
        if (rs.next() && rs.getInt(1) > 0) {
            return "PRN '" + id + "' already exists.";
        }
        return null; // Valid ID
    }
}
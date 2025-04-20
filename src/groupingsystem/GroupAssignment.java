///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package groupingsystem;
//
///**
// *
// * @author Aarya
// */
//public class GroupAssignment {
//    
//}

package groupingsystem;

import java.awt.*;
import javax.swing.*;
import java.sql.*;

public class GroupAssignment extends JFrame {

    GroupAssignment(String prn, String name) {
        setSize(1200, 700);
        setLocation(200, 100);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Title
        JLabel lbTitle = new JLabel("Group Assignment Result");
        lbTitle.setBounds(400, 50, 400, 40);
        lbTitle.setFont(new Font("Arial", Font.BOLD, 35));
        add(lbTitle);

        // Student Info
        JLabel lbStudent = new JLabel("Student: " + name + " (" + prn + ")");
        lbStudent.setBounds(300, 150, 600, 30);
        lbStudent.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbStudent);

        // Perform group assignment
        String result = assignGroup(prn);

        // Result
        JTextArea taResult = new JTextArea(result);
        taResult.setBounds(300, 200, 600, 300);
        taResult.setFont(new Font("Arial", Font.PLAIN, 20));
        taResult.setEditable(false);
        taResult.setBackground(Color.WHITE);
        taResult.setLineWrap(true);
        taResult.setWrapStyleWord(true);
        add(taResult);

        // Close Button
        JButton btnClose = new JButton("Close");
        btnClose.setBounds(500, 550, 100, 40);
        btnClose.setBackground(new Color(255, 219, 187));
        btnClose.setBorder(BorderFactory.createEmptyBorder());
        btnClose.setFont(new Font("Arial", Font.BOLD, 20));
        btnClose.addActionListener(e -> dispose());
        add(btnClose);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private String assignGroup(String prn) {
        Connection conn = null;
        try {
            // Database connection
            String url = "jdbc:mysql://localhost:3306/groupingSystem";
            String user = "root";
            String password = "your_password"; // Replace with your MySQL password
            conn = DriverManager.getConnection(url, user, password);

            // Fetch quiz responses from Response table
            String responseSQL = "SELECT Ans1FieldOfInterest FROM Response WHERE PRN = ?";
            PreparedStatement psResponse = conn.prepareStatement(responseSQL);
            psResponse.setString(1, prn);
            ResultSet rs = psResponse.executeQuery();

            if (!rs.next()) {
                return "No quiz responses found for PRN: " + prn;
            }
            String ans1 = rs.getString("Ans1FieldOfInterest");

            // Map Ans1FieldOfInterest to field_id
            int fieldId;
            String viewName;
            switch (ans1) {
                case "A":
                    fieldId = 1; // Computer Science
                    viewName = "Unassigned_FieldA";
                    break;
                case "B":
                    fieldId = 2; // Artificial Intelligence
                    viewName = "Unassigned_FieldB";
                    break;
                case "C":
                    fieldId = 3; // Cybersecurity
                    viewName = "Unassigned_FieldC";
                    break;
                case "D":
                    fieldId = 4; // Web Development
                    viewName = "Unassigned_FieldD";
                    break;
                default:
                    return "Invalid field of interest: " + ans1;
            }

            // Check if there are at least 4 students in the view (including the new student)
            String countSQL = "SELECT COUNT(*) FROM " + viewName;
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(countSQL);
            rs.next();
            int studentCount = rs.getInt(1);

            if (studentCount < 4) {
                return "No group assigned so far. Waiting for more students with the same field of interest.";
            }

            // Find a mentor with fewer than 3 groups
            String mentorSQL = "SELECT fa.mentor_id, COUNT(g.group_id) AS group_count " +
                              "FROM Faculty fa " +
                              "LEFT JOIN Group_details g ON fa.mentor_id = g.mentor_id " +
                              "WHERE fa.field_id = ? " +
                              "GROUP BY fa.mentor_id " +
                              "HAVING group_count < 3 " +
                              "LIMIT 1";
            PreparedStatement psMentor = conn.prepareStatement(mentorSQL);
            psMentor.setInt(1, fieldId);
            rs = psMentor.executeQuery();

            if (!rs.next()) {
                return "No available mentors for this field. Please try again later.";
            }
            int mentorId = rs.getInt("mentor_id");

            // Create a new group
            int newGroupId = getNextGroupId(conn);
            String insertGroupSQL = "INSERT INTO Group_details (group_id, field_id, is_approved, mentor_id) VALUES (?, ?, 0, ?)";
            PreparedStatement psGroup = conn.prepareStatement(insertGroupSQL);
            psGroup.setInt(1, newGroupId);
            psGroup.setInt(2, fieldId);
            psGroup.setInt(3, mentorId);
            psGroup.executeUpdate();

            // Assign 4 students from the view to the new group
            String selectStudentsSQL = "SELECT PRN FROM " + viewName + " LIMIT 4";
            rs = stmt.executeQuery(selectStudentsSQL);
            while (rs.next()) {
                String studentPRN = rs.getString("PRN");
                String updateStudentSQL = "UPDATE Student SET group_id = ? WHERE PRN = ?";
                PreparedStatement psUpdate = conn.prepareStatement(updateStudentSQL);
                psUpdate.setInt(1, newGroupId);
                psUpdate.setString(2, studentPRN);
                psUpdate.executeUpdate();
            }

            // Return group assignment details
            String groupDetailsSQL = "SELECT g.group_id, f.field_name, fa.name AS mentor_name " +
                                    "FROM Group_details g " +
                                    "JOIN Field f ON g.field_id = f.field_id " +
                                    "JOIN Faculty fa ON g.mentor_id = fa.mentor_id " +
                                    "WHERE g.group_id = ?";
            PreparedStatement psGroupDetails = conn.prepareStatement(groupDetailsSQL);
            psGroupDetails.setInt(1, newGroupId);
            rs = psGroupDetails.executeQuery();
            if (rs.next()) {
                return "Assigned to Group ID: " + rs.getInt("group_id") + "\n" +
                       "Field: " + rs.getString("field_name") + "\n" +
                       "Mentor: " + rs.getString("mentor_name");
            }

            return "Group assigned, but details could not be retrieved.";
        } catch (SQLException ex) {
            ex.printStackTrace();
            return "Database error: " + ex.getMessage();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    private int getNextGroupId(Connection conn) throws SQLException {
        String sql = "SELECT MAX(group_id) FROM Group_details";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getInt(1) + 1;
        }
        return 101; // Start with 101 if no groups exist
    }

    public static void main(String[] args) {
        new GroupAssignment("PRN001", "Test Student");
    }
}
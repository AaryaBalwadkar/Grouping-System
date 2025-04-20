package groupingsystem;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

public class Quiz extends JFrame {

    JRadioButton radiobtnA, radiobtnB, radiobtnC, radiobtnD;
    JLabel lbA, lbB, lbC, lbD, imageLabel;
    JButton btnext, btprev;
    int currentQuestion = 0;
    String prn;
    String selectedRole;
    String[] answers; // Store answers for all questions

    // Data structure to store questions
    String[][] options = {
        {"Computer Science", "Artificial Intelligence", "Cybersecurity", "Web Development"},
        {"Watching videos or animations", "Listening to someone explain", "Reading books or guides", "Doing hands-on activities or projects"},
        {"Becoming a scientist or researcher", "Building apps, games, or websites", "Starting my own business", "Not sure yet (that's okay!)"},
        {"I like working in a team and helping others", "I prefer doing things on my own", "I enjoy both depending on the task", "I like having a mentor or guide to help me"},
        {"Less than 2 hours", "2–4 hours", "5–7 hours", "More than 7 hours"}
    };
    String[] imagePaths = {
        "images/Quiz1Image.png",
        "images/Quiz2Image.png",
        "images/Quiz3Image.png",
        "images/Quiz4Image.png",
        "images/Quiz5Image.png"
    };

    ButtonGroup buttonGroup;

    Quiz(String prn, String selectedRole) {
        this.prn = prn;
        this.selectedRole = selectedRole;
        this.answers = new String[options.length]; // Initialize answers array
        Arrays.fill(answers, null); // Set all answers to null initially

        setSize(1200, 600);
        setLocation(200, 100);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Right section (Image panel)
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 219, 187));
        p1.setBounds(600, 0, 600, 600);
        p1.setLayout(null);
        add(p1);

        // Image label
        imageLabel = new JLabel();
        imageLabel.setBounds(0, 0, 500, 500);
        p1.add(imageLabel);

        // Left section (Options panel)
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(0, 0, 600, 600);
        p2.setLayout(null);
        add(p2);

        // Radio buttons and labels
        radiobtnA = new JRadioButton();
        radiobtnA.setBounds(100, 100, 50, 50);
        radiobtnA.setBackground(null);
        p2.add(radiobtnA);

        lbA = new JLabel();
        lbA.setBounds(150, 100, 500, 50);
        lbA.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(lbA);

        radiobtnB = new JRadioButton();
        radiobtnB.setBounds(100, 170, 50, 50);
        radiobtnB.setBackground(null);
        p2.add(radiobtnB);

        lbB = new JLabel();
        lbB.setBounds(150, 170, 500, 50);
        lbB.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(lbB);

        radiobtnC = new JRadioButton();
        radiobtnC.setBounds(100, 240, 50, 50);
        radiobtnC.setBackground(null);
        p2.add(radiobtnC);

        lbC = new JLabel();
        lbC.setBounds(150, 240, 500, 50);
        lbC.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(lbC);

        radiobtnD = new JRadioButton();
        radiobtnD.setBounds(100, 310, 50, 50);
        radiobtnD.setBackground(null);
        p2.add(radiobtnD);

        lbD = new JLabel();
        lbD.setBounds(150, 310, 500, 50);
        lbD.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(lbD);

        // Button group to ensure single selection
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radiobtnA);
        buttonGroup.add(radiobtnB);
        buttonGroup.add(radiobtnC);
        buttonGroup.add(radiobtnD);

        // Next/Submit button
        btnext = new JButton("Next");
        btnext.setBounds(400, 450, 100, 40);
        btnext.setBackground(new Color(255, 219, 187));
        btnext.setBorder(BorderFactory.createEmptyBorder());
        btnext.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(btnext);

        // Previous button
        btprev = new JButton("Previous");
        btprev.setBounds(100, 450, 100, 40);
        btprev.setBackground(new Color(255, 219, 187));
        btprev.setBorder(BorderFactory.createEmptyBorder());
        btprev.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(btprev);

        // Load first question
        loadQuestion(currentQuestion);

        // Next/Submit button action
        btnext.addActionListener(e -> {
            // Save current answer
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer == null) {
                JOptionPane.showMessageDialog(this, "Please select an option before proceeding.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            answers[currentQuestion] = selectedAnswer;

            if (currentQuestion < options.length - 1) {
                // Move to next question
                currentQuestion++;
                loadQuestion(currentQuestion);
                buttonGroup.clearSelection();
            } else {
                // Submit quiz and save responses
                try {
                    saveResponses();
                    boolean groupCreated = tryFormGroup();
                    if (groupCreated) {
                        JOptionPane.showMessageDialog(this, "Quiz submitted and group assigned!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Quiz submitted. Waiting for more students to form a group.");
                    }
                    dispose();
                    new Login(selectedRole);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            updateButtonStates();
        });

        // Previous button action
        btprev.addActionListener(e -> {
            // Save current answer
            String selectedAnswer = getSelectedAnswer();
            if (selectedAnswer != null) {
                answers[currentQuestion] = selectedAnswer;
            }

            if (currentQuestion > 0) {
                currentQuestion--;
                loadQuestion(currentQuestion);
                buttonGroup.clearSelection();
                // Restore previous answer if available
                if (answers[currentQuestion] != null) {
                    switch (answers[currentQuestion]) {
                        case "A" -> radiobtnA.setSelected(true);
                        case "B" -> radiobtnB.setSelected(true);
                        case "C" -> radiobtnC.setSelected(true);
                        case "D" -> radiobtnD.setSelected(true);
                    }
                }
            }
            updateButtonStates();
        });

        // Initial button states
        updateButtonStates();
        setVisible(true);
    }

    private void loadQuestion(int index) {
        // Update options
        lbA.setText(options[index][0]);
        lbB.setText(options[index][1]);
        lbC.setText(options[index][2]);
        lbD.setText(options[index][3]);

        // Update image
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(imagePaths[index]));
            if (i1.getImage() == null) {
                throw new Exception("Image not found: " + imagePaths[index]);
            }
            Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            imageLabel.setIcon(i3);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage());
        }
    }

    private void updateButtonStates() {
        btprev.setEnabled(currentQuestion > 0);
        btnext.setText(currentQuestion == options.length - 1 ? "Submit" : "Next");
    }

    private String getSelectedAnswer() {
        if (radiobtnA.isSelected()) return "A";
        if (radiobtnB.isSelected()) return "B";
        if (radiobtnC.isSelected()) return "C";
        if (radiobtnD.isSelected()) return "D";
        return null;
    }

    private void saveResponses() throws SQLException {
        Conn conn = new Conn();
        conn.c.setAutoCommit(false); // Start transaction
        try {
            // Validate answers
            for (String answer : answers) {
                if (answer == null || !answer.matches("[A-D]")) {
                    throw new SQLException("Invalid quiz answer");
                }
            }
            
            String query = "INSERT INTO Response (Ans1FieldOfInterest, Ans2, Ans3, Ans4, Ans5, PRN) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.c.prepareStatement(query)) {
                ps.setString(1, answers[0]);
                ps.setString(2, answers[1]);
                ps.setString(3, answers[2]);
                ps.setString(4, answers[3]);
                ps.setString(5, answers[4]);
                ps.setString(6, prn);
                ps.executeUpdate();
            }
            
            conn.c.commit();
        } catch (SQLException e) {
            conn.c.rollback();
            throw e;
        }
    }

    private boolean tryFormGroup() throws SQLException {
        Conn conn = new Conn();
        conn.c.setAutoCommit(false); // Start transaction
        try {
            String ans1Field = answers[0];
            int field_id = switch (ans1Field) {
                case "A" -> 1;
                case "B" -> 2;
                case "C" -> 3;
                case "D" -> 4;
                default -> throw new IllegalArgumentException("Invalid field of interest");
            };
            
            // Get unassigned students for this field
            String viewName = switch (ans1Field) {
                case "A" -> "Unassigned_FieldA";
                case "B" -> "Unassigned_FieldB";
                case "C" -> "Unassigned_FieldC";
                case "D" -> "Unassigned_FieldD";
                default -> throw new IllegalArgumentException("Invalid field of interest");
            };
            
            String countQuery = "SELECT COUNT(*) FROM " + viewName;
            try (PreparedStatement ps = conn.c.prepareStatement(countQuery);
                 ResultSet rs = ps.executeQuery()) {
                rs.next();
                int studentCount = rs.getInt(1);
                if (studentCount < 4) {
                    conn.c.commit();
                    return false; // Not enough students
                }
            }
            
            // Get available mentor with fewer than 3 groups
            String mentorQuery = "SELECT f.mentor_id FROM Faculty f " +
                               "LEFT JOIN Group_details g ON f.mentor_id = g.mentor_id " +
                               "WHERE f.field_id = ? " +
                               "GROUP BY f.mentor_id " +
                               "HAVING COUNT(g.group_id) < 3 " +
                               "LIMIT 1";
            String mentorId = null;
            try (PreparedStatement ps = conn.c.prepareStatement(mentorQuery)) {
                ps.setInt(1, field_id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        mentorId = rs.getString("mentor_id");
                    }
                }
            }
            
            if (mentorId == null) {
                conn.c.commit();
                return false; // No available mentor
            }
            
            // Select 4 students, including the current one
            String studentQuery = "SELECT PRN FROM " + viewName + " WHERE PRN != ? LIMIT 3";
            String[] studentPRNs = new String[4];
            studentPRNs[0] = prn;
            int index = 1;
            try (PreparedStatement ps = conn.c.prepareStatement(studentQuery)) {
                ps.setString(1, prn);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next() && index < 4) {
                        studentPRNs[index++] = rs.getString("PRN");
                    }
                }
            }
            
            if (index < 4) {
                conn.c.commit();
                return false; // Shouldn't happen due to count check
            }
            
            // Create new group
            String groupQuery = "INSERT INTO Group_details (field_id, is_approved, mentor_id) VALUES (?, 0, ?)";
            int groupId;
            try (PreparedStatement ps = conn.c.prepareStatement(groupQuery, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, field_id);
                ps.setString(2, mentorId);
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        groupId = rs.getInt(1);
                    } else {
                        throw new SQLException("Failed to retrieve group_id");
                    }
                }
            }
            
            // Assign students to group
            String updateStudentQuery = "UPDATE Student SET group_id = ? WHERE PRN = ?";
            try (PreparedStatement ps = conn.c.prepareStatement(updateStudentQuery)) {
                for (String studentPRN : studentPRNs) {
                    ps.setInt(1, groupId);
                    ps.setString(2, studentPRN);
                    ps.executeUpdate();
                }
            }
            
            conn.c.commit();
            return true;
        } catch (SQLException e) {
            conn.c.rollback();
            throw e;
        }
    }

    public static void main(String[] args) {
        new Quiz("PRN001", "student");
    }
}
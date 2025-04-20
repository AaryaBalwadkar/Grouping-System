package groupingsystem;

import java.awt.*;
import javax.swing.*;

public class Quiz extends JFrame {

    JRadioButton radiobtnA, radiobtnB, radiobtnC, radiobtnD;
    JLabel lbA, lbB, lbC, lbD, imageLabel;
    JButton btnext, btprev;
    int currentQuestion = 0;

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
        "images/Quiz4Image.png"
    };

    ButtonGroup buttonGroup;

    Quiz() {
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
            if (currentQuestion < options.length - 1) {
                // Move to next question
                currentQuestion++;
                loadQuestion(currentQuestion);
                buttonGroup.clearSelection();
            } else {
                // Handle submit action
                JOptionPane.showMessageDialog(this, "Quiz submitted! Results will be displayed.");
                // Optionally, close the quiz or show results
                dispose();
            }
            updateButtonStates();
        });

        // Previous button action
        btprev.addActionListener(e -> {
            if (currentQuestion > 0) {
                currentQuestion--;
                loadQuestion(currentQuestion);
                buttonGroup.clearSelection();
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
        if (currentQuestion == options.length - 1) {
            btnext.setText("Submit");
        } else {
            btnext.setText("Next");
        }
    }

    public static void main(String[] args) {
        new Quiz();
    }
}
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
import java.awt.event.*;

public class LoginAs extends JFrame {
    JPanel studentPanel, teacherPanel;
    String selectedRole = "";

    public LoginAs() {
    setTitle("Choose Role");
    setSize(1200, 600);
    setLocation(200,100);
    setLayout(null); // Using absolute positioning

    studentPanel = createPanel("Student", "images/Hero.png");
    teacherPanel = createPanel("Mentor", "images/Hero.png");

    studentPanel.setBounds(0, 0, 600, 600);   // Left half
    teacherPanel.setBounds(600, 0, 600, 600); // Right half

    add(studentPanel);
    add(teacherPanel);

    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
}


    private JPanel createPanel(String role, String imagePath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JLabel label = new JLabel(role, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel imageLabel = new JLabel(new ImageIcon(imagePath));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(label, BorderLayout.NORTH);
        panel.add(imageLabel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        panel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selectedRole = role.toLowerCase();
                highlightPanel(panel);
                delayRedirect();
            }
        });

        return panel;
    }

    private void highlightPanel(JPanel panel) {
        // Reset borders
        studentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        teacherPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        // Highlight selected
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        panel.setBackground(Color.red);
    }

    private void delayRedirect() {
        Timer timer = new Timer(2000, e -> {
            new Login(selectedRole);
            dispose();
        });
        timer.setRepeats(false);
        timer.start();
    }

    public static void main(String[] args) {
        new LoginAs();
    }
}


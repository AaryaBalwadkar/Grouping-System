/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nfs://SystemFileSystem/Templates/Classes/Class.java to edit this template
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
        setLocation(200, 100);
        setLayout(null); // Using absolute positioning

        studentPanel = createPanel("Student", "images/StudentImage.png");
        teacherPanel = createPanel("Mentor", "images/MentorImage.png");

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

        // Load image using ClassLoader
//        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
//        if (imageIcon.getImage() == null) {
//            JOptionPane.showMessageDialog(this, "Image not found: " + imagePath, "Error", JOptionPane.ERROR_MESSAGE);
//            imageIcon = new ImageIcon(); // Fallback to empty icon
//        }
//        JLabel imageLabel = new JLabel(imageIcon);
//        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
//
//        panel.add(label, BorderLayout.NORTH);
//        panel.add(imageLabel, BorderLayout.CENTER);
//
//        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
//
//        panel.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                selectedRole = role.toLowerCase();
//                highlightPanel(panel);
//                delayRedirect();
//            }
//        });
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image i2 = i1.getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 0, 500, 500);
        panel.add(image);

        return panel;
    }

    private void highlightPanel(JPanel panel) {
        // Reset borders
        studentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        teacherPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        // Highlight selected
        panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
        panel.setBackground(Color.RED);
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


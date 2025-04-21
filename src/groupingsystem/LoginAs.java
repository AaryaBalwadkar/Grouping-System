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
        panel.setBackground(new Color(250, 240, 215));
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(role);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        label.setBounds(250, 20, 300, 50);
        panel.add(label);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(imagePath));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 0, 500, 500);
        panel.add(image);
        
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
        panel.setBackground(new Color(255, 219, 187));
    }

    private void delayRedirect() {
        Timer timer = new Timer(1000, e -> {
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


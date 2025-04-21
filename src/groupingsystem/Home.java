package groupingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame {
    private JPanel menuPanel;
    private JPanel contentPanel;
    private boolean isMenuVisible = true;

    public Home() {
        setTitle("Home");
        setSize(1200, 700);
        setLocation(200, 60);
        setLayout(new BorderLayout()); // use BorderLayout to manage regions

        // Sidebar Menu Panel
        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(new Color(255, 219, 187));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        // Add Menu Items
        JLabel menuTitle = new JLabel("Menu");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 24));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(menuTitle);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(new JButton("Dashboard"));
        menuPanel.add(new JButton("Profile"));
        menuPanel.add(new JButton("Settings"));

        // Main Content Panel
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Toggle Button
        JButton toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 20));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(new Color(255, 219, 187));
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.addActionListener(e -> toggleMenu());

        // Top Bar with Toggle Button
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(toggleButton);
        topBar.setBackground(new Color(255, 219, 187));
        contentPanel.add(topBar, BorderLayout.NORTH);

        // Add Panels to Frame
        add(menuPanel, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void toggleMenu() {
        if (isMenuVisible) {
            remove(menuPanel);
        } else {
            add(menuPanel, BorderLayout.WEST);
        }
        isMenuVisible = !isMenuVisible;
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Home::new);
    }
}


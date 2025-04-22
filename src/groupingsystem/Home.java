package groupingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame {
    private JPanel menuPanel;
    private JPanel contentPanel;
    private boolean isMenuVisible = true;
    private String prn; // Store the student's PRN

    public Home(String prn) {
        this.prn = prn;
        setTitle("Home");
        setSize(1200, 500);
        setLocation(200, 60);
        setLayout(new BorderLayout());

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

        // Updated Buttons
        JButton profileBtn = new JButton("Profile");
        JButton groupBtn = new JButton("Your Group");
        JButton projectsBtn = new JButton("Projects");

        // Style buttons
        JButton[] buttons = {profileBtn, groupBtn, projectsBtn};
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(160, 40));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(255, 239, 213));
            menuPanel.add(Box.createVerticalStrut(10));
            menuPanel.add(btn);
        }

        // Main Content Panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        // Unicode Hamburger Toggle Button ("≡")
        JButton toggleButton = new JButton("≡");
        toggleButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(new Color(255, 219, 187));
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.addActionListener(e -> toggleMenu());

        // Top Bar
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(toggleButton);
        topBar.setBackground(new Color(255, 219, 187));
        contentPanel.add(topBar, BorderLayout.NORTH);

        // Default Content
        showContent("Welcome! Please select an option.");

        // Button Actions
        profileBtn.addActionListener(e -> showStudentProfile(prn));
        groupBtn.addActionListener(e -> {
            StudentDAO dao = new StudentDAO();
            String[] student = dao.getStudentByPRN(prn);
            if (student != null && student[6] != null) {
                showGroupDetails(Integer.parseInt(student[6]));
            } else {
                JOptionPane.showMessageDialog(this, "You are not assigned to a group.");
            }
        });
        projectsBtn.addActionListener(e -> {
            StudentDAO dao = new StudentDAO();
            String[] student = dao.getStudentByPRN(prn);
            if (student != null && student[6] != null) {
                showProjectDetails(Integer.parseInt(student[6]));
            } else {
                JOptionPane.showMessageDialog(this, "No projects available. Join a group first.");
            }
        });

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

    private void showContent(String text) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridBagLayout());

        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 24));
        panel.add(label);

        // Clear old content (except top bar)
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
                contentPanel.remove(comp);
            }
        }

        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showStudentProfile(String prn) {
        StudentDAO studentDAO = new StudentDAO();
        String[] student = studentDAO.getStudentByPRN(prn);

        if (student == null) {
            JOptionPane.showMessageDialog(this, "Student not found.");
            return;
        }

        String[] columns = {"PRN", "Name", "Email", "Branch", "Gender", "Year", "Group ID"};
        String[][] rowData = {student};

        JTable table = new JTable(rowData, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Clear old content (except top bar)
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
                contentPanel.remove(comp);
            }
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
    
    private void showGroupDetails(int groupId) {
        StudentDAO studentDAO = new StudentDAO();
        String[][] groupData = studentDAO.getGroupAndStudentDetails(groupId);

        if (groupData == null || groupData.length == 0) {
            JOptionPane.showMessageDialog(this, "No group or students found.");
            return;
        }

        String[] columns = {"Student Name", "Student PRN", "Group ID", "Field ID", "Approved", "Mentor ID"};
        JTable table = new JTable(groupData, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Clear old content (except top bar)
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
                contentPanel.remove(comp);
            }
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void showProjectDetails(int groupId) {
        StudentDAO dao = new StudentDAO();
        String[][] projectData = dao.getProjectsByGroupId(groupId);

        if (projectData == null || projectData.length == 0) {
            JOptionPane.showMessageDialog(this, "No projects found for this group.");
            return;
        }

        String[] columns = {"Project ID", "Field ID", "Project Title", "Level", "Group ID"};
        JTable table = new JTable(projectData, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        // Clear old content (except top bar)
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
                contentPanel.remove(comp);
            }
        }

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home("PRN004")); // Example PRN for testing
    }
}





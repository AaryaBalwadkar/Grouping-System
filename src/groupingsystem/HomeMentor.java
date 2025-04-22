//package groupingsystem;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//
//public class HomeMentor extends JFrame {
//    private JPanel menuPanel;
//    private JPanel contentPanel;
//    private boolean isMenuVisible = true;
//
//    public HomeMentor() {
//        setTitle("Home");
//        setSize(1200, 500);
//        setLocation(200, 60);
//        setLayout(new BorderLayout());
//
//        menuPanel = new JPanel();
//        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
//        menuPanel.setBackground(new Color(255, 219, 187));
//        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
//
//        JLabel menuTitle = new JLabel("Menu");
//        menuTitle.setFont(new Font("Arial", Font.BOLD, 24));
//        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
//        menuPanel.add(Box.createVerticalStrut(20));
//        menuPanel.add(menuTitle);
//        menuPanel.add(Box.createVerticalStrut(20));
//
//        JButton profileBtn = new JButton("Profile");
//        JButton groupBtn = new JButton("Groups Under You");
//
//        JButton[] buttons = {profileBtn, groupBtn};
//        for (JButton btn : buttons) {
//            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
//            btn.setMaximumSize(new Dimension(160, 40));
//            btn.setFocusPainted(false);
//            btn.setBackground(new Color(255, 239, 213));
//            menuPanel.add(Box.createVerticalStrut(10));
//            menuPanel.add(btn);
//        }
//
//        contentPanel = new JPanel(new BorderLayout());
//        contentPanel.setBackground(Color.WHITE);
//
//        JButton toggleButton = new JButton("≡");
//        toggleButton.setFont(new Font("SansSerif", Font.BOLD, 24));
//        toggleButton.setFocusPainted(false);
//        toggleButton.setBackground(new Color(255, 219, 187));
//        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
//        toggleButton.addActionListener(e -> toggleMenu());
//
//        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        topBar.add(toggleButton);
//        topBar.setBackground(new Color(255, 219, 187));
//        contentPanel.add(topBar, BorderLayout.NORTH);
//
//        showContent("Welcome! Please select an option.");
//
//        profileBtn.addActionListener(e -> showMentorDetails(201));
//        groupBtn.addActionListener(e -> showGroupDetails(201)); // MODIFIED
//
//        add(menuPanel, BorderLayout.WEST);
//        add(contentPanel, BorderLayout.CENTER);
//
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private void toggleMenu() {
//        if (isMenuVisible) {
//            remove(menuPanel);
//        } else {
//            add(menuPanel, BorderLayout.WEST);
//        }
//        isMenuVisible = !isMenuVisible;
//        revalidate();
//        repaint();
//    }
//
//    private void showContent(String text) {
//        JPanel panel = new JPanel();
//        panel.setBackground(Color.WHITE);
//        panel.setLayout(new GridBagLayout());
//
//        JLabel label = new JLabel(text);
//        label.setFont(new Font("Arial", Font.PLAIN, 24));
//        panel.add(label);
//
//        Component[] components = contentPanel.getComponents();
//        for (Component comp : components) {
//            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
//                contentPanel.remove(comp);
//            }
//        }
//
//        contentPanel.add(panel, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }
//
//    private void showMentorDetails(int mentorId) {
//        StudentDAO dao = new StudentDAO();
//        String[][] mentorData = dao.getMentorById(mentorId);
//
//        if (mentorData == null || mentorData.length == 0) {
//            JOptionPane.showMessageDialog(this, "No mentor found for this ID.");
//            return;
//        }
//
//        String[] columns = {"Mentor ID", "Field ID", "Name", "Email"};
//        JTable table = new JTable(mentorData, columns);
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//
//        Component[] components = contentPanel.getComponents();
//        for (Component comp : components) {
//            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
//                contentPanel.remove(comp);
//            }
//        }
//
//        contentPanel.add(scrollPane, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//    }
//
//    private void showGroupDetails(int mentorId) {
//        StudentDAO dao = new StudentDAO();
//        String[][] groupData = dao.getGroupsByMentorId(mentorId);
//
//        if (groupData == null || groupData.length == 0) {
//            JOptionPane.showMessageDialog(this, "No groups found for this mentor.");
//            return;
//        }
//
//        String[] columns = {"Group ID", "Mentor ID", "Group Name", "Field ID"};
//        JTable table = new JTable(groupData, columns);
//        JScrollPane scrollPane = new JScrollPane(table);
//        table.setFillsViewportHeight(true);
//
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.WHITE);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//
//        // 💡 Create side-by-side input panel using GridLayout
//        JPanel inputWrapper = new JPanel(new GridLayout(1, 2, 10, 10));
//        inputWrapper.setBackground(new Color(255, 239, 213));
//        inputWrapper.setBorder(BorderFactory.createTitledBorder("Manage Groups"));
//
//        // ➕ Update Group Panel
//        JPanel updatePanel = new JPanel();
//        updatePanel.setBackground(new Color(255, 239, 213));
//        JTextField groupIdField = new JTextField(5);
//        JTextField fieldIdField = new JTextField(5);
//        JButton updateButton = new JButton("Update Field ID");
//
//        updatePanel.add(new JLabel("Group ID:"));
//        updatePanel.add(groupIdField);
//        updatePanel.add(new JLabel("New Field ID:"));
//        updatePanel.add(fieldIdField);
//        updatePanel.add(updateButton);
//
//        // ❌ Delete Group Panel
//        JPanel deletePanel = new JPanel();
//        deletePanel.setBackground(new Color(255, 239, 213));
//        JTextField deleteGroupIdField = new JTextField(5);
//        JButton deleteButton = new JButton("Delete Group");
//
//        deletePanel.add(new JLabel("Group ID:"));
//        deletePanel.add(deleteGroupIdField);
//        deletePanel.add(deleteButton);
//
//        inputWrapper.add(updatePanel);
//        inputWrapper.add(deletePanel);
//
//        // Place inputWrapper ABOVE the table
//        mainPanel.add(inputWrapper, BorderLayout.NORTH);
//
//        // Replace content panel content
//        Component[] components = contentPanel.getComponents();
//        for (Component comp : components) {
//            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
//                contentPanel.remove(comp);
//            }
//        }
//
//        contentPanel.add(mainPanel, BorderLayout.CENTER);
//        contentPanel.revalidate();
//        contentPanel.repaint();
//
//        // 🔁 Update Button Logic
//        updateButton.addActionListener(e -> {
//            try {
//                int groupId = Integer.parseInt(groupIdField.getText());
//                int newFieldId = Integer.parseInt(fieldIdField.getText());
//
//                boolean success = dao.updateGroupFieldId(groupId, newFieldId);
//                if (success) {
//                    JOptionPane.showMessageDialog(this, "Group updated successfully!");
//                    showGroupDetails(mentorId);
//                } else {
//                    JOptionPane.showMessageDialog(this, "Update failed. Check the Group ID.");
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Please enter valid integers.");
//            }
//        });
//
//        // 🗑 Delete Button Logic
//        deleteButton.addActionListener(e -> {
//            try {
//                int groupIdToDelete = Integer.parseInt(deleteGroupIdField.getText());
//
//                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Group ID " + groupIdToDelete + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
//                if (confirm == JOptionPane.YES_OPTION) {
//                    boolean success = dao.deleteGroupById(groupIdToDelete);
//                    if (success) {
//                        JOptionPane.showMessageDialog(this, "Group deleted successfully.");
//                        showGroupDetails(mentorId); // Refresh table
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Delete failed. Group ID may not exist.");
//                    }
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Enter a valid Group ID.");
//            }
//        });
//    }
//
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(HomeMentor::new);
//    }
//}

package groupingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomeMentor extends JFrame {
    private JPanel menuPanel;
    private JPanel contentPanel;
    private boolean isMenuVisible = true;
    private int mentorId; // Store the mentor's ID

    public HomeMentor(int mentorId) {
        this.mentorId = mentorId;
        setTitle("Home");
        setSize(1200, 500);
        setLocation(200, 60);
        setLayout(new BorderLayout());

        menuPanel = new JPanel();
        menuPanel.setPreferredSize(new Dimension(200, getHeight()));
        menuPanel.setBackground(new Color(255, 219, 187));
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        JLabel menuTitle = new JLabel("Menu");
        menuTitle.setFont(new Font("Arial", Font.BOLD, 24));
        menuTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuPanel.add(Box.createVerticalStrut(20));
        menuPanel.add(menuTitle);
        menuPanel.add(Box.createVerticalStrut(20));

        JButton profileBtn = new JButton("Profile");
        JButton groupBtn = new JButton("Groups Under You");

        JButton[] buttons = {profileBtn, groupBtn};
        for (JButton btn : buttons) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(160, 40));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(255, 239, 213));
            menuPanel.add(Box.createVerticalStrut(10));
            menuPanel.add(btn);
        }

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JButton toggleButton = new JButton("≡");
        toggleButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        toggleButton.setFocusPainted(false);
        toggleButton.setBackground(new Color(255, 219, 187));
        toggleButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        toggleButton.addActionListener(e -> toggleMenu());

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.add(toggleButton);
        topBar.setBackground(new Color(255, 219, 187));
        contentPanel.add(topBar, BorderLayout.NORTH);

        showContent("Welcome! Please select an option.");

        profileBtn.addActionListener(e -> showMentorDetails(mentorId));
        groupBtn.addActionListener(e -> showGroupDetails(mentorId));

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

    private void showMentorDetails(int mentorId) {
        StudentDAO dao = new StudentDAO();
        String[][] mentorData = dao.getMentorById(mentorId);

        if (mentorData == null || mentorData.length == 0) {
            JOptionPane.showMessageDialog(this, "No mentor found for this ID.");
            return;
        }

        String[] columns = {"Mentor ID", "Field ID", "Name", "Email"};
        JTable table = new JTable(mentorData, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

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

    private void showGroupDetails(int mentorId) {
        StudentDAO dao = new StudentDAO();
        String[][] groupData = dao.getGroupsByMentorId(mentorId);

        if (groupData == null || groupData.length == 0) {
            JOptionPane.showMessageDialog(this, "No groups found for this mentor.");
            return;
        }

        // Updated column names to match Group_details table columns
        String[] columns = {"Group ID", "Field ID", "Is Approved", "Mentor ID"};
        JTable table = new JTable(groupData, columns);
        table.setFillsViewportHeight(true);
        // Set column widths for better readability
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Group ID
        table.getColumnModel().getColumn(1).setPreferredWidth(100); // Field ID
        table.getColumnModel().getColumn(2).setPreferredWidth(100); // Is Approved
        table.getColumnModel().getColumn(3).setPreferredWidth(100); // Mentor ID
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create side-by-side input panel using GridLayout
        JPanel inputWrapper = new JPanel(new GridLayout(1, 2, 10, 10));
        inputWrapper.setBackground(new Color(255, 239, 213));
        inputWrapper.setBorder(BorderFactory.createTitledBorder("Manage Groups"));

        // Update Group Panel
        JPanel updatePanel = new JPanel();
        updatePanel.setBackground(new Color(255, 239, 213));
        JTextField groupIdField = new JTextField(5);
        JTextField fieldIdField = new JTextField(5);
        JButton updateButton = new JButton("Update Field ID");

        updatePanel.add(new JLabel("Group ID:"));
        updatePanel.add(groupIdField);
        updatePanel.add(new JLabel("New Field ID:"));
        updatePanel.add(fieldIdField);
        updatePanel.add(updateButton);

        // Delete Group Panel
        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(new Color(255, 239, 213));
        JTextField deleteGroupIdField = new JTextField(5);
        JButton deleteButton = new JButton("Delete Group");

        deletePanel.add(new JLabel("Group ID:"));
        deletePanel.add(deleteGroupIdField);
        deletePanel.add(deleteButton);

        inputWrapper.add(updatePanel);
        inputWrapper.add(deletePanel);

        // Place inputWrapper ABOVE the table
        mainPanel.add(inputWrapper, BorderLayout.NORTH);

        // Replace content panel content
        Component[] components = contentPanel.getComponents();
        for (Component comp : components) {
            if (!(comp instanceof JPanel && ((JPanel) comp).getLayout() instanceof FlowLayout)) {
                contentPanel.remove(comp);
            }
        }

        contentPanel.add(mainPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();

        // Update Button Logic
        updateButton.addActionListener(e -> {
            try {
                int groupId = Integer.parseInt(groupIdField.getText());
                int newFieldId = Integer.parseInt(fieldIdField.getText());

                boolean success = dao.updateGroupFieldId(groupId, newFieldId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Group updated successfully!");
                    showGroupDetails(mentorId);
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed. Check the Group ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers.");
            }
        });

        // Delete Button Logic
        deleteButton.addActionListener(e -> {
            try {
                int groupIdToDelete = Integer.parseInt(deleteGroupIdField.getText());

                int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete Group ID " + groupIdToDelete + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = dao.deleteGroupById(groupIdToDelete);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Group deleted successfully.");
                        showGroupDetails(mentorId); // Refresh table
                    } else {
                        JOptionPane.showMessageDialog(this, "Delete failed. Group ID may not exist or has dependencies.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid Group ID.");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeMentor(201)); // Example mentorId for testing
    }
}
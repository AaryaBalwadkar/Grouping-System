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
//    private int mentorId;
//
//    public HomeMentor(int mentorId) {
//        this.mentorId = mentorId;
//        setTitle("Home");
//        setSize(1200, 500);
//        setLocation(200, 60);
//        setLayout(new BorderLayout());
//
//        // Menu Panel
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
//        // Content Panel
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
//        profileBtn.addActionListener(e -> showMentorDetails(mentorId));
//        groupBtn.addActionListener(e -> showGroupDetails(mentorId));
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
//        // Updated column names to include approval status
//        String[] columns = {"Group ID", "Field ID", "Status", "Mentor ID"};
//        Object[][] tableData = new Object[groupData.length][4];
//    
//        // Convert data to include human-readable status and store original is_approved value
//        for (int i = 0; i < groupData.length; i++) {
//            tableData[i][0] = groupData[i][0]; // Group ID
//            tableData[i][1] = groupData[i][1]; // Field ID
//        
//            // Convert is_approved to human-readable status
//            String isApproved = groupData[i][2];
//            tableData[i][2] = "1".equals(isApproved) ? "Approved" : "Pending Approval";
//            tableData[i][3] = groupData[i][3]; // Mentor ID
//        }
//
//        JTable table = new JTable(tableData, columns) {
//            @Override
//            public Class<?> getColumnClass(int column) {
//                return column == 0 || column == 1 || column == 3 ? Integer.class : String.class;
//            }
//        };
//    
//        table.setFillsViewportHeight(true);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.setBackground(Color.WHITE);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//
//        // Create management panel with 3 sections
//        JPanel managementPanel = new JPanel(new GridLayout(1, 3, 10, 10));
//        managementPanel.setBackground(new Color(255, 239, 213));
//        managementPanel.setBorder(BorderFactory.createTitledBorder("Group Management"));
//
//        // 1. Approval Panel
//        JPanel approvalPanel = new JPanel();
//        approvalPanel.setBackground(new Color(255, 239, 213));
//        JTextField approveGroupIdField = new JTextField(5);
//        JButton approveButton = new JButton("Approve Group");
//        approveButton.setBackground(new Color(144, 238, 144)); // Light green
//    
//        approvalPanel.add(new JLabel("Group ID:"));
//        approvalPanel.add(approveGroupIdField);
//        approvalPanel.add(approveButton);
//
//        // 2. Update Panel (existing)
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
//        // 3. Delete Panel (existing)
//        JPanel deletePanel = new JPanel();
//        deletePanel.setBackground(new Color(255, 239, 213));
//        JTextField deleteGroupIdField = new JTextField(5);
//        JButton deleteButton = new JButton("Delete Group");
//        deleteButton.setBackground(new Color(255, 182, 193)); // Light red
//    
//        deletePanel.add(new JLabel("Group ID:"));
//        deletePanel.add(deleteGroupIdField);
//        deletePanel.add(deleteButton);
//
//        managementPanel.add(approvalPanel);
//        managementPanel.add(updatePanel);
//        managementPanel.add(deletePanel);
//
//        //mainPanel.add(managementPanel, BorderLayout.NORTH);
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
//        // Approve Button Action
//        approveButton.addActionListener(e -> {
//            try {
//                int groupIdToApprove = Integer.parseInt(approveGroupIdField.getText());
//            
//                if (!dao.canMentorApproveGroup(mentorId, groupIdToApprove)) {
//                    JOptionPane.showMessageDialog(this, 
//                        "Either:\n1. Group doesn't exist\n2. You're not its mentor\n3. It's already approved",
//                        "Cannot Approve", JOptionPane.WARNING_MESSAGE);
//                    return;
//                }
//            
//                int confirm = JOptionPane.showConfirmDialog(this,
//                    "Approve Group ID " + groupIdToApprove + "?",
//                    "Confirm Approval", JOptionPane.YES_NO_OPTION);
//                
//                if (confirm == JOptionPane.YES_OPTION) {
//                    boolean success = dao.approveGroup(groupIdToApprove);
//                    if (success) {
//                        JOptionPane.showMessageDialog(this, "Group approved successfully!");
//                        showGroupDetails(mentorId); // Refresh the view
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Approval failed. Please try again.");
//                    }
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Please enter a valid Group ID.");
//            }
//        });
//
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
//                    JOptionPane.showMessageDialog(this, "Update failed. Check the Group ID and Field ID.");
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Please enter valid integers.");
//            }
//        });
//
//        deleteButton.addActionListener(e -> {
//            try {
//                int groupIdToDelete = Integer.parseInt(deleteGroupIdField.getText());
//
//                int confirm = JOptionPane.showConfirmDialog(this, 
//                    "Are you sure you want to delete Group ID " + groupIdToDelete + "?", 
//                    "Confirm Delete", JOptionPane.YES_NO_OPTION);
//                
//                if (confirm == JOptionPane.YES_OPTION) {
//                    boolean success = dao.deleteGroupById(groupIdToDelete);
//                    if (success) {
//                        JOptionPane.showMessageDialog(this, "Group deleted successfully.");
//                        showGroupDetails(mentorId);
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Delete failed. Group ID may not exist or has dependencies.");
//                    }
//                }
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(this, "Enter a valid Group ID.");
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new HomeMentor(201)); // Example mentorId for testing
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
    private int mentorId;

    public HomeMentor(int mentorId) {
        this.mentorId = mentorId;
        setTitle("Home");
        setSize(1200, 500);
        setLocation(200, 60);
        setLayout(new BorderLayout());

        // Menu Panel
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

        // Content Panel
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

        // Updated column names to match Group_details
        String[] columns = {"Group ID", "Field ID", "Is Approved", "Mentor ID"};
        Object[][] tableData = new Object[groupData.length][4];

        // Convert data to include human-readable approval status
        for (int i = 0; i < groupData.length; i++) {
            tableData[i][0] = groupData[i][0]; // Group ID
            tableData[i][1] = groupData[i][1]; // Field ID
            tableData[i][2] = "1".equals(groupData[i][2]) ? "Yes" : "No"; // Is Approved
            tableData[i][3] = groupData[i][3]; // Mentor ID
        }

        JTable table = new JTable(tableData, columns) {
            @Override
            public Class<?> getColumnClass(int column) {
                return column == 0 || column == 1 || column == 3 ? Integer.class : String.class;
            }
        };

        table.setFillsViewportHeight(true);
        table.setRowHeight(30); // Ensure rows are tall enough
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(100);
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create management panel with 3 sections
        JPanel managementPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        managementPanel.setBackground(new Color(255, 239, 213));
        managementPanel.setBorder(BorderFactory.createTitledBorder("Group Management"));
        managementPanel.setPreferredSize(new Dimension(0, 120)); // Increased height

        // Approval Panel
        JPanel approvalPanel = new JPanel();
        approvalPanel.setBackground(new Color(255, 239, 213));
        JTextField approveGroupIdField = new JTextField(5);
        JButton approveButton = new JButton("Approve Group");
        approveButton.setBackground(new Color(144, 238, 144)); // Light green
        approvalPanel.add(new JLabel("Group ID:"));
        approvalPanel.add(approveGroupIdField);
        approvalPanel.add(approveButton);

        // Update Panel
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

        // Delete Panel
        JPanel deletePanel = new JPanel();
        deletePanel.setBackground(new Color(255, 239, 213));
        JTextField deleteGroupIdField = new JTextField(5);
        JButton deleteButton = new JButton("Delete Group");
        deleteButton.setBackground(new Color(255, 182, 193)); // Light red
        deletePanel.add(new JLabel("Group ID:"));
        deletePanel.add(deleteGroupIdField);
        deletePanel.add(deleteButton);

        managementPanel.add(approvalPanel);
        managementPanel.add(updatePanel);
        managementPanel.add(deletePanel);

        mainPanel.add(managementPanel, BorderLayout.NORTH); // Add management panel

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

        // Approve Button Action
        approveButton.addActionListener(e -> {
            try {
                int groupIdToApprove = Integer.parseInt(approveGroupIdField.getText());

                if (!dao.canMentorApproveGroup(mentorId, groupIdToApprove)) {
                    JOptionPane.showMessageDialog(this,
                            "Cannot approve: Group doesn't exist, you're not its mentor, or it's already approved.",
                            "Cannot Approve", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Approve Group ID " + groupIdToApprove + "?",
                        "Confirm Approval", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = dao.approveGroup(groupIdToApprove);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Group approved successfully!");
                        showGroupDetails(mentorId); // Refresh
                    } else {
                        JOptionPane.showMessageDialog(this, "Approval failed. Please try again.");
                    }
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid Group ID.");
            }
        });

        updateButton.addActionListener(e -> {
            try {
                int groupId = Integer.parseInt(groupIdField.getText());
                int newFieldId = Integer.parseInt(fieldIdField.getText());

                boolean success = dao.updateGroupFieldId(groupId, newFieldId);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Group updated successfully!");
                    showGroupDetails(mentorId);
                } else {
                    JOptionPane.showMessageDialog(this, "Update failed. Check the Group ID and Field ID.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers.");
            }
        });

        deleteButton.addActionListener(e -> {
            try {
                int groupIdToDelete = Integer.parseInt(deleteGroupIdField.getText());

                int confirm = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete Group ID " + groupIdToDelete + "?",
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = dao.deleteGroupById(groupIdToDelete);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Group deleted successfully.");
                        showGroupDetails(mentorId);
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
        SwingUtilities.invokeLater(() -> new HomeMentor(201));
    }
}

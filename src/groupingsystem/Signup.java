///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package groupingsystem;
//
//import java.awt.*;
//import javax.swing.*;
//import java.awt.event.*;
///**
// *
// * @author Aarya
// */
//public class Signup extends JFrame implements ActionListener{
//    
//	JButton btsignup,btloginlink;
//	JTextField tfname,tfid,tfemail;
//        Choice cyear, cgender, cbranch, cfield;
//        String selectedRole;
//	
//	Signup(String selectedRole){
//            this.selectedRole = selectedRole;
//        setSize(1200,700);
//        setLocation(200,60);
//        setLayout(null);
//        
//        // ---------------Right section--------------
//        JPanel p1 = new JPanel();
//        p1.setBackground(new Color(255,219,187));
//        p1.setBounds(600,0,600, 700);
//        p1.setLayout(null);
//        add(p1);
//        
//        // Right section Hero image
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Hero.png"));
//        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(50,100, 500, 500);
//        p1.add(image);
//        
//        // -------------Left section---------------
//        JPanel p2 = new JPanel();
//        p2.setBackground(Color.white);
//        p2.setBounds(0,0,600, 700);
//        p2.setLayout(null);
//        add(p2);
//        
//        // Signup and tagline 
//        JLabel lbtitle = new JLabel("Signup");
//        lbtitle.setBounds(100,30,350,50);
//        lbtitle.setFont(new Font("Arial", Font.BOLD, 40));
//        p2.add(lbtitle);
//        
//        JLabel lbtagline1 = new JLabel("Be in the perfect group");
//        lbtagline1.setBounds(100,90,350,50);
//        lbtagline1.setFont(new Font("Arial", Font.PLAIN, 25));
//        p2.add(lbtagline1);
//        
//        JLabel lbtagline2 = new JLabel("Get the perfect guidance");
//        lbtagline2.setBounds(200,120,350,50);
//        lbtagline2.setFont(new Font("Arial", Font.PLAIN, 25));
//        p2.add(lbtagline2);
//        
//        // Name section
//        JLabel lbname = new JLabel("Name");
//        lbname.setBounds(100,200,100,25);
//        lbname.setFont(new Font("Arial", Font.BOLD, 16));
//        p2.add(lbname);
//        
//        tfname = new JTextField();
//        tfname.setBounds(100,220,350,40);
//        tfname.setFont(new Font("Arial", Font.PLAIN, 20));
//        tfname.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
//        p2.add(tfname);
//        
//        // PRN section
//        JLabel lbid;
//        if( selectedRole.equals("mentor")){
//            lbid = new JLabel("Mentor ID");
//        } else{
//            lbid = new JLabel("PRN");
//        }
//        lbid.setBounds(100,260,100,25);
//        lbid.setFont(new Font("Arial", Font.BOLD, 16));
//        p2.add(lbid);
//        
//        tfid = new JTextField();
//        tfid.setBounds(100,280,350,40);
//        tfid.setFont(new Font("Arial", Font.PLAIN, 20));
//        tfid.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
//        p2.add(tfid);
//        
//        // Email section
//        JLabel lbemail = new JLabel("Email");
//        lbemail.setBounds(100,320,100,25);
//        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
//        p2.add(lbemail);
//        
//        tfemail = new JTextField();
//        tfemail.setBounds(100,340,350,40);
//        tfemail.setFont(new Font("Arial", Font.PLAIN, 20));
//        tfemail.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
//        p2.add(tfemail);
//        
//        if( selectedRole.equals("mentor")){
//            JLabel lbfield = new JLabel("Field");
//            lbfield.setBounds(100,380,100,25);
//            lbfield.setFont(new Font("Arial", Font.BOLD, 16));
//            p2.add(lbfield);
//
//            cfield = new Choice();
//            cfield.setBounds(100,410,350,40);
//            cfield.setFont(new Font("Arial", Font.PLAIN, 20));
//            cfield.add("Select Field");
//            cfield.add("Computer Science");
//            cfield.add("Artificial Intelligence");
//            cfield.add("Cybersecurity");
//            cfield.add("Web Development");
//            p2.add(cfield);
//        }
//        
//        if( selectedRole.equals("student")){// Branch section
//            JLabel lbbranch = new JLabel("Branch");
//            lbbranch.setBounds(100,380,100,25);
//            lbbranch.setFont(new Font("Arial", Font.BOLD, 16));
//            p2.add(lbbranch);
//
//            cbranch = new Choice();
//            cbranch.setBounds(100,410,350,40);
//            cbranch.setFont(new Font("Arial", Font.PLAIN, 20));
//            cbranch.add("Select Branch");
//            cbranch.add("Computer");
//            cbranch.add("IT");
//            cbranch.add("Electronics");
//            cbranch.add("Mechanical");
//            p2.add(cbranch);
//
//            // Gender section
//            JLabel lbgender = new JLabel("Gender");
//            lbgender.setBounds(100,440,100,25);
//            lbgender.setFont(new Font("Arial", Font.BOLD, 16));
//            p2.add(lbgender);
//
//            cgender = new Choice();
//            cgender.setBounds(100,470,350,40);
//            cgender.setFont(new Font("Arial", Font.PLAIN, 20));
//            cgender.add("Select Gender");
//            cgender.add("Male");
//            cgender.add("Female");
//            p2.add(cgender);
//
//            // Year section
//            JLabel lbyear = new JLabel("Year");
//            lbyear.setBounds(100,500,100,25);
//            lbyear.setFont(new Font("Arial", Font.BOLD, 16));
//            p2.add(lbyear);
//
//            cyear = new Choice();
//            cyear.setBounds(100,530,350,40);
//            cyear.setFont(new Font("Arial", Font.PLAIN, 20));
//            cyear.add("Select Year");
//            cyear.add("1");
//            cyear.add("2");
//            cyear.add("3");
//            cyear.add("4");
//            p2.add(cyear);
//        }
//              
//        // Signup button
//        btsignup = new JButton("Signup");
//        btsignup.setBounds(100,580,350,40);
//        btsignup.setBackground(new Color(255,219,187));
//        btsignup.setBorder(BorderFactory.createEmptyBorder());
//        btsignup.setFont(new Font("Arial", Font.BOLD, 20));
//        p2.add(btsignup);
//        btsignup.addActionListener(this);
//        
//        // Login page link
//        JLabel lbloginlink = new JLabel("Already have an account?");
//        lbloginlink.setBounds(150,620,300,25);
//        lbloginlink.setFont(new Font("Arial", Font.BOLD, 16));
//        p2.add(lbloginlink);
//        
//        btloginlink = new JButton("Login");
//        btloginlink.setBounds(350,620,50,25);
//        btloginlink.setBackground(null);
//        btloginlink.setForeground(Color.blue);
//        btloginlink.setBorder(BorderFactory.createEmptyBorder());
//        btloginlink.setFont(new Font("Arial", Font.BOLD, 16));
//        p2.add(btloginlink);
//        btloginlink.addActionListener(this);
//        
//
//        setVisible(true);
//    }
//    
//    public void actionPerformed(ActionEvent ae) {
//    if (ae.getSource() == btsignup) {
//        String name, id, email, field, branch, year, gender, query;
//
//        try {
//            if(selectedRole.equals("mentor")){
//                name = tfname.getText().trim();
//                id = tfid.getText().trim();
//                email = tfemail.getText().trim();
//                field = cfield.getSelectedItem();
//                // Check if any field is empty
//                if (name.isEmpty() || id.isEmpty() || email.isEmpty() || field.equals("Select Field")) {
//                    JOptionPane.showMessageDialog(null, "Please fill all the fields and make valid selections.", "Input Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                int field_id;
//                field_id = switch (field) {
//                    case "Computer Science" -> 1;
//                    case "Artificial Intelligence" -> 2;
//                    case "Cybersecurity" -> 3;
//                    default -> 4;
//                };
//                
//                query = "insert into faculty values('" + id + "','" + field_id + "','" + name + "','" + email + "')";                
//            }else{
//                name = tfname.getText().trim();
//                id = tfid.getText().trim();
//                email = tfemail.getText().trim();
//                branch = cbranch.getSelectedItem();
//                year = cyear.getSelectedItem();
//                gender = cgender.getSelectedItem();
//                // Check if any field is empty
//                if (name.isEmpty() || id.isEmpty() || email.isEmpty()
//                        || branch.equals("Select Branch") || year.equals("Select Year") || gender.equals("Select Gender")) {
//                    JOptionPane.showMessageDialog(null, "Please fill all the fields and make valid selections.", "Input Error", JOptionPane.ERROR_MESSAGE);
//                    return;
//                }
//                
//                int yearInt = Integer.parseInt(year); // Convert year only after validation
//                query = "insert into student values('" + id + "','" + name + "','" + email + "','" + branch + "','" + gender + "','" + yearInt + "',NULL)";
//            }
//            Conn c = new Conn();
//            c.s.executeUpdate(query);
//
//            JOptionPane.showMessageDialog(null, "Data Entered Successfully");
//            setVisible(false);
//            new Login(selectedRole);
//
//            } catch (NumberFormatException nfe) {
//                JOptionPane.showMessageDialog(null, "Please select a valid year.", "Format Error", JOptionPane.ERROR_MESSAGE);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        if (ae.getSource() == btloginlink) {
//            try {
//                setVisible(false);
//                new Login(selectedRole);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//       
//    public static void main(String[] args){
//        new Signup("");
//    }
//}

package groupingsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    private JButton signupButton, loginLinkButton;
    private JTextField tfname, tfid, tfemail;
    private User user;
    private String selectedRole;

    public Signup(String selectedRole) {
        this.selectedRole = selectedRole;
        setSize(1200, 700);
        setLocation(200, 60);
        setLayout(null);

        // Right section
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 219, 187));
        p1.setBounds(600, 0, 600, 700);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Hero.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 100, 500, 500);
        p1.add(image);

        // Left section
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(0, 0, 600, 700);
        p2.setLayout(null);
        add(p2);

        JLabel lbtitle = new JLabel("Signup");
        lbtitle.setBounds(100, 30, 350, 50);
        lbtitle.setFont(new Font("Arial", Font.BOLD, 40));
        p2.add(lbtitle);

        JLabel lbtagline1 = new JLabel("Be in the perfect group");
        lbtagline1.setBounds(100, 90, 350, 50);
        lbtagline1.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(lbtagline1);

        JLabel lbtagline2 = new JLabel("Get the perfect guidance");
        lbtagline2.setBounds(200, 120, 350, 50);
        lbtagline2.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(lbtagline2);

        // Name
        JLabel lbname = new JLabel("Name");
        lbname.setBounds(100, 200, 100, 25);
        lbname.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbname);

        tfname = new JTextField();
        tfname.setBounds(100, 220, 350, 40);
        tfname.setFont(new Font("Arial", Font.PLAIN, 20));
        tfname.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfname);

        // ID (PRN or Mentor ID)
        JLabel lbid = new JLabel(selectedRole.equals("mentor") ? "Mentor ID" : "PRN");
        lbid.setBounds(100, 260, 100, 25);
        lbid.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbid);

        tfid = new JTextField();
        tfid.setBounds(100, 280, 350, 40);
        tfid.setFont(new Font("Arial", Font.PLAIN, 20));
        tfid.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfid);

        // Email
        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(100, 320, 100, 25);
        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbemail);

        tfemail = new JTextField();
        tfemail.setBounds(100, 340, 350, 40);
        tfemail.setFont(new Font("Arial", Font.PLAIN, 20));
        tfemail.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfemail);

        // Role-specific fields
        user = selectedRole.equals("mentor") ?
            new Mentor("", "", "", "") :
            new Student("", "", "", "", "", "");
        user.addSpecificFields(p2, 410);

        // Signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds(100, 580, 350, 40);
        signupButton.setBackground(new Color(255, 219, 187));
        signupButton.setBorder(BorderFactory.createEmptyBorder());
        signupButton.setFont(new Font("Arial", Font.BOLD, 20));
        signupButton.addActionListener(this);
        p2.add(signupButton);

        // Login page link
        JLabel lbloginlink = new JLabel("Already have an account?");
        lbloginlink.setBounds(150, 620, 300, 25);
        lbloginlink.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbloginlink);

        loginLinkButton = new JButton("Login");
        loginLinkButton.setBounds(350, 620, 50, 25);
        loginLinkButton.setBackground(null);
        loginLinkButton.setForeground(Color.blue);
        loginLinkButton.setBorder(BorderFactory.createEmptyBorder());
        loginLinkButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginLinkButton.addActionListener(this);
        p2.add(loginLinkButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == signupButton) {
            String name = tfname.getText().trim();
            String id = tfid.getText().trim();
            String email = tfemail.getText().trim();

            // Update existing user instance with input values
            user.updateFields(name, id, email);

            if (!user.validateSignupFields() || !user.validateSpecificFields()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields and make valid selections.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();
                // Validate ID format and uniqueness
                String idError = user.validateIdFormatAndUniqueness(c);
                if (idError != null) {
                    JOptionPane.showMessageDialog(null, idError, "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                c.s.executeUpdate(user.getSignupQuery());
                JOptionPane.showMessageDialog(null, "Data Entered Successfully");
                setVisible(false);
                new Login(selectedRole);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please select a valid year.", "Format Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Unexpected error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (ae.getSource() == loginLinkButton) {
            try {
                setVisible(false);
                new Login(selectedRole);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Signup("");
    }
}
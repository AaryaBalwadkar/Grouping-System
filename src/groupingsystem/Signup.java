/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupingsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Aarya
 */
public class Signup extends JFrame implements ActionListener{
    
	JButton btsignup,btloginlink;
	JTextField tfname,tfprn,tfemail;
        Choice cyear, cgender, cbranch;
	
	Signup(){
        setSize(1200,700);
        setLocation(200,100);
        setLayout(null);
        
        // ---------------Right section--------------
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255,219,187));
        p1.setBounds(600,0,600, 700);
        p1.setLayout(null);
        add(p1);
        
        // Right section Hero image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Hero.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50,100, 500, 500);
        p1.add(image);
        
        // -------------Left section---------------
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(0,0,600, 700);
        p2.setLayout(null);
        add(p2);
        
        // Signup and tagline 
        JLabel lbtitle = new JLabel("Signup");
        lbtitle.setBounds(100,30,350,50);
        lbtitle.setFont(new Font("Arial", Font.BOLD, 40));
        p2.add(lbtitle);
        
        JLabel lbtagline1 = new JLabel("Be in the perfect group");
        lbtagline1.setBounds(100,90,350,50);
        lbtagline1.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(lbtagline1);
        
        JLabel lbtagline2 = new JLabel("Get the perfect guidance");
        lbtagline2.setBounds(200,120,350,50);
        lbtagline2.setFont(new Font("Arial", Font.PLAIN, 25));
        p2.add(lbtagline2);
        
        // Name section
        JLabel lbname = new JLabel("Name");
        lbname.setBounds(100,200,100,25);
        lbname.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbname);
        
        tfname = new JTextField();
        tfname.setBounds(100,220,350,40);
        tfname.setFont(new Font("Arial", Font.PLAIN, 20));
        tfname.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfname);
        
        // PRN section
        JLabel lbprn = new JLabel("PRN");
        lbprn.setBounds(100,260,100,25);
        lbprn.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbprn);
        
        tfprn = new JTextField();
        tfprn.setBounds(100,280,350,40);
        tfprn.setFont(new Font("Arial", Font.PLAIN, 20));
        tfprn.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfprn);
        
        // Email section
        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(100,320,100,25);
        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(100,340,350,40);
        tfemail.setFont(new Font("Arial", Font.PLAIN, 20));
        tfemail.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfemail);
        
        // Branch section
        JLabel lbbranch = new JLabel("Branch");
        lbbranch.setBounds(100,380,100,25);
        lbbranch.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbbranch);

        cbranch = new Choice();
        cbranch.setBounds(100,410,350,40);
        cbranch.setFont(new Font("Arial", Font.PLAIN, 20));
        cbranch.add("Computer");
        cbranch.add("IT");
        cbranch.add("Electronics");
        cbranch.add("Mechanical");
        p2.add(cbranch);
        
        // Gender section
        JLabel lbgender = new JLabel("Gender");
        lbgender.setBounds(100,440,100,25);
        lbgender.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbgender);
        
        cgender = new Choice();
        cgender.setBounds(100,470,350,40);
        cgender.setFont(new Font("Arial", Font.PLAIN, 20));
        cgender.add("Male");
        cgender.add("Female");
        p2.add(cgender);
        
        // Year section
        JLabel lbyear = new JLabel("Year");
        lbyear.setBounds(100,500,100,25);
        lbyear.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbyear);
        
        cyear = new Choice();
        cyear.setBounds(100,530,350,40);
        cyear.setFont(new Font("Arial", Font.PLAIN, 20));
        cyear.add("1");
        cyear.add("2");
        cyear.add("3");
        cyear.add("4");
        p2.add(cyear);
              
        // Signup button
        btsignup = new JButton("Signup");
        btsignup.setBounds(100,580,350,40);
        btsignup.setBackground(new Color(255,219,187));
        btsignup.setBorder(BorderFactory.createEmptyBorder());
        btsignup.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(btsignup);
        btsignup.addActionListener(this);
        
        // Login page link
        JLabel lbloginlink = new JLabel("Already have an account?");
        lbloginlink.setBounds(150,620,300,25);
        lbloginlink.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbloginlink);
        
        btloginlink = new JButton("Login");
        btloginlink.setBounds(350,620,50,25);
        btloginlink.setBackground(null);
        btloginlink.setForeground(Color.blue);
        btloginlink.setBorder(BorderFactory.createEmptyBorder());
        btloginlink.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(btloginlink);
        btloginlink.addActionListener(this);
        

        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == btsignup) {
        String name = tfname.getText().trim();
        String prn = tfprn.getText().trim();
        String email = tfemail.getText().trim();
        String branch = cbranch.getSelectedItem();
        String year = cyear.getSelectedItem();
        String gender = cgender.getSelectedItem();

        // Check if any field is empty
        if (name.isEmpty() || prn.isEmpty() || email.isEmpty()
                || branch.equals("Select Branch") || year.equals("Select Year") || gender.equals("Select Gender")) {
            JOptionPane.showMessageDialog(null, "Please fill all the fields and make valid selections.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int yearInt = Integer.parseInt(year); // Convert year only after validation
            String query = "insert into student values('" + prn + "','" + name + "','" + email + "','" + branch + "','" + gender + "','" + yearInt + "',NULL)";

            Conn c = new Conn();
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data Entered Successfully");
            setVisible(false);
            new Login();

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Please select a valid year.", "Format Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == btloginlink) {
            try {
                setVisible(false);
                new Login();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
       
    public static void main(String[] args){
        new Signup();
    }
}

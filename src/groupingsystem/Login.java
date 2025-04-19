package groupingsystem;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JButton btlogin,btsignuplink;
    JTextField tfemail, tfpassword;

    Login() {
        setSize(1200, 600);
        setLocation(200, 100);
        setLayout(null);

        // Right section
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255, 219, 187));
        p1.setBounds(600, 0, 600, 600);
        p1.setLayout(null);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Hero.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50, 0, 500, 500);
        p1.add(image);

        // Left section
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(0, 0, 600, 600);
        p2.setLayout(null);
        add(p2);

        JLabel lbtitle = new JLabel("Login");
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

        // Email
        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(100, 200, 100, 25);
        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbemail);

        tfemail = new JTextField();
        tfemail.setBounds(100, 220, 350, 40);
        tfemail.setFont(new Font("Arial", Font.PLAIN, 20));
        tfemail.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfemail);

        // Password (PRN in your case)
        JLabel lbpassword = new JLabel("PRN");
        lbpassword.setBounds(100, 280, 100, 25);
        lbpassword.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbpassword);

        tfpassword = new JTextField();
        tfpassword.setBounds(100, 300, 350, 40);
        tfpassword.setFont(new Font("Arial", Font.PLAIN, 20));
        tfpassword.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfpassword);

        // Login button
        btlogin = new JButton("Login");
        btlogin.setBounds(100, 400, 350, 40);
        btlogin.setBackground(new Color(255, 219, 187));
        btlogin.setBorder(BorderFactory.createEmptyBorder());
        btlogin.setFont(new Font("Arial", Font.BOLD, 20));
        btlogin.addActionListener(this);
        p2.add(btlogin);
        
        // Signup page link
        JLabel lbloginlink = new JLabel("Don't have an account?");
        lbloginlink.setBounds(150,440,300,25);
        lbloginlink.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbloginlink);
        
        btsignuplink = new JButton("Signup");
        btsignuplink.setBounds(330,440,60,25);
        btsignuplink.setBackground(null);
        btsignuplink.setForeground(Color.blue);
        btsignuplink.setBorder(BorderFactory.createEmptyBorder());
        btsignuplink.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(btsignuplink);
        btsignuplink.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btlogin) {
            String email = tfemail.getText().trim();
            String prn = tfpassword.getText().trim(); // treated as "password"

            // Check for empty fields
            if (email.isEmpty() || prn.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter both email and PRN.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "SELECT * FROM student WHERE email = '" + email + "' AND prn = '" + prn + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    setVisible(false);
                    new Loader(); // assuming this is your next screen
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect login. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == btsignuplink) {
            try {
                setVisible(false);
                new Signup();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        new Login();
    }
}




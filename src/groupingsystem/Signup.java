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
    
	JButton btsignup;
	JTextField tfname,tfprn,tfemail,tfbranch,tfyear,tfgender;
	
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
        
        tfbranch = new JTextField();
        tfbranch.setBounds(100,400,350,40);
        tfbranch.setFont(new Font("Arial", Font.PLAIN, 20));
        tfbranch.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfbranch);
        
        // Gender section
        JLabel lbgender = new JLabel("Gender");
        lbgender.setBounds(100,440,100,25);
        lbgender.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbgender);
        
        tfgender = new JTextField();
        tfgender.setBounds(100,460,350,40);
        tfgender.setFont(new Font("Arial", Font.PLAIN, 20));
        tfgender.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfgender);
        
        // Year section
        JLabel lbyear = new JLabel("Year");
        lbyear.setBounds(100,500,100,25);
        lbyear.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbyear);
        
        tfyear = new JTextField();
        tfyear.setBounds(100,520,350,40);
        tfyear.setFont(new Font("Arial", Font.PLAIN, 20));
        tfyear.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfyear);
              
        // Signup button
        btsignup = new JButton("Signup");
        btsignup.setBounds(100,600,350,40);
        btsignup.setBackground(new Color(255,219,187));
        btsignup.setBorder(BorderFactory.createEmptyBorder());
        btsignup.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(btsignup);
        btsignup.addActionListener(this);
        

        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    	if(ae.getSource()==btsignup) {
    		String name=tfname.getText();
    		String prn=tfprn.getText();
    		String email=tfemail.getText();
    		String branch=tfbranch.getText();
    		String year=tfyear.getText();
    		int yearInt = Integer.parseInt(year);
    		String gender=tfgender.getText();
    		String query = "insert into student values('" + prn + "','" + name + "','" + email + "','" + branch + "','" + gender + "','" + yearInt + "')";
    		try {
    			Conn c=new Conn();
    			c.s.executeUpdate(query);
    			JOptionPane.showMessageDialog(null,"Data Entered Successfully");
    			setVisible(false);
    			new Signup();
    			
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
			
    	}
    	
    }
       
    public static void main(String[] args){
        new Signup();
    }
}

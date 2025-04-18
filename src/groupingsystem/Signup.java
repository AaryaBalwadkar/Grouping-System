/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupingsystem;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Aarya
 */
public class Signup extends JFrame{
    
    Signup(){
        setSize(1200,600);
        setLocation(200,100);
        setLayout(null);
        
        // ---------------Right section--------------
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(255,219,187));
        p1.setBounds(600,0,600, 600);
        p1.setLayout(null);
        add(p1);
        
        // Right section Hero image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Hero.png"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(50,0, 500, 500);
        p1.add(image);
        
        // -------------Left section---------------
        JPanel p2 = new JPanel();
        p2.setBackground(Color.white);
        p2.setBounds(0,0,600, 600);
        p2.setLayout(null);
        add(p2);
        
        // Loing and tagline 
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
        
        JTextField tfname = new JTextField();
        tfname.setBounds(100,220,350,40);
        tfname.setFont(new Font("Arial", Font.PLAIN, 20));
        tfname.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfname);
        
        // PRN section
        JLabel lbprn = new JLabel("PRN");
        lbprn.setBounds(100,280,100,25);
        lbprn.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbprn);
        
        JTextField tfprn = new JTextField();
        tfprn.setBounds(100,300,350,40);
        tfprn.setFont(new Font("Arial", Font.PLAIN, 20));
        tfprn.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfprn);
        
        // Email section
        JLabel lbemail = new JLabel("Email");
        lbemail.setBounds(100,360,100,25);
        lbemail.setFont(new Font("Arial", Font.BOLD, 16));
        p2.add(lbemail);
        
        JTextField tfemail = new JTextField();
        tfemail.setBounds(100,380,350,40);
        tfemail.setFont(new Font("Arial", Font.PLAIN, 20));
        tfemail.setBorder(BorderFactory.createBevelBorder(1, Color.lightGray, Color.lightGray));
        p2.add(tfemail);
              
        // Signup button
        JButton btlogin = new JButton("Signup");
        btlogin.setBounds(100,470,350,40);
        btlogin.setBackground(new Color(255,219,187));
        btlogin.setBorder(BorderFactory.createEmptyBorder());
        btlogin.setFont(new Font("Arial", Font.BOLD, 20));
        p2.add(btlogin);
        
        for(String fontname: GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()){
            System.out.println(fontname);
        }
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Signup();
    }
}

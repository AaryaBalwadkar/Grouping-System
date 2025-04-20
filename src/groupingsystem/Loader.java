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
public class Loader extends JFrame implements Runnable {
    Thread t;
    JProgressBar bar;
    String selectedRole, username;
    
    public void run(){
        try {
            for (int i = 0; i <= 101; i++) {
                int max = bar.getMaximum();
                int value = bar.getValue();
                
                if (value < max) {
                    bar.setValue(bar.getValue() + (i*i)/50);
                } else {
                    setVisible(false);
                    // Start Quiz on EDT
                    if(selectedRole.equals("student")){
                        SwingUtilities.invokeLater(() -> new Quiz());
                    } else{
                        SwingUtilities.invokeLater(() -> new GroupAssignment("PRN001", "Test Student"));
                    }
                    dispose();
                    break; // Exit the loop to prevent multiple Quiz instantiations
                }
                Thread.sleep(50);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    Loader(String selectedRole, String username){
        this.selectedRole = selectedRole;
        this.username = username;
        t = new Thread(this);
        
        setBounds(500, 200, 1200, 600);
        setLocation(200,100);
        getContentPane().setBackground(Color.white);
        setLayout(null);
        
        JLabel lbtext = new JLabel("Grouping System Application");
        lbtext.setBounds(400, 50, 1000, 40);
        lbtext.setFont(new Font("Arial", Font.BOLD, 35));
        add(lbtext);
        
        bar = new JProgressBar();
        bar.setBounds(0, 500, 1200, 20);
        bar.setStringPainted(true);
        bar.setForeground(new Color(255,219,187));
        bar.setBorder(BorderFactory.createEmptyBorder());
        add(bar);
        
        JLabel lbuser = new JLabel("Welcome " + username);
        lbuser.setBounds(550, 100, 400, 40);
        lbuser.setFont(new Font("Arial", Font.BOLD, 25));
        add(lbuser);
        
        JLabel lbloading = new JLabel("Loading, please wait...");
        lbloading.setBounds(500, 440, 400, 30);
        lbloading.setFont(new Font("Arial", Font.BOLD, 20));
        add(lbloading);
        
        t.start();
        setVisible(true);
    }
    
    public static void main(String[] args){
        new Loader("","");
    }
}

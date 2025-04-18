/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package groupingsystem;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Aarya
 */
public class Splash extends JFrame implements Runnable {
    Thread thread;
    Splash(){        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/Splash.jpg"));
        Image i2 = i1.getImage().getScaledInstance(1200, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        setVisible(true);
        thread = new Thread(this);
        thread.start();
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(5000);
            setVisible(false);
            new Login();
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        Splash frame = new Splash();
        
        for(int i = 1, x = 1; i <= 500; x+=7, i+=6){
            frame.setLocation(750 - (x + i)/2,400 - (i/2));
            frame.setSize(x+i,i);
            try{
                Thread.sleep(10);
            } catch (Exception e){
                
            }
        }
    }
}

package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * A Java Swing program that shows how much water you should drink a day.
 * @author www.codejava.net
 */
public class LoginPage extends JFrame implements ActionListener {
    private JLabel labelSignIn;
    private JTextField username;
    private JTextField password;
    private JButton login;
 
    public LoginPage() {
        super("Auction Sniper");
 
        initComponents();
        setResizable(false);
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 
    private void initComponents() {
        labelSignIn = new JLabel("Sign-in Here:");
        username = new JTextField(10);
        password = new JTextField(10);
        login = new JButton("Login");
 
        setLayout(new FlowLayout(1,100,100));
 
        add(labelSignIn);
        add(username);
        add(password);
        add(login);
        
        login.addActionListener(this);
    }
 
    public void actionPerformed(ActionEvent event) {
        String message = "Login Successful!";
 
        JOptionPane.showMessageDialog(this, message);
    }
 
 
    public static void main(String[] args) {
        new LoginPage().setVisible(true);
    }
}
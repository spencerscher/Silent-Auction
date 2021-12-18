package UserInterface;

import java.awt.*;

import javax.swing.JFrame;


public class Login2 extends JFrame {
    Button button1, button2, button3;
    public void init() {
        button1 = new Button("Ok");
        button2 = new Button("Open");
        button3 = new Button("Close");
        add(button1);
        add(button2);
        add(button3);
    }
    
    public static void main(String[] args) {
        new Login2().setVisible(true);
    }
}
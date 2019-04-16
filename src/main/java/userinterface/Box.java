package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Box extends JFrame  {

    public static final long serialVersionUID = 1L; //Don't touch

    //Vars

    //Items
    JTextField Username = new JTextField();
    JLabel submitCheck = new JLabel("");
    JButton button = new JButton("Submit");

    public static void main(String[] args){
        new Box().setVisible(true);
    }

    private Box(){
        //Box itself
        super("Hunger Games Simulation");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600,600);
        setResizable(false);
        setLayout(new FlowLayout()); //BorderLayout

        //Username textfield
        Username.setSize(100,100);

        //Adds - puts it on the box
        add(submitCheck);
        add(Username);
        add(button);

    }

}

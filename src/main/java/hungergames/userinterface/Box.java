package userinterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Box extends JFrame implements ActionListener{

    public static final long serialVersionUID = 1L; //Don't touch

    //Vars
    private boolean isResizable = false; //Resize Box?
    private int[] size = {600,600}; //Size of box

    //Items
    JTextField Username = new JTextField();
    JLabel submitCheck = new JLabel("");
    JButton button = new JButton("Submit");

    public static void main(String[] args){
        new Box().setVisible(true);
    }

    private Box() {
        //Box itself
        super("Hunger Games Simulation"); // Title
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(size[0], size[1]);
        setResizable(isResizable);
        setLayout(new FlowLayout()); //BorderLayout

        //Give action listeners
        button.addActionListener(this);

        //Username textfield
        Username.setSize(100, 100);

        //Adds - puts it on the box
        add(submitCheck);
        add(Username);
        add(button);

    }

    //Actions
    public void actionPerformed(ActionEvent e){
        String name = e.getActionCommand();
        if(name.equals("Submit")){
            submitCheck.setText(Username.getText());
        }
    }

}

package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import shopU.RegisteredUser;


public class RegisterUser extends JFrame {
    JTextField nameField, idField,moneyField;
    JPasswordField passwordField;
    RegisteredUser user;
    
    public RegisterUser(final RegisteredUser user, final Window window){
        this.user=user;
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setSize(410, 300);
        this.setTitle("Register new user.");
        this.setLayout(null);
        this.setLocation(500, 164);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        registerZone(this, window);       
    }
    
    private void registerZone(final RegisterUser ru, final Window window){
        JLabel disclaimer=new JLabel("Enter the information required (all) :");
        disclaimer.setBounds(10, 5, 300, 35);
        this.add(disclaimer);
        
        JLabel name=new JLabel("Name:");
        name.setBounds(10, 40, 100, 35);
        this.add(name);
        
        nameField = new JTextField();
        nameField.setBackground(Color.white);
        nameField.setBounds(80, 45, 300, 20);
        this.add(nameField);
        
        JLabel id=new JLabel("Id:");
        id.setBounds(10, 75, 100, 35);
        this.add(id);
        
        idField= new JTextField();
        idField.setBackground(Color.white);
        idField.setBounds(80, 80, 300, 20);
        this.add(idField);
        
        JLabel password=new JLabel("Password:");
        password.setBounds(10, 110, 100, 35);
        this.add(password);
        
        passwordField= new JPasswordField();
        passwordField.setBackground(Color.white);
        passwordField.setBounds(80, 115, 300, 20);
        this.add(passwordField);
        
        JLabel money=new JLabel("Money:");
        money.setBounds(10, 145, 100, 35);
        this.add(money);
        
        moneyField= new JTextField("1000000");
        moneyField.setEditable(false);
        moneyField.setBackground(Color.white);
        moneyField.setBounds(80, 150, 300, 20);
        this.add(moneyField);
        
        JButton setB = new JButton("I wanna be a User :D");
        setB.setBounds(105,this.getHeight()-100, 200, 40);
        setB.setBackground(Color.white);
        this.add(setB);
        
        setB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!nameField.getText().equals("") && !String.valueOf(passwordField.getPassword()).equals("") && !idField.getText().equals("")){
                    RegisteredUser newUser= new RegisteredUser(1000000, nameField.getText(), idField.getText(), String.valueOf(passwordField.getPassword()), window.m);
                    user.addUser(newUser);
                    
                    ru.setVisible(false);
                    JOptionPane.showMessageDialog(null, "Register Successful! Sign in\nto start shopping!");
                    
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fields Missing");
                }
            }
        });        
    }
    
}

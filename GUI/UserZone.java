package GUI;

import com.mongodb.BasicDBObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.regex.Pattern;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import shop2.Test;
import shopU.RegisteredUser;

public class UserZone extends JFrame{
    JList historialList, cartList;
    JTabbedPane father;
    Color color = Color.lightGray;
    public JTextField nameField, idField,moneyField;
    public JPasswordField passwordField;
    
    
    public UserZone(RegisteredUser user,Window window){
        this.setDefaultCloseOperation(0);
        this.setSize(410, 400);
        this.setTitle("User Zone");
        this.setLayout(null);
        this.setLocation(500, 164);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        father = new JTabbedPane();
        father.setBackground(color);
        
        
        zoneManager();
        adminCart(user,window);
        adminHistorial(user);
        adminUserData(user);
        
    }
    
    private void zoneManager(){
        
        JPanel generalP=new JPanel();
        generalP.setLayout(new GroupLayout(generalP));
        generalP.setName("General");
        generalP.setBackground(color);
        
        JPanel currentP=new JPanel();
        currentP.setLayout(new GroupLayout(currentP));
        currentP.setName("Current purchase");
        currentP.setBackground(color);
        
        JPanel historialP=new JPanel();
        historialP.setLayout(new GroupLayout(historialP));
        historialP.setName("Historial");
        historialP.setBackground(color);
        
        father.add(generalP);
        father.add(currentP);
        father.add(historialP);
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        
        this.add(father);
        
    }
    
    private void adminCart(final RegisteredUser user,final Window window){
        JButton clearCart  = new JButton();
        JButton buyCart  = new JButton();
        this.cartList = new JList();
        updatecartList(user);
        
        cartList.setAutoscrolls(true);
        cartList.setBackground(Color.WHITE);
        cartList.setForeground(Color.DARK_GRAY);
        cartList.setValueIsAdjusting(true);
        
        JScrollPane scroll = new JScrollPane(cartList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(cartList);
        scroll.setBounds(5, 5,this.getWidth()-20, this.getHeight()-150);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(1)).add(scroll);
        
        
        buyCart.setBounds(5,this.getHeight()-130, 200, 50);
        buyCart.setText("Buy cart.");
        ((JPanel)father.getComponentAt(1)).add(buyCart);
        buyCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.buyCart()){
                    JOptionPane.showMessageDialog(null, "Purchases successfull");
                    updatehistorialList(user.showShopping());
                }else{
                    JOptionPane.showMessageDialog(null, "Purchase was incomplete, some items\n were not bought ");
                    updatehistorialList(user.showShopping());
                }
                updatecartList(user);
                window.updatebookList();
                window.updatecdList();
                window.updatemovieList();
                user.modifyUser(user, user.getId());
                moneyField.setText(String.valueOf(user.getMoney()));
                
            }
        }); 
        
        clearCart.setBounds(195,this.getHeight()-130, 200, 50);
        clearCart.setText("Clear cart.");
        ((JPanel)father.getComponentAt(1)).add(clearCart);
        clearCart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                user.clearCart();
                updatecartList(user);
            }
        }); 
        
    }
    
    private void adminHistorial(final RegisteredUser user){
        JButton partialHistorial  = new JButton();
        JButton fullHistorial  = new JButton();
        JLabel sinceL = new JLabel("Since:");
        JLabel untilL = new JLabel("Until:");
        final JTextField sinceF = new JTextField("DD/MM");
        final JTextField untilF = new JTextField("DD/MM");
        this.historialList = new JList();
        updatehistorialList(new LinkedList<BasicDBObject>());
        
        historialList.setAutoscrolls(true);
        historialList.setBackground(Color.WHITE);
        historialList.setForeground(Color.DARK_GRAY);
        historialList.setValueIsAdjusting(true);
        
        JScrollPane scroll = new JScrollPane(historialList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(historialList);
        scroll.setBounds(5, 5,this.getWidth()-20, this.getHeight()-150);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(2)).add(scroll);
        
        
        fullHistorial.setBounds(5,this.getHeight()-100, 200, 40);
        fullHistorial.setText("Full historial.");
        ((JPanel)father.getComponentAt(2)).add(fullHistorial);
        
        partialHistorial.setBounds(195,this.getHeight()-100, 200, 40);
        partialHistorial.setText("Partial historial.");
        ((JPanel)father.getComponentAt(2)).add(partialHistorial);
        
        sinceL.setBounds(5,this.getHeight()-140, 80, 40);
        ((JPanel)father.getComponentAt(2)).add(sinceL);
        
        untilL.setBounds(195,this.getHeight()-140, 80, 40);
        ((JPanel)father.getComponentAt(2)).add(untilL);
        
        sinceF.setBounds(45,this.getHeight()-130, 130, 20);
        sinceF.setBackground(Color.white);
        ((JPanel)father.getComponentAt(2)).add(sinceF);
        
        untilF.setBounds(230,this.getHeight()-130, 130, 20);
        untilF.setBackground(Color.white);
        ((JPanel)father.getComponentAt(2)).add(untilF);
        
        fullHistorial.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                updatehistorialList(user.showShopping());
            }
        }); 
        partialHistorial.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                if(Pattern.matches("\\d\\d/\\d\\d",sinceF.getText()) && Pattern.matches("\\d\\d/\\d\\d",untilF.getText())){
                    updatehistorialList(user.showShopping(user.getDate(sinceF.getText()), user.getDate(untilF.getText())));
                }else{
                    JOptionPane.showMessageDialog(null, "The input format must be DD/MM\n also, must be numeric");
                }
            }
        }); 
    }
    
    private void adminUserData(final RegisteredUser user){
        JLabel disclaimer=new JLabel("Modify the information required to change:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(0)).add(disclaimer);
        
        JLabel title=new JLabel("Name:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(title);
        
        nameField= new JTextField(user.getName());
        nameField.setBackground(Color.white);
        nameField.setBounds(80, 45, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(nameField);
        
        JLabel id=new JLabel("Id:");
        id.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(id);
        
        idField= new JTextField(user.getId());
        idField.setBackground(Color.white);
        idField.setBounds(80, 80, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(idField);
        
        JLabel password=new JLabel("Password:");
        password.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(password);
        
        passwordField= new JPasswordField(user.getPassword());
        passwordField.setBackground(Color.white);
        passwordField.setBounds(80, 115, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(passwordField);
        
        JLabel money=new JLabel("Money:");
        money.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(money);
        
        moneyField= new JTextField(String.valueOf(user.getMoney()));
        moneyField.setBackground(Color.white);
        moneyField.setBounds(80, 150, 300, 20);
        moneyField.setEditable(false);
        ((JPanel)father.getComponentAt(0)).add(moneyField);
        
        JButton setB = new JButton("Save changes");
        setB.setBounds(100,this.getHeight()-200, 200, 40);
        ((JPanel)father.getComponentAt(0)).add(setB);
        setB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String oldId = user.getId();
                user.setId(idField.getText().toLowerCase());
                user.setName(nameField.getText());
                user.setPassword(String.valueOf(passwordField.getPassword()));
                user.modifyUser(user,oldId);
                JOptionPane.showMessageDialog(null, "Data updated correctly");
            }
        }); 
        
    }
    
            

    public void updatecartList(RegisteredUser user){
       DefaultListModel modelo = new DefaultListModel();
        for (BasicDBObject obj : user.getShoppingCart()) {
            modelo.addElement((BasicDBObject)new Test(obj));
        }
        cartList.setModel(modelo);
    }
    
    
    public void updatehistorialList(LinkedList<BasicDBObject> list){
        DefaultListModel modelo = new DefaultListModel();
        for (Object obj : list) {
            if(obj.getClass().equals(String.class)){
                modelo.addElement((String)obj);
            }else{
                modelo.addElement((BasicDBObject)new Test((BasicDBObject)obj));
            }
        }
        historialList.setModel(modelo);    
    }

    
}


package GUI;

import com.mongodb.BasicDBObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ScrollPaneLayout;
import shop2.Admin;

// COMENTARIO ANHADIDO PARA PROBAR GIT :p
public class EditionArticles extends JFrame{
    JTabbedPane father= new JTabbedPane();
    short tab=0;
    Admin admin;
    Window win;
    BasicDBObject modded=new BasicDBObject();
    
    public EditionArticles(Admin admin, Window win){
        
        
        this.admin=admin;
        this.win=win;
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(410, 400);
        this.setTitle("Edit Item");
        this.setLayout(null);
        this.setLocation(945, 164);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        zoneManager(tab);
        adminEditBooks(this);
        adminEditCDs(this);
        adminEditMovies(this);
        //BasicDBObject modded=new BasicDBObject();
    }
    
    public void setTabModded(Short tab, BasicDBObject modded){
        this.tab = tab;
        this.modded=modded;
        switch(tab){
            case 0:
                father.setEnabledAt(1, false);
                father.setEnabledAt(2, false);
                father.setEnabledAt(0, true);
                ((JTextField)((JPanel)father.getComponent(0)).getComponent(3)).setText((String)modded.get("title"));
                ((JTextField)((JPanel)father.getComponent(0)).getComponent(5)).setText((String)modded.get("author"));
                ((JTextField)((JPanel)father.getComponent(0)).getComponent(7)).setText((String)modded.get("editorial"));
                ((JTextField)((JPanel)father.getComponent(0)).getComponent(9)).setText(String.valueOf(modded.get("stock")));
                ((JTextField)((JPanel)father.getComponent(0)).getComponent(11)).setText(String.valueOf(modded.get("price")));
                ((JTextArea)((JViewport)((JScrollPane)((JPanel)father.getComponent(0)).getComponent(13)).getComponent(0)).getComponent(0)).setText((String)modded.get("description"));
                break;
            case 1:
                father.setEnabledAt(0, false);
                father.setEnabledAt(2, false);
                father.setEnabledAt(1, true);
                ((JTextField)((JPanel)father.getComponent(1)).getComponent(3)).setText((String)modded.get("title"));
                ((JTextField)((JPanel)father.getComponent(1)).getComponent(5)).setText((String)modded.get("author"));
                ((JTextField)((JPanel)father.getComponent(1)).getComponent(7)).setText((String)modded.get("year"));
                ((JTextField)((JPanel)father.getComponent(1)).getComponent(9)).setText(String.valueOf(modded.get("stock")));
                ((JTextField)((JPanel)father.getComponent(1)).getComponent(11)).setText(String.valueOf(modded.get("price")));
                ((JTextArea)((JViewport)((JScrollPane)((JPanel)father.getComponent(1)).getComponent(13)).getComponent(0)).getComponent(0)).setText((String)modded.get("description"));
                break;
            case 2:
                father.setEnabledAt(0, false);
                father.setEnabledAt(1, false);
                father.setEnabledAt(2, true);
                ((JTextField)((JPanel)father.getComponent(2)).getComponent(3)).setText((String)modded.get("title"));
                ((JTextField)((JPanel)father.getComponent(2)).getComponent(5)).setText((String)modded.get("author"));
                ((JTextField)((JPanel)father.getComponent(2)).getComponent(7)).setText((String)modded.get("actors"));
                ((JTextField)((JPanel)father.getComponent(2)).getComponent(9)).setText(String.valueOf(modded.get("stock")));
                ((JTextField)((JPanel)father.getComponent(2)).getComponent(11)).setText(String.valueOf(modded.get("price")));
                ((JTextArea)((JViewport)((JScrollPane)((JPanel)father.getComponent(2)).getComponent(13)).getComponent(0)).getComponent(0)).setText((String)modded.get("description"));
                break;
        }
        father.setSelectedIndex(tab);
        
    }
    
    private void zoneManager(short tabNum){
        Color color=Color.lightGray;
        father.setBackground(color);
        
                
        JPanel editBookP=new JPanel();
        editBookP.setLayout(new GroupLayout(editBookP));
        editBookP.setName("Edit Book");
        
        JPanel editCDP=new JPanel();
        editCDP.setLayout(new GroupLayout(editCDP));
        editCDP.setName("Edit Music");
        
        JPanel editMovieP=new JPanel();
        editMovieP.setLayout(new GroupLayout(editMovieP));
        editMovieP.setName("Edit Movies");
        
        editBookP.setBackground(color);
        editCDP.setBackground(color);
        editMovieP.setBackground(color);
        
        father.add(editBookP);
        father.add(editCDP);
        father.add(editMovieP);
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        
        
        
        this.add(father);
        
        
        
    }
    
   
    
    public void adminEditBooks(final EditionArticles ea){
        JLabel disclaimer=new JLabel("Fill all the information required to fix the book:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(0)).add(disclaimer);
        
        JButton fix=new JButton("Fix");
        fix.setBounds(307, 8, 60, 30);
        ((JPanel)father.getComponentAt(0)).add(fix);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(title);
        
        final JTextField titleField= new JTextField();
        
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(titleField);
        
        JLabel author=new JLabel("Author:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(author);
        
        final JTextField authorField= new JTextField();
        
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(authorField);
        
        JLabel editorial=new JLabel("Editorial:");
        editorial.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(editorial);
        
        final JTextField editorialField= new JTextField();
        
        editorialField.setBackground(Color.white);
        editorialField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(editorialField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(stock);
        
        final JTextField stockField= new JTextField();
        
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(price);
        
        final JTextField priceField= new JTextField();
        
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        ((JPanel)father.getComponentAt(0)).add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        ((JPanel)father.getComponentAt(0)).add(description);
        
        final JTextArea descriptionArea = new JTextArea();
        
        descriptionArea.setBackground(Color.white);
        
        JScrollPane scroll= new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(descriptionArea);
        scroll.setBounds(10, 250, 385, 80);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(0)).add(scroll);
        //System.out.println(((JViewport)scroll.getComponent(0)).getComponent(0));
        
        
        fix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = stockField.getText();
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        
                        pin = false;
                        break;
                    }
                }
                cad=priceField.getText();
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if(('0'>car || car >'9') && car!='.'){
                        
                        pin = false;
                        break;
                    }
                }
                if(titleField.getText().equals("") || authorField.getText().equals("") || editorialField.getText().equals("") || stockField.getText().equals("") || priceField.getText().equals("") || descriptionArea.getText().equals("")){
                    pin=false;
                }
                if(pin){
                    admin.modifyBook((String)modded.get("id"),titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(),editorialField.getText().toLowerCase() , Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()) );
                    ea.setVisible(false);
                    win.updatebookList();
                    titleField.setText("");
                    authorField.setText("");
                    editorialField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "Book Modified");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fields incorrect or missing, please check");
                }
                                
            }
        });
        
    }
    
    private void adminEditCDs(final EditionArticles ea){
        JLabel disclaimer=new JLabel("Fill all the information required to add the CD:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(1)).add(disclaimer);
        
        JButton fix=new JButton("Fix");
        fix.setBounds(307, 8, 60, 30);
        ((JPanel)father.getComponentAt(1)).add(fix);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(title);
        
        final JTextField titleField= new JTextField();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(titleField);
        
        JLabel author=new JLabel("Artist:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(author);
        
        final JTextField authorField= new JTextField();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(authorField);
        
        JLabel year=new JLabel("Year:");
        year.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(year);
        
        final JTextField yearField= new JTextField();
        yearField.setBackground(Color.white);
        yearField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(yearField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(stock);
        
        final JTextField stockField= new JTextField();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(price);
        
        final JTextField priceField= new JTextField();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(description);
        
        final JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBackground(Color.white);
        
        JScrollPane scroll= new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(descriptionArea);
        scroll.setBounds(10, 250, 385, 80);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(1)).add(scroll);
        
        fix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = stockField.getText();
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        
                        pin = false;
                        break;
                    }
                }
                cad=priceField.getText();
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if(('0'>car || car >'9') && car!='.'){
                        
                        pin = false;
                        break;
                    }
                }
                if(titleField.getText().equals("") || authorField.getText().equals("") || yearField.getText().equals("") || stockField.getText().equals("") || priceField.getText().equals("") || descriptionArea.getText().equals("")){
                    pin=false;
                }
                if(pin){
                    admin.modifyCD((String)modded.get("id"),titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(),yearField.getText().toLowerCase() , Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()) );
                    ea.setVisible(false);
                    win.updatecdList();
                    titleField.setText("");
                    authorField.setText("");
                    yearField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "CD Modified");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fields incorrect or missing, please check");
                }
                                
            }
        });
        
        
    }
    
    private void adminEditMovies(final EditionArticles ea){
        JLabel disclaimer=new JLabel("Fill all the information required to add the Movie:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(2)).add(disclaimer);
        
        JButton fix=new JButton("Fix");
        fix.setBounds(307, 8, 60, 30); 
        ((JPanel)father.getComponentAt(2)).add(fix);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(title);
        
        final JTextField titleField= new JTextField();
//        titleField.setText((String)modded.get("title"));
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(titleField);
        
        JLabel author=new JLabel("Director:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(author);
        
        final JTextField authorField= new JTextField();
     //   authorField.setText((String)modded.get("author"));
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(authorField);
        
        JLabel actors=new JLabel("Actors:");
        
        actors.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(actors);
        
        final JTextField actorsField= new JTextField();
       // actorsField.setText((String)modded.get("actors"));
        actorsField.setBackground(Color.white);
        actorsField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(actorsField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(stock);
        
        final JTextField stockField= new JTextField();
       // stockField.setText(String.valueOf(modded.get("stock")));
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(price);
        
        final JTextField priceField= new JTextField();
      //  priceField.setText(String.valueOf(modded.get("price")));
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(description);
        
        final JTextArea descriptionArea = new JTextArea();
       // descriptionArea.setText((String)modded.get("description"));
        descriptionArea.setBackground(Color.white);
        
        JScrollPane scroll= new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(descriptionArea);
        scroll.setBounds(10, 250, 385, 80);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(2)).add(scroll);
        
        fix.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = stockField.getText();
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        
                        pin = false;
                        break;
                    }
                }
                cad=priceField.getText();
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if(('0'>car || car >'9') && car!='.'){
                        
                        pin = false;
                        break;
                    }
                }
                if(titleField.getText().equals("") || authorField.getText().equals("") || actorsField.getText().equals("") || stockField.getText().equals("") || priceField.getText().equals("") || descriptionArea.getText().equals("")){
                    pin=false;
                }
                if(pin){
                    admin.modifyMovie((String)modded.get("id"),titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(),authorField.getText().toLowerCase(), actorsField.getText().toLowerCase() , Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()) );
                    ea.setVisible(false);
                    win.updatemovieList();
                    titleField.setText("");
                    authorField.setText("");
                    actorsField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "Movie Modified");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Fields incorrect or missing, please check");
                }
                                
            }
        });
        
        
    }
}

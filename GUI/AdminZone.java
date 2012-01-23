
package GUI;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import shop2.Admin;
import shop2.Book;
import shop2.Cd;
import shop2.Movie;
import shop2.Test;
import shop2.TestUser;

public class AdminZone extends JFrame {
    JList userList;
    JTabbedPane father= new JTabbedPane();
    Admin admin;
    Window window;
    BasicDBObject aux;
    
    
    public AdminZone(Admin admin, Window window){
        this.admin=admin;
        this.window=window;
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(410, 400);
        this.setTitle("Admin Zone");
        this.setLayout(null);
        this.setLocation(945, 164);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        
        zoneManager();
        adminGeneral();
        adminAddBooks();
        adminAddCDs();
        adminAddMovies();
        adminManageAdmin();
        
    }
    
    private void zoneManager(){
        Color color=Color.lightGray;
        father.setBackground(color);
        
        JPanel generalP=new JPanel();
        generalP.setLayout(new GroupLayout(generalP));
        generalP.setName("General");
                
        JPanel addBookP=new JPanel();
        addBookP.setLayout(new GroupLayout(addBookP));
        addBookP.setName("Add Books");
        
        JPanel addCDP=new JPanel();
        addCDP.setLayout(new GroupLayout(addCDP));
        addCDP.setName("Add Music");
        
        JPanel addMovieP=new JPanel();
        addMovieP.setLayout(new GroupLayout(addMovieP));
        addMovieP.setName("Add Movies");
        
        JPanel manageAdminP=new JPanel();
        manageAdminP.setLayout(new GroupLayout(manageAdminP));
        manageAdminP.setName("Manage Admin");
        
        generalP.setBackground(color);
        addBookP.setBackground(color);
        addCDP.setBackground(color);
        addMovieP.setBackground(color);
        manageAdminP.setBackground(color);
        
        father.add(generalP);
        father.add(addBookP);
        father.add(addCDP);
        father.add(addMovieP);
        father.add(manageAdminP);
        father.setBounds(0,0,this.getWidth(), this.getHeight());
        
        
        this.add(father);
        
        
        
    }
    
    private void adminGeneral(){
        userList = new JList();
        userList.setBackground(Color.white);
        updateuserList();
        JScrollPane scroll= new JScrollPane(userList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(userList);
        scroll.setBounds(3, 3, 395, 250);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(0)).add(scroll);
        
        JButton searchUserID=new JButton("Search User ID");
        searchUserID.setBounds(3, 253, 185, 35);
        ((JPanel)father.getComponentAt(0)).add(searchUserID);
        
        final JTextField userField=new JTextField();
        userField.setBackground(Color.white);
        userField.setBounds(190, 258, 185, 23);
        ((JPanel)father.getComponentAt(0)).add(userField);
        
            
        JButton showUsers=new JButton("Show all users");
        showUsers.setBounds(3, 307, 185, 35);
        ((JPanel)father.getComponentAt(0)).add(showUsers);
        
        final JButton modifyUser=new JButton("Modify User Credit");
        modifyUser.setBounds(3, 280, 185, 35);
        modifyUser.setEnabled(false);
        ((JPanel)father.getComponentAt(0)).add(modifyUser);
        
        final JTextField creditField=new JTextField();
        creditField.setBackground(Color.white);
        creditField.setBounds(190, 284, 185, 23);
        ((JPanel)father.getComponentAt(0)).add(creditField);
        
        JButton showItems=new JButton("Show all Items");
        showItems.setBounds(190, 306, 190, 35);
        ((JPanel)father.getComponentAt(0)).add(showItems);
        
        final JButton delete=new JButton("Delete User");
        
        final JPopupMenu menu = new JPopupMenu();
        menu.add(delete);
        
        
        //Listeners
        showUsers.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateuserList();
            }
        });
        
        searchUserID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateuserList(admin.findUserID(userField.getText()));
            }
        });
        
        userList.addMouseListener(new MouseListener() {
            

            @Override
            public void mouseClicked(MouseEvent me) {
                
                
                menu.setVisible(false);
                if(me.getClickCount()==2 && me.getButton()==MouseEvent.BUTTON1 && !userList.getSelectedValue().getClass().getName().equals("shop2.Test")){
                      aux=((TestUser)userList.getSelectedValue()).getData();
                      modifyUser.setEnabled(true);    
                    }
                if(me.getButton()==MouseEvent.BUTTON3 && !userList.isSelectionEmpty() && !userList.getSelectedValue().getClass().getName().equals("shop2.Test")){
                      aux=((TestUser)userList.getSelectedValue()).getData();
                      menu.setLocation(me.getLocationOnScreen());
                      menu.setVisible(true);
                      delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            admin.deleteUser((String)aux.get("id"));
                            menu.setVisible(false);
                            updateuserList();
                        }
                      });
                        
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        modifyUser.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
              if(!creditField.getText().equals("")){  
                admin.modifyUser((String)(((TestUser)userList.getSelectedValue()).getData().get("name")), (String)(((TestUser)userList.getSelectedValue()).getData().get("id")),(String)(((TestUser)userList.getSelectedValue()).getData().get("password")), Double.parseDouble(creditField.getText()) );
                modifyUser.setEnabled(false);
                creditField.setText("");
                updateuserList();
              }
            }
        });
        
        showItems.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updateallList();
            }
        });
    }
    
    private void adminAddBooks(){
        JLabel disclaimer=new JLabel("Fill all the information required to add the book:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(1)).add(disclaimer);
        
        JButton add=new JButton("Add");
        add.setBounds(307, 8, 60, 30);
        ((JPanel)father.getComponentAt(1)).add(add);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(title);
        
        final JTextField titleField= new JTextField();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(titleField);
        
        JLabel author=new JLabel("Author:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(author);
        
        final JTextField authorField= new JTextField();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(authorField);
        
        JLabel editorial=new JLabel("Editorial:");
        editorial.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(1)).add(editorial);
        
        final JTextField editorialField= new JTextField();
        editorialField.setBackground(Color.white);
        editorialField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(1)).add(editorialField);
        
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
        
        add.addActionListener(new ActionListener() {

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
                    Book newBook=new Book(titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(), editorialField.getText().toLowerCase(), Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()));
                    admin.addBook(newBook);
                    window.updatebookList();
                    authorField.setText("");
                    titleField.setText("");
                    editorialField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "Book  added, all fields in lower case");
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more Fields missing or incorrect, Book not added");
                }
            }
        });
        
        
        
        
    }
    
    private void adminAddCDs(){
        JLabel disclaimer = new JLabel("Fill all the information required to add the Album:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(2)).add(disclaimer);
        
        JButton add=new JButton("Add");
        add.setBounds(307, 8, 60, 30);
        ((JPanel)father.getComponentAt(2)).add(add);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(title);
        
        final JTextField titleField= new JTextField();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(titleField);
        
        JLabel author=new JLabel("Artist:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(author);
        
        final JTextField authorField= new JTextField();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(authorField);
        
        JLabel year=new JLabel("Year:");
        year.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(year);
        
        final JTextField yearField= new JTextField();
        yearField.setBackground(Color.white);
        yearField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(yearField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(stock);
        
        final JTextField stockField= new JTextField();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(price);
        
        final JTextField priceField= new JTextField();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        ((JPanel)father.getComponentAt(2)).add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        ((JPanel)father.getComponentAt(2)).add(description);
        
        final JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBackground(Color.white);
        
        JScrollPane scroll= new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(descriptionArea);
        scroll.setBounds(10, 250, 385, 80);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(2)).add(scroll);
        
        add.addActionListener(new ActionListener() {

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
                    Cd newCd=new Cd(titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(), yearField.getText().toLowerCase(), Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()));
                    admin.addCd(newCd);
                    window.updatecdList();
                    authorField.setText("");
                    titleField.setText("");
                    yearField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "CD  added, all fields in lower case");
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more Fields missing or incorrect, CD not added");
                }
            }
        });
    }
    
    private void adminAddMovies(){
        JLabel disclaimer=new JLabel("Fill all the information required to add the Movie:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(3)).add(disclaimer);
        
        JButton add=new JButton("Add");
        add.setBounds(307, 8, 60, 30);
        ((JPanel)father.getComponentAt(3)).add(add);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(title);
        
        final JTextField titleField= new JTextField();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        ((JPanel)father.getComponentAt(3)).add(titleField);
        
        JLabel author=new JLabel("Director:");
        author.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(author);
        
        final JTextField authorField= new JTextField();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        ((JPanel)father.getComponentAt(3)).add(authorField);
        
        JLabel actors=new JLabel("Actors:");
        actors.setBounds(10, 110, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(actors);
        
        final JTextField actorsField= new JTextField();
        actorsField.setBackground(Color.white);
        actorsField.setBounds(60, 115, 300, 20);
        ((JPanel)father.getComponentAt(3)).add(actorsField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(stock);
        
        final JTextField stockField= new JTextField();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        ((JPanel)father.getComponentAt(3)).add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(price);
        
        final JTextField priceField= new JTextField();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        ((JPanel)father.getComponentAt(3)).add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        ((JPanel)father.getComponentAt(3)).add(description);
        
        final JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBackground(Color.white);
        
        JScrollPane scroll= new JScrollPane(descriptionArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(descriptionArea);
        scroll.setBounds(10, 250, 385, 80);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(3)).add(scroll);
        
        add.addActionListener(new ActionListener() {

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
                    Movie newMovie=new Movie(titleField.getText().toLowerCase(), authorField.getText().toLowerCase(), descriptionArea.getText().toLowerCase(), authorField.getText().toLowerCase(),actorsField.getText().toLowerCase(), Integer.parseInt(stockField.getText()), Double.parseDouble(priceField.getText()));
                    admin.addMovie(newMovie);
                    window.updatemovieList();
                    authorField.setText("");
                    titleField.setText("");
                    actorsField.setText("");
                    stockField.setText("");
                    priceField.setText("");
                    descriptionArea.setText("");
                    JOptionPane.showMessageDialog(null, "Movie  added, all fields in lower case");
                }
                else{
                    JOptionPane.showMessageDialog(null, "One or more Fields missingor incorrect, Movie not added");
                }
            }
            
            
        });
        
        
    }
    
    private void adminManageAdmin(){
        JLabel disclaimer=new JLabel("Change This Admin's Password:");
        disclaimer.setBounds(10, 5, 300, 35);
        ((JPanel)father.getComponentAt(4)).add(disclaimer);
        
        JButton change=new JButton("Change");
        change.setBounds(307, 8, 90, 30);
        ((JPanel)father.getComponentAt(4)).add(change);
        
        JLabel oldPassword=new JLabel("Old Password:");
        oldPassword.setBounds(10, 40, 100, 35);
        ((JPanel)father.getComponentAt(4)).add(oldPassword);
        
        final JPasswordField oldPasswordField= new JPasswordField();
        oldPasswordField.setBackground(Color.white);
        oldPasswordField.setBounds(100, 45, 290, 20);
        ((JPanel)father.getComponentAt(4)).add(oldPasswordField);
        
        JLabel newPassword=new JLabel("New Password:");
        newPassword.setBounds(10, 75, 100, 35);
        ((JPanel)father.getComponentAt(4)).add(newPassword);
        
        final JPasswordField newPasswordField= new JPasswordField();
        newPasswordField.setBackground(Color.white);
        newPasswordField.setBounds(100, 80, 290, 20);
        ((JPanel)father.getComponentAt(4)).add(newPasswordField);
        
        JLabel disclaimer2=new JLabel("To add a new Admin fill the Fields below");
        disclaimer2.setBounds(10, 110, 300, 35);
        ((JPanel)father.getComponentAt(4)).add(disclaimer2);
        
        JButton add=new JButton("Add");
        add.setBounds(307, 113, 90, 30);
        ((JPanel)father.getComponentAt(4)).add(add);
        
        JLabel userName=new JLabel("Usermame:");
        userName.setBounds(10, 145, 100, 35);
        ((JPanel)father.getComponentAt(4)).add(userName);
        
        final JTextField userNameField= new JTextField();
        userNameField.setBackground(Color.white);
        userNameField.setBounds(100, 150, 290, 20);
        ((JPanel)father.getComponentAt(4)).add(userNameField);
        
        JLabel password=new JLabel("Password:");
        password.setBounds(10, 180, 100, 35);
        ((JPanel)father.getComponentAt(4)).add(password);
        
        final JPasswordField passwordField= new JPasswordField();
        passwordField.setBackground(Color.white);
        passwordField.setBounds(100, 185, 290, 20);
        ((JPanel)father.getComponentAt(4)).add(passwordField);
        
        JLabel disclaimer3=new JLabel("To delete an Admin use it's username or id");
        disclaimer3.setBounds(10, 215, 300, 35);
        ((JPanel)father.getComponentAt(4)).add(disclaimer3);
        
        JButton delete=new JButton("Delete");
        delete.setBounds(307, 218, 90, 30);
        ((JPanel)father.getComponentAt(4)).add(delete);
        
        JLabel id=new JLabel("Username/id:");
        id.setBounds(10, 250, 100, 35);
        ((JPanel)father.getComponentAt(4)).add(id);
        
        final JTextField idField= new JTextField();
        idField.setBackground(Color.white);
        idField.setBounds(100, 255, 290, 20);
        ((JPanel)father.getComponentAt(4)).add(idField);       
        
        change.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if((String.valueOf(oldPasswordField.getPassword())).equals(admin.getPassword())){
                    admin.modifyAdmin(admin.getId(), String.valueOf(newPasswordField.getPassword()));
                    oldPasswordField.setText("");
                    newPasswordField.setText("");
                    JOptionPane.showMessageDialog(null, "Password Changed");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Old password is not correct");
                }
            }
            
        });
        add.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userNameField.getText().equals("") && !String.valueOf(passwordField.getPassword()).equals("")){
                    Admin newAdmin=new Admin(window.m, userNameField.getText(), String.valueOf(passwordField.getPassword()));
                    admin.addAdmin(newAdmin);
                    userNameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(null, "Admin added");
                }else{
                    JOptionPane.showMessageDialog(null, "Fields Missing, Admin not added");
                }
            }
        });
        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = idField.getText();
                BasicDBObject query = new BasicDBObject().append("id", username);
                DBObject find = admin.getAdmin().findOne(query);
                if(find!=null){
                    admin.deleteAdmin(idField.getText());
                    JOptionPane.showMessageDialog(null, "Admin deleted");
                    idField.setText("");
                }else{
                    JOptionPane.showMessageDialog(null, "User not found or Fields Missing");
                }
            }
        });
        
        
        
    }
    
    private void updateuserList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = admin.getUsers().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new TestUser((BasicDBObject)cur.next()));
        }
        userList.setModel(modelo);
    }
    
    private void updateuserList(BasicDBObject user){
        DefaultListModel modelo = new DefaultListModel();
        modelo.addElement((BasicDBObject)new TestUser(user));
        userList.setModel(modelo);
    }
    
    public void updateallList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = window.shop.getBooks().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        cur = window.shop.getCds().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        cur = window.shop.getMovies().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        userList.setModel(modelo);
    }
    
    
}

package GUI;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.ButtonGroup;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import shop2.Admin;
import shop2.Shop;
import shop2.Test;
import shopU.RegisteredUser;


public final class Window extends JFrame  {
    JTabbedPane father = new JTabbedPane();
    JList bookList, movieList, cartList, cdList, godList;
    JButton addB,addC,addM;
    Color color = Color.lightGray;
    Mongo m;
    Shop shop;
    Admin admin;
    RegisteredUser user;
    UserZone userWindow;
    AdminZone adminWindow;
    RegisterUser registerWindow;
    EditionArticles editionWindow;
    ShowArticles showWindow;
    private BasicDBObject aux;
    int type =0;
        
    
    
    public Window(){
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500, 400);
        this.setTitle("ShopManager-1.0.6.3e.1");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        try {
            m = new Mongo();
        } catch (UnknownHostException ex) {
            System.out.println("No conection");
        } catch (MongoException ex) {
            System.out.println("No conection");
        }
        shop = new Shop(m);
        admin = new Admin(m,null,null);
        user = new RegisteredUser(0, null,null,null, m);
        userWindow = new UserZone(user,this);
        adminWindow = new AdminZone(admin,this);
        editionWindow=new EditionArticles(admin,this);
        registerWindow = new RegisterUser(user, this);
        
        showWindow=new ShowArticles();
        
        
        
        adminSwitch();
        adminMovies();
        adminBooks();
        adminCD();
        adminLogin();
        
    }
    
    private void adminSwitch(){
        JPanel bookP = new JPanel();
        JPanel movieP = new JPanel();
        JPanel cdP = new JPanel();
        
        
        
        //Esto es very importante.
        bookP.setLayout(new GroupLayout(bookP));
        movieP.setLayout(new GroupLayout(movieP));
        cdP.setLayout(new GroupLayout(cdP));
        
        
        father.setBounds(0,0,this.getWidth(), this.getHeight() - 100);
        father.setBackground(color);
        bookP.setBackground(color);
        movieP.setBackground(color);
        cdP.setBackground(color);
        
        bookP.setName("Books");
        movieP.setName("Movies");
        cdP.setName("CD'S");
        
        father.add(bookP);
        father.add(movieP);
        father.add(cdP);
        this.add(father);
        
                
    }
    
    private void adminBooks(){
        this.bookList = new JList();
        
        bookList.setAutoscrolls(true);
        bookList.setBackground(Color.WHITE);
        bookList.setForeground(Color.DARK_GRAY);
        bookList.setValueIsAdjusting(true);
        
        
        updatebookList();
        
        JScrollPane scroll = new JScrollPane(bookList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(bookList);
        scroll.setBounds(5, 5, 482, this.getHeight()-220);
        scroll.setLayout(new ScrollPaneLayout());
        
        ((JPanel)father.getComponentAt(0)).add(scroll);
        JButton searchBook =new JButton();
        searchBook.setText("Search Books");
        searchBook.setBounds(0, 185, 150, 35);
        searchBook.setBackground(color);
        ((JPanel)father.getComponentAt(0)).add(searchBook);
        
        JButton searchID =new JButton();
        searchID.setText("Search ID");
        searchID.setBounds(0, 211, 150, 35);
        searchID.setBackground(color);
        ((JPanel)father.getComponentAt(0)).add(searchID);
        
        addB =new JButton();
        addB.setText("Add to Cart");
        addB.setBounds(0, 237, 150, 35);
        addB.setBackground(color);
        addB.setEnabled(false);
        ((JPanel)father.getComponentAt(0)).add(addB);
        
        JButton showItem =new JButton();
        showItem.setText("Show All");
        showItem.setBounds(273, 237, 120, 35);
        showItem.setBackground(color);
        ((JPanel)father.getComponentAt(0)).add(showItem);
        
        JLabel qty=new JLabel("Quantity:");
        qty.setBounds(155, 237, 50, 35);
        ((JPanel)father.getComponentAt(0)).add(qty);
        
        final JTextField qtyField=new JTextField();
        qtyField.setBounds(205, 245, 72, 20);
        qtyField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(0)).add(qtyField);
        
        final JTextField searchField = new JTextField();
        searchField.setBounds(155, 192, 230, 20);
        searchField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(0)).add(searchField);
        
        final JTextField searchFieldID = new JTextField();
        searchFieldID.setBounds(155, 217, 230, 20);
        searchFieldID.setBackground(Color.white);
        ((JPanel)father.getComponentAt(0)).add(searchFieldID);
        
        JLabel searchFor=new JLabel("Search by:");
        ((JPanel)father.getComponentAt(0)).add(searchFor);
        searchFor.setBounds(395, 180, 100, 35);
        ButtonGroup group=new ButtonGroup();
        final JRadioButton bAuthor=new JRadioButton();
        final JRadioButton bAll=new JRadioButton();
        final JRadioButton bTitle=new JRadioButton();
        final JRadioButton bEditorial=new JRadioButton();
        bAuthor.setBounds(395,210, 100, 12);
        bAuthor.setBackground(color);
        bAuthor.setText("Author.");
        bTitle.setBounds(395,225, 100, 12);
        bTitle.setBackground(color);
        bTitle.setText("Title.");
        bEditorial.setBounds(395,240, 100, 12);
        bEditorial.setBackground(color);
        bEditorial.setText("Editorial.");
        bAll.setBounds(395,255, 100, 12);
        bAll.setBackground(color);
        bAll.setText("All.");
        group.add(bAuthor);
        group.add(bAll);
        group.add(bTitle);
        group.add(bEditorial);
        ((JPanel)father.getComponentAt(0)).add(bAuthor);
        ((JPanel)father.getComponentAt(0)).add(bAll);
        ((JPanel)father.getComponentAt(0)).add(bTitle);
        ((JPanel)father.getComponentAt(0)).add(bEditorial);
        
        final JButton delete=new JButton("Delete Item");
        final JButton modify=new JButton("Modify Item");
        final JPopupMenu menu = new JPopupMenu();
        menu.add(delete);
        menu.add(modify);
        
        
        
        //Listeners
        showItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updatebookList();
            }
        }); 
        searchBook.addActionListener(new ActionListener() {
            /**
             Cases 0: all
             *     1: title
             *     2: author.
             *     3: editorial.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<BasicDBObject> list = null;
                String cad = searchField.getText().toLowerCase();
                if(!cad.equals("")){
                    switch(type){
                        case 0:
                            list = shop.findBookEditorial(cad);
                            for (BasicDBObject obj : shop.findBookAuthor(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            for (BasicDBObject obj : shop.findBookTitle(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            break;
                        case 1:
                            list = shop.findBookTitle(cad);
                            break;
                        case 2:
                            list = shop.findBookAuthor(cad);
                            break;
                        case 3:
                            list = shop.findBookEditorial(cad);
                            break;
                    }
                    updatebookList(list);
                }
            }
        }); 
        bAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 0;
            }
        }); 
       
        bTitle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
            }
        }); 
        
        bAuthor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 2;
            }
        }); 
        
        bEditorial.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 3;
            }
        }); 
        
        searchID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = searchFieldID.getText().toLowerCase();
                LinkedList<BasicDBObject> list = new LinkedList<BasicDBObject>();
                if(!cad.equals("")){
                    BasicDBObject hola = shop.findBookID(cad);
                    if(hola!=null)
                        list.add(hola);
                    updatebookList(list);
                }
            }
        });
        
        addB.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = qtyField.getText();
                int cant;
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        pin = false;
                        break;
                    }
                }
                if(cad.equals(""))
                        cant = 1;
                else
                     cant = Integer.parseInt(cad);
                if(bookList.isSelectionEmpty())
                    pin=false;
                if(pin){
                    if(!user.addToCart(((Test)bookList.getSelectedValue()).getData(),cant)){
                        JOptionPane.showMessageDialog(null, "No Stock! D:");                        
                    }else{
                        userWindow.updatecartList(user);
                    }
                }
            }
        }); 
        
        bookList.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent me) {
                menu.setVisible(false);
                
                if(me.getClickCount()==2 && me.getButton()==MouseEvent.BUTTON1){
                    aux=((Test)bookList.getSelectedValue()).getData();
                    showWindow.setTab(Short.parseShort("0"), aux); 
                    showWindow.setVisible(true);
                    }
                
                if(me.getButton()==MouseEvent.BUTTON3 && !bookList.isSelectionEmpty() && admin.getId()!=null){
                      aux=((Test)bookList.getSelectedValue()).getData();
                      menu.setLocation(me.getLocationOnScreen());
                      menu.setVisible(true);
                      delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            admin.deleteBook((String)aux.get("id"));
                            menu.setVisible(false);
                            updatebookList();
                        }
                      });
                      modify.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            editionWindow.setTabModded(Short.parseShort("0"), aux);
                            menu.setVisible(false);
                            editionWindow.setVisible(true);
                        }
                    });
                        
                }
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }); 
        
    }
    
    public void adminMovies(){
        this.movieList = new JList();
        
        movieList.setAutoscrolls(true);
        movieList.setBackground(Color.WHITE);
        movieList.setForeground(Color.DARK_GRAY);
        movieList.setValueIsAdjusting(true);
        
        
        updatemovieList();
        JScrollPane scroll = new JScrollPane(movieList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(movieList);
        scroll.setBounds(5, 5, 482, this.getHeight()-220);
        scroll.setLayout(new ScrollPaneLayout());
        
        ((JPanel)father.getComponentAt(1)).add(scroll);
        JButton searchMovie =new JButton();
        searchMovie.setText("Search Movies");
        searchMovie.setBounds(0, 185, 150, 35);
        searchMovie.setBackground(color);
        ((JPanel)father.getComponentAt(1)).add(searchMovie);
        
        JButton searchID =new JButton();
        searchID.setText("Search ID");
        searchID.setBounds(0, 211, 150, 35);
        searchID.setBackground(color);
        ((JPanel)father.getComponentAt(1)).add(searchID);
        
        addM =new JButton();
        addM.setText("Add to Cart");
        addM.setBounds(0, 237, 150, 35);
        addM.setBackground(color);
        addM.setEnabled(false);
        ((JPanel)father.getComponentAt(1)).add(addM);
        
        JButton showItem =new JButton();
        showItem.setText("Show All");
        showItem.setBounds(273, 237, 120, 35);
        showItem.setBackground(color);
        ((JPanel)father.getComponentAt(1)).add(showItem);
        
        JLabel qty=new JLabel("Quantity:");
        qty.setBounds(155, 237, 50, 35);
        ((JPanel)father.getComponentAt(1)).add(qty);
        
        final JTextField qtyField=new JTextField();
        qtyField.setBounds(205, 245, 72, 20);
        qtyField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(1)).add(qtyField);
        
        final JTextField searchField = new JTextField();
        searchField.setBounds(155, 192, 230, 20);
        searchField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(1)).add(searchField);
        
        final JTextField searchFieldID = new JTextField();
        searchFieldID.setBounds(155, 217, 230, 20);
        searchFieldID.setBackground(Color.white);
        ((JPanel)father.getComponentAt(1)).add(searchFieldID);
        
        JLabel searchFor=new JLabel("Search by:");
        ((JPanel)father.getComponentAt(1)).add(searchFor);
        searchFor.setBounds(395, 180, 100, 35);
        ButtonGroup group=new ButtonGroup();
        JRadioButton bDirector=new JRadioButton();
        JRadioButton bAll=new JRadioButton();
        JRadioButton bTitle=new JRadioButton();
        JRadioButton bActors=new JRadioButton();
        bDirector.setBounds(395,210, 100, 12);
        bDirector.setBackground(color);
        bDirector.setText("Director.");
        bTitle.setBounds(395,225, 100, 12);
        bTitle.setBackground(color);
        bTitle.setText("Title.");
        bActors.setBounds(395,240, 100, 12);
        bActors.setBackground(color);
        bActors.setText("Actors.");
        bAll.setBounds(395,255, 100, 12);
        bAll.setBackground(color);
        bAll.setText("All.");
        group.add(bDirector);
        group.add(bAll);
        group.add(bTitle);
        group.add(bActors);
        ((JPanel)father.getComponentAt(1)).add(bDirector);
        ((JPanel)father.getComponentAt(1)).add(bAll);
        ((JPanel)father.getComponentAt(1)).add(bTitle);
        ((JPanel)father.getComponentAt(1)).add(bActors);
        
        final JButton delete=new JButton("Delete Item");
        final JButton modify=new JButton("Modify Item");
        final JPopupMenu menu = new JPopupMenu();
        menu.add(delete);
        menu.add(modify);
        
        //Listeners
        showItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updatemovieList();
            }
        }); 
        searchMovie.addActionListener(new ActionListener() {
            /**
             Cases 0: all
             *     1: title
             *     2: author.
             *     3: editorial.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<BasicDBObject> list = null;
                String cad = searchField.getText().toLowerCase();
                if(!cad.equals("")){
                    switch(type){
                        case 0:
                            list = shop.findMovieActor(cad);
                            for (BasicDBObject obj : shop.findMovieAuthor(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            for (BasicDBObject obj : shop.findMovieTitle(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            break;
                        case 1:
                            list = shop.findMovieTitle(cad);
                            break;
                        case 2:
                            list = shop.findMovieActor(cad);
                            break;
                        case 3:
                            list = shop.findMovieDirector(cad);
                            break;
                    }
                    updatemovieList(list);
                }
            }
        }); 
        bAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 0;
            }
        }); 
       
        bTitle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
            }
        }); 
        
        bActors.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 2;
            }
        }); 
        
        bDirector.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 3;
            }
        }); 
        
        searchID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = searchFieldID.getText().toLowerCase();
                LinkedList<BasicDBObject> list = new LinkedList<BasicDBObject>();
                if(!cad.equals("")){
                    BasicDBObject hola = shop.findMovieID(cad);
                    if(hola!=null)
                        list.add(hola);
                    updatemovieList(list);
                }
            }
        });
        
        addM.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = qtyField.getText();
                int cant;
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        pin = false;
                        break;
                    }
                }
                if(cad.equals(""))
                        cant = 1;
                else
                     cant = Integer.parseInt(cad);
                if(movieList.isSelectionEmpty())
                    pin=false;
                if(pin){
                    if(!user.addToCart(((Test)movieList.getSelectedValue()).getData(),cant)){
                        JOptionPane.showMessageDialog(null, "No Stock! D:");                        
                    }else{
                        userWindow.updatecartList(user);
                    }
                }
            }
        }); 
        
        movieList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                menu.setVisible(false);
                if(me.getClickCount()==2 && me.getButton()==MouseEvent.BUTTON1){
                    aux=((Test)movieList.getSelectedValue()).getData();
                    showWindow.setTab(Short.parseShort("2"), aux); 
                    showWindow.setVisible(true);
                }
                if(me.getButton()==MouseEvent.BUTTON3 && !movieList.isSelectionEmpty() && admin.getId()!=null){
                      aux=((Test)movieList.getSelectedValue()).getData();
                      menu.setLocation(me.getLocationOnScreen());
                      menu.setVisible(true);
                      delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            admin.deleteMovie((String)aux.get("id"));
                            menu.setVisible(false);
                            updatemovieList();
                        }
                      });
                      modify.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            editionWindow.setTabModded(Short.parseShort("2"), aux);
                            menu.setVisible(false);
                            editionWindow.setVisible(true);
                        }
                    });
                        
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }); 
    }
    
    public void adminCD(){
        this.cdList = new JList();
        
        cdList.setAutoscrolls(true);
        cdList.setBackground(Color.WHITE);
        cdList.setForeground(Color.DARK_GRAY);
        cdList.setValueIsAdjusting(true);
        
        
        updatecdList();
        JScrollPane scroll = new JScrollPane(cdList);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setViewportView(cdList);
        scroll.setBounds(5, 5, 482, this.getHeight()-220);
        scroll.setLayout(new ScrollPaneLayout());
        ((JPanel)father.getComponentAt(2)).add(scroll);
        JButton searchCd =new JButton();
        searchCd.setText("Search CD");
        searchCd.setBounds(0, 185, 150, 35);
        searchCd.setBackground(color);
        ((JPanel)father.getComponentAt(2)).add(searchCd);
        
        JButton searchID =new JButton();
        searchID.setText("Search ID");
        searchID.setBounds(0, 211, 150, 35);
        searchID.setBackground(color);
        ((JPanel)father.getComponentAt(2)).add(searchID);
        
        addC =new JButton();
        addC.setText("Add to Cart");
        addC.setBounds(0, 237, 150, 35);
        addC.setBackground(color);
        addC.setEnabled(false);
        ((JPanel)father.getComponentAt(2)).add(addC);
        
        JButton showItem =new JButton();
        showItem.setText("Show All");
        showItem.setBounds(273, 237, 120, 35);
        showItem.setBackground(color);
        ((JPanel)father.getComponentAt(2)).add(showItem);
        
        JLabel qty=new JLabel("Quantity:");
        qty.setBounds(155, 237, 50, 35);
        ((JPanel)father.getComponentAt(2)).add(qty);
        
        final JTextField qtyField=new JTextField();
        qtyField.setBounds(205, 245, 72, 20);
        qtyField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(2)).add(qtyField);
        
        final JTextField searchField = new JTextField();
        searchField.setBounds(155, 192, 230, 20);
        searchField.setBackground(Color.white);
        ((JPanel)father.getComponentAt(2)).add(searchField);
        
        final JTextField searchFieldID = new JTextField();
        searchFieldID.setBounds(155, 217, 230, 20);
        searchFieldID.setBackground(Color.white);
        ((JPanel)father.getComponentAt(2)).add(searchFieldID);
        
        JLabel searchFor=new JLabel("Search by:");
        ((JPanel)father.getComponentAt(2)).add(searchFor);
        searchFor.setBounds(395, 180, 100, 35);
        ButtonGroup group=new ButtonGroup();
        JRadioButton bAuthor=new JRadioButton();
        JRadioButton bAll=new JRadioButton();
        JRadioButton bTitle=new JRadioButton();
        JRadioButton bYear=new JRadioButton();
        bAuthor.setBounds(395,210, 100, 12);
        bAuthor.setBackground(color);
        bAuthor.setText("Author.");
        bTitle.setBounds(395,225, 100, 12);
        bTitle.setBackground(color);
        bTitle.setText("Title.");
        bYear.setBounds(395,240, 100, 12);
        bYear.setBackground(color);
        bYear.setText("Year.");
        bAll.setBounds(395,255, 100, 12);
        bAll.setBackground(color);
        bAll.setText("All.");
        group.add(bAuthor);
        group.add(bAll);
        group.add(bTitle);
        group.add(bYear);
        ((JPanel)father.getComponentAt(2)).add(bAuthor);
        ((JPanel)father.getComponentAt(2)).add(bAll);
        ((JPanel)father.getComponentAt(2)).add(bTitle);
        ((JPanel)father.getComponentAt(2)).add(bYear);
        final JButton delete=new JButton("Delete Item");
        final JButton modify=new JButton("Modify Item");
        final JPopupMenu menu = new JPopupMenu();
        menu.add(delete);
        menu.add(modify);
        
        //Listeners
        showItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                updatecdList();
            }
        }); 
        searchCd.addActionListener(new ActionListener() {
            /**
             Cases 0: all
             *     1: title
             *     2: author.
             *     3: editorial.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<BasicDBObject> list = null;
                String cad = searchField.getText().toLowerCase();
                if(!cad.equals("")){
                    switch(type){
                        case 0:
                            list = shop.findCDAuthor(cad);
                            for (BasicDBObject obj : shop.findCDTitle(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            for (BasicDBObject obj : shop.findCDYear(cad)) {
                                if(!list.contains(obj))
                                    list.add(obj);
                            }
                            break;
                        case 1:
                            list = shop.findCDTitle(cad);
                            break;
                        case 2:
                            list = shop.findCDAuthor(cad);
                            break;
                        case 3:
                            list = shop.findCDYear(cad);
                            break;
                    }
                    updatecdList(list);
                }
            }
        }); 
        bAll.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 0;
            }
        }); 
       
        bTitle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 1;
            }
        }); 
        
        bAuthor.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 2;
            }
        }); 
        
        bYear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                type = 3;
            }
        }); 
        
        searchID.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = searchFieldID.getText().toLowerCase();
                LinkedList<BasicDBObject> list = new LinkedList<BasicDBObject>();
                if(!cad.equals("")){
                    BasicDBObject hola = shop.findCDID(cad);
                    if(hola!=null)
                        list.add(hola);
                    updatecdList(list);
                }
            }
        });
        
        addC.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String cad = qtyField.getText();
                int cant;
                char car;
                boolean pin = true;
                for (int i = 0; i < cad.length(); i++) {
                    car=cad.charAt(i);
                    if('0'>car || car >'9'){
                        pin = false;
                        break;
                    }
                }
                if(cad.equals(""))
                        cant = 1;
                else
                     cant = Integer.parseInt(cad);
                if(cdList.isSelectionEmpty())
                    pin=false;
                if(pin){
                    if(!user.addToCart(((Test)cdList.getSelectedValue()).getData(),cant)){
                        JOptionPane.showMessageDialog(null, "No Stock! D:");                        
                    }else{
                        userWindow.updatecartList(user);
                    }
                }
            }
        }); 
        
        cdList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                menu.setVisible(false);
                
                if(me.getClickCount()==2 && me.getButton()==MouseEvent.BUTTON1){
                    aux=((Test)cdList.getSelectedValue()).getData();
                    showWindow.setTab(Short.parseShort("1"), aux); 
                    showWindow.setVisible(true);
                }
                if(me.getButton()==MouseEvent.BUTTON3 && !cdList.isSelectionEmpty() && admin.getId()!=null){
                      aux=((Test)cdList.getSelectedValue()).getData();
                      menu.setLocation(me.getLocationOnScreen());
                      menu.setVisible(true);
                      delete.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            admin.deleteCD((String)aux.get("id"));
                            menu.setVisible(false);
                            updatecdList();
                        }
                      });
                      modify.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            
                            editionWindow.setTabModded(Short.parseShort("1"), aux);
                            menu.setVisible(false);
                            editionWindow.setVisible(true);
                        }
                    });
                        
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        }); 

        
        
       
    
    }
    
    
    
    public void adminLogin(){
        JLabel lUser = new JLabel("User:");
        JLabel lPass = new JLabel("Password:");
        final JTextField userField = new JTextField();
        final JPasswordField passField=new JPasswordField();
        final JButton signUser=new JButton("Sign In as User");
        final JButton signAdmin=new JButton("Sign as Admin");
        final JButton signOut=new JButton("Sign Out");
        final JButton registerUser=new JButton("Register new user");
                
        lUser.setBounds(5,295, 100, 35);
        lPass.setBounds(5,320, 100, 35);
        
        userField.setBounds(80, 305, 300, 18);
        userField.setBackground(Color.white);
        passField.setBounds(80, 328, 300, 18);
        passField.setBackground(Color.white);
        
        signUser.setBounds(385, 295, 110, 35);
        signAdmin.setBounds(385, 318, 110, 35);
        signOut.setBounds(385, 341, 110, 35);
        signOut.setBackground(color);
        
        registerUser.setBounds(100, 341, 150, 35);
        registerUser.setBackground(color);
        //Listeners
        
        signUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText().toLowerCase();
                String pwd = String.valueOf(passField.getPassword()).toLowerCase();
                BasicDBObject query = new BasicDBObject().append("id", username).append("password", pwd);
                DBObject find = shop.getUsers().findOne(query);
                if(find != null){
                    userWindow.setVisible(true);
                    user.setMoney((Double)find.get("money"));
                    user.setName((String)find.get("name"));
                    user.setId((String)find.get("id"));
                    user.setPassword((String)find.get("password"));
                    userWindow.nameField.setText(user.getName());
                    userWindow.idField.setText(user.getId());
                    userWindow.moneyField.setText(String.valueOf(user.getMoney()));
                    userWindow.passwordField.setText(user.getPassword());
                    signUser.setEnabled(false);
                    signAdmin.setEnabled(false);
                    registerUser.setEnabled(false);
                    addC.setEnabled(true);
                    addB.setEnabled(true);
                    addM.setEnabled(true);
                }else{
                    JOptionPane.showMessageDialog(null, "User not found.");
                    userField.setText("");
                    passField.setText("");
                }
            }
        });
        
        signAdmin.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String adminId = userField.getText().toLowerCase();
                String pwd = String.valueOf(passField.getPassword()).toLowerCase();
                BasicDBObject query = new BasicDBObject().append("id", adminId).append("password", pwd);
                DBObject find = shop.getAdmin().findOne(query);
                if(find != null){
                    adminWindow.setVisible(true);
                    admin.setId(adminId);
                    admin.setPassword(pwd);
                    signUser.setEnabled(false);
                    signAdmin.setEnabled(false);
                    registerUser.setEnabled(false);
                }else{
                    JOptionPane.showMessageDialog(null, "Admin not found.");
                    userField.setText("");
                    passField.setText("");
                }
            }
        }); 
        
        signOut.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getId()!=null || admin.getId()!= null){
                    signUser.setEnabled(true);
                    signAdmin.setEnabled(true);
                    registerUser.setEnabled(true);
                    admin.clear();
                    user.clear();
                    userWindow.setVisible(false);
                    adminWindow.setVisible(false);
                    userField.setText("");
                    passField.setText("");
                    addC.setEnabled(false);
                    addB.setEnabled(false);
                    addM.setEnabled(false);
                    userWindow.updatehistorialList(new LinkedList());
                    userWindow.updatecartList(user);
                    
                }else{
                    JOptionPane.showMessageDialog(null,"No sesion found");
                }
            }
        }); 
        
        registerUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                registerWindow.setVisible(true);
            }
        });  
        
        
        this.add(userField);
        this.add(lUser);
        this.add(lPass);
        this.add(passField);
        this.add(signUser);
        this.add(signAdmin);
        this.add(signOut);
        this.add(registerUser);
    
    }
    
    
    public void updatebookList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = shop.getBooks().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        bookList.setModel(modelo);
    }
    
    public void updatebookList(LinkedList<BasicDBObject> list){
        DefaultListModel modelo = new DefaultListModel();
        for (BasicDBObject cur : list) {
            modelo.addElement((BasicDBObject)new Test(cur));
        }
        bookList.setModel(modelo);
    }
    
    public void updatemovieList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = shop.getMovies().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
        }
        movieList.setModel(modelo);
    }
    
    public void updatemovieList(LinkedList<BasicDBObject> list){
        DefaultListModel modelo = new DefaultListModel();
        for (BasicDBObject cur : list) {
            modelo.addElement((BasicDBObject)new Test(cur));
        }
        movieList.setModel(modelo);
    }
    
    public void updatecdList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = shop.getCds().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
        }
        cdList.setModel(modelo);
    }
    
    public void updatecdList(LinkedList<BasicDBObject> list){
        DefaultListModel modelo = new DefaultListModel();
        for (BasicDBObject cur : list) {
            modelo.addElement((BasicDBObject)new Test(cur));
        }
        cdList.setModel(modelo);
    }
    
    private void dummyTest(){
        final JPasswordField pwd = new JPasswordField();
        final JLabel text = new JLabel();
        pwd.setBounds(10, 10, 100, 20);
        text.setBounds(10, 30, 100, 20);
        pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(String.valueOf(pwd.getPassword()));
            }
        });
        this.add(text);
        this.add(pwd);
    }
    
    public void updateallList(){
        DefaultListModel modelo = new DefaultListModel();
        DBCursor cur = shop.getBooks().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        cur = shop.getCds().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        cur = shop.getMovies().find();
        while(cur.hasNext()){
            modelo.addElement((BasicDBObject)new Test((BasicDBObject)cur.next()));
            
        }
        adminWindow.userList.setModel(modelo);
    }
    
    
}

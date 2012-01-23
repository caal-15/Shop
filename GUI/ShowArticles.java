
package GUI;

import com.mongodb.BasicDBObject;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShowArticles extends JFrame {
    
    JPanel repaintB=new JPanel();
    JPanel repaintC=new JPanel();
    JPanel repaintM=new JPanel();
    
    
    public  ShowArticles() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        this.setSize(410, 400);
        this.setTitle("Detailed Info");
        this.setLayout(null);
        this.setLocation(945, 164);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.lightGray);
        adminShowBooks();
        adminShowMovies();
        adminShowCDs();
        
    }
    
    public void setTab(Short tab, BasicDBObject b){
        if(tab==0){
            ((JLabel)repaintB.getComponent(2)).setText((String)b.get("title"));
            ((JLabel)repaintB.getComponent(4)).setText((String)b.get("author"));
            ((JLabel)repaintB.getComponent(6)).setText((String)b.get("editorial"));
            ((JLabel)repaintB.getComponent(8)).setText(String.valueOf(b.get("stock")));
            ((JLabel)repaintB.getComponent(10)).setText(String.valueOf(b.get("price")));
            ((JLabel)repaintB.getComponent(12)).setText(String.valueOf(b.get("description")));
            
            this.setComponentZOrder(repaintB, 0);
        }
        if(tab==1){
            ((JLabel)repaintC.getComponent(2)).setText((String)b.get("title"));
            ((JLabel)repaintC.getComponent(4)).setText((String)b.get("author"));
            ((JLabel)repaintC.getComponent(6)).setText((String)b.get("year"));
            ((JLabel)repaintC.getComponent(8)).setText(String.valueOf(b.get("stock")));
            ((JLabel)repaintC.getComponent(10)).setText(String.valueOf(b.get("price")));
            ((JLabel)repaintC.getComponent(12)).setText((String)b.get("description"));
            
            this.setComponentZOrder(repaintC, 0);
        }
        if(tab==2){
            ((JLabel)repaintM.getComponent(2)).setText((String)b.get("title"));
            ((JLabel)repaintM.getComponent(4)).setText((String)b.get("director"));
            ((JLabel)repaintM.getComponent(6)).setText((String)b.get("actors"));
            ((JLabel)repaintM.getComponent(8)).setText(String.valueOf(b.get("stock")));
            ((JLabel)repaintM.getComponent(10)).setText(String.valueOf(b.get("price")));
            ((JLabel)repaintM.getComponent(12)).setText((String)b.get("description"));
            
            this.setComponentZOrder(repaintM, 0);
        }
        
    }
    
    
    
   
    
    private void adminShowBooks(){
        repaintB.setBounds(0, 0, this.getWidth(), this.getHeight());
        repaintB.setLayout(new GroupLayout(repaintB) );
        
        JLabel disclaimer=new JLabel("Detailed Information Below:");
        disclaimer.setBounds(10, 5, 300, 35);
        repaintB.add(disclaimer);
        
        
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 45 , 100, 35);
        repaintB.add(title);
        
        JLabel titleField= new JLabel();
        titleField.setBounds(60, 45, 300, 35);
        repaintB.add(titleField);
        
        JLabel author=new JLabel("Author:");
        author.setBounds(10, 75, 100, 35);
        repaintB.add(author);
        
        JLabel authorField= new JLabel();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 75, 300, 35);
        repaintB.add(authorField);
        
        JLabel editorial=new JLabel("Editorial:");
        editorial.setBounds(10, 110, 100, 35);
        repaintB.add(editorial);
        
        JLabel editorialField= new JLabel();
        editorialField.setBackground(Color.white);
        editorialField.setBounds(60, 110, 300, 35);
        repaintB.add(editorialField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        repaintB.add(stock);
        
        JLabel stockField= new JLabel();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 145, 300, 35);
        repaintB.add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        (repaintB).add(price);
        
        JLabel priceField= new JLabel();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 180, 300, 35);
        repaintB.add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        repaintB.add(description);
        
        JLabel descriptionArea = new JLabel();
        
        
        
        
        descriptionArea.setBounds(10, 250, 385, 80);
        
        repaintB.add(descriptionArea);
          
         
        
        this.add(repaintB);       
        
    }
    
    private void adminShowCDs(){
        repaintC.setBounds(0, 0, this.getWidth(), this.getHeight());
        repaintC.setLayout(new GroupLayout(repaintC) );
        
        JLabel disclaimer=new JLabel("Detailed Information Below:");
        disclaimer.setBounds(10, 5, 300, 35);
        repaintC.add(disclaimer);
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        repaintC.add(title);
        
        JLabel titleField= new JLabel();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        repaintC.add(titleField);
        
        JLabel author=new JLabel("Artist:");
        author.setBounds(10, 75, 100, 35);
        repaintC.add(author);
        
        JLabel authorField= new JLabel();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        repaintC.add(authorField);
        
        JLabel year=new JLabel("Year:");
        year.setBounds(10, 110, 100, 35);
        repaintC.add(year);
        
        JLabel yearField= new JLabel();
        yearField.setBackground(Color.white);
        yearField.setBounds(60, 115, 300, 20);
        repaintC.add(yearField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        repaintC.add(stock);
        
        JLabel stockField= new JLabel();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        repaintC.add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        repaintC.add(price);
        
        JLabel priceField= new JLabel();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        repaintC.add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        repaintC.add(description);
        
        JLabel descriptionArea = new JLabel();
        
        
        
        descriptionArea.setBounds(10, 250, 385, 80);
        
        repaintC.add(descriptionArea);
        
        this.add(repaintC);
        
        
    }
    
    private void adminShowMovies(){
        repaintM.setBounds(0, 0, this.getWidth(), this.getHeight());
        repaintM.setLayout(new GroupLayout(repaintM) );
        repaintM.setBounds(0, 0, 410, 400);
        JLabel disclaimer=new JLabel("Detailed Information Below:");
        disclaimer.setBounds(10, 5, 300, 35);
        repaintM.add(disclaimer);
        
        
        
        JLabel title=new JLabel("Title:");
        title.setBounds(10, 40, 100, 35);
        repaintM.add(title);
        
        JLabel titleField= new JLabel();
        titleField.setBackground(Color.white);
        titleField.setBounds(60, 45, 300, 20);
        repaintM.add(titleField);
        
        JLabel author=new JLabel("Director:");
        author.setBounds(10, 75, 100, 35);
        repaintM.add(author);
        
        JLabel authorField= new JLabel();
        authorField.setBackground(Color.white);
        authorField.setBounds(60, 80, 300, 20);
        repaintM.add(authorField);
        
        JLabel actors=new JLabel("Actors:");
        actors.setBounds(10, 110, 100, 35);
        repaintM.add(actors);
        
        JLabel actorsField= new JLabel();
        actorsField.setBackground(Color.white);
        actorsField.setBounds(60, 115, 300, 20);
        repaintM.add(actorsField);
        
        JLabel stock=new JLabel("Stock:");
        stock.setBounds(10, 145, 100, 35);
        repaintM.add(stock);
        
        JLabel stockField= new JLabel();
        stockField.setBackground(Color.white);
        stockField.setBounds(60, 150, 300, 20);
        repaintM.add(stockField);
        
        JLabel price=new JLabel("Price:");
        price.setBounds(10, 180, 100, 35);
        repaintM.add(price);
        
        JLabel priceField= new JLabel();
        priceField.setBackground(Color.white);
        priceField.setBounds(60, 185, 300, 20);
        repaintM.add(priceField);
        
        JLabel description=new JLabel("Description:");
        description.setBounds(10, 215, 100, 35);
        repaintM.add(description);
        
        JLabel descriptionArea = new JLabel();
        
        
        
        
        descriptionArea.setBounds(10, 250, 385, 80);
        
        repaintM.add(descriptionArea);
        
        this.add(repaintM);
    }
}


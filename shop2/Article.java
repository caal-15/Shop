
package shop2;

import com.mongodb.BasicDBObject;


public class Article extends BasicDBObject{
    String id;
    String title;
    String author;
    String description;
    int stock;
    double price;
    
    
    public Article(String id, String title,String author, String description, int stock,double price){
        this.id = id;
        this.title = title;
        this.author = author;
        this.description  = description;
        this.stock=stock;
        this.price = price;
    }
    /**
     @getDocument: Metodo para obtener un documento a partir de cada objeto.
           return: Retorna un objeto de la clase BasicDBObject construido 
                   apartir de los atributos del objeto original
     */
    BasicDBObject getDocument(){
        BasicDBObject doc = new BasicDBObject();
        doc.put("id",this.id);
        doc.put("title",this.title);
        doc.put("author",this.author);
        doc.put("description",this.description);
        doc.put("stock", this.stock);
        doc.put("price", this.price);
        return doc;
    }

    
    
}

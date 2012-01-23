
package shop2;

import com.mongodb.BasicDBObject;


public class Movie extends Article{
    String director;
    String actors;
    static int cid=(int) Shop.m.getDB("DataBase").getCollection("movies").getCount();
    
    /**
     @constructor: Se fija en la cantidad actual de peliculas en la DB para calcular el ID.
     */
    public Movie(String title,String author, String description,String director,String actors, int stock,double price){
        super("3." + String.valueOf(cid++),title,author,description, stock,price);
        this.director = director;
        this.actors = actors;
    }
    
    /**
     @getDocument: Metodo para obtener un documento a partir de cada objeto.
           return: Retorna un objeto de la clase BasicDBObject construido 
                   apartir de los atributos del objeto original
     */
    
    @Override
    BasicDBObject getDocument() {
        BasicDBObject doc = super.getDocument();
        doc.put("director", this.director);
        doc.put("actors", this.actors);
        return doc;
    }
    
}

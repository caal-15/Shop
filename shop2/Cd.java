package shop2;

import com.mongodb.BasicDBObject;

public class Cd extends Article{
    String year;
    static int cid=(int) Shop.m.getDB("DataBase").getCollection("cds").getCount();
    
    /**
     @constructor: Se fija en la cantidad actual de CD's en la DB para calcular el ID.
     */
    
    public Cd(String title,String author, String description,String year, int stock,double price){
        super("2." + String.valueOf(cid++),title,author,description, stock,price);
        this.year = year;
    }
    
    /**
     @getDocument: Metodo para obtener un documento a partir de cada objeto.
           return: Retorna un objeto de la clase BasicDBObject construido 
                   apartir de los atributos del objeto original
     */
    
    @Override
    BasicDBObject getDocument() {
        BasicDBObject doc = super.getDocument();
        doc.put("year", this.year);
        return doc;
    }
    
}

package shop2;

import com.mongodb.BasicDBObject;


public class Book extends Article{
    String editorial;
    static int cid=(int) Shop.m.getDB("DataBase").getCollection("books").getCount();
    
    /**
     @constructor: Se fija en el valor actual de libros en la DB para calcular el ID.
     */
    public Book( String title,String author, String description,String editorial, int stock,double price){
        super("1." + String.valueOf(cid++),title,author,description, stock,price);
        this.editorial = editorial;
    }

    /**
     @getDocument: Metodo para obtener un documento a partir de cada objeto.
           return: Retorna un objeto de la clase BasicDBObject construido 
                   apartir de los atributos del objeto original
     */
    
    @Override
    BasicDBObject getDocument() {
        BasicDBObject doc = super.getDocument();
        doc.put("editorial", this.editorial);
        return doc;
    }
    
}

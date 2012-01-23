package shop2;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.util.LinkedList;
import shopU.RegisteredUser;

public class Shop {
    protected static Mongo m;
    protected DB db;
    protected DBCollection books;
    protected DBCollection movies;
    protected DBCollection cds;
    protected DBCollection users;
    protected DBCollection admin;
    
    /**
      @Shop : constructor de la clase Shop
            -m :Recibe una conexión con Mongo 
     */
    public Shop(Mongo m){
        Shop.m = m;
        this.db = m.getDB("DataBase");
        this.cds = db.getCollection("cds");
        this.books = db.getCollection("books");
        this.movies = db.getCollection("movies");
        this.users=db.getCollection("users");
        this.admin=db.getCollection("admin");
    }
    
   
    
    /**
     @addUser: metodo para agregar usuarios, está aqui
     *      porque cualquier persona (desde la GUI)
     *      puede crearlo.
     */
    
    public boolean  addUser(RegisteredUser user){
        DBObject cur = this.getUsers().findOne(new BasicDBObject("id", user.getId()));
        if(cur == null){
            this.getUsers().insert(user.getDocument());
            return true;
        }
        return false;
    }

    /**
     @hasEnoughStock: Método que comprueba si hay suficiente stock de un producto para la compra
     *          -b: BasicDBObject, producto a comprar.
     *          -a: int, Cantidad solicitada.
     *      @returns:  
     *          -true si hay suficiente stock del producto.
     *          -false, en caso contrario.
     */
    
    public boolean hasEnoughStock(BasicDBObject b, int a){
        if((Integer)b.get("stock")>=a){
            return true;         
        }
        else{
            
            return false;
        }
    }
    
    /**
     @find*: Métodos de búsqueda para cada tipo de artículo.
     *      mediante diferentes criterios.
     *      -El algoritmo de búsqueda es el implementado en el gestor de DB(Mongo).
     *      @returns:
     *          BasicDBObject, en caso de entoncrar el artículo.
     *          null, en caso contrario.
     */
    
    public BasicDBObject findBookID(String id){
        BasicDBObject query=new BasicDBObject();
        query.put("id", id);
        return (BasicDBObject)getBooks().findOne(query);
    }
    
    public BasicDBObject findCDID(String id){
        BasicDBObject query=new BasicDBObject();
        query.put("id", id);
        return (BasicDBObject)getCds().findOne(query);
    }
    
    public BasicDBObject findMovieID(String id){
        BasicDBObject query=new BasicDBObject();
        query.put("id", id);
        return (BasicDBObject)getMovies().findOne(query);
    }
    
    public BasicDBObject findUserID(String id){
        BasicDBObject query=new BasicDBObject();
        query.put("id", id);
        return (BasicDBObject)getUsers().findOne(query);
    }
    
    public LinkedList<BasicDBObject> findBookTitle(String title){
        DBCursor cur=getBooks().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("title")).contains(title) || ((String)aux.get("title")).equals(title)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findBookAuthor(String author){
        DBCursor cur=getBooks().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("author")).contains(author) || ((String)aux.get("author")).equals(author)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findBookEditorial(String editorial){
        DBCursor cur=getBooks().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("editorial")).contains(editorial) || ((String)aux.get("editorial")).equals(editorial)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findCDTitle(String title){
        DBCursor cur=getCds().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("title")).contains(title) || ((String)aux.get("title")).equals(title)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findCDAuthor(String author){
        DBCursor cur=getCds().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("author")).contains(author) || ((String)aux.get("author")).equals(author)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findCDYear(String year){
        DBCursor cur=getCds().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("year")).contains(year) || ((String)aux.get("year")).equals(year)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findMovieTitle(String title){
        DBCursor cur=getMovies().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("title")).contains(title) || ((String)aux.get("title")).equals(title)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findMovieAuthor(String author){
        DBCursor cur=getMovies().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("author")).contains(author) || ((String)aux.get("author")).equals(author)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findMovieDirector(String director){
        DBCursor cur=getMovies().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("director")).contains(director) || ((String)aux.get("director")).equals(director)){
                results.add(aux);
            }
        }
        return results;
    }
    
    public LinkedList<BasicDBObject> findMovieActor(String actor){
        DBCursor cur=getMovies().find();
        BasicDBObject aux=new BasicDBObject();
        LinkedList<BasicDBObject> results=new LinkedList<BasicDBObject>();
        while(cur.hasNext()){
            aux=(BasicDBObject)cur.next();
            if(((String)aux.get("actors")).contains(actor) || ((String)aux.get("actors")).equals(actor)){
                results.add(aux);
            }
        }
        return results;
    }

    public DBCollection getUsers() {
        return users;
    }

    public DBCollection getBooks() {
        return books;
    }

    public DBCollection getMovies() {
        return movies;
    }

    public DBCollection getCds() {
        return cds;
    }

    public DBCollection getAdmin() {
        return admin;
    }
    
    public void modifyUser(RegisteredUser user,String oldId){
        BasicDBObject update= new BasicDBObject();
                // modifyArticle(user.getId(), user.gtitle, author, description, stock, price);
        update.put("id", user.getId());
        update.put("name", user.getName());
        update.put("money", user.getMoney());
        update.put("password",user.getPassword());
        users.update(new BasicDBObject().append("id", oldId), update);
    }
    
    
}

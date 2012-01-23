package shop2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.util.LinkedList;

/*Se crea esta clase porque si pongo private las funciones de shop no s epueden usar en ninguna otra clase
 * osea que no quedan sirviendo pa nada, creando esto nos aseguramos que ahora que la clase RegisteredUser que
 * esta en otro paquete, no va a tener acceso a ninguno de los métodos usados aca
 */

public class Admin extends Shop{
    private String id;
    private String password;
    
    /**
     @constructor:
     *          -m          : Mongo. Conexión con la base de datos.
     *          -id         : String. Cadena referente al id de usuario.
     *          -password   : String. Cadena referente al password de usuario.
     */
    public Admin(Mongo m, String id, String password){
        super(m);
        this.id = id;
        this.password = password;
    }
    
    /**
     @getters y setters de los atributos private.
     */
    
    public String getId(){
        return this.id;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    /**
     @addAdmin: Método para añadir un nuevo administrador a la base de datos.
     *          -admin: Admin. Objeto para añadir a la DB.
     *      @returns:   
     *          -false, si el admnistrador ya está en la DB.
     *          -true, si se añade correctamente
     */
    
    public boolean  addAdmin(Admin admin){
        DBObject cur = this.getAdmin().findOne(new BasicDBObject("id", admin.getId()));
        if(cur == null){
            this.getAdmin().insert(admin.getDocument());
            return true;
        }
        return false;
    }
    
   /**
      @addBook : Metodo para adicionar libros a la DB
               -book: Recibe el libro a ingresar.
               
               @returns : true si el libro no está y se añade correctamente.
                        false si el libro ya está en la DB.
     */
    
    public boolean  addBook(Book book){
        LinkedList<BasicDBObject> list = this.findBookAuthor(book.author);
        for (BasicDBObject obj : list) {
            if(((String)obj.get("title")).equals(book.title))
                return false;
        }
        this.getBooks().insert(book.getDocument());
        return true;
    }
    
    /**
      @addCd : Metodo para adicionar cds a la DB
               -cd: Recibe el cd a ingresar.
               
               return : true si el cd no está y se añade correctamente.
                        false si el cd ya está en la DB.
     */
    
    public boolean  addCd(Cd cd){
        LinkedList<BasicDBObject> list = this.findCDAuthor(cd.author);
        for (BasicDBObject obj : list) {
            if(((String)obj.get("title")).equals(cd.title))
                return false;
        }
        this.getCds().insert(cd.getDocument());
        return true;
    }
    
    /**
      @addMovie : Metodo para adicionar películas a la DB
               -movie: Recibe la película a ingresar.
               
               return : true si la película no está y se añade correctamente.
                        false si la película ya está en la DB.
     */
    
    public boolean  addMovie(Movie movie){
        LinkedList<BasicDBObject> list = this.findMovieAuthor(movie.author);
        for (BasicDBObject obj : list) {
            if(((String)obj.get("title")).equals(movie.title))
                return false;
        }
        this.getMovies().insert(movie.getDocument());
        return true;
    }
    
    /**
     @getDocumento: Método que genera un nuevo documento para almacenar el objeto en la DB.
     *          @retruns: BasicDBObject, obejto listo para ser añadido a la DB de mongo.
     */
    
    protected BasicDBObject getDocument(){
        BasicDBObject doc=new BasicDBObject();
        doc.put("id", this.id);
        doc.put("password", this.password);
        return doc;
    }
    
    /**
     @modifyArticle: Método para modificar artículos en general(Super).
     *              -id: String, id del artículo.
     *              -tittle: String, titulo del artículo.
     *              -description: String, descripcion del artículo.
     *              -author: String, autor del artículo.
     *              -stock: int, stock del artículo.
     *              -price: double, precio del artículo.
     *          @returns:
     *              - BasicDBObject, nuevo documento con los datos actualizados.
     */
    
    protected BasicDBObject modifyArticle(String id, String title,String author, String description, int stock,double price){
        BasicDBObject update=new BasicDBObject();
        update.put("id", id);
        update.put("title", title);
        update.put("author", author);
        update.put("description", description);
        update.put("stock", stock);
        update.put("price", price);
        return update;
    }
    
    /**
     @modifyBook: Método para modificar libros en la DB.
     *              Crea un nuevo documento y lo actualiza en la DB.
     *              -id: String, id del libro.
     *              -tittle: String, titulo del libro.
     *              -author: String, autor del libro.
     *              -description: String, descripcion del libro.
     *              -editorial: String, editorial del libro.
     *              -stock: int, stock del libro.
     *              -price: double, precio del libro.
     *          @returns:
     *              -No hay retorno ya que modifica directamente la DB.
     */
    
    public void modifyBook(String id, String title,String author, String description,String editorial, int stock, double price){
        BasicDBObject update= modifyArticle(id, title, author, description, stock, price);
        update.put("editorial", editorial);
        getBooks().update(new BasicDBObject().append("id", id), update);
    }
    
    /**
     @modifyCD: Método para modificar CD's en la DB.
     *              Crea un nuevo documento y lo actualiza en la DB.
     *              -id: String, id del CD.
     *              -tittle: String, titulo del CD.
     *              -author: String, autor del CD.
     *              -description: String, descripcion del CD.
     *              -Year: String, año del CD.
     *              -stock: int, stock del CD.
     *              -price: double, precio del CD.
     *          @returns:
     *              -No hay retorno ya que modifica directamente la DB.
     */
    
    public void modifyCD(String id, String title,String author, String description,String year, int stock, double price){
        BasicDBObject update= modifyArticle(id, title, author, description, stock, price);
        update.put("year", year)  ;
        getCds().update(new BasicDBObject().append("id", id), update);
    }
    
    /**
     @modifyMovie: Método para modificar películas en la DB.
     *              Crea un nuevo documento y lo actualiza en la DB.
     *              -id: String, id de la película.
     *              -tittle: String, titulo de la película.
     *              -author: String, autor de la película.
     *              -description: String, descripcion de la película.
     *              -director: String, director de la película.
     *              -actors: String, actores de la película
     *              -stock: int, stock de la película.
     *              -price: double, precio de la película.
     *          @returns:
     *              -No hay retorno ya que modifica directamente la DB.
     */
    
    public void modifyMovie(String id, String title,String author, String description,String director,String actors, int stock,double price){
        BasicDBObject update= modifyArticle(id, title, author, description, stock, price);
        update.put("director", director)  ;
        update.put("author", director);
        update.put("actors", actors);
        getMovies().update(new BasicDBObject().append("id", id), update);
                
    }
    
    public void modifyUser(String name, String id,String password, double money){
        BasicDBObject update=new BasicDBObject();
        update.put("id", id);
        update.put("password", password);
        update.put("name", name);
        update.put("money", money);
        getUsers().update(new BasicDBObject().append("id", id), update);
    }
    
    public void modifyAdmin(String id, String password){
        BasicDBObject update=new BasicDBObject();
        update.put("id", id);
        update.put("password", password);
        
        getAdmin().update(new BasicDBObject().append("id", id), update);
    }
    
    /***
     @deleteBook , deleteCD, deleteMovie: Métodos para eliminar artículos de la DB.
     *          Busca el artículo por su id, y lo remueve de la DB.
     *          @returns: no hay retorno.
     */
    
    public void deleteBook(String id){
        BasicDBObject todelete=new BasicDBObject();
        todelete.put("id", id);
        getBooks().remove(todelete);
    }
    
    public void deleteCD(String id){
        BasicDBObject todelete=new BasicDBObject();
        todelete.put("id", id);
        getCds().remove(todelete);
    }
    
    public void deleteMovie(String id){
        BasicDBObject todelete=new BasicDBObject();
        todelete.put("id", id);
        getMovies().remove(todelete);
    }
    
    public void deleteAdmin(String id){
        BasicDBObject todelete=new BasicDBObject();
        todelete.put("id", id);
        getAdmin().remove(todelete);
    }
    
    /**
     @showUsers: Método para mostrar todos los usuarios existentes.
     *          @returns: LinkedList, lista con todos los usuarios encontrados.
     */
    protected LinkedList<DBObject> showUsers(){
        DBCursor cur = this.getUsers().find();
        LinkedList<DBObject> list = new LinkedList<DBObject>();
        while(cur.hasNext()) {
            list.add(cur.next());
        }
        return list;
    }

    public void clear() {
        this.id = null;
        this.password = null;
    }
    
    public void deleteUser(String id){
        BasicDBObject todelete=new BasicDBObject();
        todelete.put("id", id);
        getUsers().remove(todelete);
    }

    
    
    
}

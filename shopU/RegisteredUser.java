package shopU;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.StringTokenizer;
import shop2.Shop;

//Se movio la clase de paquete para evitar completamente accesos a los métodos de la clase Admin
public class RegisteredUser extends Shop{
    public DBCollection dateShop;
    private double money;
    private String name;
    private String id;
    private String password;
    LinkedList<BasicDBObject> shoppingCart;

    /**
     @Constructor.
     */
    public RegisteredUser(double money, String name, String id, String password, Mongo m){
        super(m);
        this.dateShop = this.db.getCollection("dateshop");
        this.money=money;
        this.name=name;
        this.id=id;
        this.password = password;
        shoppingCart=new LinkedList<BasicDBObject>();
        
    }
    
    /**
     @getDocument: Métod que genera el documento para almacenar en la DB
     *          @returns: BasicDBObject, creado a aprtir de los atributos del usuario.
     */
    
    public BasicDBObject getDocument(){
        BasicDBObject doc = new BasicDBObject();
        doc.put("id", this.id);
        doc.put("password", this.password);
        doc.put("name", this.name);
        doc.put("money", this.money);
        
        
        return doc;
    }
    /**
     @Getters and Setters
     */
    public void setMoney(double money){
        this.money=money;
    }
    
    public void setName(String name){
        this.name=name;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public double getMoney(){
        return money;
    }
    
    public String getName(){
        return name;
    }
    
    public String getId(){
        return id;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public LinkedList<BasicDBObject> getShoppingCart(){
        return this.shoppingCart;
    }
    
    public void clear(){
        this.name = null;
        this.id = null;
        this.password = null;
        this.money = 0;
    }
    
    /**
     @addToCart: Método para añadir artículos al carro de compras:
     *          Verifica que exista suficiente stock del producto para añadirlo al carro 
     *          -doc: BasicDBObject, documento del articulo a comprar.
     *          -a: int, cantidad deseada del artículo.
     *      @returns:
     *          false, si no existe suficiente stock del producto
     *          true, si el atrículo fue añadido exitosamente.
     */
    
    public boolean addToCart(BasicDBObject doc, int a){
        int up=a;
        for (BasicDBObject obj : shoppingCart) {
               if(obj.get("id").equals(doc.get("id"))){
                  up+=(Integer)obj.get("quantity");
                  if(super.hasEnoughStock(doc, up)){
                    obj.removeField("quantity");
                    obj.put("quantity", up);
                    return true;
                  }else{
                      return false;
                  }
               }
        }
        if(super.hasEnoughStock(doc, a)){
            doc.put("quantity", up);
            shoppingCart.add(doc);
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     @removeFromCart: Método que elimina un solo articulo del carrito basado en su ID.
     *              -id: String, cadena con id del producto.
     *          @returns:
     *              -false si el articulo no está en el carro de compras.
     *              -true, si fue eliminado correctamente.
     */
    public boolean removeFromCart(String id){
        for(int i =0 ;i<shoppingCart.size();i++){
            if(id.equals((String)(shoppingCart.get(i).get("id")))){
                shoppingCart.remove(i);
                return true;
            }
        }
        return false;
    }
    /**
     @clearCart: Método que vacía el carro de compras.
     */
    public void clearCart(){
        this.shoppingCart.clear();
    }
    
    /**
     @getDate : Metodo que calcula una key, basandose en la fecha actual.
     *      -retruns : String con el valor mencionado anteriormente.
     */
    public int getDate(){
        Calendar cal = Calendar.getInstance();
        return ((cal.get(4)*40)+cal.get(5));        
    }
    /**
     @getDate : Metodo que calcula una key, basandose en una fecha dada.
     *       @params : -cad : String.
     *                  debe tener el formato DD/MM
     *      -retruns : String con el valor mencionado anteriormente: int.
     */
    public int getDate(String cad){
        StringTokenizer token = new StringTokenizer(cad, "/");
        return ((Integer.parseInt(token.nextToken())) + Integer.parseInt(token.nextToken())*40);
                
    }
    
    public String getDate2(int a){
        String cad = new String(),cad2 = null;
        int cas = (int)(a/40);
        switch(cas){
            case 1:
                cad2="January";
                break;
            case 2:
                cad2="February";
                break;
            case 3:
                cad2="March";
                break;
            case 4:
                cad2="April";
                break;
            case 5:
                cad2="May";
                break;
            case 6:
                cad2="Juny";
                break;
            case 7:
                cad2="July";
                break;
            case 8:
                cad2="August";
                break;
            case 9:
                cad2="September";
                break;
            case 10:
                cad2="October";
                break;
            case 11:
                cad2="November";
                break;
            case 12:
                cad2="December";
                break;
             
        }
        cad += cad2;
        cad +="-";
        cad += String.valueOf(a%40);
        return cad;
    }
    
    /**
     @showShopping: Muestra todas las compras separadas por fechas.
     */
    public LinkedList showShopping(){
        LinkedList list = new LinkedList();
        DBCursor query = this.dateShop.find(new BasicDBObject().append("id", this.id));
        int date = 0;
        while (query.hasNext()) {
            DBObject obj = query.next();
            if(((Integer)obj.get("date")) != date){
                //System.out.println(obj.get("date"));
                list.add("       " + getDate2((Integer)obj.get("date")));
                date = (Integer)obj.get("date");
            }
            //System.out.println(obj.get("car"));
            list.add(obj.get("car"));
        }
        /*for (Object object : query) {
            list.add(object);
        }*/
        return list;
    }
    
    /**
     @showShopping: Muestra todas las compras entre 2 fechas.
     */
    public LinkedList showShopping(int since, int until){
        LinkedList list = new LinkedList();
        DBCursor query = this.dateShop.find(new BasicDBObject().append("id", this.id));
        int date = 0;
        while (query.hasNext()) {
            DBObject obj = query.next();
            if((((Integer)obj.get("date")) >= since) && (((Integer)obj.get("date")) <= until)){
                   //System.out.println(obj.get("car"));
                //list.add(obj.get("car"));
                if(((Integer)obj.get("date")) != date){
                //System.out.println(obj.get("date"));
                list.add("       " + getDate2((Integer)obj.get("date")));
                date = (Integer)obj.get("date");
            }
            //System.out.println(obj.get("car"));
            list.add(obj.get("car"));
            }
        }
        return list;
    }
    
    /**
     @buyCart: Método que realiza la compra de los artículos en el carro de compras.
     *          -Actualiza el stock de cada producto tras cada compra
     *          -Verifica que el usuario tenga dinero suficiente para la compra.
     *          -Actualiza el dinero del usuario tras cada compra.
     *          -Si el usuario no tiene suficiente dinero, detiene la compra.
     *          @retunrs: true si se realizo toda la compra.
     *                    false, si falto algo por comprar (no money).
     */
    
    public boolean buyCart(){
        int qty=0;
        double price=0.0;
        String d;
        BasicDBObject doc = new BasicDBObject();
        doc.put("id", this.id);
        doc.put("date", this.getDate());
        while(shoppingCart.size()>0){
            
            doc.put("car",shoppingCart.get(0));
            qty=(Integer)shoppingCart.getFirst().get("quantity");
            d=((String)shoppingCart.getFirst().get("id"));
            price=Double.parseDouble(String.valueOf(shoppingCart.getFirst().get("price")));
            //System.out.println(shoppingCart.getFirst());
            
            
            if(price>this.money){
                
                break;
            }
            this.money=money-(price*qty);
            BasicDBObject update=new BasicDBObject().append("$inc", new BasicDBObject().append("stock", -qty));
            if(d.charAt(0)=='3'){
                getMovies().update(new BasicDBObject().append("id", d), update);
                }
            if(d.charAt(0)=='2'){
                getCds().update(new BasicDBObject().append("id", d), update);                
            }
            if(d.charAt(0)=='1'){
                getBooks().update(new BasicDBObject().append("id", d), update);
            }
           shoppingCart.removeFirst();
        }
       
        this.dateShop.insert(doc);
        if(shoppingCart.size()!=0){
            return false;
        }
        return true;
    }
    
}

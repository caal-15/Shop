package shop2;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Test extends BasicDBObject implements DBObject{
    private BasicDBObject data;
    
    public Test(BasicDBObject hola){
        data = hola;
    }

    @Override
    public String toString() {
        return "ID: " + data.get("id") + "   TITLE:  " + data.get("title") + 
                "   AUTHOR: " + data.get("author")+ "   PRICE: " + data.get("price") + "   STOCK: " + data.get("stock");
        
    }
    
    public BasicDBObject getData(){
        return this.data;
    }
    
    
    
}

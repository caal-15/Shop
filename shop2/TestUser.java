
package shop2;

import com.mongodb.BasicDBObject;


public class TestUser extends Test {
        
    public TestUser(BasicDBObject data){
            super(data);
    }

    @Override
    public String toString() {
        return "NAME: " + this.getData().get("name") + "   ID:  " + this.getData().get("id") + "   MONEY:  " + 
                this.getData().get("money");
    }
    
    
}

package domain;

import Acquaintance.IAUser;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EUser implements IAUser {

    private String userID;
    private String Name;
    private String Adress;
    private Map<String, EOrder> orders = new HashMap<String, EOrder>();
    private int index = 0;

    

    public EUser(String userID, String Name, String Adress) {
	this.userID = userID;
	this.Name = Name;
	this.Adress = Adress;
    }

    public String toString()
    {
	return "UserID: " + userID + "\nName: " + Name + "\nAdress: " + Adress;
    }

    public String getUserID(){
	return userID;
    }

    public EOrder getOrder(String OrderID){
	return orders.get(OrderID);
    }

    public HashMap getOrders(){
	Collection c = orders.values();
	Iterator itr = c.iterator();

	System.out.println("Hashmap size: " + orders.size());
	HashMap shipIDs = new HashMap();


	for(int index = 0; index < orders.size(); index++)
	{
	    //System.out.println(itr.next());
	    //System.out.println(index);
	    EOrder order = (EOrder) itr.next();
	    //Lav en array eller array liste som så tager alle shipid og returner det
	    System.out.println(order);

	    shipIDs.put(order.getShipID(),order.getOrderID());
	    //Returnering af et Hashmap, key: SkibsId, Value: OrderID

	}
	return shipIDs;
    }

    public void addOrder(EOrder order){
	orders.put(order.getOrderID(), order);
	System.out.println("Order: " + order.getOrderID() + " er tiføjet");
    }

    public int getOrderSize(){
	return orders.size();
    }



}


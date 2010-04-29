package domain;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class User {

    private String userID;
    private String Name;
    private String Adress;
    private Map<String, Order> orders = new HashMap<String, Order>();
    private int index = 0;

    

    public User(String userID, String Name, String Adress) {
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

    public Order getOrder(String OrderID){
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
	    Order order = (Order) itr.next();
	    //Lav en array eller array liste som så tager alle shipid og returner det
	    System.out.println(order);

	    shipIDs.put(order.getShipID(),order.getOrderID());
	    //Returnering af et Hashmap, key: SkibsId, Value: OrderID

	}
	return shipIDs;
    }

    public void addOrder(Order order){
	orders.put(order.getOrderID(), order);
	System.out.println("Order: " + order.getOrderID() + " er tiføjet");
    }

    public int getOrderSize(){
	return orders.size();
    }



}


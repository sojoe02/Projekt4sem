/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Entity;

import Acquaintance.IACustomer;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mats l
 */
public class ECustomer implements IACustomer {

    private Map<String, EOrder> orders = new HashMap<String, EOrder>();
    private EOrder order;
    private EOrder newOrder;
    int userID;
    String company;
    String adress;
//-----------------------------------------------------------------------------
    // Konstruktor.
    public ECustomer(int userID, String company, String adress) {
	this.userID = userID;
	this.company = company;
	this.adress = adress;
    }
//-----------------------------------------------------------------------------
    public void mapOrder(int OrderID, EShip Ship, EHarbour startHarbour, 
	    EHarbour endHarbour, String DepartureDate, String ArrivalDate) {
// Ordre oprettes.
	order = new EOrder(OrderID, getUserID(), Ship, startHarbour, 
		endHarbour, DepartureDate, ArrivalDate);
// Ordre gemmes i hashmap med OrderID som key..
	orders.put(Integer.toString(OrderID), order);
// Her har man altid referencen til den nyeste ordre.
	newOrder = order;
    }
//-----------------------------------------------------------------------------
    // Der kaldes til EOrder med referencen til EContainer.
    public void mapContainerToOrder(EOrder Order, int ContainerID,
	    EContainer Container)	{
	this.order = Order;
	order.mapContainerToOrder(ContainerID, Container);
    }
//-----------------------------------------------------------------------------
    public EOrder getOrder(int OrderID) {
	return orders.get(Integer.toString(OrderID));
    }

    public int getUserID() {
	return userID;
    }

    public String confirm() {
	return  userID + " " + company + " " + adress +  " " + newOrder.toString();
    }
}


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Entity;

import Acquaintance.IACustomer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mats l
 */
public class ECustomer implements IACustomer {

    private Map<String, EOrder> orders = new HashMap<String, EOrder>();
    private EOrder order;
    int userID;
    String company;
    String adress;


    public ECustomer(int userID, String company, String adress) {
	this.userID = userID;
	this.company = company;
	this.adress = adress;

    }

    public void mapOrder(int OrderID, EShip Ship, EHarbour startHarbour, EHarbour endHarbour, String DepartureDate, String ArrivalDate) {
	order = new EOrder(OrderID, getUserID(), Ship, startHarbour, endHarbour, DepartureDate, ArrivalDate);
	orders.put(Integer.toString(OrderID), order);
    }

    public void mapContainerToOrder(int OrderID, int ContainerID, EContainer Container)	{

	order = orders.get(Integer.toString(OrderID));
	System.out.println(order.getOrderID());
	order.mapContainerToOrder(ContainerID, Container);
    }

    public EOrder getOrder(int OrderID) {
	return orders.get(Integer.toString(OrderID));
    }

    public int getUserID() {
	return userID;
    }

    public String confirm() {
	return  userID + " " + company + " " + adress +  " ";
    }
}


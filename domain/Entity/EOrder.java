/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Entity;

import java.util.*;

public class EOrder {

    private int orderID;
    private int userID;
    private int shipID;
    private String departureDate;
    private String arrivalDate;
    private int numberOfContainers;

    private Map<String, EContainer> containers = new HashMap<String, EContainer>();

    public EOrder(int orderID, int userID, int shipID, String departureDate, String arrivalDate)	{
	this.orderID = orderID;
	this.userID = userID;
	this.shipID = shipID;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;
    }


    public String getOrderID() {
	return Integer.toString(orderID);
    }

    public String toString() {
	return Integer.toString(orderID) + Integer.toString(userID) + Integer.toString(shipID);
    }
}

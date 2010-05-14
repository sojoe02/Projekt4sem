/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Entity;

import java.util.*;

public class EOrder {

    private EShip ship;
    private int orderID;
    private int userID;

    private String departureDate;
    private String arrivalDate;
    private int numberOfContainers;

    private Map<String, EContainer> containers = new HashMap<String, EContainer>();

    public EOrder(int orderID, int userID, EShip Ship, String departureDate, String arrivalDate)	{
	this.orderID = orderID;
	this.userID = userID;
	this.ship = Ship;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;
    }

 public void mapContainerToOrder(int ContainerID, EContainer Container)	{
     containers.put(Integer.toString(ContainerID), Container);
 }

    public String getOrderID() {
	return Integer.toString(orderID);
    }

    public String toString() {
	return Integer.toString(orderID) + Integer.toString(userID) + Integer.toString(ship.getShipID());
    }
}

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
    private EHarbour startHarbour;
    private EHarbour endHarbour;

    private String departureDate;
    private String arrivalDate;
    private int numberOfContainers;

    private Map<String, EContainer> containers = new HashMap<String, EContainer>();

    public EOrder(int orderID, int userID, EShip Ship, EHarbour startHarbour, EHarbour endHarbour, String departureDate, String arrivalDate)	{
	this.orderID = orderID;
	this.userID = userID;
	this.ship = Ship;
	this.startHarbour =startHarbour;
	this.endHarbour = endHarbour;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;
    }

 public void mapContainerToOrder(int ContainerID, EContainer Container)	{
     containers.put(Integer.toString(ContainerID), Container);
 }

    public String getOrderID() {
	return Integer.toString(orderID);
    }


}

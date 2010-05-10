/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.*;

/**
 *
 * @author Mats l
 */
public class EOrder {

    static int start = 0;
    private int orderID;
    private int userID;
    private int shipID;
    private Date departureDate;
    private Date arrivalDate;
    private String[] containerID;
    //Order har en liste over hvilke container, ordren bruger
    private int numberOfContainers;
    private Map<String, Container> containers = new HashMap<String, Container>();

    public EOrder(int orderID, int userID, int shipID, Date departureDate, Date arrivalDate)	{
	this.orderID = orderID;
	this.userID = userID;
	this.shipID = shipID;
	this.departureDate = departureDate;
	this.arrivalDate = arrivalDate;

    }




    public String getOrderID() {
	return Integer.toString(orderID);
    }

    public void addContainer(Container container) {
	containerID = new String[numberOfContainers];

	for (int index = 0; index < numberOfContainers; index++) {
	    containerID[index] = container.getContainerID();
	}
    }

    public String getShipID() {
	return shipID;
    }

    public HashMap getAllContainers() {
	return (HashMap) containers;
    }


    public String toString() {
	return Integer.toString(orderID) + Integer.toString(userID) + Integer.toString(shipID);

    }
}

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
public class Order {

    static int start = 0;
    private int orderID;
    private String[] containerID;
    //Order har en liste over hvilke container, ordren bruger
    private int numberOfContainers;
    private String shipID;
    private Map<String, Container> containers = new HashMap<String, Container>();

    public Order(String endLoc, String startLoc, String date, String volume,
	    String weight, int numberOfContainer, String shipID) {
	start++;
	orderID = start;
	this.numberOfContainers = numberOfContainer;
	this.shipID = shipID;
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
	return "\nOrderens ID: " + orderID + "\nOrdrens skibsid er: " + shipID;

    }
}

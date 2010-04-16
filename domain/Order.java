/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Mats l
 */
public class Order {

    private int orderID = 0;
    private String[] containers;
    //Order har en liste over hvilke container, ordren bruger
    private int numberOfContainers;
    private String shipID;


    public Order(String endLoc, String startLoc, String date, String volume, 
	    String weight, int numberOfContainer, String shipID) {
	orderID++;
	this.numberOfContainers = numberOfContainer;
	this.shipID = shipID;
    }

    public String getOrderID() {
	return String.valueOf(orderID);
    }

    public void addContainer(Container container) {
	containers = new String[numberOfContainers];

	for (int index = 0; index < numberOfContainers; index++) {
	    containers[index] = container.getContainerID();
	}
    }
    
    public String getShipID(){
	return shipID;
    }


}

package domain.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EShip {

    private ECargo cargo;
    private EOrder order;
    private int shipID;
    private String shipName;
    private String Captain;
    private Map<String, EOrder> orders = new HashMap<String, EOrder>();

    public EShip(int shipID, String shipName, String shipType,
	    String Captain, int currentContainer, int maxContainer) {
	this.shipID = shipID;
	this.shipName = shipName;
	this.Captain = Captain;
	cargo = new ECargo(currentContainer, maxContainer);
    }

    public void placeOrder(int orderID, int userID, int shipID, Date departureDate, Date arrivalDate) {
	order = new EOrder(orderID, userID, shipID, departureDate, arrivalDate);
	orders.put(Integer.toString(orderID), order);
    }

    public void setShipID(int shipID) {
	this.shipID = shipID;
    }

    public int getShipID() {

	return shipID;

    }

    public String getShipName() {
	return shipName;
    }
}


	
	

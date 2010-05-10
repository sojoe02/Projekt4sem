package domain;

import Acquaintance.IAShip;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EShip implements IAShip{
    private ECargo cargo;
    private EOrder order;
    private int shipID;
    private String  shipName, Captain, Totalvolume, Totalweight;

    private Map<String, EOrder> orders = new HashMap<String, EOrder>();

    private ArrayList<String> DestinationList = new ArrayList<String>();
    private ArrayList<String> DatoList = new ArrayList<String>();

  	//skibets Last

    public EShip()  {
	
    }


    public EShip(int shipID, String shipName, String shipType,
	    String Captain, int currentContainer, int maxContainer) {
	this.shipID = shipID;
	this.shipName = shipName;
	this.Captain = Captain;
	cargo = new ECargo(currentContainer, maxContainer);


	//cargo = new Cargo(currentvolume, currentweight);
	//Opret en last til et skib
    }

    public void placeOrder(int orderID, int userID, int shipID, Date departureDate, Date arrivalDate)	{
	order = new EOrder(orderID, userID, shipID, departureDate, arrivalDate);
	orders.put(Integer.toString(orderID), order);
    }

    public void setShipID(int shipID)	{
	this.shipID = shipID;
    }


    public int getShipID() {

	return shipID;

    }
    public String getShipName()	{
	return shipName;
    }

    public ECargo getCargo() {
	return cargo;
    }

    public boolean availCargo(String volume, String weight) {
	if (cargo.availCargo(volume, weight) == true) {
	    return true;
	}
	return false;

    }

    public void updateShip(String volume, String weight) {
	cargo.updateShip(volume, weight);
    }

    public String getOrderInfo()    {
	return order.toString();
    }

    public String toString() {
	
	return "Skibs id:"  + shipID +  + Captain + Totalvolume + Totalweight;

    }
}
	
	

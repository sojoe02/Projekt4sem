package domain.Entity;

import java.util.*;
import Foundation.*;
import java.sql.SQLException;

public class ESas {

    private EShip ship;
    private EOrder order;
    private ECustomer customer;
    private Map<String, EShip> ships = new HashMap<String, EShip>();
    private Map<String, ECustomer> customers = new HashMap<String, ECustomer>();

    public ESas() {
    }

    public void mapCustomer(int userID, String company, String adress, String password) {
	customer = new ECustomer(userID, company, adress, password);

    }

    public int getUserID() {
	return customer.getUserID();
    }

    public void mapShip(int shipID, String shipName, String shipType,
	    String Captain, int currentContainer, int maxContainer) {
	ship = new EShip(shipID, shipName, shipType,
		Captain, currentContainer, maxContainer);
	ships.put(Integer.toString(shipID), ship);
    }

    public void mapOrder(int OrderID, int UserID, int ShipID, Date DepartureDate, Date ArrivalDate) {
	customer.mapOrder(OrderID, UserID, ShipID, DepartureDate, ArrivalDate);
    }

    public ECustomer getCustomer() {
	return customer;
    }

    public void setShipID(int shipID) {
	ship.setShipID(shipID);
    }
}
/*
public void seeShipInfo(String shipID, EUser currentUser) {
EShip currentShip = findShip(shipID);

//Foelgende er for Admin
if (currentUser == Admin) {
Collection c1 = currentShip.getCargo().getAllContainer().values();
Iterator itr1 = c1.iterator();

if (itr1.hasNext()) {
System.out.println(itr1.next());
} else {
System.out.println("Skibet indeholder ingen Container");
}
} //Foelgende er for andre end Admin, dvs. Kunden
else {
users.get(currentUser.getUserID());
HashMap ids = new HashMap();
ids = currentUser.getOrders();

Collection c = ids.values();
Iterator itr = c.iterator();
if (ids.containsKey(shipID)) {
}
}
}
 *
 */








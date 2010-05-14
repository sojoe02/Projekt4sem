package domain.Entity;

import java.util.*;

public class ESas {

    private EShip ship;
    private ECustomer customer;
    private EHarbour harbour;
    private Map<String, EShip> ships = new HashMap<String, EShip>();
    private Map<String, EHarbour> harbours = new HashMap<String, EHarbour>();

    public ESas() {
    }

    public void mapCustomer(int userID, String company, String adress) {
	customer = new ECustomer(userID, company, adress);

    }

    public int getUserID() {
	return customer.getUserID();
    }

    public void mapShip(int shipID, String shipName, String shipType,
	    String Captain, int maxContainer) {
	ship = new EShip(shipID, shipName, shipType,
		Captain, maxContainer);
	ships.put(Integer.toString(shipID), ship);
    }

    public void mapOrder(int OrderID, EShip Ship, String startCoordinate, String startNationally,
	    String endCoordinate, String endNationally, String startDest, String endDest, String DepartureDate, String ArrivalDate) {
	harbour = new EHarbour(startDest, startCoordinate, startNationally);
	harbours.put(startDest, harbour);
	harbour = new EHarbour(endDest, endCoordinate, endNationally);
	harbours.put(endDest, harbour);

	customer.mapOrder(OrderID, Ship, harbours.get(startDest), harbours.get(endDest), DepartureDate, ArrivalDate);
    }


    public void mapContainer (EShip Ship, int ContainerID, String Content,
		    int OrderID, String Status)	{
	this.ship = Ship;
	ship.mapContainer(ContainerID, Content, Status);
	customer.mapContainerToOrder(OrderID, ContainerID, ship.getContainer(ContainerID));
    }
    
    public EShip getShip(int ShipID)	{
	return ships.get(Integer.toString(ShipID));
    }
    public EOrder   getOrder (int OrderID)  {
	return customer.getOrder(OrderID);
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








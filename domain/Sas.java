package domain;

import java.util.*;

public class Sas {

    private String endLoc;
    private String startLoc;
    private String endDate;
    private String volume;
    private String weight;
    Ship ship;
    Order order;
    private String totalPrice;
    private User Admin;
    //SAS administrator

    private User currentUser;
    //Brugeren som er logget ind

    private Map<String, Ship> ships = new HashMap<String, Ship>();

    public Sas(User Admin) {
	this.Admin = Admin;
	//tilføj en Administrator, dvs. Rederiet
    }

    public void setUp() throws Exception {
	ship = new Ship("1", "50000", "100000");
	//ships.put( ship.getShipsID(),ship) ;


    }

    public String placeOrder(String endLoc, String startLoc, String endDate, 
	    String volume, String weight) {

	this.endLoc = endLoc;
	this.startLoc = startLoc;
	this.endDate = endDate;
	this.volume = volume;
	this.weight = weight;
	if (ship.availCargo(volume, weight) == true) {
	}

	return totalPrice = calcPrice(volume, weight, startLoc, endLoc);


	//	ArrayList<String> shipsList = new ArrayList<String >( ) ;	
	//	shipsList.add(ships.get("1").toString());
    }

    public String calcPrice(String volume, String weight, String startLoc,
	    String endLoc) {
	return "5";
    }

    public String chooseDate(String date) {

	ship.updateShip(volume, weight);

	order = new Order(endLoc, startLoc, date, volume, weight);

	String orderID = order.getOrderID();

	return "Confirm";
    }

    public void seeShipInfo(String shipID, User currentUser) {
	Ship currentShip = findShip(shipID);

	//Foelgende er for Admin
	if (currentUser == Admin)
	{
	    Collection c = currentShip.getCargo().getAllContainer().values();
	    Iterator itr = c.iterator();

	    if (itr.hasNext()) {
		System.out.println(itr.next());
	    }
	    else{
		System.out.println("Skibet indeholder ingen Container");
	    }
	}

	//Foelgende er for andre end Admin, dvs. Kunden
	else{
	    currentUser.getUserID();
	   

	}
    }

    public Ship findShip(String shipID) {
	return ships.get(shipID);
    }

    public void addShip(String shipID, Ship ship) {
	ships.put(shipID, ship);
    }
}



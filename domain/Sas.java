package domain;

import java.util.*;


import java.util.ArrayList;
import Foundation.*;
import java.sql.SQLException;

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
   
    private ArrayList<String> dates = new ArrayList<String>();
    private ArrayList<String> clientChoose = new ArrayList<String>();


    public Sas(User Admin) {
	this.Admin = Admin;
	//tilføj en Administrator, dvs. Rederiet
    }

    public void setUp() throws Exception {
	RunServer runS = new RunServer();

	clientChoose = runS.getClientChoose();

	ArrayList ab = new ArrayList();
	ab.add("asdsad");
	ab.add("sdsdsssss");
	ab = clientChoose;
	try {
	    System.out.println((ab.get(1)));
	} catch (IndexOutOfBoundsException e) {
	    System.out.print("fejl");
	}
	clientChoose.clear();

	clientChoose.add("1");
	clientChoose.add("Odense");
	clientChoose.add("Amsterdam");
	clientChoose.add("25-04-2010");
	clientChoose.add("4000");
	clientChoose.add("800");



	if (clientChoose.get(0).equals("1")) {
	    dates = placeOrder();
	    System.out.print("virker");
	}



	//int sd = 1;
	//System.out.println((String)clientChoose.get(sd));

    }

    public ArrayList placeOrder() throws SQLException {

	ArrayList<String> allshipList = new ArrayList<String>();
	sas_database database = new sas_database();

	allshipList =(database.connectToDatabase_allship());

	   for (int i = 0; i < allshipList.size(); i++) {
	       database.getShipInfo(Integer.parseInt(allshipList.get(i)));
	       ship = new Ship(database.getShipid(), database.getName(), database.getCaptain(), database.getTotalvolume(), database.getTotalweight(), database.getDestinationList(), database.getDatoList());
		ships.put(allshipList.get(i), ship);
		System.out.println("gentagelse");
		System.out.println(ship.toString());
	   }


	this.endLoc = endLoc;
	this.startLoc = startLoc;
	this.endDate = endDate;
	this.volume = volume;
	this.weight = weight;
	//if (ship.availCargo(volume, weight) == true) {
	return clientChoose;
    }

    //return totalPrice = calcPrice(volume, weight, startLoc, endLoc);
    //	ArrayList<String> shipsList = new ArrayList<String >( ) ;
    //	shipsList.add(ships.get("1").toString());

    











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
	if (currentUser == Admin) {
	    Collection c = currentShip.getCargo().getAllContainer().values();
	    Iterator itr = c.iterator();

	    if (itr.hasNext()) {
		System.out.println(itr.next());
	    } else {
		System.out.println("Skibet indeholder ingen Container");
	    }
	} //Foelgende er for andre end Admin, dvs. Kunden
	else {
	    currentUser.getUserID();
	}
    }

    /*
    if (ship.availCargo(volume, weight) == true) {
    }

    return totalPrice = calcPrice(volume, weight, startLoc, endLoc);


    //	ArrayList<String> shipsList = new ArrayList<String >( ) ;
    //	shipsList.add(ships.get("1").toString());
    }
     *
     */
    private Ship findShip(String ShipID) {
	return ships.get(ShipID);
    }

   

    public void addShip(String shipID, Ship ship) {
	ships.put(shipID, ship);

	





    }

    
}



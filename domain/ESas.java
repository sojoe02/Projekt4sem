package domain;

import java.util.*;


import java.util.ArrayList;
import Foundation.*;
import java.sql.SQLException;

public class ESas {

    private String endLoc;
    private String startLoc;
    private String endDate;
    private String volume;
    private String weight;
    EShip ship;
    EOrder order;
    ECustomer customer;
    private String totalPrice;
    private EUser Admin;
    //SAS administrator
    private EUser currentUser;
    //Brugeren som er logget ind
    private Map<String, EShip> ships = new HashMap<String, EShip>();
    private Map<String, EUser> users = new HashMap<String, EUser>();
 
    private ArrayList<String> dates = new ArrayList<String>();
    //private ArrayList<String> clientChoose = new ArrayList<String>();
    private ArrayList<String> dateList = new ArrayList<String>();

    public ESas() {
	customer = new ECustomer();
	ship = new EShip();
    }

    public void setCustomer(int userID, String company, String adress, String password){
	customer.setCustomer(userID, company, adress, password);
    }

    public void CreateShip()	{
	ship = new EShip();
    }

    public void createShip(int shipID, String shipName, String shipType,
	    String Captain, int currentContainer,  int maxContainer )	{
	ship = new EShip(shipID, shipName, shipType,
	    Captain, currentContainer, maxContainer);
	ships.put(Integer.toString(shipID), ship);
    }

    public void placeOrder (int orderID, int userID, int shipID, Date departureDate, Date arrivalDate)	{
	 ships.get(Integer.toString(shipID)).placeOrder(orderID, userID, shipID, departureDate, arrivalDate);
    }

    public EShip getShip()   {
	return ship;
    }

    public void getShipNull()  {
	 ship = null;
    }

    public void setShipID(int shipID)	{
	ship.setShipID(shipID);
    }


    public void setUp() throws Exception {
	RunServer runS = new RunServer();


	SendObject clientChoose = runS.getClientChoose();

	if(clientChoose.getMetodeChoose() == null ? ("placeOrder") == null : clientChoose.getMetodeChoose().equals("placeOrder"))  {
	    placeOrder();
	}


	

    }

    public ArrayList placeOrder() throws SQLException {


	//ArrayList<String> allshipList = new ArrayList<String>();
	sas_database database = new sas_database();

	database.connectToSas();

	database.findavailShips(clientChoose.getstartLoc,
		clientChoose.getendLoc, clientChoose.getvolume, clientChoose.getweight);

	allshipList = database.getAllShip();

	database.findAvrilShip(allshipList, clientChoose.get(1),clientChoose.get(2));


	
/*

	allshipList = (database.connectToDatabase_allship());

	System.out.println();
	System.out.println("bbbbbbbbbbbbbbbbaaaaaa " + database.getName());
	System.out.println();



	//for (int i = 0; i < allshipList.size(); i++) {

	System.out.println(allshipList.get(0));


	database.getShipInfo(Integer.parseInt(allshipList.get(0)));

	ship = new Ship(database.getShipid(), database.getName(), database.getCaptain(), database.getTotalvolume(), database.getTotalweight(), database.getDestinationList(), database.getDatoList());
	ships.put(allshipList.get(0), ship);

	System.out.println("skift");

	// System.out.println(allshipList.get(1));


	//  database.getShipInfo(Integer.parseInt(allshipList.get(1)));

	// ship = new Ship(database.getShipid(), database.getName(), database.getCaptain(), database.getTotalvolume(), database.getTotalweight(), database.getDestinationList(), database.getDatoList());
	//ships.put(allshipList.get(1), ship);




	//}
	//System.out.println((ships.get("1").toString()));

	 *
	 *
	 */
	return allshipList;

	//if (ship.availCargo(volume, weight) == true) {

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

	order = new EOrder(endLoc, startLoc, date, volume, weight);

	String orderID = order.getOrderID();

	return "Confirm";
    }

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

    /*
    if (ship.availCargo(volume, weight) == true) {
    }

    return totalPrice = calcPrice(volume, weight, startLoc, endLoc);


    //	ArrayList<String> shipsList = new ArrayList<String >( ) ;
    //	shipsList.add(ships.get("1").toString());
    }
     *
     */
    public EShip findShip(String ShipID) {
	return ships.get(ShipID);
    }

//private Ship
    public void addShip(String shipID, EShip ship) {
	ships.put(shipID, ship);
    }

    public void addShipTemp(String shipID, EShip Ship) {
	ships.put(shipID, Ship);
    }
}



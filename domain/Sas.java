package domain;

import java.util.*;
import java.sql.*;
import java.util.ArrayList;

public class Sas {

    private String endLoc;
    private String startLoc;
    private String endDate;
    private String volume;
    private String weight;
    // variable for database
    private static Connection con;
    private static String url = "jdbc:mysql://" + "localhost" + ":3306/" + "sas";
    private static String databaseUser = "root";
    private static String password = "abc";
    Ship ship;
    Order order;
    private String totalPrice;
    private Map<String, Ship> ships = new HashMap<String, Ship>();
    ArrayList<String> clientChoose = new ArrayList<String>();

    public Sas() {
    }

    public void setUp() throws Exception {
	RunServer runS = new RunServer();
	 clientChoose = runS.runServer();
	 //String currentMetode =clientChoose.substring(0,1);
	 //if (currentMetode.equals("1"))   {
	 

	   // placeOrder(clientChoose);
	    // System.out.println("qwerrtt");
	
	System.out.println("god stil " );
    }

    public void placeOrder(String clientChoose) {


   StringTokenizer st = new StringTokenizer(clientChoose);

	  st.nextToken();
	this.startLoc = st.nextToken();
	this.endLoc = st.nextToken();
	this.endDate = st.nextToken();
	this.volume = st.nextToken();
	this.weight = st.nextToken();

	System.out.println(clientChoose + "sdsdsd");
	System.out.println(volume);
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

    public String calcPrice(String volume, String weight, String startLoc, String endLoc) {
	return "5";
    }

    public String chooseDate(String date) {

	ship.updateShip(volume, weight);

	order = new Order(endLoc, startLoc, date, volume, weight);

	String orderID = order.getOrderID();

	return "Confirm";
    }

    public void seeShipInfo(String shipID) {
	Ship currentship = null;

	Ship s1 = new Ship("s1", "1000", "45000");
	Ship s2 = new Ship("s2", "2000", "30000");
	//tilføj test skibe

	ships.put("s1", s1);
	ships.put("s2", s2);
	//lægger skibe i hashmap

	System.out.println(findShip(shipID));
	//find skib frem

    }

    private Ship findShip(String ShipID) {
	return ships.get(ShipID);

    }

    public void createDatabase() {
//Finder Driveren her
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	    System.out.println("MySQL Driver Found");
	} catch (java.lang.ClassNotFoundException e) {
	    System.out.println("MySQL JDBC Driver not found ... ");
	}

	//Kontakter MySQL databasen her
	try {

	    con = DriverManager.getConnection(url, databaseUser, password);

	    System.out.println("Connection established to " + url + "...");
	} catch (java.sql.SQLException e) {
	    System.out.println("Connection couldn't be established to " + url + e.toString());
	}

	// create table all ship

		try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE allship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String createString = "CREATE TABLE allship (ship_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		    + "Name VARCHAR(100), Captain VARCHAR(100));";
	    statement.executeUpdate(createString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved createallship ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO allship (Name, Captain) VALUES ('god vind','Dan nyguen');";
	    statement.executeUpdate(insertString);
	    insertString = "INSERT INTO allship (Name, Captain) VALUES ('I ser mig ikke', 'Stefan larsen');";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved insert ");
	    System.err.println(e.getMessage());
	}












//Create table called 1_ship

	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE 1_ship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String createString = "CREATE TABLE 1_ship (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		    + "Destination VARCHAR(100), Dato VARCHAR(100));";
	    statement.executeUpdate(createString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved create ");
	    System.err.println(e.getMessage());
	}


	try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO 1_ship (Destination, Dato) VALUES ('Odense','15-04-2010');";
	    statement.executeUpdate(insertString);
	    insertString = "INSERT INTO 1_ship (Destination, Dato) VALUES ('Hamburg', '20-15-2010');";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved insert ");
	    System.err.println(e.getMessage());
	}





	//Create table called 2_ship

	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE 2_ship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String createString = "CREATE TABLE 2_ship (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		    + "Destination VARCHAR(100), Dato VARCHAR(100));";
	    statement.executeUpdate(createString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved create ");
	    System.err.println(e.getMessage());
	}


	try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO 2_ship (Destination, Dato) VALUES ('Odense','17-04-2010');";
	    statement.executeUpdate(insertString);
	    insertString = "INSERT INTO 2_ship (Destination, Dato) VALUES ('Amsterdam', '25-15-2010');";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ved insert ");
	    System.err.println(e.getMessage());

	}

    }
}



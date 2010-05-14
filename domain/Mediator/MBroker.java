/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Mediator;

import Acquaintance.IAContants;
import Acquaintance.IACustomer;

import Foundation.*;

import domain.Entity.ESas;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
// Der implementeres interface IAContants, som indeholder konstanter
public class MBroker implements IAContants {

    private ESas sas;
    private FConnection connection;
    private ResultSet rs;
    private String sqlStmt;
    private ArrayList<String> shipDates = new ArrayList<String>();
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

    /*
     * MBroker har samme reference til ESas som CActioner.
     * Der oprettes forbindelse til foundation.
     */
    public MBroker(ESas sas) throws ClassNotFoundException {
	this.sas = sas;
	connection = new FConnection(dbUrl, dbDriver);
    }

    /*
     * Her opretteres User i Entity pakken, hvis User altså eksistere i databasen
     * Her oprettes connection til databasen, hvis det fejler meddelles det.
     */
    public String mapUser(int userID) throws SQLException {
	String databaseStatus = "Can't get connection to the database";
	String acces_ok = "You have now acces to the system";
	String acces_fail = "You are not registered in our database";

	/*
	 * Opretter forbindelse til databasen, med de valgte konto, brugernavn
	 * og kodeord.
	 */

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return databaseStatus;
	}

	/*
	 * Dette stmt henter User fra databasen med det angivet userID.
	 */

	sqlStmt = "SELECT * FROM Customer "
		+ "WHERE UserID =" + userID;
	rs = connection.getReader().query(sqlStmt);

	/*
	 * Tester om userID er registeret i databasen.
	 * Hvis User er registeret mappes user i entity.
	 * Her mappes kun User, og ikke kundes order.
	 */
	try {
	    if (rs.next()) {
		String company = rs.getString("Company");
		String adress = rs.getString("Adress");
		sas.mapCustomer(userID, company, adress);
		return acces_ok;
	    }
	} catch (Exception e) {
	    e.getMessage();
	}
	return acces_fail;
    }
//-----------------------------------------------------------------------------

    public ArrayList findShipDates(String startDest, String endDest,
	    Date startDate, Date endDate, int containers, String content)
	    throws ParseException, SQLException {

	ResultSet rsDeparture = null;
	ResultSet rsArrival = null;
	ResultSet rsContainerInfo = null;


	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	// Her findes der ud af hvor mange skibe der ekistere ved redderiet.
	int currentShips = countShips();
// Dette loop kører alle skibene igennem.
	for (int i = 1; i < currentShips; i++) {
	    /*
	     * Henter datoer, som gemmes i ResultSet, derfor kan et skib have
	     * flere datoer.
	     */

	    rsDeparture = getDeparture(i, startDest, endDest);
	    rsArrival = getArrival(i, startDest, endDest);
	    // Henter container information om maxcontainer og brugte container.
	    rsContainerInfo = getContainerInfo(i);

	    rsContainerInfo.next();
	    // Det maxsimale container der kan være på skibet.
	    int maxContainers = rsContainerInfo.getInt("MaxContainer");

	    // Dette loop slutter når der ikke er flere dato muligheder.
	    while (rsDeparture.next()) {
		while (rsArrival.next()) {

		    String arrivalDate = rsArrival.getString("Date");
		    String departureDate = rsDeparture.getString("Date");
		    int shipID = rsDeparture.getInt("ShipID");
		    int currentContainer = getCurrentContainer(i, departureDate, arrivalDate);
		    // Tester om container er tilrådighed.
		    if ((maxContainers - currentContainer) >= containers) {

			// Tester om afgangsdatoen er før ankomstdatoen.
			if (df.parse(arrivalDate).after(df.parse(departureDate))) {

			    /*
			     * Indsættes i et arrayList, hvor række 1 er afgang
			     * 2 række er ankomst. 3 række er shipID.
			     * Hvis der er en mulighed mere, foresætter det ved
			     * række 4, 5 og 6.
			     */
			    shipDates.add(departureDate);
			    shipDates.add(arrivalDate);
			    shipDates.add(Integer.toString(shipID));
			}
		    }
		}
	    }
	}
// resultset lukkes ned.
	connection.getReader().closeResult(rsDeparture);
	connection.getReader().closeResult(rsArrival);
	connection.getReader().closeResult(rsContainerInfo);
	return shipDates;
    }

    private int countShips() throws SQLException {

	sqlStmt = "SELECT COUNT(*) AS rowcount FROM Ship;";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int currentShips = rs.getInt("rowcount");
	connection.getReader().closeResult(rs);
	return currentShips;
    }
    //---------------------------------------------------------------------------

    private ResultSet getDeparture(int currentShip, String startLoc, String endLoc) throws SQLException {

	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE Harbour = '" + startLoc + "' "
		+ "AND A_or_D = 'Departure'"
		+ "AND ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);

	connection.getReader().query(sqlStmt);

	return rs;
    }

    private ResultSet getArrival(int currentShip, String startLoc, String endLoc) throws SQLException {
	sqlStmt = "SELECT * FROM Schedulling "
		+ "Where Harbour ='" + endLoc + "' "
		+ "AND A_or_D = 'Arrival'"
		+ "AND ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);

	return rs;
    }

//-----------------------------------------------------------------------------
    private ResultSet getContainerInfo(int currentShip) throws SQLException {
	sqlStmt = "SELECT * FROM Ship "
		+ "WHERE ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);
	return rs;
    }
//-----------------------------------------------------------------------------

    private int getCurrentContainer(int currentShip, String departureDate,
	    String arrivalDate) throws SQLException {

	sqlStmt = "SELECT max(CurrentContainer) FROM Schedulling "
		+ "WHERE ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);
	rs.next();

	return rs.getInt(1);
    }
//-----------------------------------------------------------------------------

    public IACustomer placeOrder(int ShipID, String startDest, String endDest, String DepartureDate,
	    String ArrivalDate, int containers, String content) throws SQLException {

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	// Henter userID ,da der kun er user som har adgang af gangen.
	int userID = sas.getUserID();

	// referencen af iaCustomer, får referencen til den aktive user.
	IACustomer iaCustomer = (IACustomer) sas.getCustomer();
	// Den valgte placeOrder gammes i databasen.
	int orderID = saveNewOrder(userID, ShipID, DepartureDate, ArrivalDate);
	saveNewContainer(orderID, ShipID, containers, content);
	updateChange(ShipID, DepartureDate, ArrivalDate, containers);
	// Kundes order mappes.
	mapOrder(userID, startDest, endDest);
	return iaCustomer;
    }
//-----------------------------------------------------------------------------
    /*
     * Stmt sendes til writer i foundation, som opdaterer tabellen.
     */

    private int saveNewOrder(int UserID, int ShipID, String DepartureDate,
	    String ArrivalDate) throws SQLException {

	sqlStmt = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) VALUES "
		+ "(" + UserID + ", " + ShipID + ", '" + DepartureDate
		+ "', '" + ArrivalDate + "');";
	Statement stmt = connection.getWriter().updatequery(sqlStmt);
	connection.getWriter().closeStatement(stmt);

	sqlStmt = "SELECT * FROM  Ordre "
		+ "WHERE ShipID = " + ShipID
		+ " AND DepartureDate = '" + DepartureDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();

	return rs.getInt("OrderID");
    }
//----------------------------------------------------------------------------

    private void saveNewContainer(int orderID, int shipID, int containers, String content) throws SQLException {

	sqlStmt = "SELECT * FROM Container "
		+ "WHERE Status = 'Empty' AND ShipID = " + shipID + ";";
	rs = connection.getReader().query(sqlStmt);

	for (int i = 0; i < containers; i++) {
	    rs.next();
	    int containerID = rs.getInt("ContainerID");
	    System.out.println(Integer.toString(containerID));

	    sqlStmt = "UPDATE Container SET Content = '" + content + "', " +
		    "OrderID = '" + orderID + "', "
		    + "Status = 'Full' WHERE ContainerID = " + containerID + ";";

	    Statement stmt = connection.getWriter().updatequery(sqlStmt);
	    connection.getWriter().closeStatement(stmt);

	}
	connection.getReader().closeResult(rs);
    }

    private void updateChange(int ShipID, String DepartureDate, String ArrivalDate, int containers) throws SQLException {

	System.out.println("dato: " + DepartureDate + " " + ArrivalDate);


	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE ShipID = " + ShipID
		+ " AND Date = '" + DepartureDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int sIDD = rs.getInt("sID");
	System.out.println("sIDD = " + Integer.toString(sIDD));
	connection.getReader().closeResult(rs);

	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE ShipID = " + ShipID
		+ " AND Date = '" + ArrivalDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int sIDA = rs.getInt("sID");
	System.out.println("sIDA = " + Integer.toString(sIDA));

	for (int i = sIDD; i < sIDA + 1; i++) {

	    sqlStmt = "SELECT * FROM Schedulling "
		    + "WHERE sID = " + i + ";";
	    rs = connection.getReader().query(sqlStmt);
	    rs.next();
	    int currentContainer = rs.getInt("CurrentContainer");
	    connection.getReader().closeResult(rs);

	    sqlStmt = "UPDATE Schedulling "
		    + "SET CurrentContainer = " + (currentContainer + containers)
		    + " WHERE sID = " + i + ";";
	    connection.getWriter().updatequery(sqlStmt);

	}
    }

//----------------------------------------------------------------------------
    /*
     * Stmt sendes til Reader, som henter de order, som er oprettet med det 
    bestemte userID. Dermed oprettes orderne en af gangen.
     */
    private void mapOrder(int UserID, String startDest, String endDest) throws SQLException {

	sqlStmt = "SELECT * FROM Ordre "
		+ "WHERE UserID = " + UserID + ";";
	rs = connection.getReader().query(sqlStmt);

	while (rs.next()) {
	    int orderID = rs.getInt("OrderID");
	    int shipID = rs.getInt("ShipID");
	    String departureDate = rs.getString("DepartureDate");
	    String arrivalDate = rs.getString("ArrivalDate");

	    mapShip(shipID);


	    	sqlStmt = "SELECT * FROM Harbour "
		+ "WHERE Harbour = '" + startDest + "';";
	ResultSet rsstartDest = connection.getReader().query(sqlStmt);

		    	sqlStmt = "SELECT * FROM Harbour "
		+ "WHERE Harbour = '" + endDest + "';";
	ResultSet rsendDest = connection.getReader().query(sqlStmt);

	    rsstartDest.next();
	    rsendDest.next();

	    sas.mapOrder(orderID, sas.getShip(shipID), startDest, endDest, 
		    rsstartDest.getString("Coordinate"), rsstartDest.getString("Nationally"),
		     rsendDest.getString("Coordinate"), rsendDest.getString("Nationally"),
		     departureDate, arrivalDate);

	    
	    mapContainer(shipID, orderID);




	}
	connection.getReader().closeResult(rs);
    }

    private void mapShip(int shipID) throws SQLException {

	sqlStmt = "SELECT * FROM ship "
		+ "WHERE ShipID = " + shipID + ";";
	rs = connection.getReader().query(sqlStmt);
	rs.next();

	sas.mapShip(shipID, rs.getString("ShipName"), rs.getString("ShipType"),
		rs.getString("Captain"), rs.getInt("MaxContainer"));

    }
/*

    private void mapCargo(int ShipID) throws SQLException {

	sqlStmt = "SELECT * FROM ship "
		+ "WHERE ShipID = " + ShipID + ";";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int maxContainer = rs.getInt("MaxContainer");
	sas.mapCargo(sas.getShip(ShipID), maxContainer);
    }

 * 
 */

    private void mapContainer(int ShipID, int OrderID) throws SQLException {

	sqlStmt = "SELECT * FROM Container "
		+ "WHERE ShipID = " + ShipID + ";";
	rs = connection.getReader().query(sqlStmt);
	while (rs.next())   {
	    sas.mapContainer(sas.getShip(ShipID), rs.getInt("ContainerID"), rs.getString("Content"),
		  OrderID, rs.getString("Status"));

	}

    }
}


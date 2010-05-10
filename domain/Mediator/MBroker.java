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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mats l
 */
public class MBroker implements IAContants {

    private ESas sas;
    private FConnection connection;
    private ResultSet rs;
    private String sqlStmt;
    private ArrayList<String> ShipDates = new ArrayList<String>();

    public MBroker(ESas sas) throws ClassNotFoundException {
	this.sas = sas;
	sas = new ESas();
	connection = new FConnection(dbUrl, dbDriver);

    }

    public ArrayList findShipDates(String startLoc, String endLoc,
	    Date endDate, int containers) throws ParseException, SQLException {

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	int currentShips = countShips();

	ResultSet rsSchedulling = null;
	ResultSet rsContainerInfo = null;


	for (int i = 1; i < currentShips; i++) {
	    rsSchedulling = getSchedullingInfo(i, startLoc, endLoc);
	    rsContainerInfo = getContainerInfo(i);
	    rsContainerInfo.next();
	    rsSchedulling.next();
	    int currentContainers = rsContainerInfo.getInt("CurrentContainer");
	    int maxContainers = rsContainerInfo.getInt("MaxContainer");

	    while (rsSchedulling.next()) {

		String ArrivalDate = rsSchedulling.getString("Date_Arrival");
		String DepartureDate = rsSchedulling.getString("Date_Departure");
		int ShipID = rsSchedulling.getInt("ShipID");


		if ((maxContainers - currentContainers) >= containers) {

		    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		    if (df.parse(ArrivalDate).after(df.parse(DepartureDate))) {
			ShipDates.add(DepartureDate);
			ShipDates.add(ArrivalDate);
			ShipDates.add(Integer.toString(ShipID));
		    }
		}
	    }
	}


	connection.getReader().closeResult(rsSchedulling);
	connection.getReader().closeResult(rsContainerInfo);
	return ShipDates;
    }

    private int countShips() throws SQLException {

	sqlStmt = "SELECT COUNT(*) AS rowcount FROM Ship;";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int currentShips = rs.getInt("rowcount");
	connection.getReader().closeResult(rs);
	return currentShips;

    }

    private ResultSet getSchedullingInfo(int currentShip, String startLoc, String endLoc) throws SQLException {


	sqlStmt = "SELECT * FROM SchedullingArrival, SchedullingDeparture "
		+ "WHERE HarbourName_Departure = '" + startLoc + "' "
		+ "AND HarbourName_Arrival ='" + endLoc + "' "
		+ "AND SchedullingArrival.ShipID = " + currentShip
		+ " AND SchedullingDeparture.ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);

	return rs;
    }

    private ResultSet getContainerInfo(int currentShip) throws SQLException {
	sqlStmt = "SELECT * FROM Ship "
		+ "WHERE ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);
	return rs;
    }

    public IACustomer placeOrder(int shipID, String DepartureDate, String ArrivalDate) throws SQLException {


	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	sqlStmt = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) VALUES "
		+ "(1,1, '2010-05-11', '2010-06-12');";
	Statement stmt = connection.getWriter().updatequery(sqlStmt);
	connection.getWriter().closeStatement(stmt);





	sqlStmt = "SELECT * FROM Ship"
		+ " WHERE ShipID = '" + shipID + "';";
	rs = connection.getReader().query(sqlStmt);





	IACustomer iaShip = mapShip(rs, shipID);

	connection.getReader().closeResult(rs);



	sqlStmt = "SELECT * FROM Ordre "
		+ "WHERE ShipID = '" + shipID + "';";
	rs = connection.getReader().query(sqlStmt);

	mapOrder(rs, shipID);
	connection.getReader().closeResult(rs);
	return iaShip;
    }

    private IACustomer mapShip(ResultSet rs, int shipID) throws SQLException {
	rs.next();

	String shipName = rs.getString("ShipName");


	String shipType = rs.getString("ShipType");
	String Captain = rs.getString("Captain");



	int currentContainer = rs.getInt("CurrentContainer");
	int maxContainer = rs.getInt("MaxContainer");



	connection.getReader().closeResult(rs);

	sas.createShip(shipID, shipName, shipType, Captain, currentContainer, maxContainer);
	IACustomer iaShip = (IACustomer) sas.getShip();
	return iaShip;


    }

    private void mapOrder(ResultSet rs, int shipID) throws SQLException {

	while (rs.next()) {
	    int orderID = rs.getInt("OrderID");
	    int userID = rs.getInt("UserID");
	    Date departureDate = rs.getDate("DepartureDate");
	    Date arrivalDate = rs.getDate("ArrivalDate");
	    sas.placeOrder(orderID, userID, shipID, departureDate, arrivalDate);


	}
    }

    public Boolean loginAccess(int userID, String passWord) throws SQLException {
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return false;
	}
	sqlStmt = "SELECT * FROM Customer "
		+ "WHERE UserID =" + userID
		+ " AND Password = '" + passWord + "';";
	rs = connection.getReader().query(sqlStmt);

	try {
	    if (rs.next()) {
		String company = rs.getString("Company");
		String adress = rs.getString("Adress");
		sas.setCustomer(userID, company, adress, passWord);
		return true;
	    }
	} catch (Exception e) {
	    System.out.println("wrong login " + e.toString());
	}
	return false;

    }
}


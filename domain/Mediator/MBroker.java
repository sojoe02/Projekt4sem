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
public class MBroker implements IAContants {

    private ESas sas;
    private FConnection connection;
    private ResultSet rs;
    private String sqlStmt;
    private ArrayList<String> ShipDates = new ArrayList<String>();
    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

    public MBroker(ESas sas) throws ClassNotFoundException {
	this.sas = sas;
	sas = new ESas();
	connection = new FConnection(dbUrl, dbDriver);

    }

    public ArrayList findShipDates(String startDest, String endDest, Date startDate,
	    Date endDate, int containers, String content) throws ParseException, SQLException {

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	int currentShips = countShips();

	ResultSet rsSchedulling = null;
	ResultSet rsContainerInfo = null;


	for (int i = 1; i < currentShips; i++) {
	    rsSchedulling = getSchedullingInfo(i, startDest, endDest);
	    rsContainerInfo = getContainerInfo(i);
	    rsContainerInfo.next();
	    rsSchedulling.next();
	    int currentContainers = rsContainerInfo.getInt("CurrentContainer");
	    int maxContainers = rsContainerInfo.getInt("MaxContainer");

	    while (rsSchedulling.next()) {

		Date arrivalDate = rsSchedulling.getDate("Date_Arrival");
		Date departureDate = rsSchedulling.getDate("Date_Departure");
		int shipID = rsSchedulling.getInt("ShipID");


		if ((maxContainers - currentContainers) >= containers) {




		    if (arrivalDate.after(departureDate))
		    {
		   String dage = df.format(departureDate).substring(6, 7);
		   System.out.print(dage);

			ShipDates.add(df.format(departureDate));
			ShipDates.add(df.format(arrivalDate));
			ShipDates.add(Integer.toString(shipID));
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

    public IACustomer placeOrder(int ShipID, Date DepartureDate, Date ArrivalDate) throws SQLException {

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	int userID = sas.getUserID();

	IACustomer iaCustomer = (IACustomer) sas.getCustomer();
	saveNewOrder(userID, ShipID, DepartureDate, ArrivalDate);
	mapOrder(userID);
	return iaCustomer;
    }


    private void saveNewOrder(int UserID, int ShipID, Date DepartureDate, Date ArrivalDate) throws SQLException {

	sqlStmt = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) VALUES "
		+ "(" + UserID + ", " + ShipID + ", '" + df.format(DepartureDate) +
		"', '" + df.format(ArrivalDate) + "');";
	Statement stmt = connection.getWriter().updatequery(sqlStmt);
	connection.getWriter().closeStatement(stmt);

    }

    private void mapOrder(int UserID) throws SQLException {

	sqlStmt = "SELECT * FROM Ordre "
		+ "WHERE ShipID = '" + UserID + "';";
	rs = connection.getReader().query(sqlStmt);

	while (rs.next()) {
	    int orderID = rs.getInt("OrderID");
	    int shipID = rs.getInt("ShipID");
	    Date departureDate = rs.getDate("DepartureDate");
	    Date arrivalDate = rs.getDate("ArrivalDate");
	    sas.mapOrder(orderID, UserID, shipID, departureDate, arrivalDate);

	}
	connection.getReader().closeResult(rs);
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
		sas.mapCustomer(userID, company, adress, passWord);
		return true;
	    }
	} catch (Exception e) {
	    System.out.println("wrong login " + e.toString());
	}
	return false;

    }
}


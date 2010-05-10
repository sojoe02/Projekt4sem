/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Mediator;

import Acquaintance.IAContants;
import Acquaintance.IAShip;

import Foundation.*;
import domain.EUser;
import domain.ESas;
import domain.EShip;
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
public class MBroker implements IAContants{
    private ESas sas;
    private FConnection connection;
    private ResultSet rs;
    String sqlStmt;


    private ArrayList<String> ShipDates = new ArrayList<String>();

    public MBroker() throws ClassNotFoundException {
	connection = new FConnection(dbUrl, dbDriver);
	sas = new ESas();
    }

    public ArrayList findDates(String startLoc, String endLoc, Date endDate, int containers) throws ParseException, SQLException {


	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	sqlStmt = "SELECT COUNT(*) AS rowcount FROM Ship;";
	rs = connection.getReader().query(sqlStmt);

	rs.next();
	int rowcount = rs.getInt("rowcount");
	connection.getReader().closeResult(rs);


	try {

	    for (int i = 0; i < rowcount; i++) {
		sqlStmt = "SELECT * FROM SchedullingArrival, SchedullingDeparture "
			+ "WHERE HarbourName_Departure = '" + startLoc + "' "
			+ "AND HarbourName_Arrival ='" + endLoc + "' "
			+ "AND SchedullingArrival.ShipID = " + i
			+ " AND SchedullingDeparture.ShipID = " + i + ";";
		rs = connection.getReader().query(sqlStmt);

		sqlStmt = "SELECT * FROM Ship "
			+ "WHERE ShipID = " + i + ";";
		ResultSet rs1 = connection.getReader().query(sqlStmt);
		

		while (rs.next()) {
		rs1.next();
		int currentContainers = rs1.getInt("CurrentContainer");
		int maxContainers = rs1.getInt("MaxContainer");
		    String ArrivalDate = rs.getString("Date_Arrival");
		    String DepartureDate = rs.getString("Date_Departure");
		    int ShipID = rs.getInt("ShipID");
		    //System.out.println(DepartureDate + " " + ArrivalDate + " " + Integer.toString(ShipID));

		if ((maxContainers - currentContainers) >= containers)	{

		    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		    if (df.parse(ArrivalDate).after(df.parse(DepartureDate))) {
			ShipDates.add(DepartureDate);
			ShipDates.add(ArrivalDate);
			ShipDates.add(Integer.toString(ShipID));
		    }
		}
		}
	    }
	} catch (SQLException e) {
	    System.out.println("Error executing sql statement in findDates");
	}
	connection.getReader().closeResult(rs);
	return ShipDates;
    }

    public IAShip placeOrder(int shipID, String DepartureDate, String ArrivalDate) throws SQLException	{


	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	sqlStmt = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) VALUES " +
		"(1,1, '2010-05-11', '2010-06-12');";
	 Statement stmt = connection.getWriter().updatequery(sqlStmt);
	 connection.getWriter().closeStatement(stmt);

	

	

	sqlStmt = "SELECT * FROM Ship" +
		" WHERE ShipID = '" + shipID + "';";
	rs = connection.getReader().query(sqlStmt);
	
	

	
	
	IAShip iaShip = mapShip(rs, shipID);

	connection.getReader().closeResult(rs);

	

	sqlStmt = "SELECT * FROM Ordre " +
		"WHERE ShipID = '" + shipID + "';";
	rs = connection.getReader().query(sqlStmt);
	
	mapOrder(rs, shipID);
    connection.getReader().closeResult(rs);
	return iaShip;
    }
    private IAShip mapShip(ResultSet rs, int shipID) throws SQLException    {
	rs.next();
	
	String shipName = rs.getString("ShipName");

	
	String shipType = rs.getString("ShipType");
	String Captain = rs.getString("Captain");

	

	int currentContainer = rs.getInt("CurrentContainer");
	int maxContainer = rs.getInt("MaxContainer");

	
	
	connection.getReader().closeResult(rs);

	sas.createShip(shipID, shipName, shipType, Captain, currentContainer, maxContainer);
	IAShip iaShip = (IAShip) sas.getShip();
	return iaShip;
	
	
    }

    private void mapOrder(ResultSet rs, int shipID) throws SQLException {

	while(rs.next())    {
	    int orderID = rs.getInt("OrderID");
	    int userID = rs.getInt("UserID");
	    Date departureDate = rs.getDate("DepartureDate");
	    Date arrivalDate = rs.getDate("ArrivalDate");
	    sas.placeOrder(orderID, userID, shipID, departureDate, arrivalDate);


	}
    }
    
 
   


  

    public Boolean loginAccess (int userID, String passWord) throws SQLException   {
		if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return false;
	}
	sqlStmt = "SELECT * FROM Customer " +
		"WHERE UserID =" + userID  +
		" AND Password = '" + passWord + "';";
	rs = connection.getReader().query(sqlStmt);

	try {
	    if (rs.next())  {
		String company = rs.getString("Company");
		String adress = rs.getString("Adress");
		sas.setCustomer(userID, company, adress, passWord);
		return true;
	    }
	}catch (Exception e ) {
	System.out.println("wrong login " + e.toString());
    }
	return false;

    }
  
	

     
 


}


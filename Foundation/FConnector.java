/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class FConnector {

    private static Connection con;
    private static String url = "jdbc:mysql://" + "localhost" + ":3306/" + "sas";
    private static String databaseUser = "root";
    private static String password = "abc";
    private ArrayList<String> ShipDates = new ArrayList<String>();
    int rowcount;

    public FConnector() {
	connectToDataBase();
    }

    public ArrayList getDates(String startLoc, String endLoc, Date endDate) throws ParseException {

	try {
	    String sqlStmt = "SELECT COUNT(*) AS rowcount FROM Ship;";
	    Statement stmt;
	    stmt = con.createStatement();
	    ResultSet resultSet;
	    resultSet = stmt.executeQuery(sqlStmt);

	    resultSet.next();
	    rowcount = resultSet.getInt("rowcount");
	    resultSet.close();

	} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}



	try {

	    for (int i = 0; i < rowcount; i++) {


		String sqlStmt = "SELECT * FROM SchedullingArrival, SchedullingDeparture "
			+ "WHERE HarbourName_Departure = '" + startLoc + "' "
			+ "AND HarbourName_Arrival ='" + endLoc + "' "
			+ "AND SchedullingArrival.ShipID = " + i
			+ " AND SchedullingDeparture.ShipID = " + i + ";";
		Statement stmt;
		stmt = con.createStatement();
		ResultSet resultSet;
		resultSet = stmt.executeQuery(sqlStmt);

		while (resultSet.next()) {
		    String ArrivalDate = resultSet.getString("Date_Arrival");
		    String DepartureDate = resultSet.getString("Date_Departure");
		    int ShipID = resultSet.getInt("ShipID");
		    System.out.println(DepartureDate + " " + ArrivalDate + " " + Integer.toString(ShipID));

		    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");

		    if (df.parse(ArrivalDate).after(df.parse(DepartureDate))) {
			ShipDates.add(DepartureDate);
			ShipDates.add(ArrivalDate);
			ShipDates.add(Integer.toString(ShipID));
		    }

		}
	    }

	} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}

	return ShipDates;
    }


    public void placeOrder(String UserID, String ShipID, String DepartureDate, String ArrivalDate) throws SQLException  {
try {
	System.out.println(UserID);
	System.out.println(ShipID);
	System.out.println(DepartureDate);
	System.out.println(ArrivalDate);
	Statement statement;
	statement = con.createStatement();
	String insertString = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) " +
		"VALUES (" + UserID + ", " + ShipID + ", '" + DepartureDate + "', '" + ArrivalDate + "');";
	statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}
    }
    



    public void connectToDataBase() {
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
    }
}


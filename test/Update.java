/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Mats l
 */
public class Update {
    private static Connection con;
    private static String url = "jdbc:mysql://" + "localhost" + ":3306/" + "sas";
    private static String databaseUser = "root";
    private static String password = "abc";
    Statement statement;



     public void connectToSas() {
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

		try {
	    statement = con.createStatement();
	    String dropString = "DROP TABLE kunde;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!13 ");
	    System.err.println(e.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Kunde ("
		    + "Nummer VARCHAR(30),"
		    + "Navn VARCHAR(50),"
		    + "PRIMARY KEY (Nummer));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

		try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO Kunde VALUES " +
		    "('mats', 'Larsen'),('Lis', 'Larsen');";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}
		try {
	    statement = con.createStatement();
	    String insertString = "UPDATE Kunde " +
		    "SET Navn = 'mette' " +
		    "WHERE Navn = 'Larsen';";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}

    }


}

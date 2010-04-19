/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Foundation;

/**
 *
 * @author Mats l
 */
import java.sql.*;
import java.util.ArrayList;
import domain.*;

public class sas_database {

    private static Connection con;
    private static String url = "jdbc:mysql://" + "localhost" + ":3306/" + "sas";
    private static String databaseUser = "root";
    private static String password = "abc";
    private int ship_id;
    private String Name, Captain, Totalvolume, Totalweight, Destination, Dato;
    private ArrayList<String> DestinationList = new ArrayList<String>();
    private ArrayList<String> DatoList = new ArrayList<String>();

    public sas_database() {
    }

    public void createDatabase() {

	connectToSas();
	

	
	// Drop sas tabel
	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE allship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! in the metode to drop sas tabel. ");
	    System.err.println(e.getMessage());
	}

	    // create table all ship
	try {
	    Statement statement = con.createStatement();
	    String createString = "CREATE TABLE allship (ship_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		    + "Name VARCHAR(100)," + "Captain VARCHAR(100)," + "Totalvolume VARCHAR(100)," + "Totalweight VARCHAR(100));";
	    statement.executeUpdate(createString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! in creating sas tabel. ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO allship (Name, Captain, Totalvolume, Totalweight) VALUES ('god vind','Dan nyguen','10000 m3', ' 8 tons');";
	    statement.executeUpdate(insertString);
	    insertString = "INSERT INTO allship (Name, Captain, Totalvolume, Totalweight) VALUES ('I ser mig ikke', 'Stefan larsen', '12000 m3', ' 10 tons');";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! in insert data on allship tabel. ");
	    System.err.println(e.getMessage());
	}

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

    public ArrayList connectToDatabase_allship() throws SQLException {

	ArrayList<String> allshipList = new ArrayList<String>();

	connectToSas();

	try {
	    String sqlStmt = "SELECT ship_id, Name, Captain, Totalvolume, Totalweight  FROM allship";
	    Statement stmt;
	    stmt = con.createStatement();
	    ResultSet resultSet;
	    resultSet = stmt.executeQuery(sqlStmt);

	    System.out.println();

	    while (resultSet.first()) {
		Name = resultSet.getString("Name");
		System.out.println(Name + "   tttttttttteeeeeeeexxxxxxxxtttt");
		Captain = resultSet.getString("Captain");
		ship_id = resultSet.getInt("ship_id");
		Totalvolume = resultSet.getString("Totalvolume");
		Totalweight = resultSet.getString("Totalweight");
		System.out.println("allship [ship_id, Name, Captian]= " + "(" + ship_id + ")" + Name + Captain + Totalvolume + Totalweight);
		allshipList.add(Integer.toString(ship_id));

		System.out.println();
}
	    
	} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}
	return allshipList;

    }

    public void getShipInfo(int ship_id) {

	ArrayList<String> DestinationList = new ArrayList<String>();
	ArrayList<String> DatoList = new ArrayList<String>();

	connectToSas();
	
	String ship__id = Integer.toString(ship_id);
	

	try {
	    String sqlStmt = "SELECT Destination, Dato FROM " + ship__id + "_ship";

	    System.out.println("\nSELECT Destination, Dato FROM " + ship__id + "_ship" );

	    Statement stmt;
	    stmt = con.createStatement();
	    ResultSet resultSet;
	    resultSet = stmt.executeQuery(sqlStmt);


	    while (resultSet.next()) {
		Destination = resultSet.getString("Destination");
		Dato = resultSet.getString("Dato");

		System.out.println(ship__id + "_ship [ Destination, Dato]= " + Destination + " " + Dato + " " + Name + " " + Captain + " " + Totalvolume + " " + Totalweight + " " + Destination + " ");

		DestinationList.add(Destination);
		DatoList.add(Dato);
	    }
	    resultSet.close();
	} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}

    }

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
    }

    public int getShipid() {
	return ship_id;
    }

    public String getName() {
	return Name;
    }

    public String getCaptain() {
	return Captain;
    }

    public String getTotalvolume() {
	return Totalvolume;
    }

    public String getTotalweight() {
	return Totalweight;
    }

    public ArrayList getDestinationList() {
	return DestinationList;
    }


    public ArrayList getDatoList() {
	return DatoList;
    }
}


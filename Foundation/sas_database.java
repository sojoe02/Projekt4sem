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
import java.text.ParseException;
import java.util.ArrayList;
import domain.*;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

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

	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE Ordre;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	//Create ship table
	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE Ship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1 ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String dropString = "DROP TABLE Schedulling;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1 ");
	    System.err.println(e.getMessage());
	}



	try {
	    Statement statement = con.createStatement();
	    String dropString = "CREATE TABLE Ship (" +
		    "ShipID INT(4)," +
		    "ShipName VARCHAR(50) NOT NULL," +
		    "ShipType VARCHAR(50) NOT NULL," +
		    "Captain VARCHAR(70) NOT NULL," +
		    "CurrentContainers INT(6) NOT NULL," +
		    "MaxContainer INT(6) NOT NULL," +
		    "CurrentWeight INT(5) NOT NULL," +
		    "MaxWeight INT(5) NOT NULL," +
		    "VolumeOfContainer INT(4) NOT NULL," +
		    "PRIMARY KEY (ShipID));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	


		try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO Ship VALUES" +
		    " (1, 'Neil Young', 'ALM Transport', 'Dan Nyigen', 0, 1000, " +
		    "700, 1500, 60)," +
		    "(2, 'Melua', 'ALM Transport', 'Stefan Larsen', 0, 1250, " +
		    "800, 1800, 70)," +
		    "(3, 'Madonna', 'ALM Transport', 'Søren Jørgensen', 0, " +
		    "700, 500, 1100, 50)," +
		    "(4, 'Jim Morrison', 'ALM Transport', 'Mats Larsen', 0, " +
		    "1500, 900, 1900, 80)," +
		    "(5, 'Kurt Cubain', 'Olie Transport', 'Marius Vestergaard'," +
		    " 0, 250, 1100, 2500, 120)";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}

// Create order table




	try {
	    Statement statement = con.createStatement();
	    String dropString = "CREATE TABLE Ordre (" +
		    "OrderID int(5) NOT NULL," +
		    "ShipID INT(4)," +
		    "CONSTRAINT FK_ShipID FOREIGN KEY (ShipID) REFERENCES " +
		    "Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE);" ;
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	try {
	    Statement statement = con.createStatement();
	    String insertString = "INSERT INTO Ordre  VALUES" +
		    " (1,5);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}

try {
	    Statement statement = con.createStatement();
	    String dropString = "CREATE TABLE Schedulling (" +
		    "Date Date(8) NOT NULL," +
		    "Harbour VARCHAR(30)" +
		    "ShipID INT(4)," +
		    "CONSTRAINT FK_Harbour FOREIGN KEY (Harbour) REFERENCES " +
		    "Harbour (Harbour) ON DELETE SET NULL ON UPDATE CASCADE," +
		    "CONSTRAINT FK_ShipID FOREIGN KEY (ShipID) REFERENCES " +
		    "Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE);" ;
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}
	
    }

    public ArrayList findAvrilShip(ArrayList allshiipList, String startLoc, String endLoc)	{

ArrayList<String> dateChoose = new ArrayList<String>();
String afgang = null;
String ankomst = null;
try {
    ResultSet resultSet;
String sqlStmt = "SELECT * FROM 2_ship WHERE Destination= '" + startLoc + "'";
Statement stmt;
stmt = con.createStatement();

resultSet = stmt.executeQuery(sqlStmt);

while (resultSet.next()) {
afgang= resultSet.getString("Dato");

}
System.out.println("sdsdsdsdsdsd " + afgang );
} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}

try {
    String sqlStmt = "SELECT * FROM 2_ship WHERE Destination= '" + endLoc + "'";
Statement stmt;
stmt = con.createStatement();
ResultSet resultSet;
resultSet = stmt.executeQuery(sqlStmt);

while (resultSet.next()) {
ankomst= resultSet.getString("Dato");

}
System.out.println("sdsdsdsdsdsd " + ankomst );
} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}

DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
Date Dateafgang = null,Dateankomst = null;
	try {
	    Dateafgang = df.parse(afgang);
	    Dateankomst = df.parse(ankomst);
	} catch (ParseException ex) {

	}

if (Dateafgang.before(Dateankomst)) {
    dateChoose.add(afgang);

}
}



    



    public ArrayList getAllShip()   {

	ArrayList<String> allshipList = new ArrayList<String>();

	try {
	    String sqlStmt = "SELECT ship_id, Name, Captain, Totalvolume, Totalweight  FROM allship";
	    Statement stmt;
	    stmt = con.createStatement();
	    ResultSet resultSet;
	    resultSet = stmt.executeQuery(sqlStmt);



	    while (resultSet.next()) {
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

	    while (resultSet.next()) {
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


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
    Statement statement;
    public sas_database() {
    }

    public void createDatabase() {

	connectToSas();

	try {
	   statement = con.createStatement();
	    String dropString = "DROP TABLE Ordre;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}


		try {
	    statement = con.createStatement();
	    String dropString = "DROP TABLE SchedullingArrival;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1 ");
	    System.err.println(e.getMessage());
	}

		try {
	    statement = con.createStatement();
	    String dropString = "DROP TABLE SchedullingDeparture;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1 ");
	    System.err.println(e.getMessage());
	}


	//Create ship table
	
try {
	    statement = con.createStatement();
	    String dropString = "DROP TABLE Ship;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!13 ");
	    System.err.println(e.getMessage());
	}




	try {
	     statement = con.createStatement();
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

	



// Create order table




	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Ordre (" +
		    "OrderID int(5) NOT NULL," +
		    "ShipID INT(4));" ;
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

	try {
	     statement = con.createStatement();
	    String insertString = "INSERT INTO Ordre  VALUES" +
		    " (1,5);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}

try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE SchedullingArrival (" +
		    "Date DATE," +
		    "HarbourName VARCHAR(30)," +
		    "ShipID INT(4));" ;
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1212 ");
	    System.err.println(e.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE SchedullingDeparture (" +
		    "Date DATE," +
		    "HarbourName VARCHAR(30)," +
		    "ShipID INT(4));" ;
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1212 ");
	    System.err.println(e.getMessage());
	}




	try {
	     statement = con.createStatement();
	   String alterString = "ALTER TABLE SchedullingArrival " +
  "ADD CONSTRAINT FK_ShipID FOREIGN KEY (ShipID) REFERENCES Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE;" ;
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	   System.err.println("Got an exception!1212 ");
	    System.err.println(ex.getMessage());
	}


		try {
	     statement = con.createStatement();
	   String alterString = "ALTER TABLE SchedullingDeparture " +
  "ADD FOREIGN KEY (ShipID) REFERENCES Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE;" ;
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	   System.err.println("Got an exception!1213 ");
	    System.err.println(ex.getMessage());
	}



		try {
	     statement = con.createStatement();
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


	 





	 try {
	   statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (date, HarbourName, ShipID)  VALUES" +
		    " ('2010-05-01', 'Odense', 1),('2010-05-02', 'Kiel', 1)," +
		    "('2010-05-03', 'Hamburg', 1), ('2010-05-04', 'Amsterdam', 1)," +
		    "('2010-05-13', 'London', 1), ('2010-05-21', 'Porto', 1)," +
		    "('2010-05-29', 'Valencia', 1), ('2010-06-06', 'Marseille', 1);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());
	}
 try {
	   statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (date, HarbourName, ShipID)  VALUES" +
		    " ('2010-05-07', 'Odense', 2),('2010-05-08', 'Aarhus', 2)," +
		    "('2010-05-10', 'Aalborg', 2), ('2010-05-11', 'Goteborg', 2)," +
		    "('2010-05-17', 'Amsterdam', 2), ('2010-05-20', 'London', 2)," +
		    "('2010-05-26', 'Bordeaux', 2), ('2010-06-03', 'Lisabon', 2);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

    }



	 try {
	   statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (date, HarbourName, ShipID)  VALUES" +
		    " ('2010-05-02', 'Odense', 1),('2010-05-03', 'Kiel', 1)," +
		    "('2010-05-04', 'Hamburg', 1), ('2010-05-05', 'Amsterdam', 1)," +
		    "('2010-05-14', 'London', 1), ('2010-05-22', 'Porto', 1)," +
		    "('2010-05-30', 'Valencia', 1), ('2010-06-07', 'Marseille', 1);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());
	}
 try {
	   statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (date, HarbourName, ShipID)  VALUES" +
		    " ('2010-05-08', 'Odense', 2),('2010-05-09', 'Aarhus', 2)," +
		    "('2010-05-11', 'Aalborg', 2), ('2010-05-12', 'Goteborg', 2)," +
		    "('2010-05-18', 'Amsterdam', 2), ('2010-05-21', 'London', 2)," +
		    "('2010-05-27', 'Bordeaux', 2), ('2010-06-04', 'Lisabon', 2);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

    }





    }

    public ArrayList findAvrilShip(String startLoc, String endLoc, String volume, String weight)	{

ArrayList<String> dateChoose = new ArrayList<String>();
String afgang = null;
String ankomst = null;
try {
    ResultSet resultSet;
String sqlStmt = "SELECT * FROM Schedulling WHERE HarbourName= '" + startLoc + "' " +
	"AND ";
Statement stmt;
stmt = con.createStatement();

resultSet = stmt.executeQuery(sqlStmt);

while (resultSet.next()) {
afgang= resultSet.getString("Date");

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


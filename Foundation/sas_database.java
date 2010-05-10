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
	    String dropString = "DROP TABLE Harbour";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! Harbour");
	    System.err.println(e.getMessage());
	}


	try {
	    statement = con.createStatement();
	    String dropString = "DROP TABLE Container;";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}

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
	    String dropString = "DROP TABLE Customer;";
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
	    String dropString = "CREATE TABLE Harbour ("
		    + "Harbour VARCHAR(30),"
		    + "Coordinate VARCHAR(50) NOT NULL,"
		    + "Nationally VARCHAR(50) NOT NULL,"
		    + "PRIMARY KEY (Harbour));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}



	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Ship ("
		    + "ShipID INT(4),"
		    + "ShipName VARCHAR(50) NOT NULL,"
		    + "ShipType VARCHAR(50) NOT NULL,"
		    + "Captain VARCHAR(70) NOT NULL,"
		    + "CurrentContainer INT(6) NOT NULL,"
		    + "MaxContainer INT(6) NOT NULL,"
		    + "PRIMARY KEY (ShipID));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}


	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Container ("
		    + "ContainerID INT(8) AUTO_INCREMENT,"
		    + " ShipID INT(4),"
		    + "Content VARCHAR(50),"
		    + "OrderID INT(5), "
		    + "PRIMARY KEY (ContainerID));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}



// Create order table
	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Customer("
		    + "UserID INT(5) ,"
		    + "Company VARCHAR(100) ,"
		    + "Adress VARCHAR(100) ,"
		    + " Password VARCHAR(20), "
		    + "PRIMARY KEY (UserID));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}


	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE Ordre ("
		    + "OrderID INT(5) AUTO_INCREMENT, "
		    + "UserID INT(5), "
		    + "ShipID INT(4), "
		    + "DepartureDate DATE, "
		    + "ArrivalDate DATE, "
		    + "PRIMARY KEY(OrderID));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! ");
	    System.err.println(e.getMessage());
	}




	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE SchedullingArrival ("
		    + "Date_Arrival DATE,"
		    + "HarbourName_Arrival VARCHAR(30),"
		    + "ShipID INT(4));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1212 ");
	    System.err.println(e.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String dropString = "CREATE TABLE SchedullingDeparture ("
		    + "Date_Departure DATE,"
		    + "HarbourName_Departure VARCHAR(30),"
		    + "ShipID INT(4));";
	    statement.executeUpdate(dropString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!1212 ");
	    System.err.println(e.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String alterString = "ALTER TABLE Ordre "
		    + "ADD CONSTRAINT FK_UserID FOREIGN KEY (UserID) REFERENCES Customer (UserID) ON DELETE SET NULL ON UPDATE CASCADE;";
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	    System.err.println("Got an exception!12122 ");
	    System.err.println(ex.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String alterString = "ALTER TABLE Container "
		    + "ADD CONSTRAINT FK_OrderID FOREIGN KEY (OrderID) REFERENCES Ordre (OrderID) ON DELETE SET NULL ON UPDATE CASCADE, "
		    + "ADD CONSTRAINT FK_ShipID FOREIGN KEY (ShipID) REFERENCES Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE;";
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	    System.err.println("Got an exception!112122 ");
	    System.err.println(ex.getMessage());
	}


	try {
	    statement = con.createStatement();
	    String alterString = "ALTER TABLE SchedullingArrival "
		    + "ADD FOREIGN KEY (ShipID) REFERENCES Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE;";
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	    System.err.println("Got an exception!12123 ");
	    System.err.println(ex.getMessage());
	}


	try {
	    statement = con.createStatement();
	    String alterString = "ALTER TABLE SchedullingDeparture "
		    + "ADD FOREIGN KEY (ShipID) REFERENCES Ship (ShipID) ON DELETE SET NULL ON UPDATE CASCADE;";
	    statement.executeUpdate(alterString);
	} catch (SQLException ex) {
	    System.err.println("Got an exception!1213 ");
	    System.err.println(ex.getMessage());
	}

		try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO Harbour VALUES" +
		    " ('Odense', '10.39306640625, 55.3915921070334', 'Denmark'),  " +
		    " ('Kiel', '10.118408203125, 54.322931143263474', 'Germany'), " +
		    " ('Lübeck', '10.6787109375, 53.871963457471786', 'Germany'), " +
		    " ('Hamburg', '9.9755859375, 53.55336278552809', 'Germany'), "  +
		    " ('Amsterdam', '4.888916015625, 52.3755991766591', 'Nederland'), " +
		    " ('Rotterdam', '4.46044921875, 51.92394344554469', 'Nederland'), " +
		    " ('Dunkirk', '2.373046875, 51.02757633780243', 'France'), " +
		    " ('Poole', '-1.9830322265625, 50.719069112580804', 'England'), " +
		    " ('Porto', '-8.61328125, 41.16211393939692', 'Portugal'), " +
		    " ('Valencia', '-0.384521484375, 39.470125122358176', 'Spain'), " +
		    " ('Lisabon', '-9.129638671875, 38.70265930723801', 'portugal'), " +
		    " ('Gothenborg', '11.97509765625, 57.69240553526455', 'Sweden'), " +
		    " ('Stockholm', '18.03955078125, 59.33318942659219', 'Sweden'), " +
		    " ('Oslo', '10.733642578125, 59.910975970796784', 'Norway'), " +
		    " ('Bergen', '5.3173828125, 60.39214792251884', 'Norway'), " +
		    "('Riga', '24.10400390625, 56.9569571133683', 'Latvia'), " +
		    "('Helsinki', '24.9169921875, 60.174306261926034', 'Finland'), " +
		    "('Sankt-Peterburg', '30.322265625, 59.93300042374631', 'Russia'), " +
		    "('Cork', '-8.469772338867188, 51.89874055902119', 'Ireland'), " +
		    "('Bosten', '-71.08154296875, 42.35854391749705', 'USA'), " +
		    "('New York', '-74.00390625, 40.713955826286046', 'USA'), " +
		    "('Philadelphia', '-75.1904296875, 39.9602803542957', 'USA'), " +
		    "('New Port', '-76.4208984375, 36.98500309285596', 'USA'), " +
		    "('New Orleans', '-90.06591796875, 29.954934549656144', 'USA'); ";
		 

	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}




	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO Customer VALUES"
		    + " (1, 'SDU', 'Niels bohr alle Odense M Denmark', 'Hest')";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}




	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO Ship VALUES"
		    + " (1, 'Neil Young', 'ALM Transport', 'Dan Nguyen', 0, 1000),"
		    + "(2, 'Melua', 'ALM Transport', 'Mats Larsen', 0, 1250),"
		    + "(3, 'Madonna', 'ALM Transport', 'Soeren Joergensen', 0, "
		    + "700),"
		    + "(4, 'Jim Morrison', 'ALM Transport', 'Stefan Larsen', 0, "
		    + "1500),"
		    + "(5, 'Kurt Cubain', 'ALM Transport', 'Marius Vestergaard',"
		    + " 0, 250)";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}

	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO Container (ShipID)  VALUES"
		    + "(1), (1), (1), (1), (1)";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception!  ");
	    System.err.println(e.getMessage());
	}





	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (Date_Arrival, HarbourName_Arrival, ShipID)  VALUES"
		    + " ('2010-05-03', 'Kiel', 1),"
		    + "('2010-05-05', 'Hamburg', 1), ('2010-05-07', 'Amsterdam', 1),"
		    + "('2010-05-16', 'Poole', 1), ('2010-05-23', 'Porto', 1),"
		    + "('2010-05-30', 'Valencia', 1), ('2010-06-07', 'Marseille', 1);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());
	}
	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (Date_Arrival, HarbourName_Arrival, ShipID)  VALUES"
		    + " ('2010-05-23', 'Gothenburg', 2),"
		    + "('2010-05-28', 'Bergen', 2), "
		    + "('2010-06-11', 'Edinburgh', 2),"
		    + "('2010-06-27', 'Amsterdam', 2), "
		    + "('2010-06-06', 'Hamburg', 2),"
		    + "('2010-06-11', 'Kiel', 2), "
		    + "('2010-06-19', 'Riga', 2), "
		    + "('2010-06-24', 'Helsinki', 2), "
		    + "('2010-06-28', 'Stockholm', 2), "
		    + "('2010-07-02', 'Odense', 2)";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}

	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (Date_Arrival, HarbourName_Arrival, ShipID)  VALUES"
		    + " ('2010-07-11', 'Kiel', 3),"
		    + "('2010-07-13', 'Hamburg', 3), "
		    + "('2010-07-27', 'Amsterdam', 3), "
		    + "('2010-08-06', 'Poole', 3),"
		    + "('2010-08-14', 'Cork', 3), "
		    + "('2010-06-22', 'Bosten', 3), "
		    + "('2010-06-26', 'New York', 3), "
		    + "('2010-06-28', 'New Orleams', 3), "
		    + "('2010-07-16', 'Lisabon', 3), "
		    + "('2010-07-29', 'Poole', 3), "
		    + "('2010-08-11', 'Dunkirk', 3), "
		    + "('2010-08-18', 'Amsterdam', 3), "
		    + "('2010-08-23', 'Hamburg', 3), "
		    + " ('2010-08-25', 'Odense', 3);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}



	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (Date_Arrival, HarbourName_Arrival, ShipID)  VALUES"
		    + " ('2010-06-01', 'Gothenburg', 4),"
		    + "('2010-06-05', 'Oslo', 4), ('2010-06-15', 'Amsterdam', 4),"
		    + "('2010-06-17', 'Rotterdam', 4), ('2010-06-23', 'Poole', 4),"
		    + "('2010-07-01', 'Cork', 4), ('2010-07-28', 'Bosten', 4),"
		    + "('2010-08-06', 'New York', 4), ('2010-08-13', 'Philadelphia', 4),"
		    + "('2010-08-06', 'NewPort', 4) ;";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}

	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingArrival (Date_Arrival, HarbourName_Arrival, ShipID)  VALUES"
		    + " ('2010-05-22', 'Kiel', 5),"
		    + "('2010-05-24', 'Lubeck', 5), ('2010-05-26', 'Koebenhavn', 5),"
		    + "('2010-06-01', 'Riga', 5), ('2010-06-06', 'Helsinki', 5),"
		    + "('2010-06-11', 'Sankt-Peterburg', 5), ('2010-06-14', 'Stockholm', 5),"
		    + "('2010-06-21', 'Odense', 5);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}





	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (Date_Departure, HarbourName_Departure, ShipID)  VALUES"
		    + " ('2010-05-02', 'Odense', 1),('2010-05-04', 'Kiel', 1),"
		    + "('2010-05-06', 'Hamburg', 1), ('2010-05-08', 'Amsterdam', 1),"
		    + "('2010-05-17', 'Poole', 1), ('2010-05-24', 'Porto', 1),"
		    + "('2010-05-31', 'Valencia', 1) ;";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());
	}
	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (Date_Departure, HarbourName_Departure, ShipID)  VALUES"
		    + " ('2010-05-22', 'Odense', 2),"
		    + " ('2010-05-24', 'Gothenburg', 2),"
		    + "('2010-05-29', 'Bergen', 2), "
		    + "('2010-06-12', 'Edinburgh', 2),"
		    + "('2010-06-28', 'Amsterdam', 2), "
		    + "('2010-06-07', 'Hamburg', 2),"
		    + "('2010-06-12', 'Kiel', 2), "
		    + "('2010-06-20', 'Riga', 2), "
		    + "('2010-06-25', 'Helsinki', 2), "
		    + "('2010-06-29', 'Stockholm', 2)";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}

	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (Date_Departure, HarbourName_Departure, ShipID)  VALUES"
		    + " ('2010-07-10', 'Odense', 3),"
		    + " ('2010-07-12', 'Kiel', 3),"
		    + "('2010-07-14', 'Hamburg', 3), "
		    + "('2010-07-28', 'Amsterdam', 3), "
		    + "('2010-08-07', 'Poole', 3),"
		    + "('2010-08-15', 'Cork', 3), "
		    + "('2010-06-23', 'Bosten', 3), "
		    + "('2010-06-27', 'New York', 3), "
		    + "('2010-06-29', 'New Orleams', 3), "
		    + "('2010-07-17', 'Lisabon', 3), "
		    + "('2010-07-30', 'Poole', 3), "
		    + "('2010-08-12', 'Dunkirk', 3), "
		    + "('2010-08-19', 'Amsterdam', 3), "
		    + "('2010-08-24', 'Hamburg', 3); ";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}




	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (Date_Departure, HarbourName_Departure, ShipID)  VALUES"
		    + " ('2010-05-31', 'Odense', 4), "
		    + "('2010-06-02', 'Gothenburg', 4),"
		    + "('2010-06-06', 'Oslo', 4), ('2010-06-15', 'Amsterdam', 4),"
		    + "('2010-06-18', 'Rotterdam', 4), ('2010-06-23', 'Poole', 4),"
		    + "('2010-07-02', 'Cork', 4), ('2010-07-28', 'Bosten', 4),"
		    + "('2010-08-07', 'New York', 4), ('2010-08-13', 'Philadelphia', 4),"
		    + "('2010-08-07', 'NewPort', 4) ;";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 122 ");
	    System.err.println(e.getMessage());

	}



	try {
	    statement = con.createStatement();
	    String insertString = "INSERT INTO SchedullingDeparture (Date_Departure, HarbourName_Departure, ShipID)  VALUES"
		    + " ('2010-05-21', 'Odense', 5),('2010-05-23', 'Kiel', 5),"
		    + "('2010-05-25', 'Lubeck', 5), ('2010-05-27', 'Koebenhavn', 5),"
		    + "('2010-06-02', 'Riga', 5), ('2010-06-07', 'Helsinki', 5),"
		    + "('2010-06-12', 'Sankt-Peterburg', 5), ('2010-06-15', 'Stockholm', 5);";
	    statement.executeUpdate(insertString);
	} catch (SQLException e) {
	    System.err.println("Got an exception! 12 ");
	    System.err.println(e.getMessage());

	}





    }

    public ArrayList findAvrilShip(String startLoc, String endLoc, String volume, String weight) {

	ArrayList<String> dateChoose = new ArrayList<String>();
	String afgang = null;
	String ankomst = null;
	try {
	    ResultSet resultSet;
	    String sqlStmt = "SELECT * FROM Schedulling WHERE HarbourName= '" + startLoc + "' "
		    + "AND ";
	    Statement stmt;
	    stmt = con.createStatement();

	    resultSet = stmt.executeQuery(sqlStmt);

	    while (resultSet.next()) {
		afgang = resultSet.getString("Date");

	    }
	    System.out.println("sdsdsdsdsdsd " + afgang);
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
		ankomst = resultSet.getString("Dato");

	    }
	    System.out.println("sdsdsdsdsdsd " + ankomst);
	} catch (SQLException e) {
	    System.out.println("Error executing sql statement");
	}

	DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
	Date Dateafgang = null, Dateankomst = null;
	try {
	    Dateafgang = df.parse(afgang);
	    Dateankomst = df.parse(ankomst);
	} catch (ParseException ex) {
	}

	if (Dateafgang.before(Dateankomst)) {
	    dateChoose.add(afgang);

	}
    }

    public ArrayList getAllShip() {

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

	    System.out.println("\nSELECT Destination, Dato FROM " + ship__id + "_ship");

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


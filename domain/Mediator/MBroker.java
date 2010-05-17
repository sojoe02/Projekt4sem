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

// Der implementeres interface IAContants, som indeholder konstanter
public class MBroker implements IAContants {

    private ESas sas;
    private FConnection connection;
    private ResultSet rs;
    private String sqlStmt;
    private ArrayList<String> shipDates = new ArrayList<String>();
    private DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
    private String databaseStatus = "Can't get connection to the database";
    private String acces_ok = "You have now acces to the system";
    private String acces_fail = "You are not registered in our database";

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

//Opretter forbindelse til databasen, med de valgte konto, brugernavnog kodeord.
	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return databaseStatus;
	}

	// Dette stmt henter User fra databasen med det angivet userID.
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
	    Date startDate, Date endDate, int containers)
	    throws ParseException, SQLException {

	ResultSet rsDeparture = null;
	ResultSet rsArrival = null;
	ResultSet rsContainerInfo = null;

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}

	// Her findes der ud af hvor mange skibe der ekisterer ved redderiet.
	int currentShips = countShips();
// Dette loop kører alle skibene igennem.
	for (int i = 1; i < currentShips; i++) {

	    // Henter container information om maxcontainer og container som bruges.
	    rsContainerInfo = getContainerInfo(i);
	    rsContainerInfo.next();
// Det maxsimale container der kan være på skibet.
	    int maxContainers = rsContainerInfo.getInt("MaxContainer");

	    //Henter datoer, som gemmes i ResultSet, derfor kan et skib have flere datoer.
	    rsDeparture = getDeparture(i, startDest);

	    // En afgangsdato testes med alle ankomstdatoer,
	    // det foresættes til at der ikke er flere afgangsdatoer tilbage.
	    while (rsDeparture.next()) {
		String departureDate = rsDeparture.getString("Date");
		int shipID = rsDeparture.getInt("ShipID");
		rsArrival = getArrival(i, endDest);

		while (rsArrival.next()) {
		    String arrivalDate = rsArrival.getString("Date");
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
// Henter de dato muligheder, som passer med havn og ship for afgang.

    private ResultSet getDeparture(int currentShip,
	    String startDest) throws SQLException {

	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE Harbour = '" + startDest + "' "
		+ "AND A_or_D = 'Departure'"
		+ "AND ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);
	connection.getReader().query(sqlStmt);

	return rs;
    }
//-----------------------------------------------------------------------------
// Henter de dato muligheder, som passer med havn og ship for ankomst.

    private ResultSet getArrival(int currentShip,
	    String endDest) throws SQLException {

	sqlStmt = "SELECT * FROM Schedulling "
		+ "Where Harbour ='" + endDest + "' "
		+ "AND A_or_D = 'Arrival'"
		+ "AND ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);

	return rs;
    }

//-----------------------------------------------------------------------------
    // Henter maxContainer for det bestemte skib.
    private ResultSet getContainerInfo(int currentShip) throws SQLException {
	sqlStmt = "SELECT * FROM Ship "
		+ "WHERE ShipID = " + currentShip + ";";
	rs = connection.getReader().query(sqlStmt);
	return rs;
    }
//-----------------------------------------------------------------------------
// henter antal brugte container for et bestemt skip og tidsrum.

    private int getCurrentContainer(int currentShip, String departureDate,
	    String arrivalDate) throws SQLException {

	sqlStmt = "SELECT max(CurrentContainer) FROM Schedulling "
		+ "WHERE ShipID = " + currentShip + " AND Date BETWEEN '" + departureDate + "' AND '" + arrivalDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	return rs.getInt(1);
    }
//-----------------------------------------------------------------------------
/*
     * Denne metode returnerer en reference til et interface. Interfacet har en kontrakt
     * til ECustomer klassen. Metoden opretter den ønskende order, samt databasen
     * opdateres og de anvendte klasser i entity bliver mappet.
     */

    public IACustomer placeOrder(int ShipID, String startDest, String endDest, String DepartureDate,
	    String ArrivalDate, int containers, String content) throws SQLException {

	if (connection.connect(dbUrl, dbPassword, dataBaseUser) == false) {
	    return null;
	}
	// Henter userID ,da der kun er user som har adgang af gangen.
	int userID = sas.getUserID();

	// referencen af iaCustomer, får referencen til den aktive user.
	IACustomer iaCustomer = (IACustomer) sas.getCustomer();
	// Den valgte placeOrder gammes i databasen i ordretabellen.
	int orderID = saveNewOrder(userID, ShipID, DepartureDate, ArrivalDate);
	// Ordres Container skal gemmes i Containertabellen.
	saveNewContainer(orderID, ShipID, containers, content);
	// Tidsplanen opdateres i databasen.
	updateSchedulling(ShipID, DepartureDate, ArrivalDate, containers);
	// Kundes order mappes.
	mapOrder(userID, startDest, endDest);
	return iaCustomer;
    }
//----------------------------------------------------------------------------- 
// Stmt sendes til FWriter, som opdaterer tabellen og returnere OrderID.

    private int saveNewOrder(int UserID, int ShipID, String DepartureDate,
	    String ArrivalDate) throws SQLException {
// Indsætter den nye ordre i ordre tabellen.
	sqlStmt = "INSERT INTO Ordre (UserID, ShipID, DepartureDate, ArrivalDate) VALUES "
		+ "(" + UserID + ", " + ShipID + ", '" + DepartureDate
		+ "', '" + ArrivalDate + "');";
	// Skriver til FWriter klassen.
	Statement stmt = connection.getWriter().updatequery(sqlStmt);
	// Forbindelsen sluttes.
	connection.getWriter().closeStatement(stmt);
// Returnerer orderID for den nye ordre.
	return getOrderID(ShipID, DepartureDate);

    }
//----------------------------------------------------------------------------
    // Henter OrderID, da den selv genereres i databasen.

    private int getOrderID(int ShipID, String DepartureDate) throws SQLException {

	sqlStmt = "SELECT * FROM  Ordre "
		+ "WHERE ShipID = " + ShipID
		+ " AND DepartureDate = '" + DepartureDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	return rs.getInt("OrderID");
    }
//----------------------------------------------------------------------------
// Her gemmes ordres container i Containertabellen.

    private void saveNewContainer(int orderID, int shipID, int containers, String content) throws SQLException {

	// Stmt henter de container som er tomme og er på det bestemte skib.
	sqlStmt = "SELECT * FROM Container "
		+ "WHERE Status = 'Empty' AND ShipID = " + shipID + ";";
	rs = connection.getReader().query(sqlStmt);
// Antallet af container gemmes, hvor containers information opdateres.
	for (int i = 0; i < containers; i++) {
	    rs.next();
	    int containerID = rs.getInt("ContainerID");
// Container content og Status opdateres og OrderID gemmes også på containeren.
	    sqlStmt = "UPDATE Container SET Content = '" + content + "', "
		    + "OrderID = '" + orderID + "', "
		    + "Status = 'Full' WHERE ContainerID = " + containerID + ";";
	    Statement stmt = connection.getWriter().updatequery(sqlStmt);
	    connection.getWriter().closeStatement(stmt);
	}
	connection.getReader().closeResult(rs);
    }
//------------------------------------------------------------------------------
// Schedulling opdateres med currentContainer.

    private void updateSchedulling(int ShipID, String DepartureDate,
	    String ArrivalDate, int containers) throws SQLException {

// Henter sID fra afgangsdato og ankomstdato fra schedullingtabellen.
	int sIDDeparture = getSIDDeparture(ShipID, DepartureDate);
	int sIDArrival = getSIDArrival(ShipID, ArrivalDate);

	/*
	 * Opdaterer Schedullingtabellen med det nye antal af currentContainer,
	 * hvor der opdateres fra afgangsdato til ankomstdato.
	 */
	for (int i = sIDDeparture; i < sIDArrival + 1; i++) {

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

    private int getSIDDeparture(int ShipID, String DepartureDate) throws SQLException {

	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE ShipID = " + ShipID
		+ " AND Date = '" + DepartureDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int sIDDeparture = rs.getInt("sID");
	connection.getReader().closeResult(rs);
	return sIDDeparture;
    }
    //----------------------------------------------------------------------------

    private int getSIDArrival(int ShipID, String ArrivalDate) throws SQLException {
	sqlStmt = "SELECT * FROM Schedulling "
		+ "WHERE ShipID = " + ShipID
		+ " AND Date = '" + ArrivalDate + "';";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
	int sIDArrival = rs.getInt("sID");
	connection.getReader().closeResult(rs);
	return sIDArrival;
    }
//----------------------------------------------------------------------------
    /*
     * Stmt sendes til Reader, som henter de order, som er oprettet med det 
    bestemte userID. Dermed oprettes orderne en af gangen.
     */

    private void mapOrder(int UserID, String startDest, String endDest) throws SQLException {

	// Stmt henter de ordre, som tilholder til den bestemte userID.
	sqlStmt = "SELECT * FROM Ordre "
		+ "WHERE UserID = " + UserID + ";";
	ResultSet rsOrder = connection.getReader().query(sqlStmt);

	while (rsOrder.next()) {
	    int orderID = rsOrder.getInt("OrderID");
	    int shipID = rsOrder.getInt("ShipID");
	    String departureDate = rsOrder.getString("DepartureDate");
	    String arrivalDate = rsOrder.getString("ArrivalDate");
// skib mappes i entity pakken.
	    mapShip(shipID);
// henter start og slut havns informationer.
	    ResultSet rsStartDest = getHarbourInfo(startDest);
	    ResultSet rsEndDest = getHarbourInfo(endDest);

	    rsStartDest.next();
	    rsEndDest.next();
// Ordre mappes i Entity.
	    sas.mapOrder(orderID, sas.getShip(shipID), startDest, endDest,
		    rsStartDest.getString("Coordinate"), rsStartDest.getString("Nationally"),
		    rsEndDest.getString("Coordinate"), rsEndDest.getString("Nationally"),
		    departureDate, arrivalDate);
// Container mappes i entity.
	    mapContainer(shipID, orderID);
	    //ResultSet sluttes.
	    connection.getReader().closeResult(rsStartDest);
	    connection.getReader().closeResult(rsEndDest);
	}
	connection.getReader().closeResult(rs);
	connection.getReader().closeResult(rsOrder);

    }
//-----------------------------------------------------------------------------

    private ResultSet getHarbourInfo(String Dest) throws SQLException {
	sqlStmt = "SELECT * FROM Harbour "
		+ "WHERE Harbour = '" + Dest + "';";
	rs = connection.getReader().query(sqlStmt);
	return rs;
    }
//-----------------------------------------------------------------------------
    // Skib mappes i Entity pakken.

    private void mapShip(int shipID) throws SQLException {
// Stmt henter det specifikke skib via shipID.
	sqlStmt = "SELECT * FROM ship "
		+ "WHERE ShipID = " + shipID + ";";
	rs = connection.getReader().query(sqlStmt);
	rs.next();
// ESas kaldes med skibets parameter.
	sas.mapShip(shipID, rs.getString("ShipName"), rs.getString("ShipType"),
		rs.getString("Captain"), rs.getInt("MaxContainer"));
    }
//-----------------------------------------------------------------------------
    // Mapper Container via EShip og ECargo.

    private void mapContainer(int ShipID, int OrderID) throws SQLException {

// Henter Container med det specifikke orderID.
	sqlStmt = "SELECT * FROM Container "
		+ "WHERE OrderID = " + OrderID + ";";
	rs = connection.getReader().query(sqlStmt);
// Opretter det antal containerobjekter, som orderen var på.
	while (rs.next()) {
	    sas.mapContainer(sas.getShip(ShipID), rs.getInt("ContainerID"), rs.getString("Content"),
		    OrderID, rs.getString("Status"));
	}
    }
}



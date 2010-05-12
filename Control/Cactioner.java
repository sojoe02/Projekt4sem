package Control;

import Acquaintance.IACustomer;
import domain.Entity.ESas;
import domain.Mediator.MBroker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CActioner {

    private MBroker broker;
    private IACustomer iaShip;
    private ESas sas;

    public CActioner() throws ClassNotFoundException {
	sas = new ESas();
	broker = new MBroker(sas);
    }
//-----------------------------------------------------------------------------
    // findShipDates henter de skibsdatoer der overholder kundens ønsker.

    public ArrayList findShipDates(String startDest, String endDest,
	    Date startDate, Date endDate, int containers, String content)
	    throws Exception {

	/* Resultet af de fundne datoer sendes op til kunden i
	 presentationsalget i form af et arraylist.
	 */

	return broker.findShipDates(startDest, endDest, startDate, endDate, containers, content);
    }
//------------------------------------------------------------------------------------
/*
 * User bliver hentet fra databasen og oprettet i Entity pakken,
 * hvis user altså er oprettet.
 * User skal kende userID, som er en unik nummer.
 */
    public Boolean mapUser(int userID) throws SQLException {

	/*
	 * Kaldes i mediator pakken. Hvis User eksister retuneres sandt eller
	 * sendes falsk.
	 */
	Boolean access = broker.mapUser(userID);
	return access;
    }

//------------------------------------------------------------------------------------
    public IACustomer placeOrder(int shipID, Date DepartureDate, Date ArrivalDate) throws Exception {


	return broker.placeOrder(shipID, DepartureDate, ArrivalDate);

    }
}

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

//------------------------------------------------------------------------------------
/*
 * User bliver hentet fra databasen og oprettet i Entity pakken,
 * hvis user altså er oprettet.
 * User skal kende userID, som er en unik nummer.
 */
    public String mapUser(int userID) throws SQLException {

	/*
	 * Kaldes i mediator pakken. Hvis User eksister retuneres sandt eller
	 * sendes falsk.
	 */
	String access = broker.mapUser(userID);
	return access;
    }

//-----------------------------------------------------------------------------
    // findShipDates henter de skibsdatoer der overholder kundens ønsker.

    public ArrayList findShipDates(String startDest, String endDest,
	    Date startDate, Date endDate, int containers)
	    throws Exception {

	/* Resultet af de fundne datoer sendes op til kunden i
	 presentationsalget i form af et arraylist.
	 */

	return broker.findShipDates(startDest, endDest, startDate, endDate, containers);
    }
//------------------------------------------------------------------------------------

    /*
     * Her har kunden valgt et skib, derfor skal orderen oprettes og gemmes i
     * databasen. Der mappes order, ship, container klasser, da oplysninger
     * skal udvekles i presentaionslaget. Derfor returneres en reference af 
     * IACustomer, som findes i interface klassen. Denne klasse kan har et 
     * interface med ECustomer.
     */

    public IACustomer placeOrder(int shipID, String startDest, String endDest,
	    String DepartureDate,
	    String ArrivalDate, int containers, String content) throws Exception {

	return broker.placeOrder(shipID, startDest, endDest, DepartureDate, ArrivalDate, containers, content);

    }
}

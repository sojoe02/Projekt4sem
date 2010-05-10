/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;


import Acquaintance.IACustomer;
import domain.Entity.ESas;
import domain.Mediator.MBroker;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class Cactioner {

    private MBroker broker;
    private IACustomer iaShip;
    private ESas sas;

  public Cactioner() throws ClassNotFoundException	{
    sas = new ESas();
    broker = new MBroker(sas);
    }
//----------------------------------------------------------------------------------
    public ArrayList findShipDates(String metodeChoose, String startloc,
		   String endLoc, Date endDate, int containers) throws Exception   {

	return broker.findShipDates(startloc, endLoc, endDate, containers);


	}
//------------------------------------------------------------------------------------
public Boolean loginAccess (int userID, String passWord) throws SQLException	{

	   Boolean access= broker.loginAccess(userID, passWord);
	 return access;
}

//------------------------------------------------------------------------------------

    public IACustomer placeOrder(int shipID, Date DepartureDate, Date ArrivalDate) throws Exception {

	
	 return  broker.placeOrder(shipID, DepartureDate, ArrivalDate);

    }

}

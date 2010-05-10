/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Control;


import Acquaintance.IAShip;
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
    private IAShip iaShip;

  public Cactioner() throws ClassNotFoundException	{
    broker = new MBroker();
    }
//----------------------------------------------------------------------------------
    public ArrayList findDates(String metodeChoose, String startloc,
		   String endLoc, Date endDate, int containers) throws Exception   {

	return broker.findDates(startloc, endLoc, endDate, containers);


	}
//------------------------------------------------------------------------------------
public Boolean loginAccess (int userID, String passWord) throws SQLException	{

	   Boolean access= broker.loginAccess(userID, passWord);
	 return access;
}

//------------------------------------------------------------------------------------

    public IAShip placeOrder(int shipID, String DepartureDate, String ArrivalDate) throws Exception {

	
	 return  broker.placeOrder(shipID, DepartureDate, ArrivalDate);

    }

}

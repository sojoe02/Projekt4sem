/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Network;

import domain.Mediator.MBroker;
import java.util.ArrayList;

/**
 *
 * @author Mats l
 */
public class Control {

    ClientServerCommunication csc;
    SendObject sObject;
    MBroker mBroker;
    public void startControl() throws Exception	{
	    csc = new ClientServerCommunication();
	    csc.startServer();
	System.out.println( csc.getMetodeChoose());
	
	if (csc.getMetodeChoose() == null ? "FindDates" == null : csc.getMetodeChoose().equals("FindDates") )	{
	    mBroker = new MBroker();
	  ArrayList<String> ShipDates = new ArrayList<String>();
	  ShipDates = mBroker.findDates(csc.getStartLoc(),csc.getEndLoc(),csc.getEndDate());
	  csc.responseFindDates(ShipDates);
	  csc.placeOrder();
	  mBroker.placeOrder(csc.getShipID(), csc.getDepartureDate(), csc.getArrivalDate());
	}

    }
}

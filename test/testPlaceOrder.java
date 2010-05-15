/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import Acquaintance.IACustomer;
import Control.CActioner;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mats l
 */
public class testPlaceOrder {

   

    public static void main(String[] args) throws ParseException, Exception  {
	CActioner cactioner = new CActioner();

	String acces = cactioner.mapUser(1);
	System.out.println(acces);
	

	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
ArrayList<String> ShipDates = new ArrayList<String>();
	Date endDate =df.parse("2010-06-11");
	Date startDate =df.parse("2010-06-02");
	 ShipDates = cactioner.findShipDates( "Odense", "Amsterdam", startDate, endDate, 4);
	 for (int i = 0 ; ShipDates.size() > i ; i++)	{
	 System.out.println(ShipDates.get(i));
	 }

	 IACustomer iaCustomer;
	 String departureDate = "2010-05-02";
	 String arrivalDate = "2010-05-07";
	 

	 iaCustomer = cactioner.placeOrder(1, "Odense", "Amsterdam", departureDate, arrivalDate, 4, "Bananer");
	 System.out.println(iaCustomer.confirm());
    }


}

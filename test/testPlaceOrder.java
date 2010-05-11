/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import Acquaintance.IACustomer;
import Control.Cactioner;
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
	Cactioner cactioner = new Cactioner();

	Boolean access = cactioner.loginAccess(1, "Hest");

	if (access == true) System.out.println("right login");
	else {System.out.println("Wrong login");}

	DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
ArrayList<String> ShipDates = new ArrayList<String>();
	Date endDate =df.parse("2010-06-11"); 
	 ShipDates = cactioner.findShipDates("1", "Odense", "Amsterdam", endDate, 11);
	 for (int i = 0 ; ShipDates.size() > i ; i++)	{
	 System.out.println(ShipDates.get(i));
	 }
	 IACustomer iaCustomer;
	 Date departureDate = df.parse("2010-05-02");
	 Date arrivalDate = df.parse("2010-05-04");
	 System.out.println("sdsdsd " + df.format(departureDate));

	 iaCustomer = cactioner.placeOrder(1, departureDate, arrivalDate);
	 System.out.println(iaCustomer.confirm());
    }


}

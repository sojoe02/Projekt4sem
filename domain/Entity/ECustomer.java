/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.Entity;

import Acquaintance.IACustomer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mats l
 */
public class ECustomer implements IACustomer {

    private Map<String, EOrder> orders = new HashMap<String, EOrder>();
    private EOrder order;
    int userID;
    String company;
    String adress;
    String password;

    public ECustomer(int userID, String company, String adress, String password) {
	this.userID = userID;
	this.company = company;
	this.adress = adress;
	this.password = password;
    }

    public void mapOrder(int OrderID, int UserID, int ShipID, Date DepartureDate, Date ArrivalDate) {
	order = new EOrder(OrderID, UserID, ShipID, DepartureDate, ArrivalDate);
	orders.put(Integer.toString(OrderID), order);
    }

    public int getUserID() {
	return userID;
    }

    public String confirm() {
	return  userID + " " + company + " " + adress + " " + password + " ";
    }
}


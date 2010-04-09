/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

/**
 *
 * @author Mats l
 */
public class Order {
    private int orderID =0;


    public Order(String endLoc, String startLoc, String date, String volume, String weight) {
	orderID++;
    }

    public String getOrderID()	{
	return String.valueOf(orderID);
    }
}

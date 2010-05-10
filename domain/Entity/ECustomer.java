/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain.Entity;

/**
 *
 * @author Mats l
 */
public class ECustomer {
int userID;
String company;
String adress;
String password;

public ECustomer()  {

}
public void setCustomer(int userID, String company, String adress, String password) {
    this.userID = userID;
    this.company = company;
    this.adress = adress;
    this.password = password;

}
}

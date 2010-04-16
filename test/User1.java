/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import domain.*;
/**
 *
 * @author Mats l
 */
public class User1 {

     public static void main (String[] args) throws Exception {

	 User admin = new User("123","Dan","Odense");
	 Sas sas = new Sas(admin);
	 sas.setUp();

	 
     }
}

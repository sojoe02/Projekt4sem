/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import domain.Entity.ESas;
import domain.*;
/**
 *
 * @author Mats l
 */
public class User1 {

     public static void main (String[] args) throws Exception {

	 EUser admin = new EUser("123","Dan","Odense");
	 ESas sas = new ESas(admin);
	 sas.setUp();


	 
     }
}

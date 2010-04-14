/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import domain.*;
import java.util.ArrayList;

public class SeeInfoTest {

    public static void main(String[] args){
	Sas SAS = new Sas();
	//SAS.seeShipInfo("s1");

	User dan = new User("123","Dan","Odense");
	Cargo cargo = new Cargo("120","320");

	Container con1 = new Container("A121",dan,"A1","Candy");
	Container con2 = new Container("B121",dan,"B2","Telephones");

	ArrayList test = new ArrayList();

	test.add(cargo.getAllContainer());
	System.out.print(test);
	

	
	

    }
}

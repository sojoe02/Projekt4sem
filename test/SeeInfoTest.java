/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test;
import domain.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SeeInfoTest {

    public static void main(String[] args){

/*
	User dan = new User("123","Dan","Odense");
	//System.out.println(dan);
	//Lave User
	Sas SAS = new Sas(dan);

	Container con1 = new Container("A121",dan,"A1","Candy");
	Container con2 = new Container("B121",dan,"B2","Telephones");
	//opret container

	Ship ship1 = new Ship("s1", "1000", "45000");
	//opret testskib
	//System.out.println("Skib er oprettet");

	SAS.addShip("s1", ship1);

	
	//System.out.println(SAS.findShip("s1"));

	//System.out.println("Skib er tilføjet i listen ");


	SAS.findShip("s1").getCargo().addContainer("A121", con1);
	SAS.findShip("s1").getCargo().addContainer("B121", con2);
	//tilføj container til Skibs Cargo

	//System.out.println("container er tilføjet");


	
	SAS.seeShipInfo("s1", dan);
*/
	User dan = new User("123","Dan","Odense");
	Container con1 = new Container("A121",dan.getUserID(),"Candy");
	Container con2 = new Container("B121",dan.getUserID(),"Telephones");
	Order ord1 = new Order("Odense", "Aalborg", "12-12-12", "1000","10000", 2, "U27");
	ord1.addContainer(con1);
	ord1.addContainer(con2);
	System.out.println("Order er oprettet");
	
	System.out.println(ord1);




    }
}

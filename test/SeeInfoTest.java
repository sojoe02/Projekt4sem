/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import domain.Entity.ESas;
import domain.Entity.EContainer;
import domain.Entity.EShip;
import domain.Entity.EOrder;
import domain.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SeeInfoTest {

    private Map<String, EOrder> orders = new HashMap<String, EOrder>();
    int index = 0;

    public static void main(String[] args) {

	EUser dan = new EUser("123", "Dan", "Odense");
	EUser mats = new EUser("456", "Mats", "Langeland");
	System.out.println("Users er lavet");
	//Lave User
	ESas SAS = new ESas(dan);

	EContainer con1 = new EContainer("A121", dan.getUserID(), "Candy");
	EContainer con2 = new EContainer("B121", dan.getUserID(), "Telephones");
	EContainer con3 = new EContainer("C121", mats.getUserID(), "Cars");
	System.out.println("Container er oprettet");
	//opret container

<<<<<<< HEAD
	Ship ship1 = new Ship();
=======
	EShip ship1 = new EShip("s1", "1000", "45000");
>>>>>>> c46b6ec7d581798d16033f1bffaeca579dff077b
	//opret testskib


	SAS.addShipTemp("s1", ship1);
	System.out.println("Skib er oprettet og listet");

	//System.out.println(SAS.findShip("s1"));

	//System.out.println("Skib er tilføjet i listen ");


	SAS.findShip("s1").getCargo().addContainer("A121", con1, dan.getUserID(), con1.getContent());
	SAS.findShip("s1").getCargo().addContainer("B121", con2, dan.getUserID(), con2.getContent());
	SAS.findShip("s1").getCargo().addContainer("C121", con3, mats.getUserID(), con2.getContent());
	//tilføj container til Skibs Cargo

	System.out.println("container er tilføjet til skib\n");



	//SAS.seeShipInfo("s1", dan);



	//Opret ordre

	EOrder ord1 = new EOrder("Odense", "Aalborg", "12-12-12", "1000", "10000", 2, "U27");
	ord1.addContainer(con1);    //Giv ordre 1 en container
	dan.addOrder(ord1);	    //tilføj ordre1 til dan

	
	System.out.println("Du har valgt: " + dan.getOrder(ord1.getOrderID()));
	//System.out.println("Tilføjet :Order id: " + ord1.getOrderID()+ " Skibsid er: "+  ord1.getShipID());


	System.out.print("\n");
	EOrder ord2 = new EOrder("Odense", "Tokyo", "12-12-12", "3000", "30000", 2, "U2");
	ord2.addContainer(con2);    //Giv ordre 2 en container
	dan.addOrder(ord2);	    //tilføj Ordre 2 til dan 

	System.out.println("Du har valgt: " + dan.getOrder(ord2.getOrderID()));
	//System.out.println("Tilføjet :Order id: " + ord2.getOrderID() + " Skibsid er: "+  ord2.getShipID());

	
	//System.out.println("\n1: " + dan.getOrder(ord1.getOrderID()));
	//System.out.println("\n2: " + dan.getOrder(ord2.getOrderID()));


	

/*
	Order ord3 = new Order("Dalum", "Tokyo", "11-11-11", "2000","20000", 5, "U2");
	mats.addOrder(ord3);
	System.out.println("Mats ordre er færdig");
	

	ArrayList temp = new ArrayList();
	temp.add( dan.getOrders().getShipID());

	System.out.println(temp);


*/
	HashMap id = new HashMap();
        id = dan.getOrders();	//returner et

	Collection c = id.values();
	Iterator itr = c.iterator();

	

/*
	while(itr.hasNext())
	      System.out.println(itr.next());
*/

	System.out.println("Vi leder efter: " + ord1.getShipID());
	if(id.containsKey(ord1.getShipID())){


	    Collection c2 = dan.getOrder(ord1.getOrderID()).getAllContainers().values();
	    Iterator itr2 = c2.iterator();

	    while(itr2.hasNext())
	    {
		System.out.println(itr.next());
		
	    }

	    System.out.println("lort");
	}
	else
	    System.out.println("Ordre: " + ord2.getOrderID() + " findes ikke");

/*
	for(int index = 0; index < id.length; index++){
	    
	    if(id[index] == ord2.getShipID()){
		//i stedet for ord2 så har vi allerede fået den i seeShipInfo
		System.out.println("Det ønskede id er: " + id[index]);

	    }
	}


	for(int index = 0; index<dan.getOrderSize(); index++)
	{
	    System.out.println("I SeeInfoTest "+index);
	    System.out.println(dan.getOrders().getShipID());
	}
*/



    }

    
}

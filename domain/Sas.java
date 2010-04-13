package domain;

import java . util . * ;

public class Sas {


	
	private Map<String , Ship> ships = new HashMap<String , Ship >( ) ;
	
	public Sas (){}
	
	public void setUp()	{
	Ship ship = new Ship("1","50000", "100000");
	ships.put( ship.getShipsID(),ship) ;
	}
	
	
	
	public void placeOrder(String endLoc, String startLoc, String endDate, String volume, String weight)	{
		Ship ship = ships.get("1");
		
	/*	if (ship.availCargo(volume,weight)==true)	{
			
		}
	*/	
		
	//	ArrayList<String> shipsList = new ArrayList<String >( ) ;	
	//	shipsList.add(ships.get("1").toString());
	}

	public void seeShipInfo(String shipID){
	    Ship currentship = null;

	    Ship s1 = new Ship("s1","1000","45000");
	    Ship s2 = new Ship("s2","2000","30000");
	    //tilføj test skibe

	    ships.put("s1",s1);
	    ships.put("s2",s2);
	    //lægger skibe i hashmap

	    System.out.println(findShip(shipID));
	    //find skib frem

	}

	private Ship findShip(String ShipID){
	    return ships.get(ShipID);
	}

}



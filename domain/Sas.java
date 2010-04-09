package domain;

import java . util . * ;

public class Sas {


	private String endLoc;
	private String startLoc;
	private String endDate;
	private	String volume;
	private	String weight;



	Ship ship;
	Order order;
	private String totalPrice;

	private Map<String , Ship> ships = new HashMap<String , Ship >( ) ;
	
	public Sas ()	{
	
	}
	
	public void setUp()	{
	ship = new Ship("1","50000", "100000");
	//ships.put( ship.getShipsID(),ship) ;
	}
	
	
	
	public String placeOrder(String endLoc, String startLoc, String endDate, String volume,
		String weight)	{
		
		this.endLoc = endLoc;
		this.startLoc = startLoc;
		this.endDate = endDate;
		this.volume = volume;
		this.weight = weight;
		if (ship.availCargo(volume,weight)==true)	{
			
		}
		
	return totalPrice =calcPrice(volume, weight,startLoc,endLoc);

		
	//	ArrayList<String> shipsList = new ArrayList<String >( ) ;	
	//	shipsList.add(ships.get("1").toString());
	}


	public String calcPrice (String volume, String weight, String startLoc,String endLoc)	{
	    return "5";
	}

	 public String chooseDate (String date)	{

	    ship.updateShip(volume,weight);

	    order = new Order(endLoc, startLoc, date,volume,weight);

	   String orderID = order.getOrderID();

	   return "Confirm";
	 }
	public void seeShipInfo(String shipID){
	    Ship currentship = null;

	    Ship s1 = new Ship("s1","1000","45000");
	    Ship s2 = new Ship("s2","2000","30000");
	    //tilføj test skibe

	    ships.put("s1",s1);
	    ships.put("s2",s2);
	    //lægger skibe i hashmap

	    System.out.print(findShip(shipID));

	}

	private Ship findShip(String ShipID){
	    return ships.get(ShipID);

	}

}



import java . util . * ;

public class Sas {

	
	
	
	private Map<String , Ship> ships = new HashMap<String , Ship >( ) ;
	
	public Sas ()	{
	
	}
	
	public void setUp()	{
	Ship ship = new Ship("1","50000", "100000");
	ships.put( ship.getShipsID(),ship) ;
	}
	
	
	
	public void placeOrder(String endLoc, String startLoc, String endDate, String volume, String weight)	{
		Ship ship = ships.get("1");
		
		if (ship.availCargo(volume,weight)==true)	{
			
		}
		
		
	//	ArrayList<String> shipsList = new ArrayList<String >( ) ;	
	//	shipsList.add(ships.get("1").toString());
	}
}


public class Ship {

	private String shipsID;
	
	
	public Ship (String shipID, String currentvolume, String currentweight)	{
	this.shipsID = shipsID;
	Cargo cargo = new Cargo(currentvolume, currentweight);
	}
	public String getShipsID()	{
		return shipsID;
	}
	
	public boolean availCargo(String volume, String weight)	{
		if (cargo.availCargo(volume, weight)== true) {
			return true;	
		}
		
		
	}
	
	
	
	public String toString()	{
		return	"22";
	}
}

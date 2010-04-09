package domain;

public class Cargo {

	private int currentvolume;
	private int currentweight;
	
	
	public Cargo (String currentvolume, String currentweight)	{
	this.currentvolume = Integer.parseInt(currentvolume);
	this.currentweight = Integer.parseInt(currentweight);
	}
	
	public boolean availCargo(String volume, String weight)	{
		if (currentvolume > Integer.parseInt(volume)
			&& currentweight > Integer.parseInt(weight))	{
		
			return true;
		
		}
			return false;
		}
	public void updateShip(String volume, String weight)	{
	    currentvolume = currentvolume-Integer.parseInt(volume);
	    currentweight = currentweight-Integer.parseInt(weight);
	}
	
	}
	
	


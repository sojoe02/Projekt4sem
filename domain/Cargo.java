package domain;


import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cargo {

	private String currentvolume;
	private String currentweight;
	private Map<String , Container> containers = new HashMap<String , Container >( ) ;
	
	public Cargo (String currentvolume, String currentweight)	{
	    this.currentvolume = currentvolume;
	    this.currentweight = currentweight;
	}
	/*public boolean availCargo(String volome, String weight)	{
		if (currentvolume > volume) {
			(currentweight > weight)	{
			return true;	
		}
		}
			return false;
		}
	*/

	public void addContainer(String containerID,Container container)
	{
	    containers.put(containerID, container);
	}


	public Container getContainer(String containerID) //Skal bruges når man vil hent specifike containere
	{
	    return containers.get(containerID);
	    //returnere et Container objekt
	}

	public Container getAllContainer()
	{
	    Collection<Container> c = containers.values();
	    Iterator<Container> itr = c.iterator();
	    while(itr.hasNext())
		return  itr.next();
	}


}
	
	


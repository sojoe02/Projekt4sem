package domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Cargo {

    private Map<String, Container> containers = new HashMap<String, Container>();
    private int currentvolume;
    private int currentweight;
    //Den Bruger som ejer containeren
    private Container currenContainer;

    public Cargo(String currentvolume, String currentweight) {
	this.currentvolume = Integer.parseInt(currentvolume);
	this.currentweight = Integer.parseInt(currentweight);

    }

    public boolean availCargo(String volume, String weight) {
	if (currentvolume > Integer.parseInt(volume)
		&& currentweight > Integer.parseInt(weight)) {

	    return true;

	}
	return false;
    }

    public void addContainer(String containerID, Container container,
	    String userID, String content)
    {
	currenContainer = new Container(containerID, userID, content);
	containers.put(containerID, container);	//laeg container i hashmap
    }

    
    //getContainer() bruges når man vil hent specifike containere
    public Container getContainer(String containerID) {
	return containers.get(containerID);
	//returnere et Container objekt
    }

    public Map getAllContainer() {
	return containers;  //returnere et hashmap
    }

    public void updateShip(String volume, String weight) {
	currentvolume = currentvolume - Integer.parseInt(volume);
	currentweight = currentweight - Integer.parseInt(weight);
    }
}
	
	


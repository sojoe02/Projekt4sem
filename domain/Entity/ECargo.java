package domain.Entity;

import java.util.HashMap;
import java.util.Map;

public class ECargo {

    private EContainer container;

    private Map<String, EContainer> containers = new HashMap<String, EContainer>();
    private int currentContainer;
    private int maxContainer;


    public ECargo(int currentContainer, int maxContainer) {
	this.currentContainer = currentContainer;
	this.maxContainer = maxContainer;

    }





    public void addContainer(String containerID, EContainer container,
	    String userID, String content) {
	container = new EContainer(containerID, userID, content);
	containers.put(containerID, container);	//laeg container i hashmap
    }

    //getContainer() bruges når man vil hent specifike containere
    public EContainer getContainer(String containerID) {
	return containers.get(containerID);
	//returnere et Container objekt
    }




}
	
	


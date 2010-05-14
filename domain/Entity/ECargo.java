package domain.Entity;

import java.util.HashMap;
import java.util.Map;

public class ECargo {

    private EContainer container;

    private Map<String, EContainer> containers = new HashMap<String, EContainer>();
    private int maxContainer;


    public ECargo(int MaxContainer) {
	
	this.maxContainer = MaxContainer;

    }





    public void mapContainer(int ContainerID, String Content, String Status) {
	container = new EContainer(ContainerID, Content, Status);
	containers.put(Integer.toString(ContainerID), container);	//laeg container i hashmap
    }

public EContainer getContainer(int ContainerID)	{
    return containers.get(Integer.toString(ContainerID));
}

}
	
	


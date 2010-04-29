

package domain;


public class Container {

    private String containerID;
    private String ownerID;
    //brugeren som bruger containeren til at fragte noget
    private String Position;
    //position på skibet
    private String content;
    //indhold af container

    public Container(String containerID, String ownerID /*, String position*/,
	    String content){
	this.containerID = containerID;
	this.ownerID = ownerID;
	this.content = content;
	//this.Position = position;
	//instantiere container
    }

    public String toString(){
	String str = "ContainerID: " + containerID + "\n" + ownerID +"\nContent:"
	+ content +"\n--------------------";
	return str;
    }

    public String getOwnerID(){
	return ownerID;
    }

    public String getContainerID(){
	 return containerID;
    }

    public String getContent(){
	return content;
    }
}

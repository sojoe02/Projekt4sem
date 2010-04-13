

package domain;


public class Container {

    private String containerID;
    private User user;
    //brugeren som bruger containeren til at fragte noget
    private String Position;
    //position på skibet
    private String content;
    //indhold af container

    public Container(String containerID, User user, String position, String content){
	this.containerID = containerID;
	this.user = user;
	this.content = content;
	this.Position = position;
	//instantiere container
    }

    public String toString(){
	String str = "ContainerID: " + containerID + "\n " + user.toString()+"\n Content:"
	+ content;

	return str;

    }
}

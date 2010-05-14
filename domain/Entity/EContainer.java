

package domain.Entity;


public class EContainer {

    private int containerID;
   
    //brugeren som bruger containeren til at fragte noget
   
    //position på skibet
    private String content;
    private String status;
    //indhold af container

    public EContainer(int containerID, String Content, String Status){
	this.containerID = containerID;
	this.content = Content;
	this.status = Status;
	//this.Position = position;
	//instantiere container
    }

 
    
    public int getContainerID(){
	 return containerID;
    }

    public String getContent(){
	return content;
    }
}

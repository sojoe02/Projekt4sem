/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;
import java.net.*;
/**
 *
 * @author Mats l
 */
public class RunServer {

    private String clientChoose;
    private final int port = 6789;
    private String message = "I am your Server";

    ServerSocket serversocket;
    Socket socket;
    ClientServerCom csc;

	public RunServer()  {

	}


 public void runServer() throws Exception  {
		
	    serversocket = new ServerSocket(port);

	    
	      System.out.println("\nTCPServer er klar på port 6789");
	      socket = serversocket.accept();

	      csc = new ClientServerCom(socket);
	         //String str = csc.readFromSocket();     // opsionelt
	           //System.out.println("\n" + str);      // optionelt
	         csc.writeToSocket(message);
	         clientChoose = csc.readFromSocket();
	            System.out.println("\n" + clientChoose);

	      socket.close();
	    
	    System.out.println("sdsdsd");

	    }



	    public void runSas() throws Exception    {
		Sas sas = new Sas();
		if (clientChoose == null ? "1" == null : clientChoose.equals("1"))	{
		    sas.setUp();



	String endLoc = "23-04-2010";
	String startLoc = "14-04-2010";
	String endDate ="23-04-2010";
	String volume = "4000";
	String weight = "850";




	String price = sas.placeOrder(endLoc,startLoc,endDate,volume,weight);

	System.out.println(price);

	String Date = "24-04-2010";

	String confirm = sas.chooseDate(Date);

	System.out.println(confirm);
		}

	    }
	    }












	    


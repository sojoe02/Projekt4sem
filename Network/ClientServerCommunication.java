package Network;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientServerCommunication {

    ServerSocket serversocket;
    Socket socket;
    final int port = 6789;
    private String ipAddress = "localhost";
    WriteRead wr;

    SendObject sObject;


    public void startServer() throws Exception {

	serversocket = new ServerSocket(port);

	System.out.println("\nTCPServer waiting for connection on port 6789");
	socket = serversocket.accept();
	System.out.println("\nTCPServer got a connection");
	wr = new WriteRead(socket);
	//Læser fra socket i sendObject
	sObject = wr.readFromSocket();
	socket.close();

 

    }

    public void responseFindDates(ArrayList Shipdates) throws Exception 	{
	sObject.putShipDates(Shipdates);
	try {
	    socket = new Socket(ipAddress, 6431);
	    System.out.println("\nClient is ready");
	    wr = new WriteRead(socket);
	    // Sender objektet sendes
	    wr.writeToSocket(sObject);

	    socket.close();

	} catch (IOException e) {
	    e.printStackTrace();
	}


    }

    public void placeOrder () throws Exception	{
	serversocket = new ServerSocket(6433);

	System.out.println("\nTCPServer waiting for connection on port 6789");
	socket = serversocket.accept();
	System.out.println("\nTCPServer got a connection");
	wr = new WriteRead(socket);
	//Læser fra socket i sendObject
	sObject = wr.readFromSocket();
	socket.close();
	
    }




    public  String getMetodeChoose()	{
	
	return sObject.getMetodeChoose();
    }
    public  String getStartLoc()	{
	return sObject.getStartLoc();
    }
    public  String getEndLoc()	{
	return sObject.getEndLoc();
    }
    public  Date getEndDate()	{
	return sObject.getEndDate();
    }
    public String getShipID ()	{
	return sObject.getShipID();
    }
    public String getDepartureDate ()	{
	return sObject.getDepartureDate();
    }

    public String getArrivalDate()	{
	return sObject.getArrivalDate();
    }


}

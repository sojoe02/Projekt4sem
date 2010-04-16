/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.net.*;
import java.util.ArrayList;
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
    //ClientChoose clientChoose;

    public RunServer() {
    }

    public ArrayList runServer() throws Exception {



		serversocket = new ServerSocket(port);


			System.out.println("\nTCPServer er klar på port 6789");
			socket = serversocket.accept();

			ArrayList<String> clientChoose = new ArrayList<String>();
			csc = new ClientServerCom(socket);
			//csc.writeToSocket(clientChoose);
			clientChoose = csc.readFromSocket();
			//System.out.println("\n" + person1);

			socket.close();
		
	


	return clientChoose;


    }


/*

	




	String price = sas.placeOrder(endLoc, startLoc, endDate, volume, weight);

	System.out.println(price);

	String Date = "24-04-2010";

	String confirm = sas.chooseDate(Date);

	System.out.println(confirm);
    }
 * 
 */
}














	    


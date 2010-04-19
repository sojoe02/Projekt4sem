/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Mats l
 */
public class RunServer extends Thread {

    ArrayList<String> clientChoose = new ArrayList<String>();
    //private String clientChoose;
    private final int port = 6789;
    private String message = "I am your Server";
    ServerSocket serversocket;
    //Socket socket;
    ClientServerCom csc;
    //ClientChoose clientChoose;

    public RunServer() throws Exception {
     serversocket = new ServerSocket(port);
     System.out.println("Server listening on port " + port + ".");
     this.start();
    }

    public void run() {
	try {
	    System.out.println("Waiting for connections.");
	    Socket socket = serversocket.accept();
	    System.out.println("Accepted a connection from: "+
	    socket.getInetAddress());
	    Connect c = new Connect(socket);


    }
	catch(Exception e) {

	}}


    class Connect extends Thread {
   private Socket socket = null;
   private ObjectInputStream ois = null;
   private ObjectOutputStream oos = null;



   public Connect(Socket socket) {
     this.socket = socket;

     try {
      ois = new ObjectInputStream(socket.getInputStream());
      oos = new ObjectOutputStream(socket.getOutputStream());
     } catch(Exception e1) {
         try {
            socket.close();
         }catch(Exception e) {
           System.out.println(e.getMessage());
         }
         return;
     }
     this.start();
   }


   public void run() {
      try {
       // read an object from the server
        clientChoose = (ArrayList) ois.readObject();
        for (int i = 0; i < clientChoose.size(); i++) {
    System.out.println((String)(clientChoose.get(i)));
	}

        oos.close();
        ois.close();
      }
	catch(Exception e) {
        System.out.println(e.getMessage());
      }

   }
    }

public ArrayList getClientChoose()  {
    return clientChoose;
}


    /*

    public ArrayList runServer() throws Exception {



		


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














	    


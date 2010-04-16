/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;
import java.io.*;	    // pakker til socket
import java.net.*;
import java.util.ArrayList;
/**
 *
 * @author Mats l
 */
public class ComToServer {
    private String ipAddress = "localhost";
    private final int port = 6789;
    ArrayList<String> clientChoose = new ArrayList<String>();

    private String metodeChoose, startLoc, endLoc, endDate, volume, weight;


    Socket socket;
    ClientServerCom csc;
//private ClientChoose clientChoose;


    public ComToServer()  {

    }

    public void connectToServer(ArrayList clientChoose) throws Exception {
	

  

    try {
      socket = new Socket(ipAddress, port);
          System.out.println("\nClient er klar");
      csc = new ClientServerCom(socket);

      //clientChoose = csc.readFromSocket();
      	//System.out.println(person.getBirthYear());
      csc.writeToSocket(clientChoose);

      socket.close();
    }
    catch(IOException e) { e.printStackTrace(); }
    }
}



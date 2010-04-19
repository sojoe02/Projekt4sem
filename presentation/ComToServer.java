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
    //ArrayList<String> clientChoose = new ArrayList<String>();

   // private String metodeChoose, startLoc, endLoc, endDate, volume, weight;
    ObjectOutputStream oos = null;
      ObjectInputStream ois = null;
      Socket socket = null;
     // Date date = null;


   
    ClientServerCom csc;
//private ClientChoose clientChoose;


    public ComToServer()  {

    }

    public void connectToServer(ArrayList clientChoose) throws Exception {
	

  

    try {
      socket = new Socket(ipAddress, port);
          System.out.println("\nClient are ready to connect to server");
      // csc = new ClientServerCom(socket);

      //clientChoose = csc.readFromSocket();
      	//System.out.println(person.getBirthYear());
         // open I/O streams for objects
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());
        // read an object from the server

        //System.out.print("The date is: " + date);
         oos.writeObject(clientChoose);
         oos.flush();
         // close streams and connections
         ois.close();
         oos.close();
         socket.close();

}
catch(Exception e) {
        System.out.println(e.getMessage());
      }
    }
}



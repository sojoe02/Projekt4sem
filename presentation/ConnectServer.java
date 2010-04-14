/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package presentation;

/**
 *
 * @author Mats l
 */

import java.io.*;	    // pakker til socket
import java.net.*;
public class ConnectServer {

    private String ipAddress = "localhost";
    private final int port = 6789;
    private String message = "1";

    Socket socket;
    ClientServerCom csc;
    BufferedReader inFromUser;


    public ConnectServer()  {

    }

    public void conServer()  throws Exception {
 
  

    try {
      socket = new Socket(ipAddress, port);
          System.out.println("\nClient er klar");
      inFromUser = new BufferedReader(new InputStreamReader(System.in));
      //String sentence = inFromUser.readLine();
      csc = new ClientServerCom(socket);
        //csc.writeToSocket(sentence);
      String str = csc.readFromSocket();
      System.out.println("\n" + str);
      csc.writeToSocket(message);

      socket.close();
    }
    catch(IOException e) { e.printStackTrace(); }
    }
    }


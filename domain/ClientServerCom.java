/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.*;
import java.net.*;
import java.util.Scanner;


/**
 *
 * @author Mats l
 */
public class ClientServerCom {


    Scanner iStream;
  PrintWriter oStream;
  Socket sock;
  String str;

  public ClientServerCom(Socket socket) throws Exception {
    sock = socket;
    iStream = new Scanner(sock.getInputStream());
    oStream = new PrintWriter(sock.getOutputStream());
  }

  public String readFromSocket() throws Exception {

    str = iStream.nextLine();
    return str;
  }

  public void writeToSocket(String s) throws Exception {

    oStream.println(s);
    oStream.flush();
  }

}

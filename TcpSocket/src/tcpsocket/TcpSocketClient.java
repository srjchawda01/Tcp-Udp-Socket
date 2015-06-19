/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpsocket;

import java.net.*;
import java.io.*;

/**
 *
 * @author Suraj Chawda
 */
public class TcpSocketClient 
{
    private static InetAddress host;
private static final int PORT = 1234;

public static void main(String[] args) {
    try {
        host = InetAddress.getLocalHost();
    } catch(UnknownHostException e) {
	System.out.println("Host ID not found!");
	System.exit(1);
    }
    run();
}

private static void run() {
    Socket link = null;				
    try {
	link = new Socket(host,PORT);	
	BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                            link.getInputStream()));

	PrintWriter out = new PrintWriter(link.getOutputStream(),true);	 

	//Set up stream for keyboard entry...
	BufferedReader userEntry =new BufferedReader(
                                        new InputStreamReader(System.in));
	String message = null;
        String response= null;
	do {
            System.out.print("Enter message: ");
            message =  userEntry.readLine();
            out.println(message); 		
            response = in.readLine();	
            System.out.println("\nSERVER> " + response);
	}while (!message.equals("***CLOSE***"));
    } catch(IOException e){
	e.printStackTrace();
    } finally {
        try {
            System.out.println("\n* Closing connection... *");
            link.close();				
	}catch(IOException e){
            System.out.println("Unable to disconnect!");
            System.exit(1);
	}
    }
}
}

    


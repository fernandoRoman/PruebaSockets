package dsa.prueba2.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import dsa.sockets.library.MySQL;


public class Server {
	
	public static void runServer(ServerSocket MyService, MySQL mySQL)
	{
		Socket serviceSocket = null;
		ObjectInputStream sIn;
		ObjectOutputStream sOut;	
			
		try {
			int n = 0;
			while(true)
		    {
		    	
				serviceSocket = MyService.accept();
				  	
		    	System.out.println("Usuario conectedo."+ serviceSocket.getLocalAddress().toString());
		    	
		    	sIn = new ObjectInputStream( serviceSocket.getInputStream() );
		    	sOut = new ObjectOutputStream( serviceSocket.getOutputStream() );   
		    	
		    	AttendClientThread attendClient = 
		    			new AttendClientThread(n++, serviceSocket, sIn, sOut, mySQL);
		    	attendClient.start();
		    }	
		}
        catch (IOException e) {
        	System.out.println(e);
        	return;
        }   
		
	}
	public static void main(String[] args) 
	{
		ServerSocket MyService = null;		
		MySQL mySQL = new MySQL();
		
	    try {
	    	System.out.println("Creando socket del servidor.");
	    	MyService = new ServerSocket(4009);
	    	
	    	System.out.println("Esperando conexion.");
		    
			
        }
        catch (IOException e) {
        	System.out.println(e);
        	return;
        }
	    
	    runServer(MyService, mySQL);
	 	
	}
	
}

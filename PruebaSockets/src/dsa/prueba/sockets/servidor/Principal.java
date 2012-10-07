package dsa.prueba.sockets.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Principal {
	
	

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException
	{
		ServerSocket MyService = null;
		Socket serviceSocket = null;
		DataInputStream sIn;
		DataOutputStream sOut;	
		
	    try {
	    	System.out.println("Creando socket del servidor.");
	    	MyService = new ServerSocket(4009);
	    	
	    	int n = 0;

	    	System.out.println("Esperando conexion.");
		    while(true)
		    {
		    	serviceSocket = MyService.accept();	    	
		    	System.out.println("Usuario conectedo."+ serviceSocket.getLocalAddress().toString());
		    	
		    	sIn = new DataInputStream( serviceSocket.getInputStream() );
		    	sOut = new DataOutputStream( serviceSocket.getOutputStream() );
		    	    
		    	int c = sIn.readInt();
		    	sOut.writeInt(c*2);
		    	sOut.writeInt(++n);	
		    }		
			
        }
        catch (IOException e) {
        	System.out.println(e);
        	return;
        }
	    
	 	
	}
	
}

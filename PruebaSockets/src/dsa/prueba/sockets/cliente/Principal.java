package dsa.prueba.sockets.cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Principal {
	public static void main(String[] args) throws IOException
	{
		Socket MyClient = null;
		DataInputStream sIn = null;
		DataOutputStream sOut = null;
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader (isr);
		
		try
		{
			System.out.println("Conectando con el servidor.");
			MyClient = new Socket("192.168.1.161", 4009);			
			
			sOut = new DataOutputStream( MyClient.getOutputStream() );
	        sIn = new DataInputStream( MyClient.getInputStream() );
	        
	        System.out.println("Escrive un numero:");
	       	int num = Integer.parseInt(bf.readLine());
	        sOut.writeInt(num);
	        
	        int num1 = sIn.readInt();
	        int num2 = sIn.readInt();
	        
	        System.out.println("Consulta numero " + num2 + ":");
	        System.out.println("El numero " + num + " multiplicado por dos es: " + num1);
	       
		}
	    catch (IOException e) 
	    {
	        System.out.println(e.getMessage());	        
	        System.exit(-1);
		}       
        
        MyClient.close();
        sIn.close();
        sOut.close();
        isr.close();
        bf.close();
				
	}
	
}
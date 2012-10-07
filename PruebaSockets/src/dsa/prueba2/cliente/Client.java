package dsa.prueba2.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import dsa.sockets.library.CMessage;
import dsa.sockets.library.CPlayer;
import dsa.sockets.library.MessageType;

public class Client {
	
	private static boolean clientOperations(int num, ObjectOutputStream sOut, ObjectInputStream sIn, BufferedReader bf) throws IOException, ClassNotFoundException
	{
		String name, pass;
        int age = 0;        
    	switch(MessageType.withCode(num))
    	{        	
    	case REGISTER: //Darse de alta
    		
    		/* Introduccion de datos */
    		System.out.print("Introduce el nombre: ");
        	name = bf.readLine();	        	
        	System.out.print("Introduce la contraseña: ");
        	pass = bf.readLine();
        	System.out.print("Introduce la edad:");	        	
    		age = Integer.parseInt(bf.readLine());
        	
        	/* Enviando datos */ 
        	sOut.writeObject(new CMessage(num, new CPlayer(name, pass,age)));
        	
        	/* Recepcion de datos del servidor */
    		if((boolean)sIn.readObject())
        		System.out.println("Te has registrado correctamente");
        	else 
        		System.out.println("No te has podido dar de alta");
        	return false;
        	
    	case VALIDATION: //Verificar si existe  
    		/* Introduccion de datos  */
    		System.out.print("Introduce el nombre: ");
        	name = bf.readLine();	        	
        	System.out.print("Introduce la contraseña: ");
        	pass = bf.readLine();
        	
    		/* Enviando datos */ 
        	sOut.writeObject(new CMessage(num, new CPlayer(name, pass,age)));
    		
    		/* Recepcion de datos del servidor */
        	if((boolean)sIn.readObject())
        		System.out.println("Validacion satisfactoria");
        	else 
        		System.out.println("Error en la validacion");
        	return false;
        	
    	case DISCONNECT: //desconexion
    		/* Enviando datos */ 
        	sOut.writeObject(new CMessage(num, null));
    		return true;
    		
    	default: return true;
    	}   
	}
	
	private static void clientConection(ObjectInputStream sIn, ObjectOutputStream sOut) throws IOException  
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader bf = new BufferedReader (isr);
		boolean stop = false;
		while(!stop)
        {
        	System.out.println("\n\nQue operacion deseas realizar:\n" +
        					   "  0- Dar de alta.\n" +
        				       "  1- Verificar si existe.\n" +
        				       "  2- Desconectar.");
        
        	int num = Integer.parseInt(bf.readLine());    
        	try {
				stop = clientOperations(num, sOut, sIn, bf);
			} catch (ClassNotFoundException e) {
				System.out.println(e.getMessage());	        
		        return;
			}
        }	
		isr.close();
		bf.close();
	}
	
	public static void main(String[] args) throws IOException
	{
		Socket MyClient = null;
		ObjectInputStream sIn = null;
		ObjectOutputStream sOut = null;
				
		try
		{
			System.out.println("Conectando con el servidor.");
			MyClient = new Socket(InetAddress.getLocalHost(), 4009);			
			
			sOut = new ObjectOutputStream( MyClient.getOutputStream() );
	        sIn = new ObjectInputStream( MyClient.getInputStream() );   
		}
	    catch (IOException e) 
	    {
	        System.out.println(e.getMessage());	        
	        System.exit(-1);
		}   
		
		clientConection(sIn, sOut);
        
        MyClient.close();
        sIn.close();
        sOut.close();				
	}
	
}
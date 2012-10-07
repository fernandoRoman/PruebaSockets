package dsa.prueba2.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import dsa.sockets.library.CMessage;
import dsa.sockets.library.MessageType;
import dsa.sockets.library.MySQL;

public class AttendClientThread extends Thread {

	int id;
	Socket s;
	ObjectInputStream sIn;
	ObjectOutputStream sOut;
	MySQL mySQL;
			
	/**
	 * @param s
	 * @param sIn
	 * @param sOut
	 * @param mySQL
	 */
	public AttendClientThread(int id, Socket s, ObjectInputStream sIn,
			ObjectOutputStream sOut, MySQL mySQL) {
		super();
		this.s = s;
		this.sIn = sIn;
		this.sOut = sOut;
		this.mySQL = mySQL;
	}

	public void run()
	{
		while(true)
    	{    		
	    	CMessage message;
			try {
				message = (CMessage) sIn.readObject();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println(e.getMessage());
				return;
			}
			if(message.getCode() == MessageType.DISCONNECT) break;
			
	    	switch(message.getCode())
	    	{
	    	case REGISTER:
				try {
					sOut.writeObject(mySQL.execute("insert into jugador values ('" +
								           message.getPlayer().getName() +     "','" +  
								           message.getPlayer().getPassword() + "'," + 
								           message.getPlayer().getAge()  +        ");") 
								           == 0);
				} catch (IOException e) {
					System.out.println(e.getMessage());
					return;
				}				
	    		break;
	    		
	    	case VALIDATION:
	    		mySQL.executeQuery("select * from jugador where name='" + message.getPlayer().getName() + "' and " +
	    				"password='" + message.getPlayer().getPassword() + "';");
		    	
	    		try {
	    			sOut.writeObject(mySQL.getResultSet().last());
	    		} catch (IOException | SQLException e) {
					System.out.println(e.getMessage());
					return;
				}    		
	    		break;
	    	
			default:
				break;    		    	
	    	}
    	}
    	
	}
}

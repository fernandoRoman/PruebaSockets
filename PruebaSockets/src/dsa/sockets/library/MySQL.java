package dsa.sockets.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {

	Connection connect = null;
	Statement statement = null;
	ResultSet resultSet = null;

	public MySQL() {
		super();		
		try {			
			// This will load the MySQL driver			
			Class.forName("com.mysql.jdbc.Driver");			
	
			// Setup the connection with the DB
			connect = DriverManager.getConnection("jdbc:mysql://localhost/", "root", "mimara");	
			
			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();			
			statement.execute("use mus");
		
		} catch (Exception e) 
		{
			System.out.println ("Error base de datos");
		} 
	}
	


	public Statement getStatement() {
		return statement;
	}
	public ResultSet getResultSet() {
		return resultSet;
	}

	public int executeQuery(String command) {
		try {
			resultSet = statement.executeQuery(command);
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public int execute(String command) {
		try {
			statement.execute(command);
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return -1;
		}
	}
	public int getResultLength() {
		try {
			resultSet.last();
			resultSet.getRow();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	
}

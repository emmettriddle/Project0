package dev.riddle.utilities;

import java.io.*;
import java.sql.*;
import java.util.*;

public class JDBCConnection {
		
	private static Connection conn= null;
	// define a method to get the connection
	public static Connection getConnection() {
		
		try {
			// check for connection
			if (conn==null) {
				// hot fix to ensure the driver loads correctly when out app starts
				Class.forName("org.postgresql.Driver");
				
				// in order to establish a connection to our DB
				// we need our credentials
				// url (endpoint), username, password
				
				Properties props= new Properties();
				
				InputStream input = JDBCConnection.class.getClassLoader().getResourceAsStream("connection.properties");
				props.load(input);
				String url=props.getProperty("url");
				String username=props.getProperty("username");
				String password =props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				return conn;
			
			} else {return conn;}
		} catch (SQLException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	 public void releaseConnection(Connection conn) throws SQLException {
		  //conn.close();
	  }
}
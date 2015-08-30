package com;

import java.sql.*;

public class ConnectionUtility {
	public static Connection getConnection() {
		Connection conn = null;
		try{
		Class.forName("com.mysql.jdbc.Driver");

conn = DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_05e7fe6bd1f8e1c?user=b70e4174d40ce1&password=12ddc464");}
		catch(Exception e){
			e.printStackTrace();
		}
return conn;
	}
	public static Connection getConnection1() throws Exception{
		Class jdbcDriver = Class.forName("com.mysql.jdbc.Driver");
		Driver driver = (Driver) jdbcDriver.newInstance();
		DriverManager.registerDriver(driver);
		Connection con= DriverManager.getConnection("jdbc:mysql://us-cdbr-iron-east-02.cleardb.net/ad_05e7fe6bd1f8e1c?user=b70e4174d40ce1&password=12ddc464");
		return con;
	}
	
	
	/*public static void main(String args[]){
		Connection conn= ConnectionUtility.getConnection();
		System.out.println("hello");
	}*/
}

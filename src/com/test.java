package com;

import java.sql.*;
import java.util.Calendar;

public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		String name="Rajesh";
		String category="Electronics";
		long hits=1;
		float price=100;
		long ownerId=12345;
		long soldTo=12563;
		String Description="HELLO";
		String reason="RENT";
		java.sql.Date buyDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		java.sql.Date sellDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		String imageLink;
	Class.forName("oracle.jdbc.driver.OracleDriver");

        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","root");

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST");
        while(rs!=null && rs.next()){
        	System.out.println("1"+rs.getString("NAME"));
        }
	}

}

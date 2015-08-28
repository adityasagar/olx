package com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

public class RegisterUtility {
	public static UserVO getUserByEmail (String email){
		UserVO u= new UserVO();
		try{
			Connection con=ConnectionUtility.getConnection();
		String query="select * from users where email=?";
		PreparedStatement psmt= con.prepareStatement(query);
		psmt.setString(1,u.getEmail());
	ResultSet rs= psmt.executeQuery();
	if(rs!=null && rs.next()){
		u.setName(rs.getString("username"));
		u.setEmail(rs.getString("email"));
		u.setContact(rs.getString("phone"));
		u.setUserId(rs.getLong("userid"));
	}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
	
	public static boolean signUp (UserVO u){
	int rows=0;
	try{	
		Connection con = ConnectionUtility.getConnection();
		String query= "insert into users(username, email, phone,pwd,joindate) values(?,?,?,?,?)";
	
	
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println("fads--"+query);
			PreparedStatement psmt= con.prepareStatement(query);
		psmt.setString(1, u.getName());
		
		psmt.setString(2, u.getEmail());
		psmt.setString(3, u.getContact());
		psmt.setString(4, u.getPwd());
		psmt.setDate(5, sqlDate);
		//psmt.setLong(6, u.getUserId());
		rows=psmt.executeUpdate();
	
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
	if(rows>0)
		return true;
	else 
		return false;	
	}
	public static UserVO verify(String  email, String pwd){
		//boolean check= false;
		String userid=null;
		UserVO u=new UserVO();
		HashMap<String, String> results= new HashMap<String, String>();
		try{
			Connection con = ConnectionUtility.getConnection();
			String query= "select pwd,userId,username from users where email= ?";
			
			PreparedStatement psmt= con.prepareStatement(query);
			psmt.setString(1,email);
			ResultSet rs= psmt.executeQuery();
			if(rs.next()){
			if(pwd.equals(rs.getString(1)))
					{
				u.setUserId(rs.getLong(2));
				u.setName(email);
				//results.put(rs.getString(3));
				//System.out.println(rs.getString(3));
				//results.put("name",rs.getString(3));
			}
			}
		}
catch(Exception e){
	e.printStackTrace();
}
		
		
		return  u;
	}
	public static UserVO getUser(long userId){
		UserVO u = new UserVO();
		try{
			Connection con = ConnectionUtility.getConnection();
			String query= "select * from users where userid=?";
			
			PreparedStatement psmt= con.prepareStatement(query);
			psmt.setLong(1,userId);
			ResultSet rs= psmt.executeQuery();
			if(rs!=null && rs.next()){
				u.setName(rs.getString("username"));
				u.setEmail(rs.getString("email"));
				u.setContact(rs.getString("phone"));
				u.setUserId(rs.getLong("userid"));
				//u.set
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return u;
	}
}

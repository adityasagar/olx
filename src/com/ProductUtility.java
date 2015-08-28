package com;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ProductUtility {
	public static ProductVO getProduct( long value){
		//ArrayList<ProductVO> results= new ArrayList<ProductVO>();
		ProductVO p = new ProductVO();
		try{
			Connection con=ConnectionUtility.getConnection();
			String query="Select * from products where productid=? ";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setLong(1,value);
			ResultSet rs= ps.executeQuery();
			while(rs!=null && rs.next()){
				p.setProductId(rs.getLong("productid"));
				p.setName(rs.getString("productname"));
				p.setCategory(rs.getString("category"));
				p.setHits(rs.getLong("hits"));
				p.setPrice(rs.getFloat("price"));
				p.setOwnerId(rs.getLong("ownerid"));
				p.setSoldTo(rs.getLong("soldto"));
				p.setDescription(rs.getString("description"));
				p.setReason(rs.getString("reason"));
				p.setBuyDate(rs.getDate("buydate"));
				p.setSellDate(rs.getDate("selldate"));
				p.setImageLink(rs.getString("imagelink"));
			}
			
			
		}
catch(Exception e){
	e.printStackTrace();
}
		return p;
	}
	public static void sendEmail(ProductVO product,UserVO buyer,UserVO seller,String msg){
		String to=seller.getEmail();
		String from=buyer.getEmail();
		String host="smtp.verizon.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message= new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
		
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject("VZ Classified");
		message.setText(msg);
		Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static boolean productEntry(ProductVO p){
		int rows=0;
		boolean check=false;
		try{
			java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
			Connection con = ConnectionUtility.getConnection();
			String query= "insert into products(productname, category,hits, price,ownerid,description, reason, buydate ) values(?,?,?,?,?,?,?,?)";
			PreparedStatement psmt= con.prepareStatement(query);
			psmt.setString(1,p.getName() );
			psmt.setString(2, p.getCategory());
			psmt.setLong(3, 0);
			psmt.setFloat(4, p.getPrice());
			psmt.setLong(5,p.getOwnerId() );
			//psmt.setLong(6,p.getSoldTo() );
			psmt.setString(6,p.getDescription() );
			psmt.setString(7,p.getReason() );
			psmt.setDate(8,sqlDate );
			//psmt.setDate(10,p.getSellDate() );
			//psmt.setString(11,p.getImageLink() );
			rows =psmt.executeUpdate();
			if(rows>0)
				check=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
			return check;
		}
	
		
	public static ArrayList<ProductVO> getCategoryResults( String value){
		ArrayList<ProductVO> results= new ArrayList<ProductVO>();
		ProductVO p = new ProductVO();
		try{
			Connection con=ConnectionUtility.getConnection();
			String query="Select * from products where category= ? and rownum<30";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1,value);
			ResultSet rs= ps.executeQuery();
			while(rs!=null && rs.next()){
				p.setProductId(rs.getLong("productid"));
				p.setName(rs.getString("productname"));
				p.setCategory(rs.getString("category"));
				p.setHits(rs.getLong("hits"));
				p.setPrice(rs.getFloat("price"));
				p.setOwnerId(rs.getLong("ownerid"));
				p.setSoldTo(rs.getLong("soldto"));
				p.setDescription(rs.getString("description"));
				p.setReason(rs.getString("reason"));
				p.setBuyDate(rs.getDate("buydate"));
				p.setSellDate(rs.getDate("selldate"));
				p.setImageLink(rs.getString("imagelink"));
				results.add(p);
			}
			
			
		}
catch(Exception e){
	e.printStackTrace();
}
		return results;
	}
	
	public static ArrayList<ProductVO> getSearchResults( String value){
		ArrayList<ProductVO> results= new ArrayList<ProductVO>();

		value="%"+value+"%";
		try{
			Connection con=ConnectionUtility.getConnection();
			String query="Select * from products where productname like ? and rownum<30";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1,value);
			ResultSet rs= ps.executeQuery();
			while(rs!=null && rs.next()){
				ProductVO p = new ProductVO();
				p.setProductId(rs.getLong("productid"));
				p.setName(rs.getString("productname"));
				p.setCategory(rs.getString("category"));
				p.setHits(rs.getLong("hits"));
				p.setPrice(rs.getFloat("price"));
				p.setOwnerId(rs.getLong("ownerid"));
				p.setSoldTo(rs.getLong("soldto"));
				p.setDescription(rs.getString("description"));
				p.setReason(rs.getString("reason"));
				p.setBuyDate(rs.getDate("buydate"));
				p.setSellDate(rs.getDate("selldate"));
				p.setImageLink(rs.getString("imagelink"));
				results.add(p);
			}
			
			
		}
catch(Exception e){
	e.printStackTrace();
}
		return results;
	}
	public static ArrayList<ProductVO> getMostViewedResults( String value){
		ArrayList<ProductVO> results= new ArrayList<ProductVO>();

		//value="%"+value+"%";
		try{
			Connection con=ConnectionUtility.getConnection();
			String query="Select * from products where  rownum<= ? order by hits desc";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setLong(1,Integer.parseInt(value));
			ResultSet rs= ps.executeQuery();
			while(rs!=null && rs.next()){
				ProductVO p = new ProductVO();
				p.setName(rs.getString("productname"));
				p.setCategory(rs.getString("category"));
				p.setHits(rs.getLong("hits"));
				p.setPrice(rs.getFloat("price"));
				p.setOwnerId(rs.getLong("ownerid"));
				p.setSoldTo(rs.getLong("soldto"));
				p.setDescription(rs.getString("description"));
				p.setReason(rs.getString("reason"));
				p.setBuyDate(rs.getDate("buydate"));
				p.setSellDate(rs.getDate("selldate"));
				p.setImageLink(rs.getString("imagelink"));
				results.add(p);
			}
			
			
		}
catch(Exception e){
	e.printStackTrace();
}
		return results;
	}
	public static ArrayList<ProductVO> getRecentResults( String value){
		ArrayList<ProductVO> results= new ArrayList<ProductVO>();

		//value="%"+value+"%";
		try{
			Connection con=ConnectionUtility.getConnection();
			String query="Select * from products where  rownum<= ? order by buyDate desc";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setLong(1,Integer.parseInt(value));
			ResultSet rs= ps.executeQuery();
			while(rs!=null && rs.next()){
				ProductVO p = new ProductVO();
				p.setProductId(rs.getLong("productid"));
				p.setName(rs.getString("productname"));
				p.setCategory(rs.getString("category"));
				p.setHits(rs.getLong("hits"));
				p.setPrice(rs.getFloat("price"));
				p.setOwnerId(rs.getLong("ownerid"));
				p.setSoldTo(rs.getLong("soldto"));
				p.setDescription(rs.getString("description"));
				p.setReason(rs.getString("reason"));
				p.setBuyDate(rs.getDate("buydate"));
				p.setSellDate(rs.getDate("selldate"));
				p.setImageLink(rs.getString("imagelink"));
				results.add(p);
			}
			
			
		}
catch(Exception e){
	e.printStackTrace();
}
		return results;
	}
}

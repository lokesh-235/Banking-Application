package models;

import java.sql.*;

import BankApplication.connection;

public class Admin {
	private int eid;
	private String name;
	private String userName;
	private String email;
	private String password;
	private String phoneNumber;
	
	public Admin(int eid,String name,String userName,String email,String password,String phoneNumber){
		this.eid = eid;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		
	}
	
	public int getEID() {
		return eid;
	}
	
	public String getName() {
		return name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getPhoneNummber() {
		return phoneNumber;
	}
	
	public static Admin getAdminByEmail(String email) {
		Connection con = connection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql_stmt = "SELECT * from employee WHERE email=?";
		
		try {
			pstmt = con.prepareStatement(sql_stmt);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return createAdmin(rs);
			}
			else {
				return null;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Admin createAdmin(ResultSet rs) {
		// TODO Auto-generated method stub
		
		try {
			
			int eid = rs.getInt("eid");
			String nameFromDB = rs.getString("name");
			String userNameFromDB = rs.getString("username");
			String emailFromDB = rs.getString("email");
			String passwordFromDB = rs.getString("password");
			String phoneNumberFromDB = rs.getString("phoneno");
			
			Admin admin = new Admin(eid,nameFromDB,userNameFromDB,emailFromDB,passwordFromDB,phoneNumberFromDB);
			
			return admin;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString() {
		return "Name : "+name+"\nEmail : "+email+"\nUser Name : "+userName+"\nPhone Number : "+phoneNumber;
	}
	
	
}

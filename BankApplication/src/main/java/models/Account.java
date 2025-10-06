package models;

import java.sql.*;

public class Account extends User{
	private int accountNumber;
	private int balance;
	public Account(int userID,String name,String email,String password,String created_at,int accountNumber,int balance) {
		super(userID,name,email,password,created_at);
		
		this.accountNumber = accountNumber;
		this.balance = balance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public static Account getAccountByAccountNumber(int accountNumber) {
		
		String sqlgetAccountDetails = "SELECT * FROM users u INNER JOIN account a WHERE a.user_id=u.id AND a.account_no=?;";
		
		try(Connection con = connection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sqlgetAccountDetails)){
			
			pstmt.setInt(1, accountNumber);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int userID = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String createdAt = rs.getString("created_at");
				int balance = rs.getInt("balance");
				
				return new Account(userID,name,email,password,createdAt,accountNumber,balance);
			}
			
			else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static Account createAccount(String name,String email,String password,int initialBalance) {
		
		Connection con = connection.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Account account = null;
		
		String sqlUserInsert = "INSERT INTO users (name,email,password) VALUES (?,?,?)";
		String sqlAccountInsert = "INSERT INTO account (user_id,balance) VALUES (?,?)";
		String sqlSlectUser = "SELECT * FROM users WHERE email = ?";
		String sqlSelectAccount = "SELECT * FROM account WHERE user_id=?";
		
		try {
			
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(sqlUserInsert);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, password);
			
			pstmt.executeUpdate();
			
			
			pstmt = con.prepareStatement(sqlSlectUser);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			int userID=0;
			String created_at=null;
			if(rs.next()) {
				userID = rs.getInt("id");
				created_at = rs.getString("created_at");
				
				pstmt = con.prepareStatement(sqlAccountInsert);
				pstmt.setInt(1, userID);
				pstmt.setInt(2, initialBalance);
				
				pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(sqlSelectAccount);
				pstmt.setInt(1, userID);
				
				rs = pstmt.executeQuery();
				
				int accountNumberFromDB ;
				int balanceFromDB ;
				
				if(rs.next()) {
				accountNumberFromDB = rs.getInt("account_no");
				balanceFromDB = rs.getInt("balance");
				}
				
				else {
					return null;
				}
				con.commit();
				
				account = new Account(userID,name,email,password,created_at,accountNumberFromDB,balanceFromDB);
				
			}
			else {
				return null;
			}
			
			
		} catch (SQLException e) {
		    if (con != null) {
		        try { con.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
		    }
		    e.printStackTrace();
		    return null;
		}
		
		finally {
		    try {
		        if (rs != null) rs.close();
		        if (pstmt != null) pstmt.close();
		        if (con != null) con.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		return account;
		
	}
	
	public String toString() {
		return  "\nUser ID : "+this.getUserID()+
				"\nName : "+this.getName()+
				"\nEmail : "+this.getEmail()+
				"\nAccount Number : "+this.getAccountNumber()+
				"\nBalance : "+this.getBalance();
	}

	public static boolean isAccountExists(int accountNumber) {
		// TODO Auto-generated method stub
		String sqlGetAccount = "SELECT account_no from account WHERE account_no=?";
		try(Connection con = connection.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sqlGetAccount)){
			
			pstmt.setInt(1, accountNumber);
			
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next())
				return true;
			
			return false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}

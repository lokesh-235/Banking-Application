package models;

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
	
	
	public String toString() {
		return "Name : "+name+"\nEmail : "+email+"\nUser Name : "+userName+"\nPhone Number : "+phoneNumber;
	}
}

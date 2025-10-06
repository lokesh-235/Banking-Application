package models;

public class User {
	private int userID;
	private String name;
	private String email;
	private String password;
	private String created_at;
	public User(int userID,String name,String email,String password, String created_at) {
		this.userID = userID;
		this.name = name;
		this.email = email;
		this.password = password;
		this.created_at = created_at;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getCreatedAtDate() {
		return created_at;
	}
}

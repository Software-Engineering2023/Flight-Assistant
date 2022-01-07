package db2023.backEnd;


import java.util.Date;

public class User {
	public static final String DB_TABLE_NAME= "User";
	public static final String DB_ID="Id";
	public static final String DB_USER_NAME="User_Name";
	public static final String DB_LAST_NAME="LName";
	public static final String DB_FIRST_NAME="FName";
	public static final String DB_EMAIL="Email";
	public static final String DB_PASSWORD="Password";
	public static final String DB_SHIPPING_ADDRESS="Shipping_Address";
	public static final String DB_PHONE_NUMBER="Phone_Number";
	public static final String DB_isAdmin="isAdmin";


	private int ID;
	private String userName;
	private String LName;
	private String FName;
	private String Email;
	private String password;
	protected String phoneNumber;
	protected String shippingAddress;
	protected boolean admin;


	


	public User(String userName, String lName, String fName, String email, String password, String phoneNumber,
			String shippingAddress, boolean admin) {
		super();
		this.userName = userName;
		LName = lName;
		FName = fName;
		Email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.shippingAddress = shippingAddress;
		this.admin = admin;
	}





	public int getID() {
		return ID;
	}





	public void setID(int iD) {
		ID = iD;
	}





	public String getUserName() {
		return userName;
	}





	public void setUserName(String userName) {
		this.userName = userName;
	}





	public String getLName() {
		return LName;
	}





	public void setLName(String lName) {
		LName = lName;
	}





	public String getFName() {
		return FName;
	}





	public void setFName(String fName) {
		FName = fName;
	}





	public String getEmail() {
		return Email;
	}





	public void setEmail(String email) {
		Email = email;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}





	public String getPhoneNumber() {
		return phoneNumber;
	}





	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}





	public String getShippingAddress() {
		return shippingAddress;
	}





	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}





	public boolean isAdmin() {
		return admin;
	}





	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	









	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class User {
	private int ID;
	private String Email;
	private String password;
	private Date birthDate;  
	private String gender;
	protected double balance;
	protected boolean admin;

	public User(){}//Just to make program compile
	
	
	
	public User(String email, String password, Date birthDate,String gender) {
		this.Email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.gender=gender;
		
	}
	
	
	
	


	public String getGender() {
		return gender;
	}






	public void setGender(String gender) {
		this.gender = gender;
	}






	public boolean isAdmin() {
		return admin;
	}






	public void setAdmin(boolean admin) {
		this.admin = admin;
	}






	public int getID() {
		return ID;
	}






	public void setID(int iD) {
		ID = iD;
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






	public Date getBirthDate() {
		return birthDate;
	}






	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}






	public double getBalance() {
		return balance;
	}






	public void setBalance(double balance) {
		this.balance = balance;
	}






	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

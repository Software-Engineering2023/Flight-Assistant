package com.swe2023.model.signUpAndLogin;

import java.sql.SQLException;
import java.util.Date;

import com.swe2023.Admin.AdminAuthorization;
import com.swe2023.Admin.AdminSession;
import com.swe2023.HelloApplication;
import com.swe2023.User.UserAuthorization;
import Proxy.Auth;
public class LoginAndSignUp {

	//////////////////////////sign up new passenger///////////////////

	/**
	 * This function will add a user to database if email is not present
	 * The function will end successfully if no errors were found.
	 * @throws Exception in case email was already there.
	 */
	public boolean signUp(String Email,String password,Date BitrhDate,String passportNumber,String gender) throws Exception {
		try {
			checkValidEmail(Email);//check if the use already exist
		}
	catch (Exception e){
			e.printStackTrace();
		return false;
	}
		Passenger pass=new Passenger(Email,password, BitrhDate,passportNumber,gender);
		addUserToDatabase(pass); //class passenger to add it
		return true;
	}

	private void addUserToDatabase(Passenger pass) throws Exception {
		Auth.addNewUser(pass);
	}

	private Object getTheGeneratedID() {
		//TODO
		return null;
	}

	private void checkValidEmail(String email) throws Exception{
		if(!Auth.checkValidEmail(email))
			throw new RuntimeException("Email is already there!");
	}

	// return user.
	public User signIn(String Email,String password) throws Exception {
		String[] checkAdmin=Auth.checkAdmin(Email);//here we will call the check admin from database
		if(checkAdmin[0].equals("false")) { //User found but not an admin
			return authorizeUserOnPassword(Email, password, checkAdmin[1]);
		}else if (checkAdmin[0].equals("true")) {
			return  authorizeAdminOnPassword(Email, password, checkAdmin[1]);
		}
		throw new RuntimeException("Email is not correct!");
	}

	private User authorizeAdminOnPassword(String email, String password, String truePassword) throws Exception {
		System.out.println("Admin Found!");
		User admin= AdminAuthorization.signIn(email, password,truePassword);
		AdminSession.getSession();
		return admin;
	}

	private User authorizeUserOnPassword(String email, String password, String truePassword) throws Exception{
		System.out.println("Authorizing User...");
		UserAuthorization authorization= new UserAuthorization(email,password,truePassword);
		User user=authorization.authorize();
		//Call User Session Here and set the user of the session.
		System.out.println("User Authorized!");
		return user;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

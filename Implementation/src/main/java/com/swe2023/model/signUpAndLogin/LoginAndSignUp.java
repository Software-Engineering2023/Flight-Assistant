package com.swe2023.model.signUpAndLogin;

import java.util.Date;

import com.swe2023.Admin.AdminAuthorization;
import com.swe2023.Admin.AdminSession;
import com.swe2023.User.UserAuthorization;
import com.swe2023.model.Passenger.PassengerAuthorization;
import Proxy.Auth;
public class LoginAndSignUp {

	//////////////////////////sign up new passenger///////////////////
	public boolean signUp(String Email,String password,Date BitrhDate,String passportNumber,String gender) {
		boolean checkValidEmail=checkVaildEmail(Email);//check if the use already exist
		if(checkValidEmail) {
			passenger pass=new passenger(Email,password, BitrhDate,passportNumber,gender);
			addUserToDatabase(pass); //class passenger to add it 
			return true;
		}else
			return false;
		
	}

	private void addUserToDatabase(passenger pass) {
		//TODO
	}

	private Object getTheGeneratedID() {
		//TODO
		return null;
	}

	private boolean checkVaildEmail(String email) {
		//TODO
		return false;
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
		UserAuthorization authorization= new UserAuthorization(email,password,truePassword);
		User user=authorization.authorize();
		//Call User Session Here and set the user of the session.
		return user;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

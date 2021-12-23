package com.swe2023.model.signUpAndLogin;

import java.util.Date;

import com.swe2023.Admin.AdminAuthorization;
import com.swe2023.Admin.AdminSession;
import com.swe2023.model.Passenger.PassengerAuthorization;
import Proxy.Auth;
public class LoginAndSignUp {

	
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
		Auth auth=new Auth();
		String[] checkAdmin=auth.checkAdmin(Email);//here we will call the check admin from database 
		if(checkAdmin[0].equals("-1")) {
			System.out.println("Admin Not found!");
			return null;
		}else if (checkAdmin[0].equals("true")) {
			AdminAuthorization Aauth=new AdminAuthorization();
			System.out.println("Admin Found!");
			User admin= Aauth.signIn(Email, password,checkAdmin[1]);
			AdminSession.getSession();
			return admin;
		}
		return null;
	}
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

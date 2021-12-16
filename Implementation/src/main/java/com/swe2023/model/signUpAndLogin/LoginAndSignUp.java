package com.swe2023.model.signUpAndLogin;

import java.util.Date;

import com.swe2023.Admin.AdminAuthorization;
import com.swe2023.model.Passenger.PassengerAuthorization;

public class LoginAndSignUp {

	
	public boolean signUp(String Email,String password,Date BitrhDate,String passportNumber,String gender) {
		boolean checkVaildEmail=checkVaildEmail(Email);//check if the use already exist 
		if(checkVaildEmail) {
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

	public boolean signIn(String Email,String password,Date BitrhDate) {
		boolean auth=false;
		boolean checkAdmin=checkAdmin(Email);//here we will call the check admin from database 
		return false;
//		if(checkAdmin) {
//			AdminAuthorization Aauth=new AdminAuthorization();
//			auth=Aauth.signIn(Email, password);
//		}else {
//			PassengerAuthorization Pauth=new PassengerAuthorization();
//			auth=Pauth.signIn(Email, password);
//		}
//		if(auth) {
//			return true;
//		}else
//			return false;

	}

	

	private boolean checkAdmin(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

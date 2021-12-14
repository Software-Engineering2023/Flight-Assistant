package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class LoginAndSignUp {

	
	public boolean signUp(String Email,String password,Date BitrhDate,String passportNumber,String gender) {
		boolean checkVaildEmail=checkVaildEmail(Email);
		if(checkVaildEmail) {
			passenger pass=new passenger(getTheGeneratedID(),Email,BitrhDate,passportNumber,gender);
			addUserToDatabase(pass);
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
		boolean checkAdmin=checkAdmin(Email);
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
//
	}

	private boolean checkAdmin(String email) {
		//TODO
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

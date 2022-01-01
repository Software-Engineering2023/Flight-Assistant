package com.swe2023.Admin;
import Proxy.Auth;
import java.util.Date;

import com.swe2023.model.signUpAndLogin.User;

public class AdminAuthorization {

	public static User signIn(String Email,String password,String truePassword) throws Exception {
		if(password.equals(truePassword)) {
			return Auth.getUser(Email);
		}else
			return null;
	}
	private boolean checkFaildEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	
}

package com.swe2023.Admin;

import java.util.Date;

public class AdminAuthorization {
	boolean authorized;
	public boolean signIn(String Email,String password) {
		boolean checkVaildEmailAndPassword=checkFaildEmailAndPassword(Email,password);
		if(checkVaildEmailAndPassword) {
			authorized=true;
			return true;
			
		}else
			return false;
		
	}

	private boolean checkFaildEmailAndPassword(String email, String password) {
		return false;
	}
}

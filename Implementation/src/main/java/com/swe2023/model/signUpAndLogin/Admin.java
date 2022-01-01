package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class Admin extends User{
	
	public Admin(int id, String email, String password, Date birthDate,String gender) {
		super( email, password,gender, true);
		super.admin=true;
	}
	
	

}

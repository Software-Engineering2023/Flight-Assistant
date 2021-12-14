package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class Admin extends User{
	
	public Admin(int id, String email, String password, Date birthDate,String gender) {
		super(id, email, password, birthDate,gender);
		super.admin=true;
	}
	
	

}

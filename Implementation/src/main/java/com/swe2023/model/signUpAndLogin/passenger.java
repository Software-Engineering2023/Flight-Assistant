package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class passenger extends User {
	private String passportNumber;
	public passenger( String email, String password, Date birthDate,String passportNumber,String gender, int x) {
//		super( email, password, birthDate,gender);
		super.balance = 0;
		super.admin=false;
		this.passportNumber=passportNumber;
	}

	public passenger(Object theGeneratedID, String email, Date bitrhDate, String passportNumber, String gender) {
		super();
	}
}

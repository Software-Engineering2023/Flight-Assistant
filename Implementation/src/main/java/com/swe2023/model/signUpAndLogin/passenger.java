package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class passenger extends User {
	private String passportNumber;
	public passenger(int id, String email, String password, Date birthDate,String passportNumber,String gender) {
		super(id, email, password, birthDate,gender);
		super.balance = 0;
		super.admin=false;
		this.passportNumber=passportNumber;
	}

	public passenger(Object theGeneratedID, String email, Date bitrhDate, String passportNumber, String gender) {
		super();
	}
}

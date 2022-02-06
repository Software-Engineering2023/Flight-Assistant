package com.swe2023.model.signUpAndLogin;

import java.util.Date;

public class Passenger extends User {
	private String passportNumber;
	private double totalTicketCost;

	public Passenger(String email, String password, Date birthDate, String passportNumber, String gender) {
		super(email,password,gender,birthDate);
		this.passportNumber=passportNumber;
	}
	public double getTotalTicketCost() {
		return totalTicketCost;
	}
	public void setTotalTicketCost(double totalTicketCost) {
		this.totalTicketCost = totalTicketCost;
	}
//	public Passenger(Object theGeneratedID, String email, Date bitrhDate, String passportNumber, String gender) {
//
//	}


}

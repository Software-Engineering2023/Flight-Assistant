package com.swe2023.model.signUpAndLogin;

import java.security.PublicKey;
import java.util.Date;

import com.swe2023.Admin.AdminAuthorization;
import com.swe2023.Admin.AdminSession;
import com.swe2023.User.UserAuthorization;
import Proxy.Auth;
import com.swe2023.model.security.DataEncryption;
import com.swe2023.model.security.Encryptor;

public class LoginAndSignUp{

	private Encryptor encryptor;
	private PublicKey clientKey;

	//////////////////////////sign up new passenger///////////////////

	public LoginAndSignUp(PublicKey key){
		encryptor= new DataEncryption();
		this.clientKey= key;
	}
	/**
	 * This function will add a user to database if email is not present
	 * The function will end successfully if no errors were found.
	 * @throws Exception in case email was already there.
	 */
	public boolean signUp(String Email,String password,Date BitrhDate,String passportNumber,String gender) throws Exception {
		try {
			checkValidEmail(Email);//check if the use already exist
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		String decryptedPassword = decrypt(password);
		Passenger pass = new Passenger(Email, decryptedPassword, BitrhDate, passportNumber, gender);
		addUserToDatabase(pass); //class passenger to add it
		return true;
	}

	private void addUserToDatabase(Passenger pass) throws Exception {
		Auth.addNewUser(pass);
	}

	private Object getTheGeneratedID() {
		//TODO
		return null;
	}

	private void checkValidEmail(String email) throws Exception{
		if(!Auth.checkValidEmail(email))
			throw new RuntimeException("Email is already there!");
	}

	// return user.
	public User signIn(String Email,String password) throws Exception {
		String[] checkAdmin=Auth.checkAdmin(Email);//here we will call the check admin from database
		password= decrypt(password);
		User user;
		if(checkAdmin[0].equals("false")) { //User found but not an admin
			user= authorizeUserOnPassword(Email, password, checkAdmin[1]);
		}else if (checkAdmin[0].equals("true")) {
			user=authorizeAdminOnPassword(Email, password, checkAdmin[1]);
		}
		else
			throw new RuntimeException("Email is not correct!");
		//Now we need to encrypt to be sent correctly.
		String thePassword= user.getPassword();
		thePassword= encrypt(thePassword, clientKey);
		user.setPassword(thePassword);
		return user;
	}

	private User authorizeAdminOnPassword(String email, String password, String truePassword) throws Exception {
		System.out.println("Admin Found!");
		User admin= AdminAuthorization.signIn(email, password,truePassword);
		AdminSession.getSession();
		return admin;
	}

	private User authorizeUserOnPassword(String email, String password, String truePassword) throws Exception{
		System.out.println("Authorizing User...");
		UserAuthorization authorization= new UserAuthorization(email,password,truePassword);
		User user=authorization.authorize();
		//Call User Session Here and set the user of the session.
		System.out.println("User Authorized!");
		return user;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	private String encrypt(String data, PublicKey key) {
		return encryptor.encrypt(data, key);
	}

	private String decrypt(String data) {
		return encryptor.decrypt(data);
	}


	public PublicKey getPublicKey() {
		return encryptor.getPublicKey();
	}
}

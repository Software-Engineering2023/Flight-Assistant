package db2023.backEnd;

import java.sql.SQLException;
import java.util.Date;


public class LoginAndSignUp {

	//////////////////////////sign up new passenger///////////////////

	/**
	 * This function will add a user to database if email is not present
	 * The function will end successfully if no errors were found.
	 * @throws Exception in case email was already there.
	 */
	public boolean signUp(User user) throws Exception {
		try {
			checkValidEmail(user.getEmail());//check if the use already exist
		}
	catch (Exception e){
			e.printStackTrace();
		return false;
	}
		
		addUserToDatabase(user); //class passenger to add it
		return true;
	}

	private void addUserToDatabase(User user) throws Exception {
		Auth.addNewUser(user);
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
		if(checkAdmin[0].equals("false")) { //User found but not an admin
			return authorizeUserOnPassword(Email, password, checkAdmin[1]);
		}else if (checkAdmin[0].equals("true")) {
			return  authorizeAdminOnPassword(Email, password, checkAdmin[1]);
		}
		throw new RuntimeException("Email is not correct!");
	}

	private User authorizeAdminOnPassword(String email, String password, String truePassword) throws Exception {
		System.out.println("Admin Found!");
		User admin= AdminAuthorization.signIn(email, password,truePassword);
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

}

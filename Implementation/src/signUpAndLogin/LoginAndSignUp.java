package signUpAndLogin;

import java.util.Date;

import Admin.AdminAuthorization;
import Passenger.PassengerAuthorization;

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
	
	public boolean signIn(String Email,String password,Date BitrhDate) {
		boolean auth=false;
		boolean checkAdmin=checkAdmin(Email);
		if(checkAdmin) {
			AdminAuthorization Aauth=new AdminAuthorization();
			auth=Aauth.signIn(Email, password);
		}else {
			PassengerAuthorization Pauth=new PassengerAuthorization();
			auth=Pauth.signIn(Email, password);
		}
		if(auth) {
			return true;
		}else
			return false;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package db2023.backEnd;


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

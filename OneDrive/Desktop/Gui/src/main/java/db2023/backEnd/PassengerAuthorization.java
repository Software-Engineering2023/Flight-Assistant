package db2023.backEnd;


public class PassengerAuthorization {
	boolean authorized;
	public boolean signIn(String Email,String password,String truePassowrd) {
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

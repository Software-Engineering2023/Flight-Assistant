package com.swe2023.model.signUpAndLogin;

import static org.junit.Assert.*;

import com.swe2023.Proxy.DB_Utils;
import com.swe2023.model.security.DataEncryption;
import com.swe2023.model.security.Encryptor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class LoginAndSignUpTest {
	public static Encryptor encryptor= new DataEncryption();
	public static final LoginAndSignUp loginAndSignUp= new LoginAndSignUp(encryptor.getPublicKey());
	public static final String email= "seif.com";
	public static final String password1= "123456";
	public static final String password2= "47895123";

	@BeforeClass
	public static void deleteAllUsers() throws SQLException {
		System.out.println("Called!");
		Connection connection = DB_Utils.getDataSource().getConnection();
		execute(connection, "DELETE FROM User WHERE isAdmin='2'");
		execute(connection, "INSERT INTO User(Name,Gender,Email,Password,isAdmin) VALUES ('admin', 'M', 'admin.com','12345', '1')");
		connection.close();
	}

	private static void execute(Connection connection, String Query) throws SQLException {
		Statement x=connection.createStatement();
		x.execute(Query);
		x.close();
	}

	@Test
	public void performTests() throws Exception {
		signUp();
		signIn();
	}

//	@Test
//	public void test() throws Exception {
//		LoginAndSignUp lAs=new LoginAndSignUp();
//		User userExpected=lAs.signIn("a@gmail","1111");
//		User user = new User("a@gmail","1111","m",1==1);
//		assertTrue((userExpected.getPassword()).equals(user.getPassword()));
//		userExpected=lAs.signIn("sakr@gmail","1111");
//		assertNull(userExpected);
//	}

	public void signUp() throws Exception {
		String encrypted= encryptor.encrypt(password1, loginAndSignUp.getPublicKey());
		boolean passed1= loginAndSignUp.signUp(email, encrypted , new Date() , null, "M");
		encrypted= encryptor.encrypt(password2, loginAndSignUp.getPublicKey());
		boolean passed2= !loginAndSignUp.signUp(email, encrypted , new Date(), null, "F");
		assertTrue(passed1);
		assertTrue(passed2);
	}


	public void signIn() {
		boolean passed1= true;
		try {
			User user=loginAndSignUp.signIn(email, encryptor.encrypt(password1, loginAndSignUp.getPublicKey()));
			if(!user.getGender().equals("M"))
				throw new RuntimeException("Data is not correct!");
			if(!encryptor.decrypt(user.getPassword()).equals(password1))
				throw new RuntimeException("Encryption is not correct!");
		}catch (Exception e){
			e.printStackTrace();
			passed1= false;
		}
		boolean passed2= false;
		try {
			User user=loginAndSignUp.signIn(email, encryptor.encrypt(password2, loginAndSignUp.getPublicKey()));
			if(user == null)throw new NullPointerException();
		}catch (Exception e){
			passed2= true;
		}
		assertTrue(passed1);
		assertTrue(passed2);

	}
}

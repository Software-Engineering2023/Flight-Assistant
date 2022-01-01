package com.swe2023.model.signUpAndLogin;

import static org.junit.Assert.*;

import com.swe2023.Proxy.DB_Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class LoginAndSignUpTest {

	public static final LoginAndSignUp loginAndSignUp= new LoginAndSignUp();
	public static final String email= "seif.com";
	public static final String password1= "123456";
	public static final String password2= "47895123";
	@BeforeClass
	public static void deleteAllUsers() throws SQLException {
		System.out.println("Called!");
		Connection connection = DB_Utils.getDataSource().getConnection();
		execute(connection, "DELETE FROM User");
		execute(connection, "INSERT INTO User(Name,Gender,Email,Password,isAdmin) VALUES ('admin', 'M', 'admin.com','12345', '1')");
		connection.close();
	}

	private static void execute(Connection connection, String Query) throws SQLException {
		Statement x=connection.createStatement();
		x.execute(Query);
		x.close();
	}

	@Test
	public void performTests(){
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

	public void signUp() {
		boolean passed1= true;
		try{
			loginAndSignUp.signUp(email, password1 , new Date() , null, "M");
			System.out.println("Done");
		}catch (Exception e){
			e.printStackTrace();
			passed1= false;
		}
		boolean passed2= false;
		try {
			loginAndSignUp.signUp(email, password2 , new Date(), null, "F");
			System.out.println("False");
		}catch (Exception e){
			passed2= true;
		}
		assertTrue(passed1);
		assertTrue(passed2);
	}

	public void signIn() {
		boolean passed1= true;
		try {
			User user=loginAndSignUp.signIn(email, password1);
			if(!user.getGender().equals("M"))
				throw new RuntimeException("Data is not correct!");
		}catch (Exception e){
			e.printStackTrace();
			passed1= false;
		}
		boolean passed2= false;
		try {
			User user=loginAndSignUp.signIn(email, password2);
			if(user == null)throw new NullPointerException();
		}catch (Exception e){
			passed2= true;
		}
		assertTrue(passed1);
		assertTrue(passed2);

	}
}

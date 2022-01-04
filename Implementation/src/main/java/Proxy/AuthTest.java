package Proxy;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swe2023.model.signUpAndLogin.User;

public class AuthTest {

	@Test
	public void test() throws Exception {
		Auth auth=new Auth();
		String x[]= {"true","11111"};
		String expected[]=auth.checkAdmin("ab@gmail");
		assertTrue(x[0].equals(expected[0]));
		
		User userExpected=auth.getUser("a@gmail");
		User user = new User("a@gmail","1111","m",1==1);
		assertTrue((user.getPassword()).equals(userExpected.getPassword()));
		assertTrue((user.getGender()).equals(userExpected.getGender()));
		
	}

}

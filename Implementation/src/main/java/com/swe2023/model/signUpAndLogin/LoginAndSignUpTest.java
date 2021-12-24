package com.swe2023.model.signUpAndLogin;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginAndSignUpTest {

	@Test
	public void test() throws Exception {
		LoginAndSignUp lAs=new LoginAndSignUp();
		User userExpected=lAs.signIn("a@gmail","1111");
		User user = new User("a@gmail","1111","m",1==1);
		assertTrue((userExpected.getPassword()).equals(user.getPassword()));
		userExpected=lAs.signIn("sakr@gmail","1111");
		assertNull(userExpected);
	}
}

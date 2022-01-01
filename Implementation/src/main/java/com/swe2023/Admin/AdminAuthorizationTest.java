package com.swe2023.Admin;

import static org.junit.Assert.*;

import org.junit.Test;

import com.swe2023.model.signUpAndLogin.User;

public class AdminAuthorizationTest {

	@Test
	public void test() throws Exception {
		AdminAuthorization Aauth=new AdminAuthorization();
		User userExpected=Aauth.signIn("m","11111","11111");
		User user = new User("m","11111","m",1==1);
		assertTrue((userExpected.getPassword()).equals(user.getPassword()));
		userExpected=Aauth.signIn("sakr@gmail","1111","3333");
		assertNull(userExpected);
	}

}

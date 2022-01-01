package com.swe2023.User;

import Proxy.Auth;
import com.swe2023.model.signUpAndLogin.User;

import java.sql.SQLException;

public class UserAuthorization {

    private final String email;
    private final String password;
    private final String truePassword;

    public UserAuthorization(String email, String passwordWritten, String truePassword){
        this.email=email;
        this.password= passwordWritten;
        this.truePassword= truePassword;
    }
    public User authorize() throws Exception {
        checkPasswordsAreSame();
        return Auth.getUser(this.email);
    }
    private void checkPasswordsAreSame() throws Exception{
        if(!password.equals(truePassword))
            throw new RuntimeException("Passwords are not the same!");
    }
}

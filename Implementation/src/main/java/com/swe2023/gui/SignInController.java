package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.model.signUpAndLogin.LoginAndSignUp;
import com.swe2023.model.signUpAndLogin.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    public Button signInButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;

    public void signIn() throws Exception {
        String username= usernameField.getText();
        String password= passwordField.getText();
        try {
            User user = new LoginAndSignUp().signIn(username, password);
            if(user.getAdmin())
                HelloApplication.showWindow(signInButton, "/admin-home.fxml", "Administrator", 800,640);
        }catch (Exception e){e.printStackTrace();}

    }
}

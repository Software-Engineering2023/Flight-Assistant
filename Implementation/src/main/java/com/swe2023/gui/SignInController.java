package com.swe2023.gui;

import com.swe2023.HelloApplication;
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

    public void signIn() throws IOException {
        String username= usernameField.getText();
        String password= passwordField.getText();
        System.out.println("Username is "+username+"\tPassword is "+password);
        HelloApplication.showWindow(signInButton, "/admin-home.fxml", "Administrator", 800,640);
    }
}

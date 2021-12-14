package com.swe2023.gui;

import com.swe2023.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    public Button signInButton;


    public void signIn() throws IOException {
        HelloApplication.showWindow(signInButton, "/admin-home.fxml", "Administrator", 800,640);
    }
}

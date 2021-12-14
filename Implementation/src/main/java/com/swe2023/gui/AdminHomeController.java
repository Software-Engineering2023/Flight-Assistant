package com.swe2023.gui;

import com.swe2023.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminHomeController {

    public Button signInButton;

    public void goToSignIn() throws IOException {

        HelloApplication.showWindow(signInButton, "/signIn.fxml", "Welcome", 800,640);
    }
}

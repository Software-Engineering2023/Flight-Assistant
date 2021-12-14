package com.swe2023.gui;

import com.swe2023.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

public class AdminHomeController {

    public Button signInButton;

    public void goToSignIn() throws IOException {
        HelloApplication.showWindow(signInButton, "/signIn.fxml", "Welcome", 800,640);
    }

    public void goToAirport() {
        HelloApplication.showWindow(signInButton, "/admin-airport.fxml","Airports Management", 800,640);
    }
}
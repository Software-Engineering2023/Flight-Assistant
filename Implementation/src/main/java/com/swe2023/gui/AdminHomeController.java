package com.swe2023.gui;

import com.swe2023.Admin.AdminSession;
import com.swe2023.HelloApplication;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

public class AdminHomeController {

    public Button signInButton;


    public void goToSignIn() throws IOException {
        HelloApplication.showWindow(signInButton, "/signIn.fxml", "Welcome", null,950,650);
    }

    public void goToAirport() {
        HelloApplication.showWindow(signInButton, "/admin-airport.fxml","Airports Management", null,950,650);
    }

    public void managePlanes() {
        HelloApplication.showWindow(signInButton, "/admin-plane.fxml","Planes Management", null,950,650);
    }
    public void manageFlights(){
        HelloApplication.showWindow(signInButton, "/admin-flight.fxml","Flights Management", null,950,650);

    }
}

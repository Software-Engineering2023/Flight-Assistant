package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Planes_Data.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class FinalWizardController {

    @FXML
    public Button finish;
    public Label source, deptDate, takeoffTime;
    public Button logout;

    private UserSession userSession;

    public void initialize() {
        userSession = UserSession.getSession();
        Trip trip = userSession.getSelectedTrip();
        Port src = trip.getFlights().get(0).getSource();
        source.setText(src.getCountry() + " | " + src.getCity() + " | " + src.getName());
        deptDate.setText(trip.getDeptDate());
        takeoffTime.setText(trip.getTakeOffTime());
    }

    public void backToHome() {
        HelloApplication.showWindow(finish, "/wizard1.fxml", "Wizard1",
                null,620, 500);
    }

    public void signOut(ActionEvent actionEvent) {
        HelloApplication.showWindow(logout, "/signIn.fxml", "Welcome",
                "/signINCSS.css", 950, 650);
    }
}

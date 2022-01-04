package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class Wizard3Controller {

    @FXML
    public Button next, back;
    public ToggleGroup flightClasses;

    private UserSession userSession;
    private Trip trip;

    @FXML
    public void initialize() {
        userSession = UserSession.getSession();
        trip = userSession.getSelectedTrip();
    }

    public void proceed() {
        String ticketClass = ((RadioButton) flightClasses.getSelectedToggle()).getText();
        trip.setTicketClass(ticketClass);
        HelloApplication.showWindow(back, "/wizard4.fxml", "Wizard4",
                null,620, 500);
    }

    public void backToPrevious() {
        HelloApplication.showWindow(back, "/flight-details.fxml", "Details",
                null,620, 500);
    }
}

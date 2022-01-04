package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Planes_Data.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class FlightDetailsController {

    @FXML
    public Button select, back;
    public Label deptDate, takeoffTime;
    public ListView<String> flights;

    private UserSession userSession;

    @FXML
    public void initialize() {
        userSession = UserSession.getSession();
        Trip trip = userSession.getSelectedTrip();
        deptDate.setText(trip.getDeptDate());
        takeoffTime.setText(trip.getTakeOffTime());
        trip.getFlights().forEach(flight -> {
            Port src = flight.getSource(), dest = flight.getDestination();
            String value = "From\t" + src.getCountry() + "  |  " + src.getCity() + "  |  " + src.getName() + "\n" +
                    "To\t\t" + dest.getCountry() + "  |  " + dest.getCity() + "  |  " + dest.getName();
            flights.getItems().add(value);
        });
    }

//    @Override
//    public void receiveData(Object data) {
//
//    }

    public void selectFlight() {
        HelloApplication.showWindow(back, "/wizard3.fxml", "Wizard3",
                null, 620, 500);
    }

    public void backToPrevious() {
        HelloApplication.showWindow(back, "/wizard2.fxml", "Wizard2",
                null, 620, 500);
    }
}

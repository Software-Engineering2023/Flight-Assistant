package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class Wizard2Controller {

    @FXML
    public ListView<Trip> listView;
    public Button showDetails, back;
    public Button logout;

    private UserSession userSession;
//    public ArrayList<Trip> trips;


    @FXML
    public void initialize() {
        listView.setCellFactory(new TripItemFactory());
        userSession = UserSession.getSession();
        userSession.getTrips().forEach(trip -> listView.getItems().add(trip));
    }

//    @Override
//    public void receiveData(Object data) {
//        trips = (ArrayList<Trip>) data;
//
//    }

    public void showFlightDetails() {
        // pass flight to FlightDetails here...

        Trip trip = listView.getSelectionModel().selectedItemProperty().getValue();

        if (trip == null)
            HelloApplication.showErrorMessage("You have to select a Flight");
        else {
            userSession.setSelectedTrip(trip);
            HelloApplication.showWindow(showDetails, "/flight-details.fxml", "Details",
                    null, 620, 500);
        }
    }

    public void backToPrevious() {
        HelloApplication.showWindow(back, "/wizard1.fxml", "Wizard1",
                null, 620, 500);
    }

    public void signOut(ActionEvent actionEvent) {
        HelloApplication.showWindow(logout, "/signIn.fxml", "Welcome",
                "/signINCSS.css", 950, 650);
    }
}

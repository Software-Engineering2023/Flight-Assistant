package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Planes_Data.Trip;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Wizard1Controller {

    @FXML
    public ChoiceBox<String> source, destination;
    public ChoiceBox<Integer>  noOfPassengers;
    public DatePicker departureDate;
    public Button searchFlights;

    private UserSession userSession;

    private ArrayList<Port> airports;

    private int srcIndex, destIndex;
    private Date deptDate;

    public void initialize() {
        userSession = UserSession.getSession();
        loadAirports();
        fillAdultsMenu();
        createListeners();
    }

    private void loadAirports() {
        airports = (ArrayList<Port>) userSession.loadAirports();
        int index = 1;
        for (Port port : airports) {
            String value = port.getCountry() + "|" + port.getCity() + "|" + port.getName();
            source.getItems().add(value);
            destination.getItems().add(value);
        }
    }

    private void fillAdultsMenu() {
        int maxAdults = 7;
        for (int i = 0; i < maxAdults; i++)
            noOfPassengers.getItems().add(i + 1);
    }

    public void createListeners() {
        source.setOnAction(e -> srcIndex = source.getSelectionModel().getSelectedIndex());
        destination.setOnAction(e -> destIndex = destination.getSelectionModel().getSelectedIndex());
    }

    public void searchForFlights() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        deptDate = Date.from(departureDate.getValue().atStartOfDay(defaultZoneId).toInstant());
        String message = validate();
        if (message.equals("Correct")) {
            // perform the query...
            List<List<Flight>> temp = userSession.searchFlights(airports.get(srcIndex),
                    airports.get(destIndex), deptDate, noOfPassengers.getValue());
            ArrayList<Trip> trips = new ArrayList<>();
            for (List<Flight> list : temp) {
                Trip trip = new Trip(list);
                trip.setNoOfPassengers(noOfPassengers.getValue());
                trips.add(trip);
            }
            userSession.setTrips(trips);
            HelloApplication.showWindow(searchFlights, "/wizard2.fxml", "Wizard2",
                    null, 620, 500);
        }
        else
            HelloApplication.showErrorMessage(message);
    }

    private String validate() {
        if (srcIndex == destIndex)
            return "Source and Destination are not Valid";
        if (deptDate.before(new Date()))
            return "Date is not Valid";
        return "Correct";
    }

}

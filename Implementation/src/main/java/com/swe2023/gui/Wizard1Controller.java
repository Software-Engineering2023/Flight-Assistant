package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Planes_Data.Trip;
import com.swe2023.model.Tickets_Data.Ticket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Wizard1Controller {

    @FXML
    public ChoiceBox<String> source, destination;
    public ChoiceBox<Integer>  noOfPassengers;
    public DatePicker departureDate;
    public Button searchFlights;

    public ListView<Ticket> tickets;
    public Button cancelTicket;
    public Button showDetails;
    public Button logout;

    private UserSession userSession;

    private ArrayList<Port> airports;

    private int srcIndex, destIndex;
    private Date deptDate;

    public void initialize() {
        userSession = UserSession.getSession();
        loadAirports();
        fillAdultsMenu();
        createListeners();

        loadTickets();
    }

    private void loadTickets() {
        // new
        ArrayList<Ticket> userTickets = userSession.loadTickets();
        if (userTickets != null) {
            tickets.setCellFactory(new TicketItemFactory());
            userTickets.forEach(ticket -> tickets.getItems().add(ticket));
        }
    }

    private void loadAirports() {
        airports = (ArrayList<Port>) userSession.loadAirports();
        int index = 1;
        for (Port port : airports) {
            String value = port.getCountry() + " - " + port.getCity() + " - " + port.getName();
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

    public void showTicket() {
        Ticket ticket = tickets.getSelectionModel().selectedItemProperty().getValue();
        if (ticket == null) {
            HelloApplication.showErrorMessage("You have to select a Ticket");
            return;
        }
        userSession.setSelectedTicket(ticket);
        HelloApplication.showWindow(showDetails, "/ticket-details.fxml", "Details",
                null, 620, 500);
    }

    public void cancel() {
        Ticket ticket = tickets.getSelectionModel().selectedItemProperty().getValue();
        if (ticket == null) {
            HelloApplication.showErrorMessage("You have to select a Ticket");
            return;
        }
        if (ticket.getFlights().getLast().getDate().compareTo(new Date()) < 0) {
            HelloApplication.showErrorMessage("This ticket is Expired now !");
            return;
        }
        if (showConfirmBox()){
            userSession.setSelectedTicket(ticket);
            tickets.getItems().remove(ticket);
            userSession.cancelTicket();
        }
    }

    private boolean showConfirmBox() {
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setHeaderText("Cancel Ticket");
        confirmAlert.setContentText("Are you sure you want to cancel this ticket ?" + "\n" + "That may cost some taxes.");
        confirmAlert.showAndWait();
        return confirmAlert.getResult().getText().equals("OK");
    }

    public void signOut(ActionEvent actionEvent) {
        HelloApplication.showWindow(logout, "/signIn.fxml", "Welcome",
                "/signINCSS.css", 950, 650);
    }
}

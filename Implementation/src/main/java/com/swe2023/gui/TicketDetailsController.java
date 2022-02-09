package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.FlightSummary;
import com.swe2023.model.Tickets_Data.Ticket;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Date;
import java.util.LinkedList;


public class TicketDetailsController {

    public Button back;
    public TableView<FlightSummary> table;
    public TableColumn<FlightSummary, String> source;
    public TableColumn<FlightSummary, String> destination;
    public TableColumn<FlightSummary, String> date;

    public Label status;
    public Label ticketClass;
    public Label noOfPassengers;
    public Label cost;

    private UserSession userSession;

    public void initialize() {
        userSession = UserSession.getSession();

        Ticket ticket = userSession.getSelectedTicket();
        LinkedList<Flight> flights = ticket.getFlights();
        if (flights.getLast().getDate().compareTo(new Date()) < 0)
            status.setText("Expired");
        else
            status.setText("Active");
        ticketClass.setText(ticket.getExtras().getFirst()[0]);
        noOfPassengers.setText(String.valueOf(ticket.getPassengersNo()));
        cost.setText(String.valueOf(ticket.getCost()));

        // To be tested.....
        source.setCellValueFactory(new PropertyValueFactory<>("src"));
        destination.setCellValueFactory(new PropertyValueFactory<>("dest"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

        flights.forEach(flight -> table.getItems().add(new FlightSummary(flight)));

    }

    public void backToPrevious() {
        HelloApplication.showWindow(back, "/wizard1.fxml", "Wizard1",
                null, 620, 500);
    }
}

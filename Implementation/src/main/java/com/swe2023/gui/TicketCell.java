package com.swe2023.gui;

import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Tickets_Data.Ticket;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;
import java.util.Date;

public class TicketCell extends ListCell<Ticket> {

    @FXML
    public Label source;

    @FXML
    public Label destination;

    @FXML
    public Label status;

    @FXML
    public Label cost;


    public TicketCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ticket-item.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Ticket ticket, boolean empty) {
        super.updateItem(ticket, empty);

        if(empty || ticket == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {

            // loadTickets doesn't return flights in details....
            Port src = ticket.getFlights().getFirst().getSource();
            Port dest = ticket.getFlights().getLast().getDestination();
            source.setText(src.getCountry() + "\n" + src.getCity() + "\n" + src.getName());
            destination.setText(dest.getCountry() + "\n" + dest.getCity() + "\n" + dest.getName());
            if (ticket.getFlights().getLast().getDate().compareTo(new Date()) < 0)
                status.setText("Expired");
            else
                status.setText("Active");
            cost.setText(String.valueOf(ticket.getCost()));

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

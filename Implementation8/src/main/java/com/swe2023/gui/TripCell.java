package com.swe2023.gui;

import com.swe2023.model.Planes_Data.Trip;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

import java.io.IOException;

public class TripCell extends ListCell<Trip> {

    @FXML
    public Label flightType;

    @FXML
    public Label noOfStops;

    @FXML
    public Label takeoffTime;

    @FXML
    public Button showFlight;

    public TripCell() {
        loadFXML();
    }

    private void loadFXML() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/item.fxml"));
            loader.setController(this);
            loader.setRoot(this);
            loader.load();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void updateItem(Trip trip, boolean empty) {
        super.updateItem(trip, empty);

        if(empty || trip == null) {
            setText(null);
            setContentDisplay(ContentDisplay.TEXT_ONLY);
        }
        else {

            flightType.setText(trip.getType());
            noOfStops.setText(trip.getNoOfStops());
            takeoffTime.setText(trip.getTakeOffTime());

            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        }
    }
}

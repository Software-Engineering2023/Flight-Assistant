package com.swe2023.gui;

import com.swe2023.model.Planes_Data.Trip;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import javafx.util.Callback;

public class ItemFactory implements Callback<ListView<Trip>, ListCell<Trip>> {

    @Override
    public ListCell<Trip> call(ListView<Trip> param) {
        return new TripCell();
    }

}

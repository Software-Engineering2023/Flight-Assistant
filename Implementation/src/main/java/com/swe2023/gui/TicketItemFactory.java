package com.swe2023.gui;

import com.swe2023.model.Tickets_Data.Ticket;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class TicketItemFactory implements Callback<ListView<Ticket>, ListCell<Ticket>> {

    @Override
    public ListCell<Ticket> call(ListView<Ticket> param) {
        return new TicketCell();
    }
}

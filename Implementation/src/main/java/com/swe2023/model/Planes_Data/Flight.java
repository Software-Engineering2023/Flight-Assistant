package com.swe2023.model.Planes_Data;

import com.swe2023.model.Tickets_Data.Ticket;

import java.util.Date;

public class Flight {
    private int flightID;
    private Port source;
    private Port destination;
    private Plane plane;
    private Ticket[] tickets;
    private Date date;

    public Flight(int flightID, Port source, Port destination, Date Date, Plane plane) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.date = Date;
        this.plane = plane;
    }

    public int getFlightID() {
        return flightID;
    }

    public Port getSource() {
        return source;
    }

    public Port getDestination() {
        return destination;
    }

    public Plane getPlane() {
        return plane;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public java.util.Date getDate() { return date; }

    public void setDate(java.util.Date date) {
        date = date;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
}

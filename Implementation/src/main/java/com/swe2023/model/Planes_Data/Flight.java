package com.swe2023.model.Planes_Data;

import com.swe2023.model.Tickets_Data.Ticket;

import java.util.Date;

public class Flight {
    private int flightID;
    private Port source;
    private Port destination;
    private Plane plane;
    private Ticket[] tickets;

    // I think they should be dates ,but leave them Strings now.
    private Date departureDate, ArrivalDate;

    public Flight(Port source, Port destination, Date departureDate, Date arrivalDate, Plane plane) {
        this.source = source;
        this.destination = destination;
        this.departureDate = departureDate;
        ArrivalDate = arrivalDate;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public Date getArrivalDate() {
        return ArrivalDate;
    }

    // updatable attributes.
    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        ArrivalDate = arrivalDate;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
}

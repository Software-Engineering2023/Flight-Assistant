package com.swe2023.model.Planes_Data;

import com.swe2023.model.Tickets_Data.Ticket;

import java.util.Date;
import java.util.LinkedList;

import static com.swe2023.model.Planes_Data.Plane.isPlane;
import static com.swe2023.model.Planes_Data.Port.isPort;

public class Flight {

    public static final String DB_TABLE_NAME= "Flight";
    public static final String DB_ID= "Flight_id";
    public static final String DB_DATE= "Departure";
    public static final String DB_SOURCE= "Source";
    public static final String DB_DESTINATION= "Destination";
    public static final String DB_AVAILABLE_SEATS= "no_of_available_seats";
    public static final String DB_PLANE_ID= "Plane_id";


    private int flightID;
    private Port source;
    private Port destination;
    private Plane plane;
    private LinkedList<Ticket> tickets;
    private Date date;
    private int availableSeats;

    public Flight(int flightID, Port source, Port destination, Date Date, Plane plane) {
        this.flightID = flightID;
        this.source = source;
        this.destination = destination;
        this.date = Date;
        this.plane = plane;
        this.availableSeats = plane.getNo_of_seats();
        // edit...
        this.tickets = new LinkedList<>();
    }
    public Flight(int flightID) {
    	this.flightID=flightID;
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

    public LinkedList<Ticket> getTickets() {
        return tickets;
    }

    public java.util.Date getDate() { return date; }

    public void setDate(java.util.Date date) {
        date = date;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }
    public static boolean isFlight(Port source , Port destination , Date date, Plane plane){
        return isPort(source) && isPort(destination) && date != null && isPlane(plane);
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightID=" + flightID +
                ", source=" + source +
                ", destination=" + destination +
//                ", plane=" + plane +
//                ", tickets=" + tickets +
//                ", date=" + date +
//                ", availableSeats=" + availableSeats +
                '}';
    }
}

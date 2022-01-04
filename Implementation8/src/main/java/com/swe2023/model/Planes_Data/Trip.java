package com.swe2023.model.Planes_Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Trip {

    private List<Flight> flights;
    private int noOfPassengers;
    private int cost;
    private String ticketClass;

    public Trip(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public String getType() {
        if (flights.size() == 1)
            return "Direct";
        return "Transit";
    }

    public String getNoOfStops() {
        if (flights.size() == 1)
            return "Non Stop";
        return String.valueOf(flights.size() - 1);
    }

    public String getTakeOffTime() {
        return new SimpleDateFormat("HH:mm").format(flights.get(0).getDate());
    }

    public String getDeptDate() {
        return new SimpleDateFormat("dd/MM/yyyy").format(flights.get(0).getDate());
    }

    public static void main(String[] args) {
//        Flight flight = new Flight(3, new Port("11111"), new Port("22222"),
//                new Date(), new Plane(3));

        System.out.println(new SimpleDateFormat("HH:mm").format(new Date()));

    }

    public int getNoOfPassengers() {
        return noOfPassengers;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNoOfPassengers(int noOfPassengers) {
        this.noOfPassengers = noOfPassengers;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }
}

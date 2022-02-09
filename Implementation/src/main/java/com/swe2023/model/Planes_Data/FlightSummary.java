package com.swe2023.model.Planes_Data;

public class FlightSummary {

    private String src, dest, date;

    public FlightSummary(Flight flight) {
        Port source = flight.getSource(), destination = flight.getDestination();
        src = source.getCountry() + "\n" + source.getCity() + "\n" + source.getName();
        dest = destination.getCountry() + "\n" + destination.getCity() + "\n" + destination.getName();
        date = flight.getDate().toString();
    }

    public String getSrc() {
        return src;
    }

    public String getDest() {
        return dest;
    }

    public String getDate() {
        return date;
    }
}

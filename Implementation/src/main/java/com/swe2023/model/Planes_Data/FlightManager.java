package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.FlightQueryBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.swe2023.model.Planes_Data.Flight.isFlight;

public class FlightManager {

    private final FlightQueryBuilder fqb;

    public FlightManager(){
        fqb= new FlightQueryBuilder();
    }
    // Next MileStone.
    public boolean changePlaneFlight(Date date , ArrayList<Flight> newFlights){
        if(date != null) {
            boolean deleteflight = fqb.deleteFlight(date);
            boolean addFlights = fqb.addFlights(newFlights);
            return deleteflight && addFlights;
        }
        return false;
    }

    public String addNewFlight(Flight flight) {
        if(isFlight(flight.getSource(),flight.getDestination(),flight.getDate(),flight.getPlane())) {
            if(flight.getDate().compareTo(new Date()) < 1)
                return "date invalid";
            if (flight.getSource().getCode().equals(flight.getDestination().getCode()))
                return "source same port as destination";
            if( fqb.addFlight(flight))
                return  "ok";
            else
                return "flight already there";
        }
        return  "not valid flight";
    }

    public boolean deleteFlight(Flight flight){
        if(isFlight(flight.getSource(), flight.getDestination(), flight.getDate(), flight.getPlane()))
            return fqb.deleteFlight(flight.getFlightID());
        return false;
    }

    public List<Flight> searchFlights(Port source, Port destination) {
        List<Flight> flights= loadAllFlights();
        filterFlights(flights,source, destination);
        return flights;
    }

    private void filterFlights(List<Flight> flights, Port source, Port destination){
        for (Flight flight : flights) {
            if (source != null && !flight.getSource().equals(source)) {
                flights.remove(flight);
                continue;
            }
            if (destination != null && !flight.getDestination().equals(destination))
                flights.remove(flight);
        }
    }


    public ArrayList<Flight> loadAllFlights() {
        return fqb.getAll();
    }
}

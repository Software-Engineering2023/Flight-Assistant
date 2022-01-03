package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.FlightQueryBuilder;

import java.util.*;

import static com.swe2023.model.Planes_Data.Flight.isFlight;

public class FlightManager {

    private final FlightQueryBuilder fqb;
    private static final long CURRENT_DAY= 1000*60*60*24;

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

    public List<List<Flight>> searchFlights(Port source, Port destination, Date date, int passengersToBook) {
        List<List<Flight>> flights= new ArrayList<>();
        fillFlights(flights, source, destination);
        filterByDate(flights,date);
        filterByPassengers(flights, passengersToBook);
        return flights;
    }

    private void filterByPassengers(List<List<Flight>> flights, int passengersToBook) {
        for(List<Flight> flightList : flights){
            boolean remove= false;
            for(Flight singleFlight: flightList)
                if(singleFlight.getAvailableSeats() < passengersToBook){
                    remove=true;
                    break;
                }
            if(remove) flights.remove(flightList);
        }
    }

    private void filterByDate(List<List<Flight>> flights, Date date) {
        if (date == null)
            return;
        long expectedDay= date.getTime()/CURRENT_DAY;
        for (List<Flight> i : flights){
            long flightBeginningDate= (i.get(0).getDate().getTime()) / CURRENT_DAY;
            if (flightBeginningDate != expectedDay)
                flights.remove(i);
        }
    }

    /**
     * Get all flights of same destination.
     * Remove flights with same source as required (direct).
     * For each remaining flight:
     * ---Search for flights of required source and destination is source of second trip
     */
    private void fillFlights(List<List<Flight>> flights, Port source, Port destination) {
        List<Flight> correctDestinationFlights= fqb.searchFlight(null, destination);
        for (int i =0 ;i<correctDestinationFlights.size();i++) {
            Flight f= correctDestinationFlights.get(i);
            if (source.getCode().equals(f.getSource().getCode())) { //Direct Trip
                List<Flight> directFlight = new ArrayList<>();
                directFlight.add(f);
                flights.add(directFlight);
                correctDestinationFlights.remove(f);
            }
        }
        //The remaining trips have correct destination but wrong source.
        //We will search for flights having current flight source as destination and required source as source.

        for(Flight secondFlight : correctDestinationFlights) {
            List<Flight> correctSourceFlights = fqb.searchFlight(source, secondFlight.getSource());//A transit flight
            fillTransitList(flights,correctSourceFlights,secondFlight);
        }
    }

    private void fillTransitList(List<List<Flight>> flights, List<Flight> correctSourceFlights, Flight secondFlight) {
        if(correctSourceFlights == null || correctSourceFlights.isEmpty())
            return;
        for(Flight firstFlight: correctSourceFlights){
            List<Flight> transitFlights= new ArrayList<>();
            transitFlights.add(firstFlight);
            transitFlights.add(secondFlight);
            flights.add(transitFlights);
        }
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

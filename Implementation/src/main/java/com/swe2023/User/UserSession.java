package com.swe2023.User;

import com.swe2023.model.Planes_Data.*;
import com.swe2023.model.Tickets_Data.TicketBuilder;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is responsible for managing data between Backend and Frontend.
 * This class is only responsible for Ordinary Users.
 */
public class UserSession {

    private static UserSession session;
    private final PlanesInformation planesInformation;
    private final TicketBuilder ticketBuilder;

    private ArrayList<Trip> trips;

    private Trip selectedTrip;
    /* We need to add a ticket manager */
    private Passenger user; /* Used to book tickets directly and to save current user's progress */

    private UserSession(){
        planesInformation= new Manager();
        ticketBuilder= new TicketBuilder();
    }

    public ArrayList<Trip> getTrips() {
        return this.trips;
    }

    public Trip getSelectedTrip() {
        return this.selectedTrip;
    }

    public void setUser(Passenger user){
        this.user= user;
    }

    public void setTrips(ArrayList<Trip> trips){
        this.trips = trips;
    }

    public void setSelectedTrip(Trip trip){
        this.selectedTrip = trip;
    }

    public List<Port> loadAirports(){
        return planesInformation.loadPortsFromDataBase();
    }

    public List<List<Flight>> searchFlights(Port source, Port destination, Date date, int passengersToBook){
        return planesInformation.searchFlights(source,destination, date, passengersToBook);
    }

    public float getTotalCost() {
        if (selectedTrip != null) {
            System.out.println(user.getID());
            ticketBuilder.TicketBuild(user, selectedTrip.getNoOfPassengers());
            String[] extras= new String[]{selectedTrip.getTicketClass()};
            for (Flight flight : selectedTrip.getFlights()) {
                ticketBuilder.FLightBuild(flight);
                ticketBuilder.ExtrasBuild(extras);
            }
//            ticketBuilder.confirmBuild();
            return ticketBuilder.calculateCost();
        }
        return 0;
    }

    public void createTicket() {
        ticketBuilder.confirmBuild();
    }

    public static UserSession getSession(){
        if(session == null)
            session = new UserSession();
        return session;
    }







}

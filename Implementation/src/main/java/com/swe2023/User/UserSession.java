package com.swe2023.User;

import com.swe2023.model.Planes_Data.*;
import com.swe2023.model.Tickets_Data.TicketBuilder;
import com.swe2023.model.signUpAndLogin.Passenger;

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
    /* We need to add a ticket manager */
    private Passenger user; /* Used to book tickets directly and to save current user's progress */

    private UserSession(){
        planesInformation= new Manager();
        ticketBuilder= new TicketBuilder();
    }
    public void setUser(Passenger user){
        this.user= user;
    }

    public List<Port> loadAirports(){
        return planesInformation.loadPortsFromDataBase();
    }

    public List<List<Flight>> searchFlights(Port source, Port destination, Date date, int passengersToBook){
        return planesInformation.searchFlights(source,destination, date, passengersToBook);
    }

    public void createATicket(int passengersNumber, List<Flight> flights,String ticketClass){

        ticketBuilder.TicketBuild(user,passengersNumber);
        String[] extras= new String[]{ticketClass};
        for (Flight flight : flights) {
            ticketBuilder.FLightBuild(flight);
            ticketBuilder.ExtrasBuild(extras);
        }
        ticketBuilder.confirmBuild();
    }

    public static UserSession getSession(){
        if(session == null)
            session= new UserSession();
        return session;
    }







}

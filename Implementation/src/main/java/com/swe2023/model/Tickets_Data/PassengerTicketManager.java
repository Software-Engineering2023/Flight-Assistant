package com.swe2023.model.Tickets_Data;

import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.Proxy.TicketQuery;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.sql.SQLException;
import java.util.ArrayList;

public class PassengerTicketManager {
    Ticket[] ticketsShown;

//    private TicketQuery tqb;
    private FlightQueryBuilder fqb;

    public PassengerTicketManager (){
        //tqb = new TicketQuery();
        fqb = new FlightQueryBuilder();
    }

    public boolean cancelTicket(Ticket ticket){
        // call dataBase
        for(Flight flight: ticket.getFlights()){
            //increase free seats by Passengers number
            flight.setAvailableSeats(flight.getAvailableSeats()+ticket.getPassengersNo());
            flight.getTickets().remove(ticket);
            fqb.updateFlightSeats(flight);
        }
        return TicketQuery.deleteTicket(ticket);
     //   ticket = null;
    }

    public ArrayList<Ticket> loadUserTickets(int userId){
        try {
            return TicketQuery.getTicketOfUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modifyTicketExtra(Ticket ticket,Flight flight, String[] extra){
        int i = ticket.getFlights().indexOf(flight);
        ticket.getExtras().set(i,extra);
    }
}

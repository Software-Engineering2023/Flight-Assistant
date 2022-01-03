package com.swe2023.model.Tickets_Data;

import com.swe2023.Proxy.AirportQueryBuilder;
import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.Proxy.TicketQuery;

import java.util.LinkedList;

public class AdminTicketsManager {
     Ticket[] ticketsShown;
    private TicketQuery tqb;
    private FlightQueryBuilder fqb;

    public AdminTicketsManager (){
        fqb = new FlightQueryBuilder();
        tqb = new TicketQuery();
    }

    public void cancelTicket(Ticket ticket){
         // call dataBase
         for(Flight flight: ticket.getFlights()){
           //increase free seats by Passengers number
             flight.getTickets().remove(ticket);
             flight.setAvailableSeats(flight.getAvailableSeats()+ticket.getPassengersNo());
             fqb.updateFlightSeats(flight);
         }
        tqb.deleteTicket(ticket);
        
        ticket = null;
        
     }
    
    

     public void modifyTicketExtra(Ticket ticket,Flight flight,String extra[]){
         int i = ticket.getFlights().indexOf(flight);
         ticket.getExtras().set(i,extra);
         tqb.deleteTicket(ticket);
         tqb.addTicket(ticket);
     }
    public void modifyTicketUser(Ticket ticket, Passenger user){
        ticket.setUser(user);
        tqb.deleteTicket(ticket);
        tqb.addTicket(ticket);
    }

//    public void loadTicketCertainDate(String date){
//
//    }
//
//    public void loadTicketCertainFlight(Flight flight){
//
//    }

}

package com.swe2023.model.Tickets_Data;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.passenger;

import java.util.LinkedList;

public class AdminTicketsManager {
     Ticket[] ticketsShown;
     public void cancelTicket(Ticket ticket){
         // call dataBase
         for(Flight flight: ticket.getFlights()){
           //increase free seats by Passengers number
             flight.getTickets().remove(ticket);
         }
         ticket = null;
     }

     public void modifyTicketExtra(Ticket ticket,Flight flight,String extra[]){
         int i = ticket.getFlights().indexOf(flight);
         ticket.getExtras().set(i,extra);
    }
    public void modifyTicketUser(Ticket ticket, passenger user){
        ticket.setUser(user);
    }

    public void loadTicketCertainDate(String date){

    }

    public void loadTicketCertainFlight(Flight flight){

    }

}

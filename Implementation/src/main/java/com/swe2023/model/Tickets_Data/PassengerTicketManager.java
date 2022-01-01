package com.swe2023.model.Tickets_Data;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.Passenger;

public class PassengerTicketManager {
    Ticket[] ticketsShown;
    public void cancelTicket(Ticket ticket){
        // call dataBase
        for(Flight flight: ticket.getFlights()){
            //increase free seats by Passengers number
            flight.setAvailableSeats(flight.getAvailableSeats()+ticket.getPassengersNo());
            flight.getTickets().remove(ticket);
        }
        ticket = null;
    }
    public void modifyTicketExtra(Ticket ticket,Flight flight, String[] extra){
        int i = ticket.getFlights().indexOf(flight);
        ticket.getExtras().set(i,extra);
    }
    public void loadUserTickets(){

    }
}

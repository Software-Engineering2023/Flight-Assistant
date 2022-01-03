package com.swe2023.model.Tickets_Data;

import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.User;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.nio.charset.Charset;
import java.util.Random;
import com.swe2023.Proxy.TicketQuery;


public class TicketBuilder {
    private Ticket ticket;
    private TicketQuery tqb;

    public TicketBuilder (){
        tqb = new TicketQuery();
    }

    public void TicketBuild(Passenger user, int passengersNo){
        ticket = new Ticket(user,passengersNo);

        byte[] array = new byte[9];
        new Random().nextBytes(array);
        String generatedID = new String(array, Charset.forName("UTF-8"));
        ticket.setTicketID(generatedID);
    }

    public void FLightBuild(Flight flight){
        ticket.getFlights().push(flight);
    }

    public void ExtrasBuild(String [] extras){ ticket.getExtras().push(extras); }

    public void SeatsNoBuild(Integer [] seatNo){ ticket.getSeatNo().push(seatNo); } //1,2,3



    private void calculateCost() {
        float cost = 0;
        for (Flight flight : ticket.getFlights()) {
            int xCost = (flight.getDestination().getLatitude() - flight.getSource().getLatitude());
            cost += (xCost * xCost) * ticket.getPassengersNo();
            int yCost = (flight.getDestination().getLongitude() - flight.getSource().getLongitude());
            cost += (yCost * yCost) * ticket.getPassengersNo();
        }
        for (String [] extra : ticket.getExtras()) {
            cost += extra.length*10;
            //add hashmap for each extra cost
        }
        ticket.setCost(cost);
    }

    public void confirmBuild(){
        FlightQueryBuilder fqb = new FlightQueryBuilder();
        this.calculateCost();
       //add ticket to each plane
        tqb.addTicket(ticket);
        for(Flight flight: ticket.getFlights()){
            flight.setAvailableSeats(flight.getAvailableSeats()-ticket.getPassengersNo());
            flight.getTickets().add(ticket);
            fqb.updateFlightSeats(flight);
        }
    }
}

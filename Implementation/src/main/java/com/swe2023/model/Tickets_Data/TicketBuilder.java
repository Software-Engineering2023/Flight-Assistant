package com.swe2023.model.Tickets_Data;

import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.User;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Random;
import com.swe2023.Proxy.TicketQuery;


public class TicketBuilder {

    private Ticket ticket;
    private TicketQuery tqb;

    public TicketBuilder (){
        tqb = new TicketQuery();
    }

    public void TicketBuild(Passenger user, int passengersNo){
        ticket = new Ticket(user, passengersNo);

        byte[] array = new byte[9];
        new Random().nextBytes(array);
        String generatedID = new String(array, StandardCharsets.UTF_8);
        ticket.setTicketID(generatedID);
    }

    public void FLightBuild(Flight flight){
        ticket.getFlights().push(flight);
    }

    public void ExtrasBuild(String [] extras){ ticket.getExtras().push(extras); }

    public void SeatsNoBuild(Integer [] seatNo){ ticket.getSeatNo().push(seatNo); } //1,2,3



    public float calculateCost() {
        float cost = 0;
        for (Flight flight : ticket.getFlights()) {
            int xCost = (flight.getDestination().getLatitude() - flight.getSource().getLatitude());
            cost += (xCost * xCost) * ticket.getPassengersNo();
            int yCost = (flight.getDestination().getLongitude() - flight.getSource().getLongitude());
            cost += (yCost * yCost) * ticket.getPassengersNo();
        }
        HashMap<String, Integer> classCosts = new HashMap<>();
        classCosts.put("First Class", 2000);
        classCosts.put("Business", 1500);
        classCosts.put("Economic", 700);
        for (String [] extra : ticket.getExtras())
            cost += classCosts.get(extra[0]);
        ticket.setCost(cost);
        return cost;
    }

    public void confirmBuild(){
        FlightQueryBuilder fqb = new FlightQueryBuilder();

//        this.calculateCost();

       //add ticket to each plane
        tqb.addTicket(ticket);
        for(Flight flight: ticket.getFlights()){
            flight.setAvailableSeats(flight.getAvailableSeats()-ticket.getPassengersNo());
            flight.getTickets().add(ticket);
            fqb.updateFlightSeats(flight);
        }
    }
}

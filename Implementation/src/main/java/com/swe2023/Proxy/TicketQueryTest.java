package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Tickets_Data.Ticket;
import com.swe2023.model.signUpAndLogin.Passenger;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import static org.junit.Assert.*;

public class TicketQueryTest {

    @Test
    public void addTicket() throws Exception {
        TicketQuery TQ=new TicketQuery();
        String []x=new String[2];
        x[0]="saif";
        x[1]="zahaby";
        LinkedList<String [ ]> extras=new LinkedList<> () ;
        extras.add(x);
        LinkedList<Flight> flights=new LinkedList<Flight>();
        flights.add(new Flight(1));
        Integer[]y=new Integer[2];
        y[0]=54;
        y[1]=10;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        String dateInString = "7-Jun-2013";
        Date date = formatter.parse(dateInString);
        Passenger p=new Passenger("a","11",date,"123","m");
        p.setID(1);
//        new LoginAndSignUp().signUp(p.getEmail(), p.getPassword(), p.getBirthDate(),null, p.getGender());
        LinkedList<Integer []> seatNo=new LinkedList<Integer []>();
        seatNo.add(y);
        Ticket ticket =new Ticket(p,5);
        ticket.setCost(1500);
        ticket.setExtras(extras);
        ticket.setFlights(flights);
        ticket.setSeatNo(seatNo);
        //ticket.setTicketID("2");

        assertTrue(TQ.addTicket(ticket));
    }

    @Test
    public void deleteTicket() throws ParseException, SQLException {
        TicketQuery TQ=new TicketQuery();

        Ticket ticket =new Ticket("1");
        TQ.deleteTicket(ticket);
        assertFalse(TQ.seachTicket(ticket.getTicketID()));


    }



}


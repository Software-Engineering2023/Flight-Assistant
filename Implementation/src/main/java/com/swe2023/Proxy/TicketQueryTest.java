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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import static org.junit.Assert.*;

public class TicketQueryTest {


    @Before
    public  void deleteAllUsers() throws SQLException {
        System.out.println("Called!");
        Connection connection = DB_Utils.getDataSource().getConnection();

        execute(connection, "Delete FROM Flight_extra_In_Tickets");
        execute(connection, "Delete FROM Ticket");

        execute(connection, "insert into User  (Id,name, gender, Email, Password, isAdmin, Bdate,PassportNumber)values(15,'sakr','m','ziad@','11111','1','1980-12-12','2453')");
        execute(connection, "insert into User  (Id,name, gender, Email, Password, isAdmin, Bdate,PassportNumber)values(1,'sakoora','m','zayooda@','11111','1','1980-12-12','2453')");
        execute(connection, "insert into Flight values(21,'2022-11-11','140','140',10,100)");
        execute(connection, "insert into Ticket(Ticket_id,usderID,Cost,no_of_passenger) values(default, 1, 1500.0, 5)");

        connection.close();
    }

    private static void execute(Connection connection, String Query) throws SQLException {
        try {
            Statement x = connection.createStatement();
            x.execute(Query);
            x.close();
        }catch (Exception ignored){}
    }


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
    public void deleteTicket() throws  SQLException {
        TicketQuery TQ=new TicketQuery();

        Ticket ticket =new Ticket("1");
        TQ.deleteTicket(ticket);
        assertFalse(TQ.seachTicket(ticket.getTicketID()));
    }

    @Test
    public void getTeckitOfUser() throws ParseException, SQLException {
        TicketQuery TQ=new TicketQuery();
        String []x=new String[2];
        x[0]="saif";
        x[1]="zahaby";
        LinkedList<String [ ]> extras=new LinkedList<> () ;
        extras.add(x);
        LinkedList<Flight> flights=new LinkedList<Flight>();
        flights.add(new Flight(21));
        Integer[]y=new Integer[2];
        y[0]=54;
        y[1]=10;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        String dateInString = "7-Jun-2013";
        Date date = formatter.parse(dateInString);
        Passenger p=new Passenger("a","11",date,"123","m");
        p.setID(15);
        LinkedList<Integer []> seatNo=new LinkedList<Integer []>();
        seatNo.add(y);
        Ticket ticket =new Ticket(p,5);
        ticket.setCost(1500);
        ticket.setExtras(extras);
        ticket.setFlights(flights);
        ticket.setSeatNo(seatNo);
        //ticket.setTicketID("2");



        ArrayList<Ticket> t = null;
        for(int i=0; i<5;i++) {
            TQ.addTicket(ticket);
        }
        t=TQ.getTicketOfUser(15);
        System.out.println(t.size());
        assertEquals((t.size()),5);

    }



}


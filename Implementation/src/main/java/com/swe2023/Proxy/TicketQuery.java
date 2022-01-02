package com.swe2023.Proxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Tickets_Data.Ticket;
import com.swe2023.model.signUpAndLogin.Passenger;

public class TicketQuery {
	public boolean addTicket(Ticket ticket) {
        String queryForTiketTable = "insert into Ticket values(?, ?, ?, ?)";
        String queryForFlightInTickets ="insert into Flight_In_Tickets values(?, ?,)";
        String pStatementFlightExtraInTickets ="insert into Flight_extra_In_Tickets values(?, ?, ?)";
        String pStatementFlightSeatNo ="insert into seat_no_In_Tickets values(?, ?, ?)";

        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(queryForTiketTable);
            PreparedStatement pStatementForFlights = connection.prepareStatement(queryForFlightInTickets);
            PreparedStatement pStatementFlightExtra = connection.prepareStatement(pStatementFlightExtraInTickets);
            PreparedStatement pStatementSeatNo = connection.prepareStatement(pStatementFlightSeatNo);

            pStatement.setInt(1,Integer.parseInt(ticket.getTicketID()));
            pStatement.setInt(2, (ticket.getUser()).getID());
            pStatement.setDouble(3, ticket.getCost());
            pStatement.setInt(4, ticket.getPassengersNo());
            pStatement.execute();
            pStatement.close();
            ///////////////////////for queryForFlightInTickets///////////////
            LinkedList<Flight> flights=ticket.getFlights();
            for(Flight f:flights) {
            	pStatementForFlights.setInt(1,Integer.parseInt(ticket.getTicketID()));
            	pStatementForFlights.setInt(2, f.getFlightID());
            	pStatementForFlights.execute();
            }
            pStatementForFlights.close();
            ////////////// for pStatementFlightExtraInTickets//////////////////
            int flightCounter=0;
            LinkedList<String []> extras=ticket.getExtras();
            for(String ex[]:extras) {
            	
            	for(String e:ex) {
            		pStatementFlightExtra.setInt(1,Integer.parseInt(ticket.getTicketID()));
            		pStatementFlightExtra.setInt(2,flights.get(flightCounter).getFlightID());
            		pStatementFlightExtra.setString(3, e);
            		pStatementFlightExtra.execute();
            	}
            	flightCounter++;
            	
            }
            pStatementFlightExtra.close();
            
            //////////////////for seat_no_In_Tickets///////////////////////
            flightCounter=0;
            LinkedList<Integer []> seatNo=ticket.getSeatNo();
            for(Integer[] SN:seatNo) {
            	
            	for(int e:SN) {
            		pStatementSeatNo.setInt(1,Integer.parseInt(ticket.getTicketID()));
                    pStatementSeatNo.setInt(2,flights.get(flightCounter).getFlightID());
                    pStatementSeatNo.setInt(3, e);
                    pStatementSeatNo.execute();
            	}
            	flightCounter++;
            	
            }
            pStatementSeatNo.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	
//	public boolean updateTicket(Ticket tlight) {
//        String query = "update Ticket set  ";
//        try {
//            Connection connection = DB_Utils.getDataSource().getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(query);
//            pStatement.setTimestamp(1, new Timestamp(flight.getDate().getTime()));
//            pStatement.setInt(2, flight.getFlightID());
//            pStatement.execute();
//            pStatement.close();
//            connection.close();
//
//            return true;
//        } catch (SQLException e) {
//            return false;
//        }
//    }
	
	public boolean deleteFlight(Ticket tiket) {
        String query = "delete from Ticket where Ticket_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, Integer.parseInt(tiket.getTicketID()));
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	
	public ArrayList<Ticket> getAll() {
        String query = "select * from  Ticket from User as u INNER JOIN Ticket as t on t.usderID=u.Id ";
        String queryForGetFlight="select * from  Flight_In_Tickets where Ticket_id=?";
        String queryForExtra="select * from  Flight_extra_In_Tickets where Ticket_id=? and flightID=?";
        String queryForSeats="select * from  seat_no_In_Tickets where Ticket_id=? and flightID=?";

        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
            ResultSet resultSetForGetFlight = statement.executeQuery(queryForGetFlight);
            
            while (resultSet.next()) {
            	Ticket ticket =new  Ticket( new Passenger(resultSet.getString("Email"),resultSet.getString("Password"),resultSet.getDate("Bdate"),resultSet.getString("PassportNumber"),resultSet.getString("Gender")),resultSet.getInt("no_of_passenger"));
            	ticket.setTicketID(Integer.toString(resultSet.getInt("Ticket_id")));
            	ticket.setCost(resultSet.getFloat("Cost"));;
            	PreparedStatement pStatementForFlights = connection.prepareStatement(queryForGetFlight);
            	pStatementForFlights.setInt(1, resultSet.getInt("Ticket_id"));
            	ResultSet resultSetForFlights = pStatementForFlights.executeQuery();
            	 LinkedList<Flight> flights=new LinkedList<>();
            	 LinkedList<String[]> extras=new LinkedList<>();
            	 LinkedList<String> extraOfFlight=new LinkedList<>();
             	LinkedList<Integer[]> SeatsOfFlight=new LinkedList<>();

            	while (resultSetForFlights.next()) {
            		flights.add(new Flight( resultSet.getInt("flightID")) );
            		PreparedStatement pStatementForExtra = connection.prepareStatement(queryForExtra);
                	pStatementForExtra.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForExtra.setInt(2, resultSet.getInt("flightID"));
                	ResultSet resultSetForExtra = pStatementForExtra.executeQuery();
                	while(resultSetForExtra.next()) {
                		
                		String extra=resultSetForExtra.getString("extra");
                		extraOfFlight.add(extra);
                		
                	}
                	extras.add((String[]) extraOfFlight.toArray());
                	extraOfFlight.clear();
                	PreparedStatement pStatementForSeats = connection.prepareStatement(queryForSeats);
                	pStatementForSeats.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForSeats.setInt(2, resultSet.getInt("flightID"));
                	ResultSet resultSetForseats = pStatementForExtra.executeQuery();
                	LinkedList<Integer> seats=new LinkedList<>();
                	while(resultSetForseats.next()) {
                		
                		
                		int seat=resultSetForExtra.getInt("seatNo");
                		seats.add(seat);
                		
                	}
                	SeatsOfFlight.add((Integer[]) seats.toArray());
                	seats.clear();
                	
            	}
            	ticket.setExtras(extras);
            	ticket.setSeatNo(SeatsOfFlight);
            	tickets.add(ticket);
            	
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
	
	

}

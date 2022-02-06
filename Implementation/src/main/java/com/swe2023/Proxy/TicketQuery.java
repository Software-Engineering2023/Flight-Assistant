package com.swe2023.Proxy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Tickets_Data.Ticket;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.model.signUpAndLogin.User;
import com.swe2023.Proxy.FlightQueryBuilder;

public class TicketQuery {
	public static boolean addTicket(Ticket ticket) {
		System.out.println("enter add ticket query ");
		String queryForGetID="SELECT max(Ticket_id) AS Max_Id FROM Ticket";
        String queryForTiketTable = "insert into Ticket(Ticket_id,usderID,Cost,no_of_passenger) values(default, ?, ?, ?)";
        String queryForFlightInTickets ="insert into Flight_In_Tickets values(?, ?)";
        String pStatementFlightExtraInTickets ="insert into Flight_extra_In_Tickets values(?, ?, ?)";
        String pStatementFlightSeatNo ="insert into seat_no_In_Tickets values(?, ?, ?)";
        
        int newTicketID = 0;
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            System.out.println(connection);
            PreparedStatement pStatementForID = connection.prepareStatement(queryForGetID);
            ResultSet resultSetOfID = null;
            resultSetOfID=pStatementForID.executeQuery();
            while(resultSetOfID.next()){
	            //Display values
            	newTicketID=resultSetOfID.getInt("Max_Id")+1;
	        }
            pStatementForID.close();
            resultSetOfID.close();
            //System.out.println(newTicketID);
            PreparedStatement pStatement = connection.prepareStatement(queryForTiketTable);
            PreparedStatement pStatementForFlights = connection.prepareStatement(queryForFlightInTickets);
            PreparedStatement pStatementFlightExtra = connection.prepareStatement(pStatementFlightExtraInTickets);
            PreparedStatement pStatementSeatNo = connection.prepareStatement(pStatementFlightSeatNo);
//            System.out.println((ticket.getUser()).getID());
//            System.out.println(ticket.getCost());
//            System.out.println(ticket.getPassengersNo());
            Passenger u=ticket.getUser();
            pStatement.setInt(1, (ticket.getUser()).getID());
            pStatement.setDouble(2, ticket.getCost());
            pStatement.setInt(3, ticket.getPassengersNo());
            pStatement.execute();
            pStatement.close();
            ///////////////////////for queryForFlightInTickets///////////////
            LinkedList<Flight> flights=ticket.getFlights();
            for(Flight f:flights) {
            	pStatementForFlights.setInt(1,newTicketID);
            	pStatementForFlights.setInt(2, f.getFlightID());
            	pStatementForFlights.execute();
            }
            pStatementForFlights.close();
            ////////////// for pStatementFlightExtraInTickets//////////////////
            int flightCounter=0;
            LinkedList<String []> extras=ticket.getExtras();
            for(String ex[]:extras) {
            	
            	for(String e:ex) {
            		System.out.println(e);
            		pStatementFlightExtra.setInt(1,newTicketID);
            		System.out.println(flights.get(flightCounter).getFlightID());
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
            		pStatementSeatNo.setInt(1, newTicketID);
                    pStatementSeatNo.setInt(2, flights.get(flightCounter).getFlightID());
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
	
	public static boolean deleteTicket(Ticket ticket) {
		
       
        String query = "delete from Ticket where Ticket_id = "+ticket.getTicketID();
        String queryForFlightInTickets ="delete from Flight_In_Tickets where Ticket_id = "+ticket.getTicketID();
        String queryForFlightExtraInTickets ="delete from Flight_extra_In_Tickets where Ticket_id = "+ticket.getTicketID();
        String queryForFlightSeatNo ="delete from seat_no_In_Tickets where Ticket_id = "+ticket.getTicketID();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.execute();
            pStatement = connection.prepareStatement(queryForFlightInTickets);
            pStatement.execute();
            pStatement = connection.prepareStatement(queryForFlightExtraInTickets);
            pStatement.execute();
            pStatement = connection.prepareStatement(queryForFlightSeatNo);
            pStatement.execute();
            
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
	///////fuction to get all ticket on the system if flightID=0 and ticket of flight if flightID has other numbeer
	public static ArrayList<Ticket> getAll(int flightID) {
		String query;
		if(flightID==0) {
			 query = "select * from User as u INNER JOIN Ticket as t on t.usderID=u.Id  ";
		}else {
			 query="select * from Flight_In_Tickets as f INNER JOIN Ticket as t on t.Ticket_id=f.Ticket_id  INNER JOIN User as u on t.usderID=u.Id  where  flightID= "+flightID;

		}
        
        String queryForGetFlight="select * from  Flight_In_Tickets where Ticket_id=?";
        String queryForExtra="select * from  Flight_extra_In_Tickets where Ticket_id=? and flightID=?";
        String queryForSeats="select * from  seat_no_In_Tickets where Ticket_id=? and flightID=?";
        String queryOfFlightDetail="select * from Flight where Flight_id= ?";
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);
           
            
            while (resultSet.next()) {
            	Passenger p=new Passenger(resultSet.getString("Email"),resultSet.getString("Password"),resultSet.getDate("Bdate"),resultSet.getString("PassportNumber"),resultSet.getString("Gender"));
            	p.setID(resultSet.getInt("usderID"));
            	Ticket ticket =new  Ticket( p,resultSet.getInt("no_of_passenger"));
            	ticket.setTicketID(Integer.toString(resultSet.getInt("Ticket_id")));
            	ticket.setCost(resultSet.getFloat("Cost"));
            
            	PreparedStatement pStatementForFlights = connection.prepareStatement(queryForGetFlight);
            	pStatementForFlights.setInt(1, resultSet.getInt("Ticket_id"));
            	System.out.println(resultSet.getInt("Ticket_id"));
            	ResultSet resultSetForFlights = pStatementForFlights.executeQuery();
            	 LinkedList<Flight> flights=new LinkedList<>();
            	 LinkedList<String[]> extras=new LinkedList<>();
            	 LinkedList<String> extraOfFlight=new LinkedList<>();
             	LinkedList<Integer[]> SeatsOfFlight=new LinkedList<>();

            	while (resultSetForFlights.next()) {
            		flights.add(new Flight( resultSetForFlights.getInt("flightID")));
            		PreparedStatement pStatementForExtra = connection.prepareStatement(queryForExtra);
                	pStatementForExtra.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForExtra.setInt(2, resultSetForFlights.getInt("flightID"));
                	ResultSet resultSetForExtra = pStatementForExtra.executeQuery();
                	while(resultSetForExtra.next()) {
                		
                		String extra=resultSetForExtra.getString("extra");
                		extraOfFlight.add(extra);
                		
                	}
                	String[] forExtra=new String[extraOfFlight.size()];
                	for(int i=0; i<extraOfFlight.size();i++) {
                		forExtra[i]=extraOfFlight.get(i);
                	}
                	extras.add(forExtra);
                	extraOfFlight.clear();
                	PreparedStatement pStatementForSeats = connection.prepareStatement(queryForSeats);
                	pStatementForSeats.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForSeats.setInt(2, resultSetForFlights.getInt("flightID"));
                	ResultSet resultSetForseats = pStatementForSeats.executeQuery();
                	LinkedList<Integer> seats=new LinkedList<>();
                	while(resultSetForseats.next()) {
                		
                		Integer seat = new Integer(resultSetForseats.getInt("seatNo"));
                		seats.add(seat);
                		
                	}
                	Integer[] forseats=new Integer[seats.size()];
                	for(int i=0; i<seats.size();i++) {
                		forseats[i]=seats.get(i);
                	}
                	SeatsOfFlight.add(forseats);
                	seats.clear();
                	
            	}
            	ticket.setFlights(flights);
            	ticket.setExtras(extras);
            	ticket.setSeatNo(SeatsOfFlight);
            	tickets.add(ticket);
            	
            }
            PreparedStatement pStatementForFlightDetails = connection.prepareStatement(queryOfFlightDetail);
            FlightQueryBuilder  flightQueryBuilder=new FlightQueryBuilder();
            
            int counter=0;
            for(Ticket t:tickets) {
            	LinkedList<Flight> flights=new LinkedList<>();
            	for(Flight f:t.getFlights()) {
            		flights.add(flightQueryBuilder.getFlightByID(f.getFlightID()));
            	}
            	tickets.get(counter++).setFlights(flights);
            }
            
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
	
	
	public static ArrayList<Passenger> getTopTenUser() throws SQLException{
		String query = "select * ,sum(cost) As Total_cost from User as u INNER JOIN Ticket as t on t.usderID=u.Id group by u.Id  ORDER BY Total_cost DESC limit 5;";
		ArrayList<Passenger> passengers=new ArrayList<>();
		Connection connection = DB_Utils.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        
        while(resultSet.next()) {
        	Passenger p=new Passenger(resultSet.getString("Email"),resultSet.getString("Password"),resultSet.getDate("Bdate"),resultSet.getString("PassportNumber"),resultSet.getString("Gender"));
        	p.setTotalTicketCost((double)resultSet.getObject("Total_cost"));
        	passengers.add(p);
        }
		return passengers;
	}
	
	
	public static ArrayList<Ticket> getTicketOfUser(int id) throws SQLException{
		String query="select * from Ticket ,User where "+
				"User.Id=Ticket.usderID"+
				" and User.Id="+id;
		String queryForGetFlight="select * from  Flight_In_Tickets where Ticket_id=?";
        String queryForExtra="select * from  Flight_extra_In_Tickets where Ticket_id=? and flightID=?";
        String queryForSeats="select * from  seat_no_In_Tickets where Ticket_id=? and flightID=?";
		Connection connection = DB_Utils.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        ArrayList<Ticket> tickets = new ArrayList<>();
        try {

            ResultSet resultSet = statement.executeQuery(query);
           
            
            while (resultSet.next()) {
            	Ticket ticket =new  Ticket( new Passenger(resultSet.getString("Email"),resultSet.getString("Password"),resultSet.getDate("Bdate"),resultSet.getString("PassportNumber"),resultSet.getString("Gender")),resultSet.getInt("no_of_passenger"));
            	ticket.setTicketID(Integer.toString(resultSet.getInt("Ticket_id")));
            	ticket.setCost(resultSet.getFloat("Cost"));
            	PreparedStatement pStatementForFlights = connection.prepareStatement(queryForGetFlight);
            	pStatementForFlights.setInt(1, resultSet.getInt("Ticket_id"));
            	System.out.println(resultSet.getInt("Ticket_id"));
            	ResultSet resultSetForFlights = pStatementForFlights.executeQuery();
            	 LinkedList<Flight> flights=new LinkedList<>();
            	 LinkedList<String[]> extras=new LinkedList<>();
            	 LinkedList<String> extraOfFlight=new LinkedList<>();
             	LinkedList<Integer[]> SeatsOfFlight=new LinkedList<>();

            	while (resultSetForFlights.next()) {
            		flights.add(new Flight( resultSetForFlights.getInt("flightID")));
            		PreparedStatement pStatementForExtra = connection.prepareStatement(queryForExtra);
                	pStatementForExtra.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForExtra.setInt(2, resultSetForFlights.getInt("flightID"));
                	ResultSet resultSetForExtra = pStatementForExtra.executeQuery();
                	while(resultSetForExtra.next()) {
                		
                		String extra=resultSetForExtra.getString("extra");
                		extraOfFlight.add(extra);
                		
                	}
                	String[] forExtra=new String[extraOfFlight.size()];
                	for(int i=0; i<extraOfFlight.size();i++) {
                		forExtra[i]=extraOfFlight.get(i);
                	}
                	extras.add(forExtra);
                	extraOfFlight.clear();
                	PreparedStatement pStatementForSeats = connection.prepareStatement(queryForSeats);
                	pStatementForSeats.setInt(1, resultSet.getInt("Ticket_id"));
                	pStatementForSeats.setInt(2, resultSetForFlights.getInt("flightID"));
                	ResultSet resultSetForseats = pStatementForSeats.executeQuery();
                	LinkedList<Integer> seats=new LinkedList<>();
                	while(resultSetForseats.next()) {
                		
                		Integer seat = new Integer(resultSetForseats.getInt("seatNo"));
                		seats.add(seat);
                		
                	}
                	Integer[] forseats=new Integer[seats.size()];
                	for(int i=0; i<seats.size();i++) {
                		forseats[i]=seats.get(i);
                	}
                	SeatsOfFlight.add(forseats);
                	seats.clear();
                	
            	}
            	ticket.setFlights(flights);
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
	
//	public ArrayList<Ticket> getTicketOfFlight(int flightID) throws SQLException{
//		String query="select * from Flight_In_Tickets as f INNER JOIN Ticket as t on t.Ticket_id=f.Ticket_id  INNER JOIN User as u on t.usderID=u.Id  where  flightID= "+flightID;
//		Connection connection = DB_Utils.getDataSource().getConnection();
//        Statement statement = connection.createStatement();
//
//        ResultSet resultSet = statement.executeQuery(query);
//		return null;
//		
//	}
	
	
	//String data 2022-11-11 
	public ArrayList<Ticket> getTicketOnDay(String date) throws SQLException{
		
		String query="select * from Flight ,Flight_In_Tickets,Ticket ,User where "+
					"flightID=Flight_id and Ticket.Ticket_id=Flight_In_Tickets.Ticket_id"+
					"and User.Id=Ticket.usderID"+
					"where Flight.Departure ='"+date+"'";
		String queryForGetFlight="select * from  Flight_In_Tickets where Ticket_id=?";
        String queryForExtra="select * from  Flight_extra_In_Tickets where Ticket_id=? and flightID=?";
        String queryForSeats="select * from  seat_no_In_Tickets where Ticket_id=? and flightID=?";
		Connection connection = DB_Utils.getDataSource().getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);
        ResultSet resultSetForGetFlight = statement.executeQuery(queryForGetFlight);
        ArrayList<Ticket> tickets=new ArrayList<>();
        while (resultSet.next()) {
        	PreparedStatement pStatementForFlights = connection.prepareStatement(queryForGetFlight);
        	Ticket ticket =new Ticket(Integer.toString(resultSet.getInt("Ticket_id")),new Passenger(resultSet.getString("Email"),resultSet.getString("Password"),resultSet.getDate("Bdate"),resultSet.getString("PassportNumber"),resultSet.getString("Gender")),resultSet.getFloat("cost"),resultSet.getInt("PassportNumber"));
        	ResultSet resultSetForFlights = pStatementForFlights.executeQuery();
       	    LinkedList<Flight> flights=new LinkedList<>();
       	    LinkedList<String[]> extras=new LinkedList<>();
       	    LinkedList<String> extraOfFlight=new LinkedList<>();
        	LinkedList<Integer[]> SeatsOfFlight=new LinkedList<>();

       	while (resultSetForFlights.next()) {
       		flights.add(new Flight( resultSet.getInt("flightID")));
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
       	ticket.setFlights(flights);
       	ticket.setExtras(extras);
       	ticket.setSeatNo(SeatsOfFlight);
       	tickets.add(ticket);
       	
       }
       connection.close();
       statement.close();
       resultSet.close();
   
   return tickets;
     
		
	}
	
	

	
	public static void main(String[] args) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException, ParseException {
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
		LinkedList<Integer []> seatNo=new LinkedList<Integer []>();
		seatNo.add(y);
		Ticket ticket =new Ticket(p,6);
		ticket.setCost(1000);
		ticket.setExtras(extras);
		ticket.setFlights(flights);
		ticket.setSeatNo(seatNo);
		//ticket.setTicketID("4");
		//addTicket(ticket);
		//deleteTicket(ticket);
		//ArrayList<Ticket> t=getAll(0);
		//System.out.println("size :"+t.size());
		//Ticket t1=t.get(0);
		//Ticket t2=t.get(1);
		//System.out.println(t1.getCost());
		//System.out.println(t2.getCost());
		//ArrayList<Ticket> t=getTicketOfUser(3);
		//System.out.println("size"+t.size());
		getTopTenUser();
	}
	
	

}

package com.swe2023.model.Tickets_Data;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.signUpAndLogin.User;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.util.Date;
import java.util.LinkedList;


public class Ticket {
    public static final String DB_TABLE_NAME= "Ticket";
    public static final String DB_ID= "Ticket_no";
    public static final String DB_COST= "Cost";
    public static final String DB_USER= "Owner";


    private String ticketID;
    private Passenger user;
    private float cost;
    private int passengersNo;

    private LinkedList<String []> extras;
    private LinkedList<Flight> flights;
    private LinkedList<Integer []> seatNo;

    public Ticket(Passenger user,int passengersNo){
        this.user = user;
        this.passengersNo = passengersNo;
        // edit...
        this.flights = new LinkedList<>();
        this.extras = new LinkedList<>();
        this.seatNo = new LinkedList<>();
    }
    public Ticket(String ticketID,Passenger user,float cost,int passengersNo){
        this.user = user;
        this.passengersNo = passengersNo;
        this.ticketID=ticketID;
        this.cost=cost;
        // edit...
        this.flights = new LinkedList<>();
        this.extras = new LinkedList<>();
        this.seatNo = new LinkedList<>();
    }

    
    
    
    public Ticket(String ticketID) {
		super();
		this.ticketID = ticketID;
	}
	public float getCost() {
        return cost;
    }
    
    

    public Passenger getUser() {
		return user;
	}

	public int getPassengersNo() {
        return passengersNo;
    }

    public String getTicketID() {
        return ticketID;
    }


    public LinkedList<Flight> getFlights() {
        return flights;
    }

    public LinkedList<Integer[]> getSeatNo() {
        return seatNo;
    }

    public LinkedList<String[]> getExtras() {
        return extras;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setExtras(LinkedList<String[]> extras) {
        this.extras = extras;
    }

    public void setFlights(LinkedList<Flight> flights) {
        this.flights = flights;
    }

    public void setPassengersNo(int passengersNo) {
        this.passengersNo = passengersNo;
    }

    public void setSeatNo(LinkedList<Integer[]> seatNo) {
        this.seatNo = seatNo;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setUser(Passenger user) { this.user = user; }
}

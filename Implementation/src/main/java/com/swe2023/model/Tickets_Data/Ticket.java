package com.swe2023.model.Tickets_Data;

import com.swe2023.model.signUpAndLogin.Passenger;


public class Ticket {
    public static final String DB_TABLE_NAME= "Ticket";
    public static final String DB_ID= "Ticket_no";
    public static final String DB_COST= "Cost";
    public static final String DB_USER= "Owner";


    int ticketID;
    Passenger user;
    int cost;
    String extra[];
    String date;
    public void calculateCost(  String extra[]){
        // calculation here
    }

}

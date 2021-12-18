package com.swe2023.model.Planes_Data;

import com.swe2023.model.Tickets_Data.Ticket;

public class Flight {

    public static final String DB_TABLE_NAME= "Flight";
    public static final String DB_ID= "Flight_id";
    public static final String DB_DATE= "Arrival";
    public static final String DB_SOURCE= "Source";
    public static final String DB_DESTINATION= "Destination";


    String flightID;
    String source;
    String destination;
    Ticket[] tickets;
    String date;
}

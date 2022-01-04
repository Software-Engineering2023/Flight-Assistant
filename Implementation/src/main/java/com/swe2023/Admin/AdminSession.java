package com.swe2023.Admin;

import com.swe2023.model.Planes_Data.*;
import com.swe2023.model.Tickets_Data.AdminTicketsManager;

import java.util.ArrayList;
import java.util.List;

public class AdminSession {

    private static AdminSession session;
    private final Manager manager;
    private AdminTicketsManager ticketManager;

    private AdminSession(){
        System.out.println("111111");
        manager= new Manager();
    }

    public static AdminSession getSession() {
        if(session == null)
            session= new AdminSession();
        return  session;
    }

    /**
     * @param port to be added
     * @return false if error occured.
     */
    public boolean addNewAirPort(Port port){
        return manager.getPortManager().addNewPort(port);
    }

    public boolean addNewPlane(Plane plane){
        return manager.getPlaneManager().addPlane(plane);
    }

    public String addNewFlight(Flight flight){
        return manager.getFlightManager().addNewFlight(flight);
    }

    public ArrayList<Port> loadPortsFromDatabase(){
        return manager.getPortManager().loadPortsFromDataBase();
    }

    public ArrayList<Plane> loadPlanesFromDataBase(){
        return manager.getPlaneManager().loadPlanesFromDataBase();
    }

    public boolean deletePort(Port port){
        return manager.getPortManager().deletePort(port);
    }
    public boolean deletePlane(Plane plane){
        return manager.getPlaneManager().deletePlane(plane);
    }

    public void setPlaneToShowFlights(Plane plane){manager.setPlaneToShowFlights(plane);}

    public ArrayList<Flight> loadFlightsFromDataBase() {return manager.loadFlightsFromDataBase(); }

    public boolean deleteFlight(Flight flight){
        return manager.getFlightManager().deleteFlight(flight);
    }
}

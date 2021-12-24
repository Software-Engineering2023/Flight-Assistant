package com.swe2023.Admin;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.PlaneManager;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Tickets_Data.AdminTicketsManager;

import java.util.ArrayList;
import java.util.List;

public class AdminSession {

    private static AdminSession session;
    private final PlaneManager planeManager;
    private AdminTicketsManager ticketManager;

    private AdminSession(){
        System.out.println("111111");

        planeManager= new PlaneManager();
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
        return planeManager.addNewPort(port);
    }

    public boolean addNewPlane(Plane plane){
        return planeManager.addPlane(plane);
    }

    public String addNewFlight(Flight flight){
        return planeManager.addNewFlight(flight);
    }

    public ArrayList<Port> loadPortsFromDatabase(){
        return planeManager.loadPortsFromDataBase();
    }

    public ArrayList<Plane> loadPlanesFromDataBase(){
        return planeManager.loadPlanesFromDataBase();
    }

    public boolean deletePort(Port port){
        return planeManager.deletePort(port);
    }
    public boolean deletePlane(Plane plane){
        return planeManager.deletePlane(plane);
    }

    public void setPlaneToShowFlights(Plane plane){planeManager.setPlaneToShowFlights(plane);}

    public ArrayList<Flight> loadFlightsFromDataBase() {return planeManager.loadFlightsFromDataBase(); }

    public boolean deleteFlight(Flight flight){
        return planeManager.deleteFlight(flight);
    }
}

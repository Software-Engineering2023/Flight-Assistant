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
        planeManager= new PlaneManager();
    }

    public static AdminSession getSession() {
        return session==null ? new AdminSession():session;
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

    public boolean addNewFlight(Flight flight){
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

}

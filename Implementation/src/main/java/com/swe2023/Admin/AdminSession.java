package com.swe2023.Admin;

import com.swe2023.model.Planes_Data.PlaneManager;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.Tickets_Data.AdminTicketsManager;

import java.util.List;

public class AdminSession {

    private static AdminSession session;
    private PlaneManager planeManager;
    private AdminTicketsManager ticketManager;

    private AdminSession(){}

    public static AdminSession getSession() {
        return session==null ? new AdminSession():session;
    }

    /**
     *
     * @param port to be added
     * @return false if error occured.
     */
    public boolean addNewAirPort(Port port){
        //TODO Need to be connected to DB
        return true;
    }

    public List<Port> loadPortsFromDatabase(){
        return null;
    }

    public void deletePort(Port port){

    }
}

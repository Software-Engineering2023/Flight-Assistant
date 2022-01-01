package com.swe2023.User;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.PlaneManager;
import com.swe2023.model.Planes_Data.PlanesInformation;
import com.swe2023.model.Planes_Data.Port;
import com.swe2023.model.signUpAndLogin.Passenger;

import java.util.List;

/**
 * This class is responsible for managing data between Backend and Frontend.
 * This class is only responsible for Ordinary Users.
 */
public class UserSession {

    private static UserSession session;
    private final PlanesInformation planesInformation;
    /* We need to add a ticket manager */
    private Passenger user; /* Used to book tickets directly and to save current user's progress */

    private UserSession(){
        planesInformation= new PlaneManager();
    }
    public void setUser(Passenger user){
        this.user= user;
    }

    public List<Port> loadAirports(){
        return planesInformation.loadPortsFromDataBase();
    }

    public List<Flight> searchFlights(Port source, Port destination){
        return planesInformation.searchFlights(source,destination);
    }

    public static UserSession getSession(){
        if(session == null)
            session= new UserSession();
        return session;
    }







}

package com.swe2023.model.Planes_Data;

import java.util.ArrayList;
import java.util.List;

public class Manager implements PlanesInformation {

    private final PlaneManager planeManager;
    private final FlightManager flightManager;
    private final PortManager portManager;
    private Plane planeToShowFlights= null;

    public Manager(){
        planeManager= new PlaneManager();
        flightManager= new FlightManager();
        portManager= new PortManager();
    }

    public PlaneManager getPlaneManager() {
        return planeManager;
    }

    public FlightManager getFlightManager() {
        return flightManager;
    }

    public PortManager getPortManager() {
        return portManager;
    }
    public void setPlaneToShowFlights(Plane planeToShowFlights) {
        this.planeToShowFlights = planeToShowFlights;
    }

    @Override
    public ArrayList<Flight> loadFlightsFromDataBase(){
        if(planeToShowFlights==null)
            return flightManager.loadAllFlights();
        return  planeManager.getPlaneFlights(planeToShowFlights);
    }

    @Override
    public List<Port> loadPortsFromDataBase() {
        return portManager.loadPortsFromDataBase();
    }

    @Override
    public List<Flight> searchFlights(Port source, Port destination) {
        return flightManager.searchFlights(source,destination);
    }

}

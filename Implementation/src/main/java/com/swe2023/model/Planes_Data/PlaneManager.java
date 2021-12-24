package com.swe2023.model.Planes_Data;


import com.swe2023.Proxy.AirportQueryBuilder;
import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.Proxy.PlaneQueryBuilder;

import java.util.ArrayList;
import java.util.Date;

public class PlaneManager {
    Plane[] planesShown;
    Port[] portsShown;

    Plane planeToShowFlights;

    private PlaneQueryBuilder pqb;
    private FlightQueryBuilder fqb;
    private AirportQueryBuilder aqb;

    public PlaneManager() {
        pqb = new PlaneQueryBuilder();
        fqb = new FlightQueryBuilder();
        aqb = new AirportQueryBuilder();
        planeToShowFlights = null;
    }

     public ArrayList<Plane> loadPlanesFromDataBase(){
         return pqb.getAll();
    }
    public boolean addPlane(Plane plane){
        if(isPlane(plane)) {
            boolean returend = pqb.addPlane(plane);
            return returend;
        }
        return false;
    }

    public ArrayList<Flight> loadFlightsFromDataBase(){
        if(planeToShowFlights==null)
            return fqb.getAll();
        return  pqb.getPlaneFlights(planeToShowFlights);
    }

    public boolean updatePlaneStatus(Plane plane ,String status){
        if(isPlane(plane)) {
            plane.setStatus(status);
            boolean returend = pqb.updatePlane(plane);
            return returend;
        }
        return false;

    }
    public void stopPlaneFlights(Plane plane){
        // ToDo.
//        plane.flights=null;
        //call database to update here
    }

    // Next MileStone.
    public boolean changePlaneFlight(Date date ,ArrayList<Flight> newFlights){
        if(date != null) {
            boolean deleteflight = fqb.deleteFlight(date);
            boolean addFlights = fqb.addFlights(newFlights);
            return deleteflight && addFlights;
        }
        return false;
    }

    public int getPlaneIncome(Plane plane){
         return plane.getIncome();
    }

    public boolean isFlight(Port source , Port destination , Date date, Plane plane){
        if(isPort(source)&&isPort(destination)&&date!= null &&isPlane(plane)) {
            return true;
        }
        return false;
    }

    public boolean addNewFlight(Flight flight) {
        if(isFlight(flight.getSource(),flight.getSource(),flight.getDate(),flight.getPlane())) {
            return fqb.addFlight(flight);
        }
        return  false;
    }

    // edited because : what is this? and why does it return Port?
    public boolean addNewPort(Port airport) {
        if(isPort(airport)) {
            return aqb.addAirport(airport);
        }
        return  false;
    }

    public ArrayList<Port> loadPortsFromDataBase(){
         return aqb.getAll();
    }

    public boolean deletePort(Port port){
        if(isPort(port))
            return aqb.deleteAirport(port.getCode());
        return false;
    }

    public boolean deletePlane(Plane plane){
        boolean returned=pqb.deletePlane(plane.getId());
        return returned;
    }

    private boolean isPlane(Plane plane){
        if(plane.getNo_of_seats() != 0 && plane.getType() != null && plane.getStatus() != null) {
            return true;
        }
        return false;
    }

    private boolean isPort(Port port){
        return port.getCode().length() != 0 && port.getCountry().length() != 0
                && port.getCity().length() != 0 && port.getName().length() != 0;
    }

    public void setPlaneToShowFlights(Plane planeToShowFlights) {
        this.planeToShowFlights = planeToShowFlights;
    }

    public boolean deleteFlight(Flight flight){
        if(isFlight(flight.getSource(), flight.getDestination(), flight.getDate(), flight.getPlane()))
            return fqb.deleteFlight(flight.getFlightID());
        return false;
    }

}

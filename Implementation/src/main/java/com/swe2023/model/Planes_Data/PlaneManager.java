package com.swe2023.model.Planes_Data;


import com.swe2023.Proxy.AirportQueryBuilder;
import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.Proxy.PlaneQueryBuilder;

import java.util.ArrayList;
import java.util.Date;

public class PlaneManager {
    Plane[] planesShown;
    Port[] portsShown;

    private PlaneQueryBuilder pqb;
    private FlightQueryBuilder fqb;
    private AirportQueryBuilder aqb;

    public PlaneManager() {
        pqb = new PlaneQueryBuilder();
        fqb = new FlightQueryBuilder();
        aqb = new AirportQueryBuilder();
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

    public boolean updatePlaneStatus(Plane plane ,String status){
        if(isPlane(plane)) {
            plane.setStatus(status);
            boolean returend = pqb.updatePlane(plane);
            return returend;
        }
        return false;

    }
    public void stopPlaneFlights(Plane plane){
         // what should do here xd
        // ToDo.
        // fe3lan what should we do here.
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

    public boolean addNewFlight(Port source , Port destination , Date deptDate, Date arrivalDate, Plane plane){
        if(isPort(source)&&isPort(destination)&&deptDate!= null &&arrivalDate!= null &&isPlane(plane)) {
           boolean returned= fqb.addFlight(new Flight(source, destination, deptDate, arrivalDate, plane));
            return returned;
        }
        return false;
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
        return aqb.deleteAirport(port.getCode());
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
        if(port.getCode()!= null &&port.getCountry()!= null &&port.getCity()!= null&&port.getName()!= null )
            return true;
        return false;
    }





}

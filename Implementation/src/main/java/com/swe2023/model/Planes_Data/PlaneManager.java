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
         // call from database here to laod the planes into the variable palnes
         return pqb.getAll();
    }

    public void updatePlaneStatus(Plane plane ,String status){
         plane.setStatus(status);
         pqb.updatePlane(plane);
         //send changed status to data base here
    }
    public void stopPlaneFlights(Plane plane){
         // what should do here xd
        // ToDo.
        // fe3lan what should we do here.
//        plane.flights=null;
        //call database to update here


    }

    // Next MileStone.
    public void changePlaneFlight(Date date ,ArrayList<Flight> newFlights){
        fqb.deleteFlight(date);
        fqb.addFlights(newFlights);
         // ToDo.
//         for (int i=0 ; i<plane.flights.length;i++){
//             if(plane.flights[i].flightID.equals(oldFlight.flightID)){
//                 plane.flights[i]=newFlight;
//                 //update in dataBase
//                 break;
//             }
//         }


    }

    public int getPlaneIncome(Plane plane){
         return plane.getIncome();
    }

    public void addNewFlight(Port source , Port destination , Date deptDate, Date arrivalDate, Plane plane){
        fqb.addFlight(new Flight(source, destination, deptDate, arrivalDate, plane));
    }

    // edited because : what is this? and why does it return Port?
    public void addNewPort(String code, String country, String city, String name, int longitude, int latitude) {
        Port airport = new Port(code, country, city, name, longitude, latitude);
        aqb.addAirport(airport);
    }

    public ArrayList<Port> loadPortsFromDataBase(){
         return aqb.getAll();
    }

    public void deletePort(Port port){
         aqb.deleteAirport(port.getCode());
    }

    public void deletePlane(Plane plane){
         pqb.deletePlane(plane.getId());
    }





}

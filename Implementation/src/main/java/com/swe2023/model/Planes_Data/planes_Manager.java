package com.swe2023.model.Planes_Data;


public class planes_Manager {
    Plane[] planesShown;
    Port[] portsShown;

     public Plane[]  loadPlanesFromDataBase(){
         // call from database here to laod the planes into the variable palnes
         Plane[] planesShown = new Plane[0];
         return planesShown;
    }

    public void updatePlaneStatus(Plane plane ,String status){
         plane.setStatus(status);
         //send changed status to data base here
    }
    public void stopPlaneFlights(Plane plane){
         // what should do here xd
        // ToDo.
        // fe3lan what should we do here.
//        plane.flights=null;
        //call database to update here
    }

    public void changePlaneFlight(Plane plane , Flight oldFlight, Flight newFlight){
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

    public Flight addNewFlight(String source , String destination , String date){
         // ToDo.
        // Should source and destination be ports.
        // should specify the plane.
        // should specify departure and arrival dates.

//         Flight flight = new Flight();
//         flight.source=source;
//         flight.destination=destination;
//         flight.date= date;
//         //update the dataBase
//        return flight;
        return null;
    }

    // edited because : what is this? and why does it return Port?
    public Port addNewPort(String code, String country, String city, String name, int longitude, int latitude) {
//         Port port = new Port();
//         port.name=name;
//         port.ID=ID;
//         port.longitude =X;
//         port.latitude =Y;
        Port airport = new Port(code, country, city, name, longitude, latitude);

         // call database here
         return airport;
    }

    public Port[]  loadPortsFromDataBase(){
         Port[] portsShown = new Port[0];
         // call dataBase to fill data here
        return portsShown;
    }

    public void deletePort(Port port){
         // call dataBase to delte this port
    }

    public void deletePlane(Plane plane){
         //call datBase
    }





}

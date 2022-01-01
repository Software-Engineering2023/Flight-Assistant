package com.swe2023.model.Planes_Data;


import com.swe2023.Proxy.PlaneQueryBuilder;
import java.util.ArrayList;
import static com.swe2023.model.Planes_Data.Plane.isPlane;

public class PlaneManager{


    private PlaneQueryBuilder pqb;

    public PlaneManager() {
        pqb = new PlaneQueryBuilder();
    }

     public ArrayList<Plane> loadPlanesFromDataBase(){
         return pqb.getAll();
    }
    public boolean addPlane(Plane plane){
        if(isPlane(plane)) {
            return pqb.addPlane(plane);
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
        // ToDo.
//        plane.flights=null;
        //call database to update here
    }

    public int getPlaneIncome(Plane plane){
         return plane.getIncome();
    }

    public boolean deletePlane(Plane plane){
        boolean returned=pqb.deletePlane(plane.getId());
        return returned;
    }

    public ArrayList<Flight> getPlaneFlights(Plane planeToShowFlights) {
        return pqb.getPlaneFlights(planeToShowFlights);
    }
}

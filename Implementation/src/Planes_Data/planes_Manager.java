package Planes_Data;


public class planes_Manager {
    Plane[] planesShown;
    Port[] portsShown;

     public Plane[]  loadPlanesFromDataBase(){
         // call from database here to laod the planes into the variable palnes
         Plane[] planesShown = new Plane[0];
         return planesShown;
    }

    public void updatePlaneStatus(Plane plane ,String status ){
         plane.status=status;
         //send changed status to data base here
    }
    public void stopPlaneFlights(Plane plane){
         // what should do here xd
        plane.flights=null;
        //call database to update here
    }

    public void changePlaneFlight(Plane plane , Flight oldFlight, Flight newFlight){
         for (int i=0 ; i<plane.flights.length;i++){
             if(plane.flights[i].flightID.equals(oldFlight.flightID)){
                 plane.flights[i]=newFlight;
                 //update in dataBase
                 break;
             }
         }
    }

    public int getPlaneIncome(Plane plane){
         return plane.income;
    }

    public Flight addNewFlight(String source , String destination , String date){
         Flight flight = new Flight();
         flight.source=source;
         flight.destination=destination;
         flight.date= date;
         //update the dataBase
        return flight;
    }

    public Port addNewPort(String name , String ID,int X, int Y){
         Port port = new Port();
         port.name=name;
         port.ID=ID;
         port.xLocation=X;
         port.yLocation=Y;
         // call database here
         return port;
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

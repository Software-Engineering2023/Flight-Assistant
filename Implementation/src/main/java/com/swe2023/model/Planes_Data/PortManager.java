package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.AirportQueryBuilder;

import java.util.ArrayList;

import static com.swe2023.model.Planes_Data.Port.isPort;

public class PortManager {

    private AirportQueryBuilder aqb;

    public PortManager (){
        aqb= new AirportQueryBuilder();
    }

    // edited because : what is this? and why does it return Port?
    public boolean addNewPort(Port airport) {
        if(isPort(airport)) {
            return aqb.addAirport(airport);
        }
        return  false;
    }

    public boolean deletePort(Port port){
        if(isPort(port))
            return aqb.deleteAirport(port.getCode());
        return false;
    }

    public ArrayList<Port> loadPortsFromDataBase(){
        return aqb.getAll();
    }




}

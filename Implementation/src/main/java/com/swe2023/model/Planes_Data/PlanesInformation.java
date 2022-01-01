package com.swe2023.model.Planes_Data;

import java.util.List;

public interface PlanesInformation {
    List<Flight> loadFlightsFromDataBase();
    List<Port> loadPortsFromDataBase();
    List<Flight> searchFlights(Port source, Port destination);
}

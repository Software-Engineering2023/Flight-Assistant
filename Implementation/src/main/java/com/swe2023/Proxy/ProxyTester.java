package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Port;
import org.junit.Test;

public class ProxyTester {

    private AirportQueryBuilder aqb = new AirportQueryBuilder();
    private PlaneQueryBuilder pqb = new PlaneQueryBuilder();
    private FlightQueryBuilder fqb = new FlightQueryBuilder();

    @Test
    public void testAddAirport() {
        Port airport = new Port("77777", "France", "Paris", "Airport_77777",
                55, 66);
        aqb.addAirport(airport);

    }
}

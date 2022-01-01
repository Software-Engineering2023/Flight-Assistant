package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class ProxyTester {

    private AirportQueryBuilder aqb = new AirportQueryBuilder();
    private PlaneQueryBuilder pqb = new PlaneQueryBuilder();
    private FlightQueryBuilder fqb = new FlightQueryBuilder();

    @Test
    public void testAddAirport() {
        Port airport = new Port("99999", "UAE", "Dubai", "Airport_99999",
                53, 67);
        if (aqb.addAirport(airport)) {
            HashMap<String, Object> criterias = new HashMap<>();
            criterias.put("Airport_code", airport.getCode());
            Port testedAirPort = aqb.search(criterias).get(0);

            assertEquals(airport.getCode(), testedAirPort.getCode());
            assertEquals(airport.getCountry(), testedAirPort.getCountry());
            assertEquals(airport.getCity(), testedAirPort.getCity());
            assertEquals(airport.getName(), testedAirPort.getName());
            assertEquals(airport.getLongitude(), testedAirPort.getLongitude());
            assertEquals(airport.getLatitude(), testedAirPort.getLatitude());
        }
    }

    @Test
    public void testDeleteAirport() {
        String code = "44444";
        ArrayList<Port> airports = aqb.getAll();
        if (airports.stream().anyMatch((airport) -> airport.getCode().equals(code))) {
            if (aqb.deleteAirport(code)) {
                HashMap<String, Object> criterias = new HashMap<>();
                criterias.put("Airport_code", code);
                ArrayList<Port> testedData = aqb.search(criterias);
                assertTrue(testedData.isEmpty());
            }
        }
    }

    @Test
    public void testAddPlane() {
        Plane plane = new Plane(7, "Y-MT12", "Active", 500, 0);
        if (pqb.addPlane(plane)) {
            HashMap<String, Object> criterias = new HashMap<>();
            criterias.put("Plane_id", plane.getId());
            Plane testedPlane = pqb.search(criterias).get(0);

            assertEquals(plane.getId(), testedPlane.getId());
            assertEquals(plane.getType(), testedPlane.getType());
            assertEquals(plane.getStatus(), testedPlane.getStatus());
            assertEquals(plane.getNo_of_seats(), testedPlane.getNo_of_seats());
            assertEquals(plane.getIncome(), testedPlane.getIncome());
        }
    }



    @Test
    public void testDeletePlane() {
        int id = 6;
        ArrayList<Plane> planes = pqb.getAll();

        if (planes.stream().anyMatch((plane) -> plane.getId() == id)) {
            if (pqb.deletePlane(id)) {
                HashMap<String, Object> criterias = new HashMap<>();
                criterias.put("Plane_id", id);
                ArrayList<Plane> testedData = pqb.search(criterias);
                assertTrue(testedData.isEmpty());
            }
        }
    }

    @Test
    public void testGetPlaneFlights() {
        Plane plane = new Plane(7, "Y-MT12", "Active", 500, 0);
        if (pqb.addPlane(plane)) {
            ArrayList<Flight> flights = new ArrayList<>();
            flights.add(new Flight(9, new Port("11111"), new Port("33333"),
                    new Date(), plane));
            flights.add(new Flight(10, new Port("22222"), new Port("11111"),
                    new Date(), plane));
            if (fqb.addFlights(flights)) {
                ArrayList<Flight> testedData = pqb.getPlaneFlights(plane);
                for (Flight test : testedData) {
                    for (Flight actual: flights) {
                        if (test.getFlightID() == actual.getFlightID()) {
                            assertEquals(test.getSource().getCode(), actual.getSource().getCode());
                            assertEquals(test.getDestination().getCode(), actual.getDestination().getCode());
                            String testDate = test.getDate().toString().substring(0, 13);
                            String actualDate = actual.getDate().toString().substring(0, 13);
                            assertEquals(testDate, actualDate);
                            assertEquals(test.getPlane().getId(), actual.getPlane().getId());
                        }
                    }
                }
            }
        }
    }

//    @Test
//    public void testAddFlight() {
//        Flight flight = new Flight(11, new Port("77777"), new Port("44444"),
//                new Date(), new Plane(3));
//        if (fqb.addFlight(flight)) {
//            Flight testFlight = fqb.searchFlight(flight.getFlightID());
//            assertNull(testFlight);
//        }
//    }

    @Test
    public void testAddFlights() {

    }

    @Test
    public void testDeleteFlight() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-03-23 06:30:00");
            if (fqb.getAll().stream().anyMatch((flight) -> flight.getDate().compareTo(date) < 0)) {
                if (fqb.deleteFlight(date)) {
                    fqb.getAll().stream().forEach((flight) ->
                            assertTrue(flight.getDate().compareTo(date) >= 0));
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}

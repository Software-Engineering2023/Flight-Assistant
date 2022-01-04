package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.DB_Utils;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PlaneManagerTest {


    private final List<Port> ports= new ArrayList<>();
    private final List<Flight> flights= new ArrayList<>();

    private static final PlaneManager manager= new PlaneManager();

    @BeforeClass
    public static void deletePlanes() throws SQLException {
        Connection connection = DB_Utils.getDataSource().getConnection();
        Statement st= connection.createStatement();
        st.execute("DELETE FROM Plane");
        st.close();
        connection.close();
    }

    @Test
    public void testPlanes(){
        addPlane();
        loadPlanesFromDataBase();
        deletePlane();
        getPlaneFlights();
    }


    private void addPlane() {
        Plane plane= new Plane(0,"Buing","Good",15,654);
        boolean passed1=manager.addPlane(plane);
        assertTrue(passed1);

        plane= new Plane(0, "Xtran", "Perfect", 10,254);
        boolean passed2= !manager.addPlane(plane);
        assertTrue(passed2);

        plane= new Plane(2, null, null, 45,25);
        boolean passed3= !manager.addPlane(plane);
        assertTrue(passed3);
    }
    private void loadPlanesFromDataBase() {
        List<Plane> planes= manager.loadPlanesFromDataBase();
        assertEquals(1,planes.size());

        Plane plane= new Plane(2,"Another One", "Well", 147,658);
        manager.addPlane(plane);

        planes= manager.loadPlanesFromDataBase();
        assertEquals(2,planes.size());
    }

    private void deletePlane() {
        Plane plane= new Plane(0);
        boolean passed1= manager.deletePlane(plane);
        assertTrue(passed1);

        assertEquals(1, manager.loadPlanesFromDataBase().size());

        plane= new Plane(0);
        assertTrue(manager.deletePlane(plane));
    }


    private void getPlaneFlights(){
        addPorts();
        addFlights();
        Plane plane= manager.loadPlanesFromDataBase().get(0);
        assertEquals(1,manager.getPlaneFlights(plane).size());
        Flight flight= manager.getPlaneFlights(plane).get(0);
        assertEquals(10,flight.getFlightID());
        assertEquals(ports.get(0).getCode(), flight.getSource().getCode());
        assertEquals(ports.get(1).getCode(), flight.getDestination().getCode());
    }
    private void addPorts(){
        Port source= new Port("123456","Egypt","Alexandria","Borg El Arab",35,45);
        Port destination= new Port("1234567","Egypt","Cairo","Cairo Airport",35,45);
        PortManager mn= new PortManager();
        mn.addNewPort(source);
        mn.addNewPort(destination);
        ports.add(source);
        ports.add(destination);
    }
    private void addFlights(){
        Plane plane= manager.loadPlanesFromDataBase().get(0);
        Date date= new Date();
        date.setTime(System.currentTimeMillis()+10000);
        Flight flight= new Flight(10,ports.get(0),ports.get(1),date,plane);
        new FlightManager().addNewFlight(flight);
    }
}
package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.DB_Utils;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class FlightManagerTest {
    private static final Manager manager= new Manager();
    private static Port[] ports;
    private static Plane[] planes;

    @Before
    public void setUp() throws Exception {
        System.out.println("Called");
        Connection connection= DB_Utils.getDataSource().getConnection();
        Statement st = connection.createStatement();
        st.execute("DELETE FROM Flight");
        st.execute("DELETE FROM Airport");
        st.execute("DELETE FROM Plane");
        st.close();
        connection.close();
        ports= new Port[]{new Port("AA1","AX","XX","A",10,15),
                new Port("AA2","BX","XX","AB",10,15),
                new Port("BB1","CX","XX","ABB",10,15),
                new Port("BB2","CX","XX","ABBB",10,15)};
        planes= new Plane[]{new Plane(1,"A","AA",10,10),new Plane(2,"A","AA",10,10),
                new Plane(3,"A","AA",10,10),new Plane(4,"A","AA",10,10)};
        for(Port port :ports)
            manager.getPortManager().addNewPort(port);

        for(Plane plane: planes)
            manager.getPlaneManager().addPlane(plane);

        Date date= new Date();
        date.setTime(System.currentTimeMillis()+ 654789L);
        System.out.println(manager.getFlightManager().addNewFlight(new Flight(1,ports[0],ports[2],date,planes[0])));
        manager.getFlightManager().addNewFlight(new Flight(2,ports[1],ports[2],date,planes[0]));
        manager.getFlightManager().addNewFlight(new Flight(3,ports[0],ports[3],date,planes[0]));
        manager.getFlightManager().addNewFlight(new Flight(4,ports[1],ports[3],date,planes[0]));
        manager.getFlightManager().addNewFlight(new Flight(5,ports[2],ports[3],date,planes[2]));
    }

    @Test
    public void searchFlights() {
        List<List<Flight>> flights=manager.getFlightManager().searchFlights(ports[0],ports[1],null,10);
        assertEquals(0,flights.size());
        flights=manager.getFlightManager().searchFlights(ports[1],ports[3],null,10);
        assertEquals(2,flights.size());
        for (List<Flight> flight: flights)
            System.out.println(flight);

    }



}
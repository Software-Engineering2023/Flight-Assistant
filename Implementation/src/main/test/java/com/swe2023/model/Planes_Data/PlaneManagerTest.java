package com.swe2023.model.Planes_Data;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;

public class PlaneManagerTest {
    PlaneManager manager = new PlaneManager();
    Plane plane1 = new Plane(1,"Piston ","Active",100,999999);
    Plane plane2 = new Plane(2,"Turboprop  ","Suspended",0,0);
    Port port1 =new Port("12345","Egypt","Alex","AlexPort",12,156);
    Port port2 =new Port("78965","USA","Newyork","NewyorkPort",15748,69);
    Port port3 =new Port("","France","Paris","ParisPort",787,4564);
    Port port4 =new Port("365948","Germany","Berlin","BerlinPort",656,787);
    LocalDate  myDateObj = LocalDate.now();
    ZoneId defaultZoneId = ZoneId.systemDefault();
    Flight flight1 =new Flight(1,port1,port2, Date.from(myDateObj.atStartOfDay(defaultZoneId).toInstant()),plane1);
    Flight flight2 =new Flight(2,port3,port4, null,plane2);
    @Test
    public void testUpdatePlaneStatus(){
        boolean result1= manager.updatePlaneStatus(plane1,"Suspended");
        boolean result2=manager.updatePlaneStatus(plane2,"Active");
        assertTrue(result1);
        assertFalse(result2);

    }
    @Test
    public void testGetPlaneIncome(){
        int result1= manager.getPlaneIncome(plane1);
        int result2=manager.getPlaneIncome(plane2);
        assertEquals(999999,result1);
        assertEquals(0,result2);

    }

    @Test
    public void testAddNewFlight(){
        boolean result1= manager.addNewFlight(flight1);
        boolean result2=manager.addNewFlight(flight2);
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testAddNewPort(){
        boolean result1= manager.addNewPort(port1);
        boolean result2=manager.addNewPort(port3);
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testDeletePlane(){
        boolean result1= manager.deletePlane(plane1);
        boolean result2=manager.deletePlane(plane2);
        assertTrue(result1);
        assertFalse(result2);
    }

    @Test
    public void testDeleteFlight(){
        boolean result1= manager.deleteFlight(flight1);
        boolean result2=manager.deleteFlight(flight2);
        assertTrue(result1);
        assertFalse(result2);
    }

}
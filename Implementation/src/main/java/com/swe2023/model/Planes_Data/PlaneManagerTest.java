package com.swe2023.model.Planes_Data;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.*;

public class PlaneManagerTest {


    static PlaneManager manager = new PlaneManager();
    static Plane plane1 = new Plane(1,"Piston ","Active",100,999999);
    static Plane plane2 = new Plane(2,"Turboprop  ","Suspended",0,0);
    static Port port1 =new Port("12345","Egypt","Alex","AlexPort",12,156);
    static Port port2 =new Port("78965","USA","Newyork","NewyorkPort",15748,69);
    static Port port3 =new Port("","France","Paris","ParisPort",787,4564);
    static Port port4 =new Port("365948","Germany","Berlin","BerlinPort",656,787);
    static LocalDate  myDateObj = LocalDate.of(2022,2,1);
    static ZoneId defaultZoneId = ZoneId.systemDefault();
    static Flight flight1 =new Flight(1,port1,port2, Date.from(myDateObj.atStartOfDay(defaultZoneId).toInstant()),plane1);
    static Flight flight2 =new Flight(2,port3,port4, null,plane2);

    @Test
    public void testAddNewPort(){
        boolean result1 = manager.addNewPort(port1);
        boolean result2 = manager.addNewPort(port3);
        assertTrue(result1);
        assertFalse(result2);
    }
    @Test
    public void testAddNewPlane(){
        manager.addNewPort(port2);
        manager.addNewPort(port4);
        boolean result1= manager.addPlane(plane1);
        boolean result2=manager.addPlane(plane2);
        assertTrue(result1);
        assertFalse(result2);
    }


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

        String result1= manager.addNewFlight(flight1);
        String result2=manager.addNewFlight(flight2);
        assertEquals("ok",result1);
        assertEquals("not valid flight",result2);
    }


    @Test
    public void testDeletePlane(){
        boolean result1= manager.deletePlane(plane1);
//        boolean result2=manager.deletePlane(plane2);
        assertTrue(result1);
//        assertFalse(result2);
    }

    @Test
    public void testDeleteFlight(){
        boolean result1= manager.deleteFlight(flight1);
        boolean result2=manager.deleteFlight(flight2);
        assertTrue(result1);
        assertFalse(result2);
    }

}
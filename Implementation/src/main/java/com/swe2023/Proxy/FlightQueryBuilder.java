package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FlightQueryBuilder {


    public boolean addFlight(Flight flight) {
        String query = "insert into Flight values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, flight.getFlightID());
            pStatement.setDate(2, (java.sql.Date) flight.getDate());
            pStatement.setString(3, flight.getSource().getCode());
            pStatement.setString(4, flight.getDestination().getCode());
            pStatement.setInt(5, flight.getPlane().getId());
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addFlights(ArrayList<Flight> flights) {
        String query = "insert into Flight values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            for (Flight flight : flights) {
                pStatement.setInt(1, flight.getFlightID());
                pStatement.setTimestamp(2, new Timestamp(flight.getDate().getTime()));
                pStatement.setString(3, flight.getSource().getCode());
                pStatement.setString(4, flight.getDestination().getCode());
                pStatement.setInt(5, flight.getPlane().getId());
                pStatement.execute();
            }
            pStatement.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateFlight(Flight flight) {
        String query = "update Flight set Departure = ? where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setTimestamp(1, (java.sql.Timestamp) flight.getDate());
            pStatement.setInt(2, flight.getFlightID());
            pStatement.execute();
            pStatement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFlight(Date date) {
        String query = "delete from Flight where Departure < ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setDate(1, (java.sql.Date) date);
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteFlight(int id) {
        String query = "delete from Flight where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, id);
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        FlightQueryBuilder fqb = new FlightQueryBuilder();
        ArrayList<Flight> flights = new ArrayList<>();
//        flights.add(new Flight(new Port("11111"), new Port("22222"),
//                fqb.parseDate("2021-11-20 23:18:03"), fqb.parseDate("2021-11-21 20:25:14"), new Plane(1)));
//        flights.add(new Flight(new Port("22222"), new Port("11111"),
//                fqb.parseDate("2021-11-20 23:18:03"), fqb.parseDate("2021-11-21 20:25:14"), new Plane(3)));

        fqb.addFlights(flights);
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}

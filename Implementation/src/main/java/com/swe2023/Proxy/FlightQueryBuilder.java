package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class FlightQueryBuilder {


    public void addFlight(Flight flight) {
        String query = "insert into Flight (Departure, Arrival, Source, Destination, Plane_id) values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setDate(1, (java.sql.Date) flight.getDepartureDate());
            pStatement.setDate(2, (java.sql.Date) flight.getArrivalDate());
            pStatement.setString(3, flight.getSource().getCode());
            pStatement.setString(4, flight.getDestination().getCode());
            pStatement.setInt(5, flight.getPlane().getId());
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addFlights(ArrayList<Flight> flights) {
        String query = "insert into Flight (Departure, Arrival, Source, Destination, Plane_id) values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            for (Flight flight : flights) {
                pStatement.setDate(1, (java.sql.Date) flight.getDepartureDate());
                pStatement.setDate(2, (java.sql.Date) flight.getArrivalDate());
                pStatement.setString(3, flight.getSource().getCode());
                pStatement.setString(4, flight.getDestination().getCode());
                pStatement.setInt(5, flight.getPlane().getId());
                pStatement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFlight(Flight flight) {
        String query = "update Flight set Departure = ?, Arrival = ? where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setDate(1, (java.sql.Date) flight.getDepartureDate());
            pStatement.setDate(2, (java.sql.Date) flight.getArrivalDate());
            pStatement.setInt(3, flight.getFlightID());
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(int id) {
        String query = "delete from Flight where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, id);
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(Date date) {
        String query = "delete from Flight where Departure < ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setDate(1, (java.sql.Date) date);
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

    }
}

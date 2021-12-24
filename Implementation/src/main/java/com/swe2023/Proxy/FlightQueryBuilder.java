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
            pStatement.setTimestamp(2, new Timestamp(flight.getDate().getTime()));
            pStatement.setString(3, flight.getSource().getCode());
            pStatement.setString(4, flight.getDestination().getCode());
            pStatement.setInt(5, flight.getPlane().getId());
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
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
            return false;
        }
    }

    public boolean updateFlight(Flight flight) {
        String query = "update Flight set Departure = ? where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setTimestamp(1, new Timestamp(flight.getDate().getTime()));
            pStatement.setInt(2, flight.getFlightID());
            pStatement.execute();
            pStatement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteFlight(Date date) {
        String query = "delete from Flight where Departure < ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setTimestamp(1, new Timestamp(date.getTime()));
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
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
            return false;
        }
    }

    public ArrayList<Flight> getAll() {
        String query = "select * from ((select * from Flight) as F " +
                "join Airport As A on F.Source = A.Airport_code) " +
                "join Airport on F.Destination = Airport.Airport_code";
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                flights.add(new Flight(resultSet.getInt(1),
                        new Port(resultSet.getString(6), resultSet.getString(7),
                                resultSet.getString(8), resultSet.getString(9),
                                resultSet.getInt(10), resultSet.getInt(11)),
                        new Port(resultSet.getString(12), resultSet.getString(13),
                                resultSet.getString(14), resultSet.getString(15),
                                resultSet.getInt(16), resultSet.getInt(17)),
                        new Date(resultSet.getTimestamp(2).getTime()),
                        new Plane(resultSet.getInt(5))));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public static void main(String[] args) {
        FlightQueryBuilder fqb = new FlightQueryBuilder();
        ArrayList<Flight> flights = fqb.getAll();
//        flights.add(new Flight(new Port("11111"), new Port("22222"),
//                fqb.parseDate("2021-11-20 23:18:03"), fqb.parseDate("2021-11-21 20:25:14"), new Plane(1)));
//        flights.add(new Flight(new Port("22222"), new Port("11111"),
//                fqb.parseDate("2021-11-20 23:18:03"), fqb.parseDate("2021-11-21 20:25:14"), new Plane(3)));
        System.out.println(flights);
//        fqb.addFlights(flights);
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}

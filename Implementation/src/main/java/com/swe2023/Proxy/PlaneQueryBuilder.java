package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlaneQueryBuilder {
    // add, update, delete.

    // getFlights.

    public void addPlane(Plane plane) {
        String query = "insert into Plane (Type, No_of_seats, Status) values(?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, plane.getType());
            pStatement.setInt(2, plane.getNo_of_seats());
            pStatement.setString(3, plane.getStatus());
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePlane(Plane plane) {
        String query = "update Plane set Status = ? where Plane_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, plane.getStatus());
            pStatement.setInt(2, plane.getId());
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlane(int id) {
        String query = "delete from Plane where Plane_id = ?";
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

    public ArrayList<Plane> search(HashMap<String, Object> criterias) {
        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("Plane");
        String query = selectQueryBuilder.getQuery(criterias);
        return processQuery(query);
    }

    public ArrayList<Plane> getAll() {
        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("Plane");
        String query = selectQueryBuilder.getQuery();
        return processQuery(query);
    }

    private ArrayList<Plane> processQuery(String query) {
        ArrayList<Plane> planes = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Plane plane = new Plane(resultSet.getString("Type"),
                        resultSet.getInt("No_of_seats"));
                plane.setStatus(resultSet.getString("Status"));
                plane.setId(resultSet.getInt("Plane_id"));
                plane.setIncome(resultSet.getInt("Income"));
                planes.add(plane);
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }

    public ArrayList<Flight> getPlaneFlights(Plane plane) {
        String query = "select * from ((select * from Flight where Plane_id = ?) as F " +
                "join Airport As A on F.Source = A.Airport_code) " +
                "join Airport on F.Destination = Airport.Airport_code";
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, plane.getId());

            ResultSet resultSet = pStatement.executeQuery();


            while (resultSet.next()) {
                Flight flight = new Flight(new Port(resultSet.getString(7), resultSet.getString(8),
                        resultSet.getString(9), resultSet.getString(10),
                        resultSet.getInt(11), resultSet.getInt(12)),
                        new Port(resultSet.getString(13), resultSet.getString(14),
                                resultSet.getString(15), resultSet.getString(16),
                                resultSet.getInt(17), resultSet.getInt(18)),
                        resultSet.getString(2), resultSet.getString(3),
                        plane);
                flight.setFlightID(resultSet.getInt(1));
            }
            connection.close();
            pStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public static void main(String[] args) {
        PlaneQueryBuilder pqb = new PlaneQueryBuilder();

//        Plane plane = new Plane("TTT", 300);
//        Plane plane = new Plane("HHH", 350);
//        Plane plane = new Plane("SSS", 400);
//        pqb.addPlane(plane);

//        pqb.deletePlane(2);
    }
}

package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PlaneQueryBuilder {
    // add, update, delete.

    // getFlights.

    public boolean addPlane(Plane plane) {
        String query = "insert into Plane values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);

            pStatement.setInt(1, plane.getId());
            pStatement.setString(2, plane.getType());
            pStatement.setInt(3, plane.getNo_of_seats());
            pStatement.setInt(4, plane.getIncome());
            pStatement.setString(5, plane.getStatus());

            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updatePlane(Plane plane) {
        String query = "update Plane set Status = ? where Plane_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);


            pStatement.setString(1, plane.getStatus());
            pStatement.setInt(2, plane.getId());

            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deletePlane(int id) {
        String query = "delete from Plane where Plane_id = ?";
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

    public ArrayList<Plane> search(HashMap<String, Object> criterias) {
        if (criterias.isEmpty())
            return null;
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
                planes.add(new Plane(resultSet.getInt(Plane.DB_ID),
                        resultSet.getString(Plane.DB_TYPE),
                        resultSet.getString(Plane.DB_STATUS),
                        resultSet.getInt(Plane.DB_SEATS_NUMBER),
                        resultSet.getInt(Plane.DB_INCOME)));
            }
            connection.close();
            statement.close();
            resultSet.close();
            System.out.println(planes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }

    public ArrayList<Flight> getPlaneFlights(Plane plane) {
        String query = "select * from ((select * from Flight where Plane_id = ?) as F " +
                "join Airport As A on F.Source = A.Airport_code) " +
                "join Airport AS B on F.Destination = B.Airport_code";
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, plane.getId());

            ResultSet resultSet = pStatement.executeQuery();

            while (resultSet.next()) {
                flights.add(new Flight(resultSet.getInt(Flight.DB_ID),
                        new Port(resultSet.getString("A."+Port.DB_ID), resultSet.getString("A."+Port.DB_COUNTRY),
                                resultSet.getString("A."+Port.DB_CITY), resultSet.getString("A."+Port.DB_NAME),
                                resultSet.getInt("A."+Port.DB_X_LOCATION), resultSet.getInt("A."+Port.DB_Y_LOCATION)),
                        new Port(resultSet.getString("B."+Port.DB_ID), resultSet.getString("B."+Port.DB_COUNTRY),
                                resultSet.getString("B."+Port.DB_CITY), resultSet.getString("B."+Port.DB_NAME),
                                resultSet.getInt("B."+Port.DB_X_LOCATION), resultSet.getInt("B."+Port.DB_Y_LOCATION)),
                        new Date(resultSet.getTimestamp(2).getTime()),
                        plane));
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
        ArrayList<Plane> planes = pqb.getAll();
//        ArrayList<Flight> flights = pqb.getPlaneFlights(planes.get(2));
//
//        System.out.println(flights);

//        Plane plane = new Plane("TTT", 300);
//        Plane plane = new Plane("HHH", 350);
//        Plane plane = new Plane("SSS", 400);
//        pqb.addPlane(plane);

//        pqb.deletePlane(2);

        System.out.println(new Date().toString().substring(0, 13));
        System.out.println(new Date().toString().substring(0, 13));
    }
}

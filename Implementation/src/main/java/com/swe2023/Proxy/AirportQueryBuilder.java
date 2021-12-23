package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Port;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class AirportQueryBuilder {

    public boolean addAirport(Port airport) {
        String query = "insert into Airport values(?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, airport.getCode());
            pStatement.setString(2, airport.getCountry());
            pStatement.setString(3, airport.getCity());
            pStatement.setString(4, airport.getName());
            pStatement.setInt(5, airport.getLongitude());
            pStatement.setInt(6, airport.getLatitude());
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAirport(String code) {
        String query = "delete from Airport where Airport_code = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, code);
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Port> search(HashMap<String, Object> criterias) {
        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("Airport");
        String query = selectQueryBuilder.getQuery(criterias);
        return processQuery(query);
    }

    public ArrayList<Port> getAll() {
        SelectQueryBuilder selectQueryBuilder = new SelectQueryBuilder("Airport");
        String query = selectQueryBuilder.getQuery();
        return processQuery(query);
    }

    private ArrayList<Port> processQuery(String query) {
        ArrayList<Port> airports = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                airports.add(new Port(resultSet.getString(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5), resultSet.getInt(6)));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airports;
    }

    public static void main(String[] args) {
        AirportQueryBuilder aqb = new AirportQueryBuilder();
        HashMap<String, Object> map = new HashMap<>();
        map.put("Country", "Egypt");
        ArrayList<Port> ports = aqb.getAll();
        ports.forEach((k) -> System.out.println(k.getCode() + " " + k.getCountry()));

    }
}

package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Port;

import java.sql.*;

public class AirportQueryBuilder {

    // getAll.
    public void addAirport(Port airport) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAirport(String code) {
        String query = "delete from Airport where Airport_code = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setString(1, code);
            pStatement.execute();
            pStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AirportQueryBuilder aqb = new AirportQueryBuilder();
        Port airport = new Port("22233", "Qatar", "GGG", "Airport_22233", 112, 71);
//        Port airport = new Port("55555", "USA", "XXX", "Airport_55555", 17, 81);
//        Port airport = new Port("11111", "Egypt", "ZZZ", "Airport_11111", 53, 13);
        aqb.addAirport(airport);

//        aqb.deleteAirport("67623");
    }
}

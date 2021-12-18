package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    public static void main(String[] args) {
        PlaneQueryBuilder pqb = new PlaneQueryBuilder();

//        Plane plane = new Plane("TTT", 300);
//        Plane plane = new Plane("HHH", 350);
//        Plane plane = new Plane("SSS", 400);
//        pqb.addPlane(plane);

//        pqb.deletePlane(2);
    }
}

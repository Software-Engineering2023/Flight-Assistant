package com.swe2023.model.Planes_Data;

import java.util.Arrays;

public class Plane {

    public static final String DB_TABLE_NAME="Plane";
    public static final String DB_ID="Plane_id";
    public static final String DB_TYPE="Type";
    public static final String DB_INCOME="Income";
    public static final String DB_STATUS="Status";
    public static final String DB_SEATS_NUMBER="No_of_seats";

    private int id, no_of_seats, income;
    private String type, status;
    private Flight[] flights;

    public Plane(int id) {
        this.id = id;
    }

    public Plane(int id,  String type, String status, int no_of_seats, int income) {
        this.id = id;
        this.no_of_seats = no_of_seats;
        this.income = income;
        this.type = type;
        this.status = status;
    }

    public Plane(String type, int no_of_seats) {
        this.no_of_seats = no_of_seats;
        this.type = type;
        this.status = "Active";
        this.income = 0;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getNo_of_seats() {
        return no_of_seats;
    }

    // driven attribute.
    // should be calculated periodically.
    public int getIncome() {
        return income;
    }

    public Flight[] getFlights() {
        return flights;
    }

    public String getStatus() {
        return status;
    }

    // updatable attributes.

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public static boolean isPlane(Plane plane){
        if(plane.getNo_of_seats() != 0 && plane.getType() != null && plane.getStatus() != null) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", no_of_seats=" + no_of_seats +
                ", income=" + income +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", flights=" + Arrays.toString(flights) +
                '}';
    }
}
package com.swe2023.model.Planes_Data;

public class Plane {

    private int id, no_of_seats, income;
    private String type, status;
    private Flight[] flights;

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
}

package com.swe2023.model.Planes_Data;

public class Port {

    public static final String DB_TABLE_NAME= "Airport";
    public static final String DB_ID= "Airport_code";
    public static final String DB_CITY= "City";
    public static final String DB_NAME= "Name";
    public static final String DB_X_LOCATION= "Longitude";
    public static final String DB_Y_LOCATION= "Latitude";

    String name;
    String ID;
    int xLocation;
    int yLocation;

    public Port(){}
    public Port(String name, int xLocation, int yLocation) {
        this.name = name;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public String getName() {
        return name;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }
}

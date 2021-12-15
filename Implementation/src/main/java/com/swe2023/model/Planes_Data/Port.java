package com.swe2023.model.Planes_Data;

public class Port {
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

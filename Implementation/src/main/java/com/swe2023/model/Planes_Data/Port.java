package com.swe2023.model.Planes_Data;

public class Port {
    // edit to match database.
    String code, country, city, name;
    int longitude;
    int latitude;

    public Port(){}

    // edit.
    public Port(String code, String country, String city, String name, int longitude, int latitude) {
        this.code = code;
        this.country = country;
        this.city = city;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCode() {
        return code;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }
}

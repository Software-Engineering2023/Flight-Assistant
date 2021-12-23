package com.swe2023.model.Planes_Data;

public class Port {
    // edit to match database.
    private String code, country, city, name;
    private int longitude;
    private int latitude;

    public Port(String code) {
        this.code = code;
    }

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

    @Override
    public String toString() {
        return "Port{" +
                "code='" + code + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", name='" + name + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}

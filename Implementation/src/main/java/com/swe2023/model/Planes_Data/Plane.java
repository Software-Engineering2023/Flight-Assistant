package com.swe2023.model.Planes_Data;

public class Plane {


    public static final String DB_TABLE_NAME="Plane";
    public static final String DB_ID="Plane_id";
    public static final String DB_TYPE="Plane_id";
    public static final String DB_INCOME="Income";
    public static final String DB_STATUS="Status";


    String planeID;
    String status;
    Flight[] flights;
    int income;

}

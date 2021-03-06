package com.swe2023.Proxy;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DB_Utils {


    // add your db main setup here.
    private static final String URL = "jdbc:mysql://localhost:3306/Airline";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root$12345";

    private static BasicDataSource dataSource;

    static {
        try {
            dataSource = new BasicDataSource();
            dataSource.setUrl(URL);
            dataSource.setUsername(DB_USERNAME);
            dataSource.setPassword(DB_PASSWORD);

            dataSource.setMinIdle(100);
            dataSource.setMaxIdle(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

}

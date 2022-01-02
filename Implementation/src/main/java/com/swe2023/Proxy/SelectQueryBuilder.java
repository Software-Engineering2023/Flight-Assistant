package com.swe2023.Proxy;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;


public class SelectQueryBuilder {

    private StringBuilder sb;
    private HashMap<String, Object> criterias;
    private final String TABLE;


    public SelectQueryBuilder(String TABLE) {
        this.TABLE = TABLE;
    }

    private void build() {
        sb = new StringBuilder();
        sb.append("select * from ".concat(TABLE).concat(" where "));
        criterias.forEach((col, value) -> {
            if (value != null)
                sb.append(col.concat(" = '").concat(value.toString()).concat("' and "));
        });
        int lastAnd = sb.length()-5;
        sb.delete(lastAnd, sb.length());
    }

    public String getQuery(HashMap<String, Object> criterias) {
        this.criterias = criterias;
        build();
        return sb.toString();
    }

    public String getQuery() {
        return "select * from ".concat(TABLE);
    }

    public static void main(String[] args) throws SQLException {
        HashMap<String, Object> map = new HashMap<>();

        map.put("Country", "Egypt");
        map.put("Latitude", 13);

        SelectQueryBuilder sqb = new SelectQueryBuilder("Airport");
        String str = sqb.getQuery(map);

        System.out.println(str);

        Connection connection = DB_Utils.getDataSource().getConnection();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(str);


        while (resultSet.next()) {
            System.out.println(resultSet.getString("City") + " " + resultSet.getInt("Latitude"));
        }

        connection.close();
        statement.close();
        resultSet.close();
    }
}

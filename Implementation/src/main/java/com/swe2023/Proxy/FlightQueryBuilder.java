package com.swe2023.Proxy;

import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class FlightQueryBuilder {


    public boolean addFlight(Flight flight) {
        String query = "insert into Flight values(?, ?, ?, ?, ?,?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, flight.getFlightID());
            pStatement.setTimestamp(2, new Timestamp(flight.getDate().getTime()));
            pStatement.setString(3, flight.getSource().getCode());
            pStatement.setString(4, flight.getDestination().getCode());
            pStatement.setInt(5, flight.getPlane().getId());
            pStatement.setInt(6, flight.getAvailableSeats());
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addFlights(ArrayList<Flight> flights) {
        String query = "insert into Flight values(?, ?, ?, ?, ?)";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            for (Flight flight : flights) {
                pStatement.setInt(1, flight.getFlightID());
                pStatement.setTimestamp(2, new Timestamp(flight.getDate().getTime()));
                pStatement.setString(3, flight.getSource().getCode());
                pStatement.setString(4, flight.getDestination().getCode());
                pStatement.setInt(5, flight.getPlane().getId());
                pStatement.execute();
            }
            pStatement.close();
            connection.close();
            return true;

        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateFlight(Flight flight) {
        String query = "update Flight set Departure = ? where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setTimestamp(1, new Timestamp(flight.getDate().getTime()));
            pStatement.setInt(2, flight.getFlightID());
            pStatement.execute();
            pStatement.close();
            connection.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteFlight(Date date) {
        String query = "delete from Flight where Departure < ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setTimestamp(1, new Timestamp(date.getTime()));
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean deleteFlight(int id) {
        String query = "delete from Flight where Flight_id = ?";
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            PreparedStatement pStatement = connection.prepareStatement(query);
            pStatement.setInt(1, id);
            pStatement.execute();
            pStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public ArrayList<Flight> getAll() {
        String query = "select * from ((select * from Flight) as F " +
                "join Airport As A on F.Source = A.Airport_code) " +
                "join Airport AS B on F.Destination = B.Airport_code";
        ArrayList<Flight> flights = new ArrayList<>();
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
//                for(int i= 1 ; i<=20; i++)
//                    System.out.println(resultSet.getString(i));
                flights.add(new Flight(resultSet.getInt(Flight.DB_ID),
                        new Port(resultSet.getString("A."+Port.DB_ID), resultSet.getString("A."+Port.DB_COUNTRY),
                                resultSet.getString("A."+Port.DB_CITY), resultSet.getString("A."+Port.DB_NAME),
                                resultSet.getInt("A."+Port.DB_X_LOCATION), resultSet.getInt("A."+Port.DB_Y_LOCATION)),

                        new Port(resultSet.getString("B."+Port.DB_ID), resultSet.getString("B."+Port.DB_COUNTRY),
                                resultSet.getString("B."+Port.DB_CITY), resultSet.getString("B."+Port.DB_NAME),
                                resultSet.getInt("B."+Port.DB_X_LOCATION), resultSet.getInt("B."+Port.DB_Y_LOCATION))
                        ,
                        new Date(resultSet.getTimestamp(Flight.DB_DATE).getTime()),
                        new Plane(resultSet.getInt(Flight.DB_PLANE_ID))));
            }
            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public List<Flight> searchFlight(Port source, Port dest) {
        String query=configureQuery(source, dest);
        try {
            Connection connection = DB_Utils.getDataSource().getConnection();
            Statement st= connection.createStatement();
            ResultSet resultSet = st.executeQuery(query);
            List<Flight> flights= extractFlightsFromResultSet(connection,resultSet);
            connection.close();
            st.close();
            resultSet.close();
            return flights;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String configureQuery(Port source, Port dest){
        SelectQueryBuilder builder= new SelectQueryBuilder("Flight");
        HashMap<String, Object> portGetter= new HashMap<>();
        if(source != null)
            portGetter.put("Source",source.getCode());
        if(dest != null)
            portGetter.put("Destination",dest.getCode());
        return builder.getQuery(portGetter);
    }

    private List<Flight> extractFlightsFromResultSet(Connection conn,ResultSet set) throws SQLException {
        List<Flight> flights= new ArrayList<>();
        while(set.next()){
            int flight_id= set.getInt("Flight_id");
            Port source= getPort(conn.createStatement(),set.getString("Source"));
            Port dest= getPort(conn.createStatement(),set.getString("Destination"));
            Date timeStamp= new Date(set.getTimestamp("Departure").getTime());
            Plane plane= getPlane(conn.createStatement(),set.getInt("Plane_id"));
            flights.add(new Flight(flight_id,source,dest,timeStamp,plane));
        }
        return flights;
    }

    private Port getPort(Statement st, String code) throws SQLException {
        SelectQueryBuilder builder= new SelectQueryBuilder("Airport");
        HashMap<String,Object> data= new HashMap<>();
        data.put("Airport_code",code);
        String query=builder.getQuery(data);
        ResultSet set= st.executeQuery(query);
        set.next();
        Port port= new Port(set.getString(1),set.getString(2),set.getString(3),set.getString(4),set.getInt(5),set.getInt(6));
        set.close();
        st.close();
        return port;
    }

    private Plane getPlane(Statement st, int plane_id) throws SQLException {
        SelectQueryBuilder builder= new SelectQueryBuilder("Plane");
        HashMap<String,Object> data= new HashMap<>();
        data.put("Plane_id",plane_id);
        String query=builder.getQuery(data);
        ResultSet set= st.executeQuery(query);
        set.next();

        Plane plane= new Plane(set.getInt(1),set.getString(2),
        set.getString(5),set.getInt(3),set.getInt(4));

        set.close();
        st.close();
        return  plane;
    }
    public static void main(String[] args) {
        FlightQueryBuilder fqb = new FlightQueryBuilder();
//        Timestamp timestamp = new Timestamp(Objects.requireNonNull(fqb.parseDate("2021-03-20 03:00:00")).getTime());
//        fqb.deleteFlight(new Date(timestamp.getTime()));
//        ArrayList<Flight> flights = fqb.getAll();
//        System.out.println(flights);

        Flight flight = new Flight(10, new Port("44444"), new Port("33333"),
                new Date(), new Plane(6));

        fqb.addFlight(flight);
        ArrayList<Flight> flights = fqb.getAll();
        System.out.println(flights);
    }

    private Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (Exception e) {
            return null;
        }
    }
}

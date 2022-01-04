package com.swe2023.model.Planes_Data;

import com.swe2023.Proxy.DB_Utils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

public class PortManagerTest {

    private static final PortManager manager= new PortManager();
    @BeforeClass
    public static void deleteAllPorts() throws SQLException {
        Connection connection = DB_Utils.getDataSource().getConnection();
        Statement st= connection.createStatement();
        st.execute("DELETE FROM Airport");
        st.close();
        connection.close();
    }
    @Test
    public void test(){
        addNewPort();
        loadPortsFromDataBase();
        deletePort();
    }
    public void addNewPort() {
        Port port= new Port("1234", "Egypt", "Alex", "Borg", 10,100);
        assertTrue(manager.addNewPort(port));
        port= new Port("12345", "Egypt", "Alex", "Nozha", 47, 96);
        assertTrue(manager.addNewPort(port));
        port= new Port("12345", "Peru", "X", "PERU", 45, 778);
        assertFalse(manager.addNewPort(port));
    }
    public void loadPortsFromDataBase(){
        assertEquals(2, manager.loadPortsFromDataBase().size());
    }
    public void deletePort(){
        manager.deletePort(new Port("12345","s","s","s",10,10));
        assertEquals(1, manager.loadPortsFromDataBase().size());
    }
}
package com.swe2023.model.Tickets_Data;

import com.swe2023.Proxy.DB_Utils;
import com.swe2023.Proxy.TicketQuery;
import com.swe2023.model.Tickets_Data.Ticket;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AdminTicketsManagerTest {

    private static final AdminTicketsManager manager= new AdminTicketsManager();
    @BeforeClass
    public static void deleteAllTickets() throws SQLException {
        Connection connection = DB_Utils.getDataSource().getConnection();
        Statement st= connection.createStatement();
        st.execute("DELETE FROM Ticket");
        st.close();
        connection.close();
    }
    @Test
    public void test(){
        Ticket ticket  = new Ticket("214567",null,233,2);
        TicketQuery.addTicket(ticket);
        deleteTicket(ticket);
    }
    public void deleteTicket(Ticket ticket){
        manager.cancelTicket(ticket);
        ArrayList<Ticket> allTickets = TicketQuery.getAll();
        assertFalse( allTickets.contains(ticket));
    }
}
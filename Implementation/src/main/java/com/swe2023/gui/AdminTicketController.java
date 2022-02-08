package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Tickets_Data.AdminTicketsManager;
import com.swe2023.model.Tickets_Data.Ticket;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.model.signUpAndLogin.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


public class AdminTicketController {
    @FXML
    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button b5;
    public Button b6;
    public ListView listview1;
    public Label l1;
    public Label l2;
    public Label l3;
    public Label l4 ;
    public TextField t2;
    public TextField t3;

    AdminTicketsManager ticketManager=new AdminTicketsManager();
    ArrayList<Ticket> tickets=new ArrayList<>();
    int index;
    private String errorMessage;

    public void initialize() {
        //fil tickets here
        allTickets();
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b5.setVisible(false);
        b6.setVisible(false);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(false);
        listview1.setVisible(true);
        if (!listview1.getItems().isEmpty()) {
            listview1.getSelectionModel().select(0);
        }

    }

    public void b1_Clicked(){
        t2.clear();
        t3.clear();
        System.out.println("index is :"+index);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b5.setVisible(true);
        b6.setVisible(true);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        l4.setVisible(true);
        t2.setVisible(true);
        t3.setVisible(true);
        listview1.setVisible(false);
        l4.setText(tickets.get(index).getTicketID());
        t2.setPromptText("Old Value : "+tickets.get(index).getCost());
        t3.setPromptText("Old Value : "+tickets.get(index).getPassengersNo());
    }
    public void b2_Clicked(){
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b6.setVisible(true);
        System.out.println("ticket size"+tickets.size());
        Ticket t=tickets.get(2);   
        System.out.println(t.getFlights().size());
        showTicketFlights();


    }
    public void b3_Clicked() throws SQLException{
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b6.setVisible(true);
        //fill users
        ArrayList<Passenger> users =  ticketManager.getTopUsers();
        topUsers(users);


    }

    public void b4_Clicked(){
        HelloApplication.showWindow(b4, "/admin-home.fxml", "Administrator",
                null, 950,650);

    }
    public void b6_Clicked(){
        initialize();
    }
    public void allTickets(){
    	tickets=ticketManager.getAllTickets();
        listview1.getItems().clear();
        for(Ticket ticket : tickets)
            listview1.getItems().add("Ticket ID  : "+ticket.getTicketID()+" Passenger Numbers : "+ticket.getPassengersNo()+" Cost : "+ticket.getCost());
    }

    public void getViewItem() {
        index= listview1.getSelectionModel().getSelectedIndex();
        System.out.println("index pressed "+index);
    }

    public void editTicket(){
        Ticket ticket =tickets.get(index);
        String ticketCost = t2.getText();
        String ticketPassenegerNo = t3.getText();
        if (ticketCost!=""&&ticketPassenegerNo!="") {
            System.out.println("cost : "+ticketCost);
            ticket.setCost(Float.parseFloat(ticketCost));
            ticket.setPassengersNo(Integer.parseInt(ticketPassenegerNo));
            ticketManager.modifyTicketUser(ticket, ticket.getUser());
            initialize();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Invliad input");
            alert.setContentText("Please enter ticket cost and passneger number");
            alert.showAndWait();
        }
    }

    public void  showTicketFlights(){
    	System.out.println("enter show tickets");
        listview1.getItems().clear();
        LinkedList<Flight> flights = tickets.get(index).getFlights();
        for(Flight flight : flights)
            listview1.getItems().add("Flight ID  : "+flight.getFlightID()+" Available Seats : "+flight.getAvailableSeats()+" Date : "+flight.getDate());
    }

    public void topUsers(ArrayList<Passenger> users){
        listview1.getItems().clear();
        for(Passenger user : users)
            listview1.getItems().add("Email : "+user.getEmail()+" Gender : "+user.getGender()+" Birthday : "+user.getBirthDate()+" Total Tickets Cost : "+user.getTotalTicketCost());
    }
}

package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Tickets_Data.Ticket;
import com.swe2023.model.signUpAndLogin.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    public TextField t1;
    public TextField t2;
    public TextField t3;


    ArrayList<Ticket> tickets=new ArrayList<>();
    int index;

    public void initialize() {
        //fil tickets here
        allTickets();
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b5.setVisible(true);
        b6.setVisible(true);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        t1.setVisible(true);
        t2.setVisible(true);
        t3.setVisible(true);

    }

    public void b1_Clicked(){
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b5.setVisible(false);
        b6.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        t1.setVisible(false);
        t2.setVisible(false);
        t3.setVisible(false);
        t1.setPromptText("Old Value : "+tickets.get(index).getTicketID());
        t2.setPromptText("Old Value : "+tickets.get(index).getCost());
        t3.setPromptText("Old Value : "+tickets.get(index).getPassengersNo());
        editTicket();
    }
    public void b2_Clicked(){
        b1.setVisible(false);
        b2.setVisible(true);
        b3.setVisible(true);
        b6.setVisible(true);
        showTicketFlights();


    }
    public void b3_Clicked(){
        b1.setVisible(false);
        b2.setVisible(true);
        b3.setVisible(true);
        //fill users
        ArrayList<User> users = new ArrayList<User>();
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
        listview1.getItems().clear();
        for(Ticket ticket : tickets)
            listview1.getItems().add("Ticket ID  : "+ticket.getTicketID()+"Passenger Numbers : "+ticket.getPassengersNo()+"Cost : "+ticket.getCost());
    }

    public void getViewItem() {
        index= listview1.getSelectionModel().getSelectedIndex();
        System.out.println("index pressed "+index);
    }

    public void editTicket(){
        Ticket ticket =tickets.get(index);
        String ticketCost = t1.getText();
        String ticketPassenegerNo = t1.getText();
        ticket.setCost(Float.parseFloat(ticketCost));
        ticket.setPassengersNo(Integer.parseInt(ticketPassenegerNo));
        tickets.set(index,ticket);
    }

    public void  showTicketFlights(){
        listview1.getItems().clear();
        LinkedList<Flight> flights = tickets.get(index).getFlights();
        for(Flight flight : flights)
            listview1.getItems().add("Flight ID  : "+flight.getFlightID()+"Available Seats : "+flight.getAvailableSeats()+"Date : "+flight.getDate());
    }

    public void topUsers(ArrayList<User> users){
        listview1.getItems().clear();
        for(User user : users)
            listview1.getItems().add("ID : "+user.getID()+"Email : "+user.getEmail()+"Gender : "+user.getGender()+"Birthday : "+user.getBirthDate());
    }
}

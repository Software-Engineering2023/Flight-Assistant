//package com.swe2023.gui;
//
//import com.swe2023.HelloApplication;
//import com.swe2023.model.Planes_Data.Flight;
//import com.swe2023.model.Planes_Data.Plane;
//import javafx.collections.FXCollections;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//public class AdminFlightController {
//    @FXML
//    public Button updateButton;
//    public TextField IDField;
//    public ChoiceBox PlaneIDField;
//    public ChoiceBox SourceField;
//    public ChoiceBox DestinationField;
//    public DatePicker DateField;
//
//
//    public ListView<String> listView;
//
//    private ArrayList<Flight> Flights;
//    private Flight flight;
//    private ArrayList<String> Ports;
//    private ArrayList<String> Planes;
//
//
//    public void initialize() {
//        Flights = new ArrayList<>();
//        Ports = new ArrayList<>();
//        Planes = new ArrayList<>();
//        Ports.add("paris");
//        Ports.add("cairo");
//        Ports.add("london");
//        Ports.add("new york");
//        Planes.add("1");
//        Planes.add("2");
//        Planes.add("3");
//
//        for (int i = 0; i < Ports.size(); i++) {
//            SourceField.getItems().add(Ports.get(i));
//            DestinationField.getItems().add(Ports.get(i));
//        }
//        for (int i = 0; i < Planes.size(); i++)
//            PlaneIDField.getItems().add(Planes.get(i));
//    }
//    public void goToAdminHome() {
//        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator", 800,640);
//    }
//
//    public void createFlight(ActionEvent actionEvent) {
//        Flight flight= new Flight(IDField.getText(),(String) PlaneIDField.getSelectionModel().getSelectedItem(),
//                (String)SourceField.getSelectionModel().getSelectedItem()
//        ,(String)DestinationField.getSelectionModel().getSelectedItem(),DateField.getValue());
//        Flights.add(flight);
//        updateListView();
//    }
//
//    public void updateListView(){
//        listView.getItems().clear();
//        for(Flight i : Flights)
//            listView.getItems().add(i.getFlightID());
//        listView.getItems().removeAll();
//    }
//
//
//    public void getViewItem() {
//        int index= listView.getSelectionModel().getSelectedIndex();
//        Flight flight= Flights.get(index);
//        updateDetailedView(flight);
//    }
//    public void updateDetailedView(Flight flight){
//        IDField.setText(flight.getPlaneID());
//        PlaneIDField.setValue(flight.getFlightID());
//        System.out.println(flight.getFlightID());
//        SourceField.setValue(flight.getSource());
//        DestinationField.setValue(flight.getDestination());
//        DateField.setValue(flight.getDate());
//    }
//}

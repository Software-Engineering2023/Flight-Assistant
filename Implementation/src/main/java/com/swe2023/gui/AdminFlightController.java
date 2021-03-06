package com.swe2023.gui;

import com.swe2023.Admin.AdminSession;
import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Flight;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class AdminFlightController {
    @FXML
    public Button updateButton;
    public TextField IDField;
    public ChoiceBox PlaneIDField = null;
    public ChoiceBox SourceField = null;
    public ChoiceBox DestinationField = null;
    public DatePicker DateField = new DatePicker();


    public ListView<String> listView;
    private AdminSession adminSession;


    private ArrayList<Flight> Flights;
    private Flight flight;
    private ArrayList<Port> Ports;
    private ArrayList<Plane> Planes;


    public void initialize() {

        adminSession= AdminSession.getSession();
        Flights = new ArrayList<>();
        loadFlights();
        Planes= new ArrayList<>();
        loadPlanes();
        Ports= new ArrayList<>();
        loadAirports();

        for (int i = 0; i < Ports.size(); i++) {
            SourceField.getItems().add(Ports.get(i).getName()+" "+Ports.get(i).getCity()+" "+Ports.get(i).getCountry());
            DestinationField.getItems().add(Ports.get(i).getName()+" "+Ports.get(i).getCity()+" "+Ports.get(i).getCountry());
        }
        for (int i = 0; i < Planes.size(); i++)
            PlaneIDField.getItems().add(Planes.get(i).getId());
    }
    public void goToAdminHome() {
        adminSession.setPlaneToShowFlights(null);
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator",
                null, 950,650);
    }
    private void loadFlights(){
        Flights = adminSession.loadFlightsFromDataBase();
        for (Flight flight :Flights )
            listView.getItems().add(String.valueOf(flight.getFlightID()));
    }

    private void loadAirports(){
        Ports= adminSession.loadPortsFromDatabase();
    }

    private void loadPlanes(){
        Planes= adminSession.loadPlanesFromDataBase();
    }

    private Flight getCurrentFlight(){
        int id = Integer.parseInt(IDField.getText());
        Plane plane = Planes.get(PlaneIDField.getSelectionModel().getSelectedIndex());
        Port source = Ports.get(SourceField.getSelectionModel().getSelectedIndex());
        Port destination = Ports.get(DestinationField.getSelectionModel().getSelectedIndex());
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date date = Date.from(DateField.getValue().atStartOfDay(defaultZoneId).toInstant());
        //return new Flight(id,plane,source,,Integer.parseInt(size));
        return new Flight( id, source,  destination,  date,  plane);

    }

    private void reset(){
        PlaneIDField.getSelectionModel().clearSelection();
        SourceField.getSelectionModel().clearSelection();
        DestinationField.getSelectionModel().clearSelection();
        IDField.setText("");
    }


    public void createFlight(ActionEvent actionEvent) {
        try {
            Flight flight = getCurrentFlight();
            System.out.println(flight.getFlightID());
            String add = adminSession.addNewFlight(flight);
            if (add.equals("ok")) {
                reset();
                Flights.add(flight);
                updateListView();
            } else {
                HelloApplication.showErrorMessage(add);
            }
        }
        catch (Exception e){
            HelloApplication.showErrorMessage("invalid data");
        }
    }


    public void updateListView(){
        listView.getItems().clear();
        for(Flight i : Flights)
            listView.getItems().add(String.valueOf(i.getFlightID()));
        //listView.getItems().removeAll();
    }


    public void getViewItem() {
        int index = listView.getSelectionModel().getSelectedIndex();
        Flight flight = Flights.get(index);
        updateDetailedView(flight);
    }
    public void updateDetailedView(Flight flight){
        IDField.setText(String.valueOf(flight.getFlightID()));
        PlaneIDField.setValue(flight.getPlane().getId());
        //System.out.println(flight.getFlightID());
        SourceField.getSelectionModel().select(flight.getSource().getName()+" "+flight.getSource().getCity()+" "+flight.getSource().getCountry());
        DestinationField.getSelectionModel().select(flight.getDestination().getName()+" "+flight.getDestination().getCity()+" "+flight.getDestination().getCountry());
        DateField.setValue((flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate( )));
    }

    public void deleteFlight(){
        Flight flight= getCurrentFlight();
        if(adminSession.deleteFlight(flight)){
            //removePortFromLists();
            for (int i = 0; i < Flights.size(); i++) {
                if(Flights.get(i).getFlightID() == flight.getFlightID()){
                    Flights.remove(i);
                    break;
                }
            }
            reset();
            updateListView();
            return;
        }
        HelloApplication.showErrorMessage("plane not found!");

    }

}

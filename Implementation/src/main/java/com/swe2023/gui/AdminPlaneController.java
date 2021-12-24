package com.swe2023.gui;

import com.swe2023.Admin.AdminSession;
import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Plane;
import com.swe2023.model.Planes_Data.Port;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import com.swe2023.Proxy.AirportQueryBuilder;
import com.swe2023.Proxy.FlightQueryBuilder;
import com.swe2023.Proxy.PlaneQueryBuilder;

import java.util.ArrayList;

public class AdminPlaneController {

    @FXML
    public Button updateButton;
    public Button showFlights;
    public TextField IDField;
    public TextField SizeField;
    public TextField IncomeField;
    public TextField StatusField;
    public TextField KindField;


    public ListView<String> listView;

    private ArrayList<Plane> Planes;
    private Plane plane;

    private AdminSession adminSession;


    public void initialize(){
        adminSession= AdminSession.getSession();
        Planes= new ArrayList<>();
        loadPlanes();
    }

    private void loadPlanes(){
        Planes= adminSession.loadPlanesFromDataBase();
        for (Plane port :Planes )
            listView.getItems().add(String.valueOf(port.getId()));
    }

    public void goToAdminHome() {
        adminSession.setPlaneToShowFlights(null);
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator", 800,640);
    }

    private Plane getCurrentPlane(){
        int id = Integer.parseInt(IDField.getText());
        String kind = KindField.getText();
        String status = StatusField.getText();
        String income = IncomeField.getText();
        String size = SizeField.getText();
        return new Plane(id,status,kind,Integer.parseInt(income),Integer.parseInt(size));
    }

    private void reset(){
        KindField.setText("");
        IDField.setText("");
        StatusField.setText("");
        IncomeField.setText("");
        SizeField.setText("");
    }

    public void createPlane(ActionEvent actionEvent) {

        Plane plane= getCurrentPlane();
        if(adminSession.addNewPlane(plane)) {
            reset();
            Planes.add(plane);
            updateListView();
        }
        else{
            HelloApplication.showErrorMessage("plane already there");
        }
    }

    public void updateListView(){
        listView.getItems().clear();
        for(Plane i : Planes)
            listView.getItems().add(String.valueOf(i.getId()));
        listView.getItems().removeAll();
    }


    public void getViewItem() {
        int index= listView.getSelectionModel().getSelectedIndex();
        plane= Planes.get(index);
        updateDetailedView(plane);
    }
    public void updateDetailedView(Plane plane){
        IDField.setText(String.valueOf(plane.getId()));
        SizeField.setText(String.valueOf(plane.getNo_of_seats()));
        KindField.setText(plane.getType());
        StatusField.setText(plane.getStatus());
        IncomeField.setText(String.valueOf(plane.getIncome()));
    }

    public void deletePlane(){
        Plane plane= getCurrentPlane();
        if(adminSession.deletePlane(plane)){
            //removePortFromLists();
            for (int i = 0; i < Planes.size(); i++) {
                if(Planes.get(i).getId() == plane.getId()){
                    Planes.remove(i);
                    break;
                }
            }
            reset();
            updateListView();
            return;
        }
        HelloApplication.showErrorMessage("plane not found!");

    }

    public void showFLights(){
        adminSession.setPlaneToShowFlights(plane);
        HelloApplication.showWindow(showFlights, "/admin-flight.fxml","Flights Management", 800,640);
    }
}

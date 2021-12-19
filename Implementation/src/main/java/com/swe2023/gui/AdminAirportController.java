package com.swe2023.gui;

import com.swe2023.Admin.AdminSession;
import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Port;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AdminAirportController {

    @FXML
    public Button updateButton;
    public TextField nameField;
    public Spinner<Double> xSpinner;
    public Spinner<Double> ySpinner;
    public ListView<String> listView;
    public TextField codeField;
    public TextField countryField;
    public TextField cityField;

    private AdminSession adminSession;

    private ArrayList<Port> ports;
    private Port airport;

    public void initialize(){
        adminSession= AdminSession.getSession();
        xSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-90,90,0.0,0.01));
        ySpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-180,180,0.0,0.01));
        ports= new ArrayList<>();
    }
    public void goToAdminHome() {
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator", 800,640);
    }

    public void createAirport(ActionEvent actionEvent) {
        String airportName= nameField.getText();
        String code= codeField.getText();
        String country= countryField.getText();
        String city= cityField.getText();
        double x= xSpinner.getValue();
        double y= ySpinner.getValue();
        Port port= new Port(code, country, city, airportName, (int)x, (int)y);

        if(adminSession.addNewAirPort(port)) {
            reset();
            ports.add(port);
            updateListView();
        }
        else{
            System.out.println("Error");
        }


    }

    public void updateListView(){
        listView.getItems().removeAll();
        for(Port i : ports)
                listView.getItems().add(i.getName());
    }


    public void getViewItem() {
        int index= listView.getSelectionModel().getSelectedIndex();
        Port port= ports.get(index);
        updateDetailedView(port);
    }
    public void updateDetailedView(Port port){
        nameField.setText(port.getName());
        xSpinner.getValueFactory().setValue((double)port.getLongitude());
        ySpinner.getValueFactory().setValue((double)port.getLatitude());
    }

    private void reset(){
        nameField.setText("");
        countryField.setText("");
        codeField.setText("");
        cityField.setText("");
        xSpinner.getValueFactory().setValue(0.0);
        ySpinner.getValueFactory().setValue(0.0);
    }
}

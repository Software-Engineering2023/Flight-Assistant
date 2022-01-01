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
        loadAirports();
    }

    private void loadAirports(){
        ports= adminSession.loadPortsFromDatabase();
        for (Port port :ports )
            listView.getItems().add(port.getName());
    }
    public void goToAdminHome() {
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator",null, 950,650);
    }

    private Port getCurrentPort(){
        String airportName= nameField.getText();
        String code= codeField.getText();
        String country= countryField.getText();
        String city= cityField.getText();
        double x= xSpinner.getValue();
        double y= ySpinner.getValue();
        return new Port(code, country, city, airportName, (int)x, (int)y);
    }
    public void createAirport() {
        try{
        Port port = getCurrentPort();
        if (adminSession.addNewAirPort(port)) {
            reset();
            ports.add(port);
            updateListView();
        } else {
            HelloApplication.showErrorMessage("Airport already there");
        }
        }catch (Exception e){
            HelloApplication.showErrorMessage("invalid data");
        }

    }

    public void updateListView(){
        listView.getItems().remove(0,listView.getItems().size());
        for(Port i : ports)
                listView.getItems().add(i.getName());
    }


    public void getViewItem() {
        int index= listView.getSelectionModel().getSelectedIndex();
        Port port= ports.get(index);
        System.out.println(port);

        updateDetailedView(port);
    }
    public void updateDetailedView(Port port){
        nameField.setText(port.getName());
        codeField.setText(port.getCode());
        countryField.setText(port.getCountry());
        cityField.setText(port.getCity());
        xSpinner.getValueFactory().setValue((double)port.getLongitude());
        ySpinner.getValueFactory().setValue((double)port.getLatitude());
    }

    public void deleteAirport(){
        Port port= getCurrentPort();
        if(adminSession.deletePort(port)){
            removePortFromLists(port);
            reset();
            return;
        }
        HelloApplication.showErrorMessage("Port not found!");
    }
    private void removePortFromLists(Port port){
        Port temp=null;
        for(Port i : ports)
            if(i.getCode().equals(port.getCode())){
                temp= i;
                break;
            }
        listView.getItems().remove(temp.getName());
        ports.remove(temp);
    }
    private void reset(){
        nameField.setText("");
        countryField.setText("");
        codeField.setText("");
        cityField.setText("");
        xSpinner.getValueFactory().setValue(0.0);
        ySpinner.getValueFactory().setValue(0.0);
    }

//    private void emptyList(){
//        while(listView.getItems().size()> 0)
//            listView.getItems().remove(0);
//    }
}

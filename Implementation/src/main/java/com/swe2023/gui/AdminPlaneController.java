package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.model.Planes_Data.Plane;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class AdminPlaneController {

    @FXML
    public Button updateButton;
    public TextField IDField;
    public TextField SizeField;
    public TextField IncomeField;
    public TextField StatusField;
    public TextField KindField;


    public ListView<String> listView;

    private ArrayList<Plane> Planes;
    private Plane plane;

    public void initialize(){
        Planes= new ArrayList<>();
    }
    public void goToAdminHome() {
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator", 800,640);
    }

    public void createPlane(ActionEvent actionEvent) {
        int id = IDField.getText();
        String kind = KindField.getText();
        String status = StatusField.getText();
        String income = IncomeField.getText();
        String size = SizeField.getText();

        Plane plane= new Plane(id,status,kind,Integer.parseInt(income),Integer.parseInt(size));
        Planes.add(plane);
        updateListView();
    }

    public void updateListView(){
        listView.getItems().clear();
        for(Plane i : Planes)
            listView.getItems().add(String.valueOf(i.getId()));
        listView.getItems().removeAll();
    }


    public void getViewItem() {
        int index= listView.getSelectionModel().getSelectedIndex();
        Plane plane= Planes.get(index);
        updateDetailedView(plane);
    }
    public void updateDetailedView(Plane plane){
        IDField.setText(String.valueOf(plane.getId()));
        SizeField.setText(String.valueOf(plane.getNo_of_seats()));
        KindField.setText(plane.getKind());
        StatusField.setText(plane.getStatus());
        IncomeField.setText(String.valueOf(plane.getIncome()));
    }
}

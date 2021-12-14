package com.swe2023.gui;

import com.swe2023.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class AdminAirportController {

    @FXML
    public Button updateButton;

    public void goToAdminHome() {
        HelloApplication.showWindow(updateButton, "/admin-home.fxml", "Administrator", 800,640);
    }
}

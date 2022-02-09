package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.Planes_Data.Trip;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Wizard4Controller {

    @FXML
    public Button confirm, back;
    public Label cost;
    public Button logout;

    private UserSession userSession;
    private Trip trip;

    @FXML
    public void initialize() {
        userSession = UserSession.getSession();
        cost.setText((int) userSession.getTotalCost() + " LE");
    }

    public void createTicket() {
        userSession.createTicket();
        HelloApplication.showWindow(back, "/final-wizard.fxml", "FinalWizard",
                null,620, 500);
    }

    public void backToPrevious() {
        HelloApplication.showWindow(back, "/wizard3.fxml", "Wizard3",
                null,620, 500);
    }

    public void signOut(ActionEvent actionEvent) {
        HelloApplication.showWindow(logout, "/signIn.fxml", "Welcome",
                "/signINCSS.css", 950, 650);
    }
}

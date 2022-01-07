package db2023.gui;

import javafx.scene.control.Button;

import java.io.IOException;

public class Managerhome {
    public Button signInButton;


    public void goToSignIn() throws IOException {
        HelloApplication.showWindow(signInButton, "signIn.fxml", "Welcome", 950,650);
    }

    public void goToAddNewBooks() {
        HelloApplication.showWindow(signInButton, "AddNewBooks.fxml","Add New Books", 950,650);
    }

    public void goToModifyExistingBooks() {
        HelloApplication.showWindow(signInButton, "ModifyExistingBooks.fxml","Modify Existing Books", 950,650);
    }
    public void manageFlights(){
        HelloApplication.showWindow(signInButton, "/admin-flight.fxml","Flights Management",950,650);

    }
}

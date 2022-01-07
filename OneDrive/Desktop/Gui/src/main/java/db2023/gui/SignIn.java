package db2023.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignIn {
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button SignUPButton;
    @FXML
    public Button signInButton;
    public void signIn() {
        String username= usernameField.getText();
        String password= passwordField.getText();
        System.out.println(username+" "+password);
    }

    public void ButtonClicked(){
        HelloApplication.showWindow(SignUPButton, "SignUp.fxml", "Sign Up", 950,650);

    }
}

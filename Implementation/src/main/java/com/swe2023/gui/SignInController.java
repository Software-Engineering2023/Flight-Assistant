package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.signUpAndLogin.LoginAndSignUp;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.model.signUpAndLogin.User;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class SignInController {
    @FXML
    public Button signInButton;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField usernameField;
    @FXML
    public Button SignUPButton;


    private String errorMessage;

    public void h1_Clicked() throws URISyntaxException, IOException {
        System.out.println("h1 clicked");
        java.awt.Desktop.getDesktop().browse(new URI("https://www.facebook.com/EGYPTAIR/"));
    }
    public void h2_Clicked() throws URISyntaxException, IOException {
        System.out.println("h2 clicked");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Call Us");
        alert.setHeaderText("Call Us On : ");
        alert.setContentText("Land line: 090070000 \n" + "Mobile: 1717 ");
        alert.showAndWait();
    }

    public void h3_Clicked() throws URISyntaxException, IOException {
        System.out.println("h3 clicked");
        java.awt.Desktop.getDesktop().browse(new URI("https://www.google.com/gmail/about/"));
    }
    public void h4_Clicked() throws URISyntaxException, IOException {
        System.out.println("h4 clicked");
        java.awt.Desktop.getDesktop().browse(new URI("https://www.google.com/maps/place/Alexandria,+Alexandria+Governorate/@31.2240349,29.8148008,11z/data=!3m1!4b1!4m5!3m4!1s0x14f5c49126710fd3:0xb4e0cda629ee6bb9!8m2!3d31.2000924!4d29.9187387"));
    }



    public void signIn() {
        String username= usernameField.getText();
        String password= passwordField.getText();
        User user;
        try{
            checkValidityOfInputs(username, password);
            user=checkUserIsAdmin(username, password);
        }catch (Exception e){
            HelloApplication.showErrorMessage(getErrorMessage());
            return;
        }
        if(user.getAdmin())
            HelloApplication.showWindow(signInButton, "/admin-home.fxml", "Administrator",
                    "/home-admin.css", 950,650);
        else {
            Passenger passenger = new Passenger(user.getEmail(), user.getPassword(), user.getBirthDate(),
                    null, user.getGender());
            passenger.setID(user.getID());
            UserSession.getSession().setUser(passenger);
            HelloApplication.showWindow(signInButton, "/wizard1.fxml","Wizard1",
                    null, 700, 500);
//            UserSession.getSession().setUser(user);

        }
    }

    public void ButtonClicked(){
        HelloApplication.showWindow(SignUPButton, "/user-SignUp.fxml", "Sign Up",
                null, 950,650);

    }

    private User checkUserIsAdmin(String username, String password){
        try {
            User user= new LoginAndSignUp().signIn(username, password);
            if(user ==null)throw new RuntimeException();
            return user;
        }catch (Exception e){
            setErrorMessage("Incorrect Username or Password!");
            throw new RuntimeException();
        }
    }

    private void checkValidityOfInputs(String username, String password){
        boolean userCorrect=(username != null) && username.matches("[A-Za-z0-9_@.]+");
        boolean passwordCorrect= (password != null) && password.length() > 4 ;
        if(!userCorrect || !passwordCorrect){
            setErrorMessage("Username or Password are not valid inputs!");
            throw new RuntimeException();
        }
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.User.UserSession;
import com.swe2023.model.signUpAndLogin.LoginAndSignUp;
import com.swe2023.model.signUpAndLogin.Passenger;
import com.swe2023.model.signUpAndLogin.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        else{
            UserSession.getSession().setUser((Passenger) user);
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
        boolean userCorrect=  (username != null) && username.matches("[A-Za-z0-9_@.]+");
        boolean passwordCorrect=  (password != null) && password.length() > 4 ;
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

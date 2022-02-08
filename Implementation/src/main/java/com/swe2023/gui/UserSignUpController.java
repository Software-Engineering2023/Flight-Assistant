package com.swe2023.gui;

import com.swe2023.HelloApplication;
import com.swe2023.model.security.DataEncryption;
import com.swe2023.model.security.Encryptor;
import com.swe2023.model.signUpAndLogin.LoginAndSignUp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UserSignUpController {
    @FXML
    public Button b1;
    @FXML
    public Button back;
    @FXML
    public PasswordField password1;
    @FXML
    public TextField email;
    @FXML
    public TextField passport;
    @FXML
    public RadioButton rb1;
    @FXML
    public RadioButton rb2;
    @FXML
    public DatePicker birthdate;
    String gender ;
    private String errorMessage;
    String Email;
    String password;
    String passportNumber;
    Date date;

    private final Encryptor encryptor= new DataEncryption();
    public boolean signUp() throws Exception {
         Email= email.getText();
         password= password1.getText();
         passportNumber= passport.getText();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        try{
            checkValidityOfInputs(Email, password,passportNumber,birthdate.getValue());
            radioSelect();
        }catch (Exception e){
            HelloApplication.showErrorMessage(getErrorMessage());
            return false;
        }
        date = Date.from(birthdate.getValue().atStartOfDay(defaultZoneId).toInstant());
        LoginAndSignUp log= new LoginAndSignUp(encryptor.getPublicKey());
        password= encryptor.encrypt(password,log.getPublicKey());
        System.out.println("Email "+Email+" password :"+ password + " date "+date+" passportNumber "+passportNumber+" gender "+gender);
        log.signUp(Email,password,date,passportNumber,gender);
        return true;
    }
    public void SignUpButtonClicked() throws Exception {
        boolean bool=signUp();
        if(bool)
        ButtonClicked();

    }
    public void radioSelect (){
        if(rb1.isSelected())
            gender="M";
       else if(rb2.isSelected())
            gender="F";
        else{
            setErrorMessage("Select your gender please!");
            throw new RuntimeException();
            }
    }
    public void ButtonClicked(){
        HelloApplication.showWindow(b1, "/signIn.fxml", "Welcome",
                null,950,650);

    }

    private void checkValidityOfInputs(String username, String password, String passportNumber, LocalDate date){
        boolean userCorrect=  (username != null) && username.matches("[A-Za-z0-9_@.]+");
        boolean passwordCorrect=  (password != null) && password.length() > 4 ;
        boolean passportNumberCorrect =(passportNumber != null) &&passportNumber.length() > 9 ;
//        System.out.println(" "+userCorrect+" "+passwordCorrect+" "+passportNumberCorrect+" "+date);

        if(!userCorrect || !passwordCorrect|| !passportNumberCorrect||(date==null)){
            setErrorMessage("Username or Password passportNumber or Date are not valid inputs!");
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

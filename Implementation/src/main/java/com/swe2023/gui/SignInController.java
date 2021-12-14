package com.swe2023.gui;

import com.swe2023.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    public Button signInButton;


    public void signIn() throws IOException {
        Stage st= (Stage)signInButton.getScene().getWindow();
        st.close();

        Stage stage= new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/admin-home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 640);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}

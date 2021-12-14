package com.swe2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        showWindow(null, "/signIn.fxml","Welcome", 800,640);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showWindow(Control control, String newWindow ,String title,int v, int v1){
        try {
            if (control !=null) {
                Stage st = (Stage) control.getScene().getWindow();
                st.close();
            }

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(newWindow));
            Scene scene = new Scene(fxmlLoader.load(), v, v1);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
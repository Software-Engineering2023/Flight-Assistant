package com.swe2023;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        showWindow(null, "/signIn.fxml","Welcome","/signINCSS.css", 950,650);
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showWindow(Control control, String newWindow, String title,String cssPath, int v, int v1){
        try {
            if (control !=null) {
                Stage st = (Stage) control.getScene().getWindow();
                st.close();
            }

            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(newWindow));
            Scene scene = new Scene(fxmlLoader.load(), v, v1);
            if(cssPath!=null)
                scene.getStylesheets().add(
                        HelloApplication.class.getResource(cssPath).toExternalForm());
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void showErrorMessage(String errorMessage){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid Operation");
        errorAlert.setContentText(errorMessage);
        errorAlert.showAndWait();
    }
}
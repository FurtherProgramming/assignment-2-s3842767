package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import main.model.UserSession;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminLandingController implements Initializable {
    @FXML
    private Label welcomeMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        welcomeMessage.setText("Welcome, " + UserSession.getName());
    }

    public void signOut(ActionEvent event) throws Exception
    {
        UserSession.signOut();
        nextWindow("/main/ui/home.fxml");
    }

    public void manageDesks(ActionEvent event) throws IOException {
        nextWindow("/main/ui/desk-manager.fxml");
    }

    public void manageEmployees(ActionEvent event) throws Exception
    {
        nextWindow("/main/ui/employee-manager.fxml");
    }

    void nextWindow(String fileLocation) throws IOException {
        Stage window = Main.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource(fileLocation));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }


}

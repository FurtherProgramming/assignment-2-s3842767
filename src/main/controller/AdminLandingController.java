package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import main.Main;
import main.model.UserSession;

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


}

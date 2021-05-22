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

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeLandingController implements Initializable {
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
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/home.fxml"));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Signing Out...");
        stage.show();
    }


}

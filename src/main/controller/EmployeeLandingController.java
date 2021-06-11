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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/*
 * The landing window when Employee successfully logs in, can then move onto the desk booking page or sign out.
 */
public class EmployeeLandingController implements Initializable {
    @FXML
    private Label welcomeMessage;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        welcomeMessage.setText("Welcome, " + UserSession.getName() + ".");
    }

    public void empBookClicked(ActionEvent event) throws Exception
    {
        nextWindow("/main/ui/desk-booking.fxml");
    }

    public void signOut(ActionEvent event) throws Exception
    {
        UserSession.signOut();
        nextWindow("/main/ui/home.fxml");
    }

    public void nextWindow(String location) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(location));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

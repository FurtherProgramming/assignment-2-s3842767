package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;
import main.model.DeskModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeskBookingController implements Initializable {

    private final int NUMBER_OF_DESKS = 15;

    @FXML
    Button desk1;
    @FXML
    Button desk2;
    @FXML
    Button desk3;
    @FXML
    Button desk4;
    @FXML
    Button desk5;
    @FXML
    Button desk6;
    @FXML
    Button desk7;
    @FXML
    Button desk8;
    @FXML
    Button desk9;
    @FXML
    Button desk10;
    @FXML
    Button desk11;
    @FXML
    Button desk12;
    @FXML
    Button desk13;
    @FXML
    Button desk14;
    @FXML
    Button desk15;

    ArrayList<DeskModel> deskList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    public void backToLanding(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/employee-landing.fxml"));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Back to landing...");
        stage.show();
    }
}

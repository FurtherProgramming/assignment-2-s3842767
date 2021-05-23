package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import main.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagerController implements Initializable {

    @FXML
    TableView tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backToLanding(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/admin-landing.fxml"));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Back to landing...");
        stage.show();
    }
}

package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

/*
 * Home page of the application
 */
public class HomeController{
    public void loginClicked(ActionEvent event) throws Exception
    {
        System.out.println("Login button clicked...");
        nextWindow("/main/ui/login.fxml");
    }

    public void registerClicked(ActionEvent event) throws Exception
    {
        System.out.println("Register button clicked...");
        nextWindow("/main/ui/register.fxml");
    }

    void nextWindow(String fileLocation) throws IOException {
        Stage window = Main.getPrimaryStage();
        window.setTitle("HotDesk");
        Parent root = FXMLLoader.load(getClass().getResource(fileLocation));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}

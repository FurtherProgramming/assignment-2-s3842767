package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;

public class HomeController{
    private Stage window;
    private Scene scene;
    private Parent root;
    public void loginClicked(ActionEvent event) throws Exception
    {
        window = Main.getPrimaryStage();
        System.out.println("Login button clicked...");
        root = FXMLLoader.load(getClass().getResource("/main/ui/login.fxml"));
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }

    public void registerClicked(ActionEvent event) throws Exception
    {
        window = Main.getPrimaryStage();
        System.out.println("Register button clicked...");
        root = FXMLLoader.load(getClass().getResource("/main/ui/register.fxml"));
        scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}

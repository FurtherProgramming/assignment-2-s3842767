package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    public void loginClicked(ActionEvent event) throws Exception
    {
        System.out.println("Login button clicked...");
        root = FXMLLoader.load(getClass().getResource("/main/ui/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registerClicked(ActionEvent event) throws Exception
    {
        System.out.println("Register button clicked...");
        root = FXMLLoader.load(getClass().getResource("/main/ui/register.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.InitializeApplication;

import java.sql.Connection;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        InitializeApplication.initializeDesks();
        Parent root = FXMLLoader.load(getClass().getResource("ui/home.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // source : https://stackoverflow.com/questions/35398180/whats-the-best-way-to-switch-between-scenes-in-javafx
    public static Stage getPrimaryStage()
    {
        return primaryStage;
    }

}



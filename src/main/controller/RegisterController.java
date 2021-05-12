package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterController implements Initializable {
    @FXML
    private Label isConnected;
    @FXML
    private TextField empID;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField secretQuestion;
    @FXML
    private TextField txtAnswer;

    private Stage stage;
    private Scene scene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void confirmClicked()
    {
        System.out.println("Confirm Registry...");
    }

    public void cancelClicked(ActionEvent event) throws Exception
    {
        System.out.println("Going back home...");
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void registryForm()
    {

    }


}

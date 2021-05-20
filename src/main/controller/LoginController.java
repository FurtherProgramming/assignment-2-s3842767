package main.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.model.LoginModel;
import main.model.UserSession;
import main.Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public static LoginModel loginModel = new LoginModel();
    @FXML
    private Label isConnected;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;

    // Check database connection
    @Override
    public void initialize(URL location, ResourceBundle resources){
        if (loginModel.isDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

    }
    /* login Action method
       check if user input is the same as database.
     */
    public void Login(ActionEvent event) throws Exception{
        try {
            if (loginModel.isLogin(txtUsername.getText(),txtPassword.getText())){
                isConnected.setText("Logged in successfully");

                if(loginModel.getSession().isAdmin() == true)
                {
                    System.out.println("Is Admin");
                    Parent root = FXMLLoader.load(getClass().getResource("/main/ui/admin-landing.fxml"));
                    Stage stage = Main.getPrimaryStage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    System.out.println("Is NOT Admin!!!!");
                    Parent root = FXMLLoader.load(getClass().getResource("/main/ui/employee-landing.fxml"));
                    Stage stage = Main.getPrimaryStage();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }

            }else{
                isConnected.setText("username and password is incorrect");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    //11.2.3 big sur



}

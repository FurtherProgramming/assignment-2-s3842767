package main.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
import main.model.UserModel;
import main.model.EmployeeManagerModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeManagerController implements Initializable {
    EmployeeManagerModel empManModel = new EmployeeManagerModel();

    @FXML
    TableView<UserModel> userTable;

    @FXML
    TableColumn<UserModel, String> idCol;

    @FXML
    TableColumn<UserModel, String> ageCol;

    @FXML
    TableColumn<UserModel, String> nameCol;

    @FXML
    TableColumn<UserModel, String> surnameCol;

    @FXML
    TableColumn<UserModel, String> usernameCol;

    @FXML
    TableColumn<UserModel, String> passwordCol;

    @FXML
    TableColumn<UserModel, String> roleCol;

    @FXML
    TableColumn<UserModel, String> questionCol;

    @FXML
    TableColumn<UserModel, String> answerCol;

    @FXML
    TableColumn<UserModel, Boolean> adminCol;

    ObservableList<UserModel> userList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userList = empManModel.initializeUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // id
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        // name
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        // surname
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        // age
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));

        // username
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        // password
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        // role
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));

        // secret question
        questionCol.setCellValueFactory(new PropertyValueFactory<>("secretQuestion"));

        // secret answer
        answerCol.setCellValueFactory(new PropertyValueFactory<>("secretAnswer"));

        // admin
        adminCol.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        userTable.setItems(userList);



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

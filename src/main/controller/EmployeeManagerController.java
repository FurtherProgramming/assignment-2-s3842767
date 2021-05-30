package main.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
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

    @FXML
    Label statusLabel;

    ObservableList<UserModel> userList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userList = empManModel.initializeUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userTable.setEditable(true);

        // id
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // name
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // surname
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // age
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // username
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // password
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // role
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // secret question
        questionCol.setCellValueFactory(new PropertyValueFactory<>("secretQuestion"));
        questionCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // secret answer
        answerCol.setCellValueFactory(new PropertyValueFactory<>("secretAnswer"));
        answerCol.setCellFactory(TextFieldTableCell.forTableColumn());

        // admin
        adminCol.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));

        userTable.setItems(userList);



    }

    public void saveChanges(ActionEvent event) throws Exception
    {
        if(empManModel.saveChangesToTable(userList))
        {
            statusLabel.setText("Updated Successfully");
        }
        else
        {
            statusLabel.setText("Something went wrong...");
        }
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

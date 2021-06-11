package main.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import main.model.DeskBookModel;
import main.model.DeskModel;
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
    TextField exportTextField;

    @FXML
    Label statusLabel;

    ObservableList<UserModel> userList;

    private final String exportLocation = "D:\\Employees.csv";


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            userList = empManModel.initializeUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }

        userTable.setEditable(true);

        // id IS NOT NULL!
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setCellFactory(TextFieldTableCell.forTableColumn());
        idCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setId(event.getNewValue());
            }
        });

        // name
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setName(event.getNewValue());
            }
        });

        // surname
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        surnameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setSurname(event.getNewValue());
            }
        });

        // age
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn());
        ageCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setAge(event.getNewValue());
            }
        });

        // username
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        usernameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        usernameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setUsername(event.getNewValue());
            }
        });

        // password
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setPassword(event.getNewValue());
            }
        });

        // role
        roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
        roleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        roleCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setRole(event.getNewValue());
            }
        });

        // secret question
        questionCol.setCellValueFactory(new PropertyValueFactory<>("secretQuestion"));
        questionCol.setCellFactory(TextFieldTableCell.forTableColumn());
        questionCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setSecretQuestion(event.getNewValue());
            }
        });

        // secret answer
        answerCol.setCellValueFactory(new PropertyValueFactory<>("secretAnswer"));
        answerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        answerCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<UserModel, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<UserModel, String> event) {
                UserModel user = event.getRowValue();
                user.setSecretAnswer(event.getNewValue());
            }
        });
        userTable.setItems(userList);
    }

    // Saves changes made to the employee table
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

    // Deletes a selected employee from records.
    public void deleteButton(ActionEvent event) throws Exception
    {
        if(userTable.getSelectionModel().getSelectedItem() != null)
        {
            DeskBookModel deskBookModel = new DeskBookModel();
            UserModel user = userTable.getSelectionModel().getSelectedItem();
            if(empManModel.deleteItemFromTable(user.getId()))
            {
                String name = user.getName();
                userTable.getItems().remove(user);
                deskBookModel.removeTable(user.getId());
                statusLabel.setText("Deleted " + name + " successfully");
            }
            else
            {
                statusLabel.setText("Something went wrong...");
            }
        }
        else
            statusLabel.setText("Nothing Selected");
    }

    // Export employee data to csv
    public void exportToCsv(ActionEvent event) throws Exception
    {
        String location;
        if(exportTextField.getText().isEmpty())
            location = exportLocation;
        else
            location = exportTextField.getText();

        if(empManModel.exportEmployeeListToCSV(location, userList))
            statusLabel.setText("Export Successful");
        else
            statusLabel.setText("Export Unsuccessful");
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
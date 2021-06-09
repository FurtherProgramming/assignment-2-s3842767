package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import main.model.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeskBookingController implements Initializable {

    InitializeApplication initApp = new InitializeApplication();
    DeskBookModel deskBookModel = new DeskBookModel();
    int cat = 1;
    private final int NUMBER_OF_DESKS = 15;
    private final String OCCUPIED = "red";
    private final String FREE = "green";
    private final String LOCKED = "orange";
    public static ArrayList<DeskModel> deskList = new ArrayList<>();
    public ArrayList<Button> deskButtonList = new ArrayList();

    @FXML
    Button desk1;
    @FXML
    Button desk2;
    @FXML
    Button desk3;
    @FXML
    Button desk4;
    @FXML
    Button desk5;
    @FXML
    Button desk6;
    @FXML
    Button desk7;
    @FXML
    Button desk8;
    @FXML
    Button desk9;
    @FXML
    Button desk10;
    @FXML
    Button desk11;
    @FXML
    Button desk12;
    @FXML
    Button desk13;
    @FXML
    Button desk14;
    @FXML
    Button desk15;

    @FXML
    Label status;
    @FXML
    Label selectedButton;

    @FXML
    TextField exportTextField;

    final String exportLocation = "D:\\desk_data.csv";
    boolean isLocked = false;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selectedButton.setText("None");
        status.setText("Status");

        deskButtonList.add(desk1);
        deskButtonList.add(desk2);
        deskButtonList.add(desk3);
        deskButtonList.add(desk4);
        deskButtonList.add(desk5);
        deskButtonList.add(desk6);
        deskButtonList.add(desk7);
        deskButtonList.add(desk8);
        deskButtonList.add(desk9);
        deskButtonList.add(desk10);
        deskButtonList.add(desk11);
        deskButtonList.add(desk12);
        deskButtonList.add(desk13);
        deskButtonList.add(desk14);
        deskButtonList.add(desk15);

        try {
            deskList = initApp.initializeDesks(deskButtonList);
        } catch (Exception e) {
            System.out.println("Error in initialize");
            e.printStackTrace();
        }

        // Makes the buttons colourful
        for(int i = 0; i < deskList.size(); i++)
        {

            // Set Colour of Buttons
            if(!deskList.get(i).getOccupied())
                deskList.get(i).setColour(FREE);
            if(deskList.get(i).getOccupied())
                deskList.get(i).setColour(OCCUPIED);
            if(deskList.get(i).getLocked())
                deskList.get(i).setColour(LOCKED);

            // Checks to see if desk lockdown is in place
            if(deskList.get(i).getLocked())
                isLocked = true;

            deskList.get(i).setButtonText(deskList.get(i).getId());

            desk1 = deskList.get(0).getButton();
            desk2 = deskList.get(1).getButton();
            desk3 = deskList.get(2).getButton();
            desk4 = deskList.get(3).getButton();
            desk5 = deskList.get(4).getButton();
            desk6 = deskList.get(5).getButton();
            desk7 = deskList.get(6).getButton();
            desk8 = deskList.get(7).getButton();
            desk9 = deskList.get(8).getButton();
            desk10 = deskList.get(9).getButton();
            desk11 = deskList.get(10).getButton();
            desk12 = deskList.get(11).getButton();
            desk13 = deskList.get(12).getButton();
            desk14 = deskList.get(13).getButton();
            desk15 = deskList.get(14).getButton();
        }
    }

    // updates the GUI by reinitializing the desk buttons after the user commits an action
    public void updateGUI()
    {
        selectedButton.setText("None");

        try {
            deskList = initApp.initializeDesks(deskButtonList);
        } catch (Exception e) {
            System.out.println("Error in initialize");
            e.printStackTrace();
        }

        for(int i = 0; i < deskList.size(); i++)
        {
            // Set Colour of Buttons
            if(!deskList.get(i).getOccupied())
                deskList.get(i).setColour(FREE);
            if(deskList.get(i).getOccupied())
                deskList.get(i).setColour(OCCUPIED);
            if(deskList.get(i).getLocked())
                deskList.get(i).setColour(LOCKED);


            // Checks to see if desk lockdown is in place
            if(deskList.get(i).getLocked())
                isLocked = true;

            deskList.get(i).setButtonText(deskList.get(i).getId());

            desk1 = deskList.get(0).getButton();
            desk2 = deskList.get(1).getButton();
            desk3 = deskList.get(2).getButton();
            desk4 = deskList.get(3).getButton();
            desk5 = deskList.get(4).getButton();
            desk6 = deskList.get(5).getButton();
            desk7 = deskList.get(6).getButton();
            desk8 = deskList.get(7).getButton();
            desk9 = deskList.get(8).getButton();
            desk10 = deskList.get(9).getButton();
            desk11 = deskList.get(10).getButton();
            desk12 = deskList.get(11).getButton();
            desk13 = deskList.get(12).getButton();
            desk14 = deskList.get(13).getButton();
            desk15 = deskList.get(14).getButton();
        }
    }

    public boolean bookButton(ActionEvent event) throws SQLException {
        if(selectedButton.getText() == "None")
        {
            status.setText("No desk selected");
            return false;
        }

        if(deskBookModel.bookTable(selectedButton.getText(), UserSession.getId()))
        {
            status.setText(deskBookModel.statusMessage);
            updateGUI();
            return true;
        }
        else
        {
            status.setText("An error has occurred");
            return false;
        }
    }

    public void lockDownTables(ActionEvent event) throws SQLException {
        if(deskBookModel.covidLockTables(deskList, isLocked))
        {
            status.setText(deskBookModel.statusMessage);
        }
        else
        {
            status.setText(deskBookModel.statusMessage);;
        }
        updateGUI();
    }

    public void exportDataToCsv(ActionEvent event) throws Exception
    {
        String location;
        if(exportTextField.getText().isEmpty())
        {
            location = exportLocation;
        }
        else
        {
            location = exportTextField.getText();
        }

        if(deskBookModel.exportDeskDataToCSV(location, deskList))
        {
            status.setText("Export Successful");
        }
        else
        {
            status.setText("Export Unsuccessful");
        }
    }

    public boolean remBook(ActionEvent event) throws SQLException
    {

        if(deskBookModel.removeTable(UserSession.getId()))
        {
            status.setText(deskBookModel.statusMessage);
            updateGUI();
            return true;
        }
        else
        {
            status.setText(deskBookModel.statusMessage);
            return false;
        }
    }

    public void rejectBooking(ActionEvent event) throws SQLException {
        if(selectedButton.getText() == "None")
        {
            status.setText("No desk selected");
        }
        else
        {
            for(DeskModel desk : deskList)
            {
                if(desk.getId() == selectedButton.getText())
                {
                    if(deskBookModel.removeTable(desk.getEmpID()))
                        status.setText("Rejected Successfully");
                    else
                        status.setText("Something went wrong");
                }
            }
        }
        updateGUI();
    }



    // The following are just action methods for the desk buttons

    public void desk1Clicked(ActionEvent event)
    {
        selectedButton.setText(desk1.getText());
    }

    public void desk2Clicked(ActionEvent event)
    {
        selectedButton.setText(desk2.getText());
    }

    public void desk3Clicked(ActionEvent event)
    {
        selectedButton.setText(desk3.getText());
    }

    public void desk4Clicked(ActionEvent event)
    {
        selectedButton.setText(desk4.getText());
    }

    public void desk5Clicked(ActionEvent event)
    {
        selectedButton.setText(desk5.getText());
    }

    public void desk6Clicked(ActionEvent event)
    {
        selectedButton.setText(desk6.getText());
    }

    public void desk7Clicked(ActionEvent event)
    {
        selectedButton.setText(desk7.getText());
    }

    public void desk8Clicked(ActionEvent event)
    {
        selectedButton.setText(desk8.getText());
    }

    public void desk9Clicked(ActionEvent event)
    {
        selectedButton.setText(desk9.getText());
    }

    public void desk10Clicked(ActionEvent event)
    {
        selectedButton.setText(desk10.getText());
    }

    public void desk11Clicked(ActionEvent event)
    {
        selectedButton.setText(desk11.getText());
    }

    public void desk12Clicked(ActionEvent event)
    {
        selectedButton.setText(desk12.getText());
    }

    public void desk13Clicked(ActionEvent event)
    {
        selectedButton.setText(desk13.getText());
    }

    public void desk14Clicked(ActionEvent event)
    {
        selectedButton.setText(desk14.getText());
    }

    public void desk15Clicked(ActionEvent event)
    {
        selectedButton.setText(desk15.getText());
    }

    public void backToLanding(ActionEvent event) throws Exception
    {
        Parent root;
        if(UserSession.isAdmin())
            root = FXMLLoader.load(getClass().getResource("/main/ui/admin-landing.fxml"));
        else
            root = FXMLLoader.load(getClass().getResource("/main/ui/employee-landing.fxml"));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Back to landing...");
        stage.show();
    }
}

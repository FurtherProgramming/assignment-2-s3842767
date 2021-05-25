package main.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;
import main.model.DeskModel;
import main.model.InitializeApplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeskBookingController implements Initializable {

    InitializeApplication initApp = new InitializeApplication();
    int cat = 1;
    private final int NUMBER_OF_DESKS = 15;
    private final String occupiedColour = "red";
    private final String freeColour = "green";
    private final String lockedColour = "orange";
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


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selectedButton.setText("None");

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

        for(int i = 0; i < deskList.size(); i++)
        {

            // Set Colour of Buttons
            if(!deskList.get(i).getOccupied())
            {
                deskList.get(i).setColour(freeColour);
            }
            if(deskList.get(i).getOccupied())
            {
                deskList.get(i).setColour(occupiedColour);
            }
            if(deskList.get(i).getLocked())
            {
                deskList.get(i).setColour(lockedColour);
            }

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
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/employee-landing.fxml"));
        Stage stage = Main.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        System.out.println("Back to landing...");
        stage.show();
    }
}

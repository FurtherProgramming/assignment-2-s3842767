package main.model;

import javafx.scene.control.Button;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeskModel {
    static Connection connection;
    private String id;
    private boolean isOccupied, isLocked;
    private String empID;
    private Button deskButton;

    /*
     * Colours of the desk representing their state
     * Red - Occupied
     * Green - Free
     * Orange - Covid Locked
     */
    private final String red = "-fx-background-color: #ff0000;";
    private final String green = "-fx-background-color: #00ff22;";
    private final String orange = "-fx-background-color: #ff9d00;";


    public DeskModel(String id, boolean isOccupied, boolean isLocked, String empID, Button deskButton) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.isLocked = isLocked;
        this.empID = empID;
        this.deskButton = deskButton;
    }

    public String getId()
    {
        return id;
    }

    public void setColour(String colour)
    {
        if(colour == "green") { deskButton.setStyle(green); }
        if(colour == "red") { deskButton.setStyle(red); }
        if(colour == "orange") { deskButton.setStyle(orange); }
    }

    public boolean getOccupied()
    {
        return isOccupied;
    }

    public boolean getLocked()
    {
        return isLocked;
    }

    public String getEmpID()
    {
        return empID;
    }

    public void setButtonText(String text) {
        deskButton.setText(text);
    }

    public Button getButton() {
        return deskButton;
    }

    public static ResultSet getDeskResultSet() throws Exception
    {
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String query = "select * from desk_booking";

        preparedStatement = connection.prepareStatement(query);
        result = preparedStatement.executeQuery();

        return result;
    }


}

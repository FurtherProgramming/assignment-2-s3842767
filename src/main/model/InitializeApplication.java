package main.model;

import javafx.scene.control.Button;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InitializeApplication {
    private final int NUMBER_OF_DESKS = 15;

    Connection connection;

    public InitializeApplication(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    /*
     * Initializes the desks by implementing desk info from desk_list into deskList
     */
    public ArrayList initializeDesks(ArrayList<Button> deskButtonList) throws Exception
    {
        int deskButtonIndex = 0;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String query = "select * from desk_booking";

        ArrayList<DeskModel> deskList = new ArrayList<>();

        try
        {
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while(result.next())
            {
                deskList.add(new DeskModel(result.getString("deskID"), result.getBoolean("isOccupied"), result.getBoolean("isLocked"), result.getString("employee"), deskButtonList.get(deskButtonIndex)));
                deskButtonIndex++;
            }
        }
        catch (Exception e)
        {
            System.out.println("Error in  init Model");
            throw e;
        }
        finally
        {
            preparedStatement.close();
            result.close();
        }
        return deskList;
    }
}

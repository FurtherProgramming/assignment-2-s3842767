package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeManagerModel {

    Connection connection;

    public EmployeeManagerModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public ObservableList<UserModel> initializeUsers() throws SQLException {
        ObservableList<UserModel> userList = FXCollections.observableArrayList();

        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String query = "select * from Employee";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();

            while(result.next())
            {
                userList.add(new UserModel(result.getString("id"), result.getString("age"), result.getString("name"), result.getString("surname"), result.getString("username"), result.getString("password"), result.getString("role"), result.getString("secret_question"), result.getString("secret_answer"), result.getBoolean("isAdmin")));
            }
        }
        catch(Exception e)
        {
            userList = null;
        } finally
        {
            preparedStatement.close();
            result.close();
        }
        return userList;
    }



}

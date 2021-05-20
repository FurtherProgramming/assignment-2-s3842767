package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class RegisterModel {
    Connection connection;

    public RegisterModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean isRegDbConnected(){
        try {
            return !connection.isClosed();
        }
        catch(Exception e){
            return false;
        }
    }

    public Boolean register(int id, String name, String sure_name,
                            String age, String username, String password, String jobRole, String secret_question,
                            String secret_answer, boolean isAdmin)

    {
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String query = "insert into employee values (empID, firstName, lastName, age, txtUsername, txtPassword, jobRole, secretQuestion, txtAnswer, isAdmin)";

        try
        {
            //System.out.println("All good :)");
            preparedStatement = connection.prepareStatement(query);
            result = preparedStatement.executeQuery();
            return true;
        }
        catch (Exception e)
        {
            System.out.println("Something Happened...");
            return false;
        }

    }
}

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

    public Boolean register()
    {
        PreparedStatement preparedStatement = null;
        String query = "insert into employee values (empID, firstName, lastName, txtUsername, txtPassword, secretQuestion, txtAnswer)";
        return false;
    }
}

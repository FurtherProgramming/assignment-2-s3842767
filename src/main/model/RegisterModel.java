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

    public Boolean register(String id, String name, String sure_name,
                            String age, String username, String password, String jobRole, String secret_question,
                            String secret_answer, boolean isAdmin) throws Exception

    {
        boolean reg;
        PreparedStatement preparedStatement = null;
        // String query = "insert into employee (id, name, surname, age, username, password, role, secret_question, secret_answer, isAdmin) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String query = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try
        {
            //System.out.println("All good :)");
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, sure_name);
            preparedStatement.setString(4, age);
            preparedStatement.setString(5, username);
            preparedStatement.setString(6, password);
            preparedStatement.setString(7, jobRole);
            preparedStatement.setString(8, secret_question);
            preparedStatement.setString(9, secret_answer);
            preparedStatement.setBoolean(10, isAdmin);
            preparedStatement.executeUpdate();
            reg = true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            reg = false;
        } finally {
            preparedStatement.close();
        }
        return reg;
    }
}

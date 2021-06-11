package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

 public class RegisterModel {
    Connection connection;

    final String DUP_ID_MESSAGE = "Someone with the ID already exists";
    final String ERROR_MESSAGE = "An error has occurred";
    String message;

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

    public String getMessage() {
        return message;
    }

     /*
      * After checks are passed, user details input in the controller are registered and input into the database.
      */
    public Boolean register(String id, String name, String sure_name,
                            String age, String username, String password, String jobRole, String secret_question,
                            String secret_answer, boolean isAdmin) throws Exception

    {
        boolean reg = false;
        PreparedStatement preparedStatement = null;
        String query = "insert into employee values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
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
            preparedStatement.setString(11, null);
            if(!checkForDuplicateID(id)){
                preparedStatement.executeUpdate();
                reg = true;
            }
            else {
                message = DUP_ID_MESSAGE;
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            message = ERROR_MESSAGE;
        } finally {
            preparedStatement.close();
        }
        return reg;
    }

     /*
      * Checks for duplicate IDs
      */
    boolean checkForDuplicateID(String id) throws SQLException {
        boolean dp = false;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;
        String query = "select * from employee where id = ?";

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            result = preparedStatement.executeQuery();
            if(result.next()) {
                dp = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            result.close();
        }
        return dp;
    }
}

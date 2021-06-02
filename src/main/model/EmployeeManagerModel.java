package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.SQLConnection;

import java.io.*;
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

    public boolean saveChangesToTable(ObservableList<UserModel> userList) throws SQLException
    {
        boolean update = false;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String getQuery = "select * from Employee";
        String updateQuery = "update Employee set id = ?, name = ?, surname = ?, age = ?, username = ?, password = ?, role = ?, secret_question = ?, secret_answer = ? where  id = ?";

        try
        {
            preparedStatement = connection.prepareStatement(getQuery);
            result = preparedStatement.executeQuery();

            preparedStatement = connection.prepareStatement(updateQuery);
            for(int i = 0; i < userList.size(); i++)
            {
                preparedStatement.setString(1, userList.get(i).getId());
                preparedStatement.setString(2, userList.get(i).getName());
                preparedStatement.setString(3, userList.get(i).getSurname());
                preparedStatement.setString(4, userList.get(i).getAge());
                preparedStatement.setString(5, userList.get(i).getUsername());
                preparedStatement.setString(6, userList.get(i).getPassword());
                preparedStatement.setString(7, userList.get(i).getRole());
                preparedStatement.setString(8, userList.get(i).getSecretQuestion());
                preparedStatement.setString(9, userList.get(i).getSecretAnswer());
                preparedStatement.setString(10, userList.get(i).getId());
                preparedStatement.executeUpdate();
            }

            update = true;
        }
        catch (Exception e)
        {
            System.out.println(e);
            update = false;
        } finally {
            preparedStatement.close();
            result.close();
        }
        return update;
    }

    public boolean deleteItemFromTable(String id)
    {
        boolean delete = false;
        PreparedStatement preparedStatement = null;

        String query = "delete from Employee where id = ?";

        try
        {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            delete = true;
        } catch (Exception e) {
            e.printStackTrace();
            delete = false;
        }
        return delete;
    }

    public boolean exportEmployeeListToCSV(String exportLocation, ObservableList<UserModel> userList) throws IOException {
        Boolean wrote = false;
        Writer writer = null;
        try
        {
            File file = new File(exportLocation);
            if(file.createNewFile())
            {
                System.out.println("Successfully created file");
            }
            else
            {
                System.out.println("Cant create file, possible already exists");
            }

            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < userList.size(); i++)
            {
                String text = userList.get(i).getId() + "," + userList.get(i).getName() + "," + userList.get(i).getSurname() + "," + userList.get(i).getAge() + "," + userList.get(i).getUsername() + ","
                        + userList.get(i).getPassword() + "," + userList.get(i).getRole() + "," + userList.get(i).getSecretQuestion() + "," + userList.get(i).getSecretAnswer() + "\n";
                writer.write(text);
            }
            wrote = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            writer.flush();
            writer.close();
        }
        return wrote;
    }
}

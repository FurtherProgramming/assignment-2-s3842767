package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class ResetPasswordModel {
    final int RAND_PASS_SIZE = 8;
    Connection connection;
    UserSession session;

    String newPassword;

    public ResetPasswordModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public Boolean getUser(String user) throws Exception {
        if(user.isEmpty())
        {
            return false;
        }
        boolean u = false;

        PreparedStatement preparedStatement = null;
        ResultSet resultSet=null;
        String query = "select * from employee where username = ?";
        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("got username");
                this.session = new UserSession(resultSet.getString("id"), resultSet.getString("age"), resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("role"), resultSet.getString("secret_question"), resultSet.getString("secret_answer"), resultSet.getBoolean("isAdmin"));
                u = true;
            }
            else{
                u = false;
            }
        }
        catch (Exception e)
        {
            u = false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        return u;
    }

    public boolean resetPassword(String username, String answer) throws SQLException {
        System.out.println(username);
        System.out.println(UserSession.getSecretAnswer());
        boolean reset = false;
        newPassword = getNewPassword();
        System.out.println(newPassword);
        if(answer.equals(UserSession.getSecretAnswer()))
        {
            PreparedStatement preparedStatement = null;
            String query = "update employee set password = ? where username = ?";

            try{
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, newPassword);
                preparedStatement.setString(2, username);
                preparedStatement.executeUpdate();
                reset = true;
            } catch (SQLException e) {
               e.printStackTrace();
            } finally {
                preparedStatement.close();
            }
        }

        return reset;
    }

    // Source: https://www.programiz.com/java-programming/examples/generate-random-string
    public String getNewPassword()
    {
        String CHARS = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random rand = new Random();
        StringBuilder randString = new StringBuilder();

        for(int i = 0; i < RAND_PASS_SIZE; i++)
        {
            int idx = rand.nextInt(CHARS.length());

            char c = CHARS.charAt(idx);

            randString.append(c);
        }
        String randPassword = randString.toString();
        return randPassword;
    }

    public UserSession getSession()
    {
        return session;
    }

    public String getPassword()
    {
        return newPassword;
    }

}

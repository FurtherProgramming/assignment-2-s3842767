package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSession
{
    Connection connection;
    public ResultSet session;

    private int id, age;
    private String name, surname, username, password, role, secretQuestion, secretAnswer;
    private boolean isAdmin;

    public UserSession(int id, int age, String name, String surname, String username, String password, String role, String secretQuestion, String secretAnswer, boolean isAdmin) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.role = role;
        this.secretQuestion = secretQuestion;
        this.secretAnswer = secretAnswer;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

//        public UserSession(){
//        connection = SQLConnection.connect();
//        if (connection == null)
//            System.exit(1);
//    }

//    public boolean isAdmin() throws Exception
//    {
//        if(session.getBoolean("isAdmin") == true)
//        {
//            return true;
//        }
//        else
//        {
//            return false;
//        }
//    }

}


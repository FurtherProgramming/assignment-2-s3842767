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

    private static int id;
    private static String age, name, surname, username, password, role, secretQuestion, secretAnswer;
    private static boolean isAdmin;

    public UserSession(int id, String age, String name, String surname, String username, String password, String role, String secretQuestion, String secretAnswer, boolean isAdmin) {
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

    public static int getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static boolean isAdmin() {
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


package main.model;

import main.SQLConnection;
import org.sqlite.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * UserSession temporarily stores the information of a logged in user or when a user wishes to reset their password for
 * use with the functionalities of the program such as desk booking and management. When user logs out all information
 * within UserSession is cleared.
 */
public class UserSession
{
    private static String id;
    private static String age, name, surname, username, password, role, secretQuestion, secretAnswer;
    private static boolean isAdmin;

    public UserSession(String id, String age, String name, String surname, String username, String password, String role, String secretQuestion, String secretAnswer, boolean isAdmin) {
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

    public static String getId() {
        return id;
    }

    public static String getName() {
        return name;
    }

    public static String getUsername() {
        return username;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static String getSecretQuestion()
    {
        return secretQuestion;
    }

    public static String getSecretAnswer()
    {
        return secretAnswer;
    }

    public static void signOut()
    {
        id = null;
        age = null;
        name = null;
        surname = null;
        username = null;
        password = null;
        role = null;
        secretQuestion = null;
        secretAnswer = null;
        isAdmin = false;
    }


}


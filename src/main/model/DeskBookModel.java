package main.model;

import main.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeskBookModel {
    Connection connection;

    private final String success = "success";
    private final String occupied = "occupied";
    private final String locked = "locked";
    private final String alreadyBooked = "already";

    public DeskBookModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    public boolean bookTable(String deskID, String userID) throws SQLException {
        boolean bk;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String searchQuery = "select * from desk_booking where deskID = ?";
        String updateQuery = "update desk_booking set isOccupied = 1, employee = ? where deskID = ?";

        try {

            preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, deskID);
            result = preparedStatement.executeQuery();

            if(!checkOccupancy(result) && !checkLocked(result) && !checkBooking(userID))
            {
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, userID);
                preparedStatement.setString(2, deskID);
                preparedStatement.executeUpdate();
                bk = true;
            }
            else
            {
                bk = false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            bk = false;
        } finally {
            preparedStatement.close();
            result.close();
        }
        return bk;
    }

    public boolean removeTable(String userID) throws SQLException {
        boolean rm = false;
        PreparedStatement preparedStatement = null;

        String query = "update desk_booking set isOccupied = 0, employee = NULL where employee = ?";

        try
        {
            if(checkBooking(userID))
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userID);
                preparedStatement.executeUpdate();
                rm = true;
            }
            else
            {
                rm = false;
            }
        } catch(Exception e)
        {
            rm = false;
        } finally {
            preparedStatement.close();
        }
        return rm;
    }

    public boolean checkOccupancy(ResultSet result) throws SQLException {
        if(result.getBoolean("isOccupied"))
        {
            return true;
        }
        return false;
    }

    public boolean checkLocked(ResultSet result) throws SQLException {
        if(result.getBoolean("isLocked"))
        {
            return true;
        }
        return false;
    }

    public boolean checkBooking(String userID) throws SQLException {
        boolean chk;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String query = "select * from desk_booking where employee = ?";

        try
        {
            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userID);

            result = preparedStatement.executeQuery();

            if(result.next())
            {
                chk = true; // Employee already exists
            }
            else
            {
                chk = false; // Employee doesn't yet exist on table
            }
        } catch (Exception e)
        {
            System.out.println(e);
            return true;
        } finally {
            preparedStatement.close();
            result.close();
        }

        return chk;
    }
}

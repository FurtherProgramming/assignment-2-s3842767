package main.model;

import javafx.collections.ObservableList;
import main.SQLConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeskBookModel {
    Connection connection;

    private final String CONFIRM_MESSAGE = "Success";
    private final String BOOKED_ALREADY_MESSAGE = "You have already booked a seat";
    private final String OCCUPIED_MESSAGE = "Desk is already occupied";
    private final String LOCKDOWN_MESSAGE = "Desk is currently locked down";
    private final String PREV_BOOKED_MESSAGE = "You have booked this desk previously";
    private final String ERROR_MESSAGE = "An error has occurred";
    private final String NO_DESK_MESSAGE = "No desk selected";

    public String statusMessage;

    public DeskBookModel(){

        connection = SQLConnection.connect();
        if (connection == null)
            System.exit(1);

    }

    /*
     * Books an available table, returns false if desk is unavailable.
     */
    public boolean bookTable(String deskID, String userID) throws SQLException {
        boolean bk = false;
        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String searchQuery = "select * from desk_booking where deskID = ?";
        String updateQuery = "update desk_booking set isOccupied = 1, employee = ? where deskID = ?";
        String setPrevDeskQuery = "update Employee set prev_seat = ? where id = ?";

        try {
            preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1, deskID);
            result = preparedStatement.executeQuery();
            if(!checkOccupancy(result) && !checkLocked(result) && !checkBooking(userID) && !checkPrevBooking(userID, deskID))
            {
                // Update desk table with the booking made
                preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setString(1, userID);
                preparedStatement.setString(2, deskID);
                preparedStatement.executeUpdate();
                // Update prev_desk column in employee table with the table just booked so it cant be booked the next time.
                preparedStatement = connection.prepareStatement(setPrevDeskQuery);
                preparedStatement.setString(1, deskID);
                preparedStatement.setString(2, userID);
                preparedStatement.executeUpdate();
                statusMessage = CONFIRM_MESSAGE;
                bk = true;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            statusMessage = ERROR_MESSAGE;
        } finally {
            preparedStatement.close();
            result.close();
        }
        return bk;
    }

    /*
     * If user has booked a table, removes the booking
     */
    public boolean removeTable(String userID) throws SQLException {
        boolean rm = false;
        PreparedStatement preparedStatement = null;

        String query = "update desk_booking set isOccupied = 0, employee = NULL where employee = ?";

        if(checkBooking(userID))
        {
            try
            {
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, userID);
                preparedStatement.executeUpdate();
                statusMessage = CONFIRM_MESSAGE;
                rm = true;
            } catch(Exception e)
            {
                e.printStackTrace();
                statusMessage = ERROR_MESSAGE;
            } finally {
                preparedStatement.close();
            }
        }
        else
        {
            statusMessage = NO_DESK_MESSAGE;
        }
        return rm;
    }

    /*
     * Checks to see if desk is currently occupied or not
     */
    public boolean checkOccupancy(ResultSet result) throws SQLException {
        boolean oc = false;
        if(result.getBoolean("isOccupied"))
        {
            oc = true;
            statusMessage = OCCUPIED_MESSAGE;
        }
        return oc;
    }

    /*
     * Checks to see if desk trying to book is COVID-locked.
     */
    public boolean checkLocked(ResultSet result) throws SQLException {
        boolean lk = false;
        if(result.getBoolean("isLocked"))
        {
            lk = true;
            statusMessage = LOCKDOWN_MESSAGE;
        }
        return lk;
    }

    /*
     * Checks to see if user has sat at the desk previously
     */
    public boolean checkPrevBooking(String userID, String deskID) throws SQLException {
        boolean bk = false;

        PreparedStatement preparedStatement = null;
        ResultSet result = null;

        String getEmpQuery = "select * from employee where id = ?";

        try
        {
            preparedStatement = connection.prepareStatement(getEmpQuery);
            preparedStatement.setString(1, userID);
            result = preparedStatement.executeQuery();

            if(result.getString("prev_seat") != null)
            {
                if(result.getString("prev_seat").equals(deskID))
                {
                    statusMessage = PREV_BOOKED_MESSAGE;
                    bk = true;
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            preparedStatement.close();
            result.close();
        }
        return bk;
    }

    /*
     * Checks to see if employee has already booked a desk
     */
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
                statusMessage = BOOKED_ALREADY_MESSAGE;
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

    /*
     * Locks every second desk within the Desk List, deallocates seat if desk is occupied. If desks are already locked
     * then unlocks the desks.
     */
    public boolean covidLockTables(ArrayList<DeskModel> deskList, boolean isLocked) throws SQLException {
        boolean locked = false;
        for(int i = 0; i < deskList.size(); i++)
        {
            if(i % 2 == 0)
            {
                if(lockDesk(deskList.get(i).getId(), isLocked))
                    locked = true;
                else
                    locked = false;
            }
        }
        return locked;
    }

    // Locks the tables.
    private boolean lockDesk(String deskID, boolean isLocked) throws SQLException {
        boolean locked = false;
        PreparedStatement preparedStatement = null;
        String lockQuery;

        if(isLocked)
            lockQuery = "update desk_booking set isOccupied = 0, employee = NULL, isLocked = 0 where deskID = ?";
        else
            lockQuery = "update desk_booking set isOccupied = 0, employee = NULL, isLocked = 1 where deskID = ?";

        try
        {
            preparedStatement = connection.prepareStatement(lockQuery);
            preparedStatement.setString(1, deskID);
            preparedStatement.executeUpdate();
            locked = true;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            preparedStatement.close();
        }
        return locked;
    }

    /*
     * Exports Desk information to csv
     */
    public boolean exportDeskDataToCSV(String exportLocation, ArrayList<DeskModel> deskList) throws IOException {
        Boolean wrote = false;
        Writer writer = null;
        try
        {
            File file = new File(exportLocation);
            if(file.createNewFile())
                System.out.println("Successfully created file");
            else
                System.out.println("Cant create file, possible already exists");

            writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < deskList.size(); i++)
            {
                String text = deskList.get(i).getId() + "," + deskList.get(i).getOccupied() + "," + deskList.get(i).getLocked() + "," + deskList.get(i).getEmpID()  + "\n";
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

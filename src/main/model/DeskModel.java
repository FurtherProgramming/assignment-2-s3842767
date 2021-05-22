package main.model;

import javafx.scene.control.Button;

public class DeskModel {
    private static String id;
    private static boolean isOccupied, isLocked;
    private static String empID;
    private static Button deskButton;

    private final String red = "-fx-background-color: #ff0000;";
    private final String green = "-fx-background-color: #00ff22;";
    private final String orange = "-fx-background-color: #ff9d00;";


    public DeskModel(String id, boolean isOccupied, boolean isLocked, String empID, Button deskButton) {
        this.id = id;
        this.isOccupied = isOccupied;
        this.isLocked = isLocked;
        this.empID = empID;
        this.deskButton = deskButton;
    }




}

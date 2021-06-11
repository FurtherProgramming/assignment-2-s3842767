package test;

import main.model.DeskBookModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeskBookModelTest {

    DeskBookModel deskBookModel;

    @BeforeAll
    void setUp()
    {
        deskBookModel = new DeskBookModel();
    }
    @Test
    void bookTable_ReturnsFalse_IfInvalidInfo() throws SQLException {
        Assertions.assertFalse(deskBookModel.bookTable("falseID", "FalseUser"));
    }

    @Test
    void removeTable() {
    }

    @Test
    void checkOccupancy() {
    }

    @Test
    void checkLocked() {
    }

    @Test
    void checkPrevBooking() {
    }

    @Test
    void checkBooking() {
    }

    @Test
    void covidLockTables() {
    }

    @Test
    void exportDeskDataToCSV() {
    }
}
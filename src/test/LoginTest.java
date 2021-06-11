package test;

import main.model.EmployeeManagerModel;
import main.model.LoginModel;
import main.model.RegisterModel;
import main.model.ResetPasswordModel;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTest {

    RegisterModel registerModel;
    LoginModel loginModel;
    EmployeeManagerModel employeeManagerModel;
    ResetPasswordModel resetPasswordModel;


    @BeforeAll
    void setUp() throws Exception {
        registerModel = new RegisterModel();
        loginModel = new LoginModel();
        employeeManagerModel = new EmployeeManagerModel();
        resetPasswordModel = new ResetPasswordModel();
        registerModel.register("testID", "TestName", "McTestFace", "85", "Tester", "TestPassword"
                , "Tester", "TestQuestion", "TestPassword", false);
    }

    @Test
    void register_ReturnsTrue_IfRegisterSuccessful() throws Exception {
        Assertions.assertTrue(registerModel.register("NewTestID", "NewTestName", "NewMcTestFace", "81", "Tester", "TestPassword"
                , "Tester", "TestQuestion", "TestPassword", false));
    }

    @Test
    void register_ReturnsFalse_IfRegisterUnsuccessful() throws Exception{
        Assertions.assertFalse(registerModel.register("testID", "Bobby", "Firmino", "44", "false", "falsepassword"
                , "Con Artist", "TestQuestion", "TestPassword", false));
    }

    @Test
    void register_ReturnsFalse_IfFieldMissing() throws Exception {
        Assertions.assertFalse(registerModel.register("testID", "", "", "", "false", "falsepassword"
                , "Con Artist", "", "TestPassword", false));
    }

    @Test
    void resetPassword_ReturnsFalse_ifWrongAnswer() throws SQLException {
        Assertions.assertFalse(resetPasswordModel.resetPassword("Tester", "Wrong Answer"));
    }

    @Test
    void login_ReturnsFalse_IfIncorrectInfo() throws Exception {
        Assertions.assertFalse(loginModel.isLogin("false","false"));
    }

    @AfterAll
    void cleanUp()
    {
        employeeManagerModel.deleteItemFromTable("testID");
        employeeManagerModel.deleteItemFromTable("NewTestID");
    }


}

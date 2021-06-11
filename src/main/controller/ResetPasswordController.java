package main.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import main.model.ResetPasswordModel;
import main.model.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Controller for resetting the password of a user. Requires the username and secret answer for account in question.
 */
public class ResetPasswordController implements Initializable {
    ResetPasswordModel resetPasswordModel = new ResetPasswordModel();

    @FXML
    Label statusLabel;

    @FXML
    Label secretQuestion;

    @FXML
    TextField usernameField;

    @FXML
    TextArea answerField;

    @FXML
    Label newPassLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        newPassLabel.setText("");
        secretQuestion.setText("");
    }

    public void usernameEntered(ActionEvent event) throws Exception {
        if(resetPasswordModel.getUser(usernameField.getText()))
        {
            statusLabel.setText("Username is Valid");
            secretQuestion.setText(UserSession.getSecretQuestion());
        }
        else
        {
            statusLabel.setText("Username is Invalid");
        }
    }

    public void confirmAnswer(ActionEvent event) throws Exception {
        if(resetPasswordModel.resetPassword(UserSession.getUsername(), answerField.getText()))
        {
            statusLabel.setText("Reset successfully");
            newPassLabel.setText(resetPasswordModel.getPassword());
        }
        else
        {
            statusLabel.setText("Incorrect Answer");
        }
    }

    public void cancelClicked() throws IOException {
        Stage window = Main.getPrimaryStage();
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/home.fxml"));
        Scene scene = new Scene(root);
        window.setScene(scene);
        window.show();
    }
}

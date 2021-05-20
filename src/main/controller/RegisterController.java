package main.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

import main.model.RegisterModel;


public class RegisterController implements Initializable {
    RegisterModel registerModel = new RegisterModel();

    @FXML
    private Label isConnected;
    @FXML
    private TextField empID;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField age;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField role;
    @FXML
    private TextField secretQuestion;
    @FXML
    private TextField txtAnswer;
    @FXML
            private ChoiceBox ageList;

    private Stage stage;
    private Scene scene;
    private final int MAX_AGE = 100;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (registerModel.isRegDbConnected()){
            isConnected.setText("Connected");
        }else{
            isConnected.setText("Not Connected");
        }

        for(int i = 0; i < MAX_AGE; i++)
        {
            String s = String.valueOf(i);
            ageList.getItems().add(s);
        }
    }

    public void confirmClicked(ActionEvent event) throws Exception
    {
        int id = Integer.parseInt(empID.getText());
        String ag = (String) ageList.getValue();


        if(empID == null || firstName == null || lastName == null || ageList == null || txtUsername == null || txtPassword == null ||
        role == null || secretQuestion == null || txtAnswer == null)
        {
            isConnected.setText("One or more fields are empty");
        }
        else
        {
            if(registerModel.register(id, firstName.getText(), lastName.getText(), ag, txtUsername.getText(),
                    txtPassword.getText(), role.getText(), secretQuestion.getText(), txtAnswer.getText(), false))
            {
                System.out.println("Confirm Registry...");
                System.out.println("Going back home...");
                Parent root = FXMLLoader.load(getClass().getResource("/main/ui/home.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                System.out.println("Registry Failed!");
                isConnected.setText("Registry Failed!");
            }

        }
    }

    public void cancelClicked(ActionEvent event) throws Exception
    {
        System.out.println("Going back home...");
        Parent root = FXMLLoader.load(getClass().getResource("/main/ui/home.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

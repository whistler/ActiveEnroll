package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class LoginController implements Initializable {

    @FXML ChoiceBox userType;
    @FXML TextField userId;
    @FXML PasswordField userPassword;
    @FXML Button login;
    @FXML Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }

    /**
     * Checks if the username and password entered are correct if so sets the 
     * currently logged in user and displays the dashboard
     * @param event the button click event that triggered login
     * @throws SQLException
     * @throws Exception 
     */
    public void processLogin(ActionEvent event) throws SQLException, Exception {

        if (userId.getText().isEmpty() || userPassword.getText().isEmpty()) {
            errorLabel.setText("Username and password are required");
        } else {

            String type = (String) userType.getValue();
            Integer loginId = Integer.parseInt(userId.getText());
            String password = userPassword.getText();
            User user;

            try{
                user = User.login(loginId, password, type);
            } catch(NullPointerException e)
            {
                errorLabel.setText("Username and Password do not match");
                return;
            }
            
            CurrentUser.setUser(user);
            ViewManager.changeView("/com/mss/tuess/views/Dashboard.fxml");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.DatabaseConnector;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class LoginController implements Initializable {

    @FXML
    ChoiceBox userType;
    @FXML
    TextField userId;
    @FXML
    PasswordField userPassword;
    @FXML
    Button login;
    @FXML
    Label errorLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }

    public void processLogin(ActionEvent event) throws SQLException, Exception {

        if (userId.getText().isEmpty() || userPassword.getText().isEmpty()) {
            errorLabel.setText("Username and password are required");
        } else {
            
            String type = (String) userType.getValue();
            Integer loginId = Integer.parseInt(userId.getText());
            String password = userPassword.getText();
            
            if (type.equals("Student")) {
                Student student = new Student();
                student.fetch(loginId);
                if(student.getPassword().equals(password))
                {
                    ViewManager.changeView("/com/mss/tuess/views/Dashboard.fxml");
                    return;
                } 
            } else if (type.equals("Instructor")) {
                Instructor instructor = new Instructor();
                instructor.fetch(loginId);
                if(instructor.getPassword().equals(password))
                {
                    ViewManager.changeView("/com/mss/tuess/views/Dashboard.fxml");
                    return;
                } 
            } else {
                Administrator admin;
            }
            errorLabel.setText("Useranme/Password invalid!!!!");
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import Login.MyLogin;
import Login.UserLoginModel;
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

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class AuthenticationController implements Initializable {

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

    public void processLogin(ActionEvent event) throws SQLException {
        System.out.println("------fff----");
        System.out.println(userId.getText() + "   " + userPassword.getText());
        if (userId.getText().isEmpty() || userPassword.getText().isEmpty()) {
            errorLabel.setText("Username and password is mandatory!!!");
        } else {
            
            ResultSet rs = com.mss.tuess.views.Tuess.db_con.exeResultSet("SELECT * FROM student stu WHERE stu.studentID = " + userId.getText());
            String temp = "";
            while (rs.next()) {
                temp = rs.getString("password");
            }
            System.out.println(temp + " @@@@@@@@@   " + userPassword.getText());

            if (userPassword.getText().equals(temp)) {
                //MyLogin.getInstance().gotoStudentDashBoard();
                System.out.println("done...");
            } else {
                errorLabel.setText("Useranme/Password invalid!!!!");
            }
        }
    }
}

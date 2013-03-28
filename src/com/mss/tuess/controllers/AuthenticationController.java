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

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class AuthenticationController implements Initializable {

    @FXML
    TextField userName;
    @FXML
    PasswordField password;
    @FXML
    Button login;
    @FXML
    Label errorLabel;

    private MyLogin application;
    private UserLoginModel userLogin = new UserLoginModel();
    
    public void setApp(MyLogin application){
        this.application = application;
        errorLabel.setText("");
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setText("");
    }    
    
    public void processLogin(ActionEvent event) {
            authenticateLogin();
    }
    
    private void authenticateLogin(){
        if(userName.getText().isEmpty() || password.getText().isEmpty()){
            errorLabel.setText("Username and password is mandatory!!!");
        }else{
            if(password.getText().equals(userLogin.getPassword(userName.getText()))){
                MyLogin.getInstance().gotoStudentDashBoard();
            }else{
                errorLabel.setText("Useranme/Password invalid!!!!");
            } 
        }
    }
    
}

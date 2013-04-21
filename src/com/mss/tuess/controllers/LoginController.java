package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.InputType;
import com.mss.tuess.util.Validator;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;

public class LoginController implements Initializable {

    @FXML
    private ChoiceBox userType;
    @FXML
    private TextField userId;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label statusLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusLabel.setText("");
    }

    /**
     * Checks if the username and password entered are correct if so sets the
     * currently logged in user and displays the view
     *
     * @param event the button click event that triggered login
     * @throws SQLException
     * @throws Exception
     */
    public void processLogin(ActionEvent event) throws SQLException, Exception {

        if (userId.getText().isEmpty() || userPassword.getText().isEmpty()) {
            ViewManager.setStatus("Username and password are required");
        } else {

            Validator validator = new Validator();

            String type = validator.validate("Type", (String) userType.getValue(), true, 5, 15, InputType.STRING);
            userId.setText(validator.validate("Login ID", userId.getText(), true, 10000000, 99999999, InputType.POSITIVE_INTEGER));
            String password = validator.validate("Password", userPassword.getText(), true, 6, 20, InputType.STRING);
            
            Integer loginId =0;
            if(Validator.isInteger(userId.getText())) loginId=Integer.parseInt(userId.getText());
                    
            User user;
            user = User.login(loginId, password, type);

            if (user == null) {
                validator.addError("Username and Password do not match. Contact Administrator if you have forgotten your password.");
            }

            if (validator.hasErrors()) {
                ViewManager.setStatus(validator.getErrors().get(0).toString());
            } else {
                CurrentUser.setUser(user);
                ViewManager.changeScene("/com/mss/tuess/views/Layout.fxml");
                ViewManager.setStatus("Successfully logged in!");
                ViewManager.setUser(user);
            }
        }
    }
}

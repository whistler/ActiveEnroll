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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.scene.control.ChoiceBox;

public class LoginController implements Initializable {

    private @FXML
    ChoiceBox userType;
    private @FXML
    TextField userId;
    private @FXML
    PasswordField userPassword;
    private @FXML
    Button login;
    private @FXML
    Label statusLabel;
    private Validator validator = new Validator();

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

            validator.reset();

            String type = validator.validate("Type", (String) userType.getValue(), true, 5, 15, InputType.STRING);
            Integer loginId = Integer.parseInt(validator.validate("Login ID", userId.getText(), true, 8, 8, InputType.POSITIVE_INTEGER));
            String password = validator.validate("Password", userPassword.getText(), true, 6, 20, InputType.STRING);

            User user;
            user = User.login(loginId, password, type);

            if (user == null) {
                validator.addError("Username and Password do not match");
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

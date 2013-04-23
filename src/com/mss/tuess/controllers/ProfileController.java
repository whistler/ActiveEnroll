package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.InputType;
import com.mss.tuess.util.Validator;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * @ProfileController
 * This method is the controller of modifying personal profile of all three kinds of users
 */

public class ProfileController implements Initializable {

    @FXML
    private Label ID;
    @FXML
    private Label status;
    @FXML
    private Label program;
    @FXML
    private Label registeredSince;
    @FXML
    private Label statusLabel;
    @FXML
    private Label programLabel;
    @FXML
    private Label registeredSinceLabel;
    @FXML
    private Label IDLabel;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextArea address;
    @FXML
    private TextField city;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField state;
    @FXML
    private TextField country;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;

    /**
     * Initializes the controller class. Shows the current user in the view
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        User user = CurrentUser.getUser();
        ID.setText(Integer.toString(user.getID()));
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        zipCode.setText(user.getZipcode());
        state.setText(user.getState());
        country.setText(user.getCountry());
        phone.setText(user.getPhone());

        IDLabel.setText(user.getClass().getSimpleName() + " ID");

        if (user.getClass() == Student.class) {
            status.setVisible(true);
            registeredSince.setVisible(true);
            program.setVisible(true);
            statusLabel.setVisible(true);
            registeredSinceLabel.setVisible(true);
            programLabel.setVisible(true);

            Student student = (Student) user;
            status.setText(student.getStatus());
            registeredSince.setText(student.getRegisteredSince());
            program.setText(student.getProgramID());
        }
    }
    
     /**
     * Update is to update personal information from input.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */

    @FXML
    public void update() throws SQLException {
        User user = CurrentUser.getUser();
        Validator validator = new Validator();

        user.setAddress(validator.validate("Address", address.getText(), true, 6, 100, InputType.STRING));
        user.setCity(validator.validate("City", city.getText(), true, 4, 20, InputType.LETTERS));
        user.setState(validator.validate("State", state.getText(), true, 2, 20, InputType.LETTERS));
        user.setCountry(validator.validate("Country", country.getText(), true, 4, 20, InputType.LETTERS));
        user.setEmail(validator.validate("Email", email.getText(), true, 5, 30, InputType.EMAIL));
        user.setFirstName(validator.validate("First name", firstName.getText(), true, 2, 20, InputType.LETTERS));
        user.setLastName(validator.validate("Last name", lastName.getText(), true, 2, 20, InputType.LETTERS));
        user.setPhone(validator.validate("Phone number", phone.getText(), true, 9, 20, InputType.STRING));
        user.setZipcode(validator.validate("Zip code", zipCode.getText(), true, 5, 6, InputType.STRING));

        user.setID(Integer.parseInt(ID.getText()));

        if (!newPassword.getText().isEmpty()) {
            if (newPassword.getText().equals(confirmPassword.getText())) {
                user.setPassword(validator.validate("Password", newPassword.getText(), true, 6, 10, InputType.STRING));
            } else {
                validator.addError("New password and password confimation do not match");
            }
        }

        if (validator.hasErrors()) {
            ViewManager.setStatus(validator.getErrors().get(0).toString());
        } else {

            if (user.getClass() == Student.class) {
                Student student = (Student) user;
                status.setText(student.getStatus());
                registeredSince.setText(student.getRegisteredSince());
                program.setText(student.getProgramID());
            }

            user.update();
            ViewManager.setStatus("Saved!");
        }
    }
}

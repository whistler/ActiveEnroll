package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ProfileController implements Initializable {
    @FXML Label ID;
    @FXML Label status;
    @FXML Label program;
    @FXML Label registeredSince;
    @FXML Label statusLabel;
    @FXML Label programLabel;
    @FXML Label registeredSinceLabel;
    @FXML Label IDLabel;
    @FXML Label errorLabel;
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField email;
    @FXML TextArea address;
    @FXML TextField city;
    @FXML TextField zipCode;
    @FXML TextField state;
    @FXML TextField country;
    @FXML TextField phone;
    @FXML PasswordField newPassword;
    @FXML PasswordField confirmPassword;

    /**
     * Initializes the controller class. Shows the current user in the view
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

    @FXML
    public void update() throws SQLException {
        User user = CurrentUser.getUser();

        user.setAddress(address.getText());
        user.setCity(city.getText());
        user.setState(state.getText());
        user.setCountry(country.getText());
        user.setEmail(email.getText());
        user.setFirstName(firstName.getText());
        user.setID(Integer.parseInt(ID.getText()));
        user.setLastName(lastName.getText());
        user.setPhone(phone.getText());
        user.setZipcode(zipCode.getText());

        if (!newPassword.getText().isEmpty()) {
            if (newPassword.getText().equals(confirmPassword.getText())) {
                user.setPassword(newPassword.getText());
            } else {
                errorLabel.setText("New password and password confimation do not match");
                return;
            }
        }

        if (user.getClass() == Student.class) {
            Student student = (Student) user;
            status.setText(student.getStatus());
            registeredSince.setText(student.getRegisteredSince());
            program.setText(student.getProgramID());
        }

        user.update();
        errorLabel.setText("Saved!");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.entity.User;
import com.mss.tuess.entitylist.ProgramList;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Prabal
 */
public class AddStudentController implements Initializable {
    @FXML
    private Button addStudent;
    @FXML
    private Label IDLabel;
    @FXML
    private Font x1;
    @FXML
    private Label programLabel;
    @FXML
    private Label registeredSinceLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phone;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextArea address;
    @FXML
    private TextField email;
    @FXML
    private TextField city;
    @FXML
    private TextField zipCode;
    @FXML
    private TextField country;
    @FXML
    private Label errorLabel;
    @FXML
    private TextField state;
    @FXML
    private TextField registeredSince;
    @FXML
    private ChoiceBox program;
    @FXML
    private TextField ID;
    @FXML
    private TextField status;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ProgramList.fetch();
        } catch (SQLException ex) {
            Logger.getLogger(AddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<String> programs = FXCollections.observableArrayList();
        for(int i=0;i<ProgramList.size();i++)
            programs.add(ProgramList.get(i).getProgramID());
        program.setItems( programs);
    }    

    @FXML
    public void addNewStudent() {
      
        Student student = new Student();

        student.setAddress(address.getText());
        student.setCity(city.getText());
        student.setState(state.getText());
        student.setCountry(country.getText());
        student.setEmail(email.getText());
        student.setFirstName(firstName.getText());
        student.setID(Integer.parseInt(ID.getText()));
        student.setLastName(lastName.getText());
        student.setPhone(phone.getText());
        student.setZipcode(zipCode.getText());
        student.setProgramID((String)program.getValue());
        student.setRegisteredSince(registeredSince.getText());
        student.setStatus(status.getText());

        if (!newPassword.getText().isEmpty()) {
            if (newPassword.getText().equals(confirmPassword.getText())) {
                student.setPassword(newPassword.getText());
            } else {
                errorLabel.setText("New password and password confimation do not match");
                return;
            }
        }
        try {
            student.insert();
        } catch (SQLException ex) {
           errorLabel.setText("New Student not created!");
        }
        errorLabel.setText("Added a New Student!");
    
    }
}

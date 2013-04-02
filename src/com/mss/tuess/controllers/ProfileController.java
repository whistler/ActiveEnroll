package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileController implements Initializable {

    @FXML Pane sidebar;
    
    @FXML Label ID;
    
    @FXML Label status;
    @FXML Label program;
    @FXML Label registeredSince;
    @FXML Label statusLabel;
    @FXML Label programLabel;
    @FXML Label registeredSinceLabel;
    @FXML Label IDLabel;
    
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField email;
    @FXML TextArea address;
    @FXML TextField city;
    @FXML TextField zipCode;
    @FXML TextField country;
    @FXML TextField phone;
    @FXML TextField newPassWord;
    @FXML TextField confirmPassWord;
    
    /**
     * Initializes the controller class. Shows the current user in the view
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewManager.loadSidebar(sidebar);
        
        User user = CurrentUser.getUser();
        ID.setText(Integer.toString(user.getID()));
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
        address.setText(user.getAddress());
        city.setText(user.getCity());
        zipCode.setText(user.getZipcode());
        country.setText(user.getCountry());
        phone.setText(user.getPhone());
 
        IDLabel.setText(user.getClass().getSimpleName() + " ID");
        
        if(user.getClass()==Student.class)
        {
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
}

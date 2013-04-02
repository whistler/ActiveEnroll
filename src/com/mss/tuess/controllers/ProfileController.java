package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import com.mss.tuess.entity.*;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ProfileController implements Initializable {

    @FXML Pane sidebar;
    
    @FXML Label studentID;
    @FXML TextField firstName;
    @FXML TextField lastName;
    @FXML TextField email;
    @FXML TextField address;
    @FXML TextField phone;
    @FXML TextField newPassWord;
    @FXML TextField confirmPassWord;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        User user = CurrentUser.getUser();
        studentID.setText(Integer.toString(user.getID()));
        ViewManager.loadSidebar(sidebar);
        //firstName.setText("Wen Han");
    }    
}

package com.mss.tuess.controllers;

import com.mss.tuess.entity.User;
//import com.mss.tuess.entitylist.WaivePrerequisiteList;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class WaivePrerequisiteController implements Initializable {
    
 @FXML ChoiceBox coursesToWaive;
 @FXML Label errorLabel;
 @FXML TextField studentID;
 @FXML Button waiveButton;
      
     private ObservableList<String> coursesToWaiveList = FXCollections.observableArrayList();

 
     /**
     * Constructor of RegisteredCourseController
     *
     * @throws SQLException
     */
    public WaivePrerequisiteController() throws SQLException
    {
//        WaivePrerequisiteList.fetch();
//        int courseToTeachSize = WaivePrerequisiteList.getAll().size();
//        for(int i=0;i<courseToTeachSize;i++)
//        {
//            coursesToWaiveList.addAll(WaivePrerequisiteList.getCoursesToTeach(i).getCourseDept()
//                    +WaivePrerequisiteList.getCoursesToTeach(i).getCourseNum()+" "
//                    +WaivePrerequisiteList.getCoursesToTeach(i).getSectionID()+": "
//                    +WaivePrerequisiteList.getCoursesToTeach(i).getCourseName());
//        }


    }
    
    
     /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        coursesToWaive.setItems(coursesToWaiveList);
        errorLabel.setText("");

    } 
    
    
    
    
    /**
     * Checks if the username and password entered are correct if so sets the 
     * currently logged in user and displays the dashboard
     * @param event the button click event that triggered login
     * @throws SQLException
     * @throws Exception 
     */
    public void processWaiveProrerequisite(ActionEvent event) throws SQLException, Exception {

        if (studentID.getText().isEmpty()||coursesToWaive.getItems().isEmpty()) {
            errorLabel.setText("Course and student ID are required");
        } /*else {

            String type = (String) userType.getValue();
            Integer loginId = Integer.parseInt(userId.getText());
            String password = userPassword.getText();
            User user;

            try{
                user = User.login(loginId, password, type);
            } catch(NullPointerException e)
            {
                errorLabel.setText("Username and Password do not match");
                return;
            }
            
            CurrentUser.setUser(user);
            ViewManager.changeScene("/com/mss/tuess/views/Dashboard.fxml");
        }*/
    }
    
    
    
    
}

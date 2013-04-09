package com.mss.tuess.controllers;

import com.mss.tuess.entity.Term;
import com.mss.tuess.entitylist.SectionList;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
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
    
 @FXML ComboBox coursesToWaive;
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
        int instructorID;
        instructorID = CurrentUser.getUser().getID();
        Term currentTerm=State.getCurrentTerm();
        
        SectionList.fetchByInstructor(instructorID,currentTerm.getTermID());
        
        
        int sectionListSize = SectionList.getAll().size();
        for(int i=0;i<sectionListSize;i++)
       {
            coursesToWaiveList.addAll(SectionList.get(i).getTermID()+" "
                                      +SectionList.get(i).getCourseDept()+" "
                                      +SectionList.get(i).getCourseNum()+" "
                                      +SectionList.get(i).getSectionID());
        }


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
    public void processWaivePrerequisite(ActionEvent event) throws SQLException, Exception {

        if (studentID.getText().isEmpty()||coursesToWaive.getValue()==null){
            errorLabel.setText("Course and student ID are required");
        } 
        else {
/*
            String courseInfo = coursesToWaive.getValue().toString();
            String courseDept=courseInfo.substring(0, 3);
            String courseNum=courseInfo.substring(4, 6);
            String sectionID=courseInfo.substring(8,9);
            
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
            * */
        }
    }
    
    
    
    
}

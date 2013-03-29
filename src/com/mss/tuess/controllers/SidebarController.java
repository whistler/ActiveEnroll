/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author ibrahim
 */
public class SidebarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML public void showMyProfile() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/Profile.fxml");
    }
    
    @FXML public void showCourseSearch() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/CourseSearch.fxml");
    }
    
    @FXML public void showRegisteredCourses() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/ViewRegisterDropCourse.fxml");
    }
    
    @FXML public void showTranscript() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/Transcript.fxml");
    }
    
    @FXML public void showMyProgram() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/DegreeRequirements.fxml");
    }
    
    @FXML public void logout() throws Exception 
    {
        ViewManager.changeView("/com/mss/tuess/views/Login.fxml");
    }
    
}

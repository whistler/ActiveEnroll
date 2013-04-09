package com.mss.tuess.controllers;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SidebarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    /* Admin Navigation */
    
    @FXML public void showMailGrades() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/MailGrades.fxml");
    }
    
    @FXML public void showMailInstructions() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/MailInstructions.fxml");
    }
    
    
    
    /* Instructor Navigation */
    
    @FXML public void showWaivePrerequisite() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/WaivePrerequisite.fxml");
    }
    
    
    
    /* Student Navigation */
    
    @FXML public void showCourseSearch() throws Exception
    {
        State.setCurrentSearchView("CourseSearch");
        ViewManager.changeView("/com/mss/tuess/views/AdvancedCourseSearch.fxml");
    }
    
    @FXML public void showAllCourses() throws Exception
    {
        State.setCurrentSearchView("AllCourses");
        ViewManager.changeView("/com/mss/tuess/views/ViewAllCourses.fxml");
    }
    
    @FXML public void showRegisteredCourses() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/RegisteredCourses.fxml");
    }
    
    @FXML public void showTranscript() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/Transcript.fxml");
    }
    
    @FXML public void showTimetable() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/Timetable.fxml");
    }
    
    @FXML public void showMyProgram() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/MyProgram.fxml");
    }
    
    
    
    /* Reuseable Navigation */
    
    @FXML public void logout() throws Exception 
    {
        CurrentUser.setUser(null);
        ViewManager.changeScene("/com/mss/tuess/views/Login.fxml");
    }
    
    @FXML public void showMyProfile() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/Profile.fxml");
    }
        @FXML public void showAddStudent() throws Exception
    {
        ViewManager.changeView("/com/mss/tuess/views/AddStudent.fxml");
    }
}

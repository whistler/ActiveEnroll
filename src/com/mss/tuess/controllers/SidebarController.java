package com.mss.tuess.controllers;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * @SidebarController
 * This method is the controller of loading all the content of the sidebar.
 */

public class SidebarController implements Initializable {

    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    
    
    /* Admin Navigation */
    /**
    * @showMailGrades
    * This method is the controller of showing mail grades interface.
    */
    @FXML public void showMailGrades() throws Exception
    {
        ViewManager.changeView("MailGrades");
        ViewManager.setTitle("Mail Grades");
    }
    
    @FXML public void showMailInstructions() throws Exception
    {
        ViewManager.changeView("MailInstructions");
        ViewManager.setTitle("Mail Instructions");
    }
    
    
    
    /* Instructor Navigation */
    /**
    * @showWaivePrerequisite
    * This method is the controller of showing waive prerequisites
    */
    @FXML public void showWaivePrerequisite() throws Exception
    {
        ViewManager.changeView("WaivePrerequisite");
        ViewManager.setTitle("Waive Prerequisite");
    }
    /**
    * @showTaughtSections
    * This method is the controller of showing taught sections of a instructor
    */
    @FXML public void showTaughtSections() throws Exception
    {
        ViewManager.changeView("TaughtCourses");
        ViewManager.setTitle("Taught Sections");
    }
    
    
    /* Student Navigation */
    
    @FXML public void showCourseSearch() throws Exception
    {
        State.setCurrentSearchView("CourseSearch");
        ViewManager.changeView("AdvancedCourseSearch");
        ViewManager.setTitle("Course Search");
    }
    
    @FXML public void showAllCourses() throws Exception
    {
        State.setCurrentSearchView("AllCourses");
        ViewManager.changeView("ViewAllCourses");
        ViewManager.setTitle("View All Courses");
    }
    
    @FXML public void showRegisteredCourses() throws Exception
    {
        ViewManager.changeView("RegisteredCourses");
        ViewManager.setTitle("Registered Courses");
    }
    
    @FXML public void showTranscript() throws Exception
    {
        ViewManager.changeView("Transcript");
        ViewManager.setTitle("Transcript");
    }
    
    @FXML public void showTimetable() throws Exception
    {
        ViewManager.changeView("Timetable");
        ViewManager.setTitle("Timetable");
    }
    
    @FXML public void showMyProgram() throws Exception
    {
        ViewManager.changeView("MyProgram");
        ViewManager.setTitle("My Program");
    }
    
    
    
    /* Reuseable Navigation */
    
    @FXML public void logout() throws Exception 
    {
        CurrentUser.setUser(null);
        ViewManager.changeScene("/com/mss/tuess/views/Login.fxml");
    }
    
    @FXML public void showMyProfile() throws Exception
    {
        ViewManager.changeView("Profile");
        ViewManager.setTitle("My Profile");
    }
    
    @FXML public void showAddStudent() throws Exception
    {
        ViewManager.changeView("AddStudent");
        ViewManager.setTitle("Add New Student");
    }
    
    @FXML public void showHelp()
    {
        ViewManager.showCurrentHelp();
    }
}

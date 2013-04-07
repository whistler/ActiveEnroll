package com.mss.tuess.controllers;

import com.mss.tuess.entity.Department;
import com.mss.tuess.entity.Program;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MyProgramController implements Initializable {

    @FXML Pane sidebar;
    @FXML TextField programID;
    @FXML TextField degreeTitle;
    @FXML TextField facultyName;
    @FXML TextField deptName;
    @FXML TextField creditsRequired;
    @FXML TextField creditsCompleted;
    @FXML ListView courses;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewManager.loadSidebar(sidebar);
        Student student = (Student) CurrentUser.getUser();
        Program program = new Program();
        program.fetch(student.getProgramID());
        System.out.println(program.getDeptID());
        Department department = new Department();
        department.fetch(program.getDeptID());
        
        programID.setText(program.getProgramID());
        degreeTitle.setText(program.getDegreeTitle());
        facultyName.setText(department.getFacultyName());
        deptName.setText(department.getDeptName());
        creditsRequired.setText(Integer.toString(program.getMinCredit()));
        creditsCompleted.setText(Integer.toString(student.getCreditsCompleted()));
    }    
}

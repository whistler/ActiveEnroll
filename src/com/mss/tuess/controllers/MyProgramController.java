package com.mss.tuess.controllers;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Department;
import com.mss.tuess.entity.Program;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * @MyProgramController
 * This method is the controller of mailing grades function of administrator
 */


public class MyProgramController implements Initializable {

    @FXML TextField programID;
    @FXML TextField degreeTitle;
    @FXML TextField facultyName;
    @FXML TextField deptName;
    @FXML TextField creditsRequired;
    @FXML TextField creditsCompleted;
    @FXML ListView coursesCompleted;
    @FXML ListView coursesRequired;
    
    
    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        loadCourseLists(student);

    }
    
    private void loadCourseLists(Student student)
    {
        ObservableList<String> required = FXCollections.observableArrayList();
        ObservableList<String> completed = FXCollections.observableArrayList();
        
        ArrayList completedCourses = student.getCompletedRequiredCourses();
        for(int i=0;i<completedCourses.size();i++)
        {
            Course c = (Course)completedCourses.get(i);
            required.add(c.getCourseDept() + " " + c.getCourseNum());
        }

        ArrayList requiredCourses = student.getIncompleteRequiredCourses();
        for(int i=0;i<requiredCourses.size();i++)
        {
            Course c = (Course)requiredCourses.get(i);
            completed.add(c.getCourseDept() + " " + c.getCourseNum());
        }
        
        coursesCompleted.setItems(completed);
        coursesRequired.setItems(required);
    }
}

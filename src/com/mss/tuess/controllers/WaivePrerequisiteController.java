package com.mss.tuess.controllers;

import com.mss.tuess.entity.EnrollSection;
import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.Student;
import com.mss.tuess.entity.Term;
import com.mss.tuess.entitylist.SectionList;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
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
     * Constructor of WaivePrerequisiteController
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
     * Checks if the course and student ID entered are correct and process waive prerequisite
     * @param event the button click event that triggered waive prerequisite
     * @throws SQLException
     * @throws Exception 
     */
    public void processWaivePrerequisite(ActionEvent event) throws SQLException, Exception {

        if (studentID.getText().isEmpty()||coursesToWaive.getValue()==null){
            errorLabel.setText("Course and student ID are required");
        } 
        else {

                String courseInfo = coursesToWaive.getValue().toString();
                String termID=courseInfo.substring(0,5);
                String courseDept=courseInfo.substring(6, 10);
                String courseNum=courseInfo.substring(11, 14);
                String sectionID=courseInfo.substring(15,16);
            
           // System.out.print(termID+":"+courseDept+":"+courseNum+":"+sectionID);
          
            
            int sID=Integer.parseInt(studentID.getText());
            Student studentToWaive=new Student();
            studentToWaive.fetch(sID);
           
            if(studentToWaive.getID()>0)//student ID exists
            {
                Section selectedSection=new Section();
                selectedSection=SectionList.get(coursesToWaive.getSelectionModel().getSelectedIndex());
                
                if(EnrollSection.isEnrolled(studentToWaive,selectedSection))
                {
                            errorLabel.setText("ERROR: The student has already enrolled.");
                }
                else
                {
                    if(EnrollSection.isFull(selectedSection))
                    {
                        errorLabel.setText("ERROS: The selected section is full.");
                    }

                    else
                    {
                        if(EnrollSection.isTimeConflict(studentToWaive, selectedSection))
                        {
                                errorLabel.setText("ERROR: The student has time conflict.");            
                        }
                        else
                        {
                            enrollToSection(selectedSection,studentToWaive);
                            errorLabel.setText("Succeed: Student "+studentToWaive.getID()+" is enrolled to "+selectedSection.getCourseDept()+" "+selectedSection.getCourseNum());        
                        }    
                    }
                }
            }
            
            else
            {
                errorLabel.setText("Student ID does not exist.");
            }
            
          
        }
    }
    
    /**
     * Helper function to enroll a student waving the prerequisite
     * @param section the section to enroll a student
     * @param student the student to be waived prerequisite and get enrolled
     * @throws SQLException 
     */
    private void enrollToSection(Section section, Student student)throws SQLException
    {
       
            String sql = "INSERT INTO enrollSection VALUES ("
                    + student.getID() + ", '"
                    + section.getSectionID() + "', '"
                    + section.getCourseDept() + "', '"
                    + section.getCourseNum() + "', '"
                    + section.getTermID()+"', ''"
                    + ")";
            DatabaseConnector.updateQuery(sql);
            
           
    }
    
}

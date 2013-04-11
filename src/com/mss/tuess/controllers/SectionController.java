/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.mss.tuess.util.State;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SectionController implements Initializable {
    
    @FXML
    private TextField courseCode;
    @FXML
    private TextField courseName;
    @FXML
    private TextArea courseInfo;
    @FXML
    private TextField courseCredits;
    @FXML
    private TextField instructor;
    @FXML
    private TextField startDate;
    @FXML
    private TextField endDate;
    @FXML
    private TextField lastDayToEnroll;
    @FXML
    private TextField lastDayToWithdraw;
    @FXML
    private Label prerequisites;
    @FXML
    private Label corequisites;
    @FXML
    private Button dropButton;
    @FXML
    private Button enrollButton;
    @FXML
    private TableView<SectionClass> sectionClassTable;
    @FXML
    private TableColumn<SectionClass, String> sectionID;
    @FXML
    private TableColumn<SectionClass, String> type;
    @FXML
    private TableColumn<SectionClass, String> day;
    @FXML
    private TableColumn<SectionClass, String> displayStartTime;
    @FXML
    private TableColumn<SectionClass, String> displayEndTime;
    @FXML
    private TableColumn<SectionClass, String> location;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        Section section = State.getCurrentSection();
        
        if (section != null) {
            
            /* set the prerequisite and corequisites */
            Course course = new Course();
            course.setCourseDept(section.getCourseDept());
            course.setCourseNum(section.getCourseNum());
            String prereqs = Prerequisite.getPrerequisitesString(Prerequisite.prerequisitesForCourse(course));
            String coreqs = Corequisite.getCorequisitesString(Corequisite.corequisitesForCourse(course));
            prerequisites.setText(prereqs);
            corequisites.setText(coreqs);
            
            /* show course fields */
            courseCode.setText(section.getCourse().getCourseDept() + "-" 
                    + section.getCourse().getCourseNum());
            courseName.setText(section.getCourse().getCourseName());
            courseInfo.setText(section.getCourse().getInfo());
            courseCredits.setText(Integer.toString(section.getCourse().getCredit()));
            String name = section.getInstructor().getFirstName() + " "
                    + section.getInstructor().getLastName();
            instructor.setText(name);
            startDate.setText(new SimpleDateFormat("d MMM yyyy").format(section.getTerm().getStart()));
            endDate.setText(new SimpleDateFormat("d MMM yyyy").format(section.getTerm().getEnd()));
            lastDayToEnroll.setText(new SimpleDateFormat("d MMM yyyy").format(section.getTerm().getRegistrationEnd()));
            lastDayToWithdraw.setText(new SimpleDateFormat("d MMM yyyy").format(section.getTerm().getDropWithoutW()));
            
            /* Populate the schedule table with sectionClasses */
            type.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("type"));
            day.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("day"));
            displayStartTime.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("displayStartTime"));
            displayEndTime.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("displayEndTime"));
            location.setCellValueFactory(new PropertyValueFactory<SectionClass, String>("location"));

            ObservableList<SectionClass> sectionClasses = FXCollections.observableArrayList();
            sectionClasses.addAll(SectionClass.getSectionClassesForSection(section));
            sectionClassTable.setItems(sectionClasses);

            /* Show the right button either to enroll or drop*/
            if (EnrollSection.isEnrolled((Student) CurrentUser.getUser(), section)) {
                enrollButton.setVisible(false);
            } else {
                dropButton.setVisible(false);
            }
        }
    }
   /**
    * 
    */
    public static void processEnroll()  {
        
        Section section = State.getCurrentSection();
        int studentID = CurrentUser.getUser().getID();
        EnrollSection es = new EnrollSection();
        
        es.setStudentID(studentID);
        es.setSectionID(section.getSectionID());
        es.setCourseDept(section.getCourseDept());
        es.setCourseNum(section.getCourseNum());
        es.setTermID(section.getTermID());
        es.setGrade("");
        try {
            if (canEnroll(section, studentID)) {
                es.insert();
                section.setRegistered(section.getRegistered() + 1);
                if (section.getRegistered() == section.getCapacity()) {
                    section.setStatus("full");
                }
            } else {
                System.out.println("\nCannot be added!!!!!!!!!");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static boolean canEnroll(Section section, int studentID) throws SQLException {
        Student student=new Student();
        student.fetch(CurrentUser.getUser().getID());
//        if (!registrationEndNotPass(section)) {
//            return false;
//        }
        if (EnrollSection.isAlreadyRegistered(section, studentID)) {
            return false;
        }
        if (EnrollSection.isFull(section)) {
            return false;
            
        }
        if (EnrollSection.checkPrerequisite(section, studentID) == false) {
            System.out.println("\ncheckPrerequisite: false");
            return false;
        }
        if(EnrollSection.isTimeConflict(student, section)){
            System.out.println("\nisTimeConflict!!!!!!!!!!!");
            return false;
        }
        System.out.println("\ncheckPrerequisite: true");
        return true;
    }
    
    public static int processDrop()  {
        try {
            Section section = State.getCurrentSection();
            int studentID = CurrentUser.getUser().getID();
            EnrollSection es = new EnrollSection();
            int check = 0;
            check = es.fetch(studentID, section.getSectionID(), section.getCourseDept(), section.getCourseNum(), section.getTermID());
            
            if (check == 1) {
                if (canDropWithoutW(section, studentID)) {
                    es.delete();
                    if (section.getRegistered() > 0) {
                        section.setRegistered(section.getRegistered() - 1);
                        section.update();
                    }
                } else {
                    es.setGrade("W");
                    es.update();
                    System.out.println("\nWill be add W!!!!!!!!!");
                }
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public static boolean canDropWithoutW(Section section, int studentID) throws SQLException {
        if (EnrollSection.registrationEndNotPass(section)) {
            return true;
        }
        return false;
    }
}
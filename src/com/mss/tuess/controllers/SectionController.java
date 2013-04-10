/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.mss.tuess.util.State;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SectionController implements Initializable {

    @FXML
    TextField courseCode;
    @FXML
    TextField courseName;
    @FXML
    TextArea courseInfo;
    @FXML
    TextField courseCredits;
    @FXML
    TextField instructor;
    @FXML
    TextField startDate;
    @FXML
    TextField endDate;
    @FXML
    TextField lastDayToEnroll;
    @FXML
    TextField lastDayToWithdraw;
    @FXML 
    private ListView<String> preRequisiteList;
    @FXML 
    private ListView<String> coRequisiteList;
    @FXML
    Button dropButton;
    @FXML
    Button enrollButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList<String> preReqItems;
        ObservableList<String> coReqItems;
        preReqItems = FXCollections.observableArrayList("CICS-505","CICS-520","CICS-511","CICS-502");
        coReqItems = FXCollections.observableArrayList("CICS-580 Lecture","CICS-580 Lab");
        
        
        Section section = State.getCurrentSection();
        System.out.println(section.getCourseNum());
        if (section != null) {
            //set prerequisities
            preRequisiteList.setItems(preReqItems);
            //set corequisites
            coRequisiteList.setItems(coReqItems);
            //set course fields
            courseCode.setText(section.getCourse().getCourseNum() + " " 
                    + section.getCourse().getCourseDept());
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
            
            if (EnrollSection.isEnrolled((Student) CurrentUser.getUser(), section)) {
                enrollButton.setVisible(false);
            } else {
                dropButton.setVisible(false);
            }
        }
    }

    public static void processEnroll() throws SQLException {
        Section section = State.getCurrentSection();
        int studentID = CurrentUser.getUser().getID();

        if (canEnroll(section, studentID)) {
            String sql = "INSERT INTO enrollSection VALUES ("
                    + studentID + ", '"
                    + section.getSectionID() + "', '"
                    + section.getCourseDept() + "', '"
                    + section.getCourseNum() + "', '"
                    + section.getTermID()+"', ''"
                    + ")";
            DatabaseConnector.updateQuery(sql);
            System.out.println("\nCan!!!!!!!!   " + sql);
        } else {
            System.out.println("\nCannot be added!!!!!!!!!");
        }
    }

    public static boolean canEnroll(Section section, int studentID) throws SQLException {
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
        System.out.println("\ncheckPrerequisite: true");
        return true;
    }

    public static void processDrop() throws SQLException {
        Section section = State.getCurrentSection();
        int studentID = CurrentUser.getUser().getID();

        if (canDropWithoutW(section, studentID)) {
            String sql = "DELETE FROM enrollSection WHERE "
                    + "studentID=" + studentID + " AND "
                    + "sectionID='" + section.getSectionID() + "' AND "
                    + "courseDept='" + section.getCourseDept() + "' AND "
                    + "courseNum='" + section.getCourseNum() + "' AND "
                    + "termID='" + section.getTermID() + "' ";
            //DatabaseConnector.updateQuery(sql);
            System.out.println("\nCan!!!!!!!!   " + sql);
        } else {
            section.setStatus("W");
            //section.update();
            System.out.println("\nWill be add W!!!!!!!!!");
        }
    }

    public static boolean canDropWithoutW(Section section, int studentID) throws SQLException {
        if (EnrollSection.registrationEndNotPass(section)) {
            return true;
        } 
        return false;
    }
}

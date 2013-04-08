/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.*;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SectionController implements Initializable {

    @FXML
    TextField courseNum;
    @FXML
    TextField courseName;
    @FXML
    TextField courseDept;
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
    Button dropButton;
    @FXML
    Button enrollButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Section section = State.getCurrentSection();
        System.out.println(section.getCourseNum());
        if (section != null) {
            //set course fields
            courseNum.setText(section.getCourse().getCourseNum());
            courseName.setText(section.getCourse().getCourseName());
            courseInfo.setText(section.getCourse().getInfo());
            courseDept.setText(section.getCourse().getCourseDept());
            courseCredits.setText(Integer.toString(section.getCourse().getCredit()));
            String name = section.getInstructor().getFirstName() + " "
                    + section.getInstructor().getLastName();
            instructor.setText(name);
            courseDept.setText(section.getCourseDept());
            startDate.setText(section.getTerm().getStart().toString());
            endDate.setText(section.getTerm().getEnd().toString());
            lastDayToEnroll.setText(section.getTerm().getRegistrationEnd().toString());
            lastDayToWithdraw.setText(section.getTerm().getDropWithoutW().toString());
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

        if (canDrop(section, studentID)) {
            String sql = "DELETE FROM enrollSection WHERE "
                    + "studentID=" + studentID + " AND "
                    + "sectionID='" + section.getSectionID() + "' AND "
                    + "courseDept='" + section.getCourseDept() + "' AND "
                    + "courseNum='" + section.getCourseNum() + "' AND "
                    + "termID='" + section.getTermID() + "' ";
            //DatabaseConnector.updateQuery(sql);
            System.out.println("\nCan!!!!!!!!   " + sql);
        } else {
            System.out.println("\nCannot be del!!!!!!!!!");
        }
    }

    public static boolean canDrop(Section section, int studentID) throws SQLException {
        return true;
    }
}

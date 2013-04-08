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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SectionController implements Initializable {

    @FXML
    Pane sidebar;
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
        ViewManager.loadSidebar(sidebar);
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
                    + studentID + ", "
                    + section.getSectionID() + ", "
                    + section.getCourseDept() + ", "
                    + section.getCourseNum() + ", "
                    + section.getTermID()
                    + ")";
            //DatabaseConnector.updateQuery(sql);
            System.out.println("\nCan!!!!!!!!   " + sql);
        } else {
            System.out.println("\nCannot be added!!!!!!!!!");
        }
    }

    public static ResultSet fetchEnrolledCourses(int studentID) throws SQLException {
        ResultSet rs;
        String sql = "select sectionID, courseDept, courseNum, termID from enrollSection where studentID=" + studentID;
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }

    public static ResultSet fetchPrereqCourses(Section section) throws SQLException {
        //CurrentUser.getUser().getID();
        ResultSet rs;
        String sql = "select * from prerequisite where courseNum='"
                + section.getCourseNum() + "' and courseDept='" + section.getCourseDept() + "'"
                + "ORDER by prereqGroup";
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }

    public static boolean isInSet(ResultSet rs_enrolled, String courseDept, String courseNum) throws SQLException {
        rs_enrolled.beforeFirst();
        while (rs_enrolled.next()) {
            if (courseDept.compareTo(rs_enrolled.getString("courseDept")) == 0 && courseNum.compareTo(rs_enrolled.getString("courseNum")) == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkPrerequisite(Section section, int studentID) throws SQLException {
//        ResultSet rs;
//        String sql = " SELECT prereqDept, prereqNum FROM prerequisite pr"
//                + "	WHERE courseDept=" + section.getCourseDept()
//                + "		AND courseNum=" + section.getCourseNum()
//                + "		AND NOT EXISTS"
//                + "			( SELECT * FROM enrollSection e"
//                + "			WHERE  e.studentID=" + studentID
//                + "				AND e.courseDept=pr.prereqDept"
//                + "				AND e.courseNum=pr.prereqNum"
//                + "				AND (e.grade='A' OR e.grade='B' OR e.grade='C' OR e.grade='D')"
//                + "                )";
//        rs = DatabaseConnector.returnQuery(sql);
//        return rs;
        ResultSet rs_enrolled = fetchEnrolledCourses(studentID);
        ResultSet rs_pre = fetchPrereqCourses(section);
        System.out.println("+-------------------------------------+");
        while (rs_enrolled.next()) {
            System.out.println(rs_enrolled.getString("courseDept") + "__" + rs_enrolled.getString("courseNum"));
        }
        System.out.println("=========");
        while (rs_pre.next()) {
            System.out.println(rs_pre.getString("prereqDept") + "__" + rs_pre.getString("prereqNum") + "__" + rs_pre.getString("prereqGroup"));
        }
        System.out.println("+-------------------------------------+");
        rs_enrolled.beforeFirst();
        rs_pre.beforeFirst();
        int flag = 0;
        int change = 0;
        while (rs_pre.next()) {
            if (change == 0) {
                flag = 0;
            }
            if (isInSet(rs_enrolled, rs_pre.getString("prereqDept"), rs_pre.getString("prereqNum"))) {
                flag = 1;
            }
            String now = rs_pre.getString("prereqGroup");
            while (!isInSet(rs_enrolled, rs_pre.getString("prereqDept"), rs_pre.getString("prereqNum"))) {
                rs_pre.next();
                if (now.compareTo(rs_pre.getString("prereqGroup")) != 0) {
                    change = 0;
                    if (flag == 0) {
                        return false;
                    }
                }
            }
            flag = 1;
            change = 1;
        }
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAlreadyRegistered(Section section, int studentID) throws SQLException {

        ResultSet rs_stu_reg = fetchEnrolledCourses(CurrentUser.getUser().getID());
        while (rs_stu_reg.next()) {
            if (rs_stu_reg.getString("sectionID").compareTo(section.getSectionID()) == 0
                    && rs_stu_reg.getString("courseDept").compareTo(section.getCourseDept()) == 0
                    && rs_stu_reg.getString("courseNum").compareTo(section.getCourseNum()) == 0) {
                System.out.println("\nisAlreadyRegistered returns: true");
                return true;
            }
        }
        System.out.println("\nisAlreadyRegistered returns: false");
        return false;
    }

    public static boolean canEnroll(Section section, int studentID) throws SQLException {
//        if (!registrationEndNotPass(section)) {
//            return false;
//        }
//        if (isAlreadyRegistered(section, studentID)) {
//            return false;
//        }
//        if (notFull(section)) {
//            return false;
//
//        }
        if (checkPrerequisite(section, studentID) == false) {
            System.out.println("\ncheckPrerequisite: false");
            return false;
        }
        System.out.println("\ncheckPrerequisite: true");
        return true;
    }

    public static boolean registrationEndNotPass(Section section) {
        Term currentTerm = State.getCurrentTerm();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println(now + "___" + currentTerm.getRegistrationEnd());
        if (now.compareTo(currentTerm.getRegistrationEnd()) < 0) {
            System.out.println("\nregistrationEndNotPass returns: true");
            return true;
        } else {
            System.out.println("\nregistrationEndNotPass returns: false");
            return false;
        }
    }

    public static boolean notFull(Section section) {
        if (section.getStatus().compareTo("open") == 0) {
            System.out.println("\nnotFull returns: true");
            return true;
        } else {
            System.out.println("\nnotFull returns: false");
            return false;
        }
    }
}

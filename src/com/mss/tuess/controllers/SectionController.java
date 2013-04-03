/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.EnrollSection;
import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import com.mss.tuess.util.State;
import java.sql.SQLException;
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
}

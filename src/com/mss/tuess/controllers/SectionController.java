/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import static com.mss.tuess.controllers.SectionController.canDropWithW;
import static com.mss.tuess.controllers.SectionController.canDropWithoutW;
import static com.mss.tuess.controllers.SectionController.validate;
import com.mss.tuess.entity.*;
import com.mss.tuess.entity.Student;
import com.mss.tuess.util.CurrentUser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.mss.tuess.util.State;
import com.mss.tuess.util.Validator;
import com.mss.tuess.util.ViewManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * @SectionController This method is the controller of section operations,
 * validating enroll constrains
 */
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
    private TableColumn<SectionClass, String> type;
    @FXML
    private TableColumn<SectionClass, String> day;
    @FXML
    private TableColumn<SectionClass, String> displayStartTime;
    @FXML
    private TableColumn<SectionClass, String> displayEndTime;
    @FXML
    private TableColumn<SectionClass, String> location;
    private static Validator validator = new Validator();
    private static Stage stage;

    /**
     * Initializes the controller class.
     *
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
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
                if (!EnrollSection.withdrawWithWNotPass(section)) {
                    dropButton.setDisable(true);
                    ViewManager.setStatus("Last day to drop has passed");
                }
            } else {
                dropButton.setVisible(false);
                if (!EnrollSection.registrationEndNotPass(section)) {
                    enrollButton.setDisable(true);
                    ViewManager.setStatus("Registration has ended");
                }
                try {
                    if (EnrollSection.isAlreadyRegistered(section, CurrentUser.getUser().getID())) {
                        enrollButton.setDisable(true);
                        ViewManager.setStatus("You have withdrawn from this course");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     */
    @FXML
    public void processEnroll() {

        validator.reset();
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
            validate(section, studentID);
            if (!validator.hasErrors()) {
                es.insert();
                section.setRegistered(section.getRegistered() + 1);
                if (section.getRegistered() == section.getCapacity()) {
                    section.setStatus("full");
                }
                    section.update();
                initialize(null, null);
                ViewManager.setStatus("Enrolled successfully");
            } else {
                ViewManager.setStatus(validator.getErrors().get(0).toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @validate This method checks all kinds of constrains when trying to
     * enroll in a particular course section.
     */
    public static void validate(Section section, int studentID) throws SQLException {
        Student student = new Student();
        student.fetch(CurrentUser.getUser().getID());
        if (!EnrollSection.registrationEndNotPass(section)) {
            validator.addError("Registration period is over");
        }
        if (EnrollSection.isAlreadyRegistered(section, studentID)) {
            validator.addError("You are either already registered for this course or have dropped the course");
        }
        if (EnrollSection.isFull(section)) {
            validator.addError("There are no more seats remaining in this section");
        }
        if (EnrollSection.meetsPrerequisites(section, studentID) == false) {
            validator.addError("You do not have the prerequisites to take this course. Talk to course instructor.");
        }
        if (EnrollSection.isTimeConflict(student, section)) {
            //validator.addError("There is a time conflict with one of the other courses you are registered for");
        }
    }

    /**
     * @processDrop This method is to perform the drop enrolled course sections.
     */
    @FXML
    public void processDrop() {
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
                    if (canDropWithW(section, studentID)) {
                        DialogResponse response = Dialogs.showConfirmDialog(stage,
                                "Do you want to continue? You will get a 'W' if you choose YES on your transcript", "Confirm Dialog", "title");
                        if (response.equals(DialogResponse.YES)) {
                            System.out.println("YES!!!!!!!!!");
                            es.setGrade("W");
                            es.update();
                            ViewManager.setStatus("Course has been dropped");

                        } else if (response.equals(DialogResponse.NO)) {
                            System.out.println("NO!!!!!!!!!");
                            ViewManager.setStatus("Drop Canceled");

                        }
                    }
                }
                initialize(null, null);
            } else {
                ViewManager.setStatus("Unable to drop course");
            }
        } catch (SQLException ex) {
            Logger.getLogger(SectionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @canDropWithoutW This method is to check if one section could be dropped
     * without a W label.
     */
    public static boolean canDropWithoutW(Section section, int studentID) throws SQLException {
        if (EnrollSection.registrationEndNotPass(section)) {
            return true;
        }
        return false;
    }

    /**
     * @canDropWithW This method is to check if one section could be dropped
     * with a W label.
     */
    public static boolean canDropWithW(Section section, int studentID) throws SQLException {
        if (EnrollSection.withdrawWithWNotPass(section)) {
            return true;
        }
        return false;
    }

    /**
     * @goBack This method is to go to previous page of search course results.
     */
    public static void goBack() {
        ViewManager.showPreviousView();
    }
}
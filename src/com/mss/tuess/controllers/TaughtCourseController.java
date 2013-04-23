/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import javafx.scene.control.TableView;
import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.*;
import com.mss.tuess.entity.EnrollSection;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
import com.mss.tuess.util.Validator;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * @TaughtCourseController
 * This method is the controller of getting all courses taught by a instructor.
 */

public class TaughtCourseController implements Initializable {

    //Section Table and fields
    @FXML
    private TableView<Section> sectionTable;
    @FXML
    private TableColumn<Section, String> courseNum;
    @FXML
    private TableColumn<Section, String> courseDept;
    @FXML
    private TableColumn<Section, String> term;
    @FXML
    private TableColumn<Section, String> credit;
    //Section Table and fields
    @FXML
    private TableView<EnrollSection> studentTable;
    @FXML
    private TableColumn<EnrollSection, String> sectionID;
    @FXML
    private TableColumn<EnrollSection, String> studentID;
    @FXML
    private TableColumn<EnrollSection, String> grade;
    //filter fields
    @FXML
    private TextField sectionFilter;
    @FXML
    private TextField studentFilter;
    @FXML
    private TextField gradeField;
    //Section list
    private ObservableList<Section> sectionTableContent = FXCollections.observableArrayList();
    private ObservableList<Section> sectionFilterContent = FXCollections.observableArrayList();
    //Section list
    private ObservableList<EnrollSection> studentTableContent = FXCollections.observableArrayList();
    private ObservableList<EnrollSection> studentFilterContent = FXCollections.observableArrayList();
    private int checkChangeOutside;
    private int checkChangeInside;
    private int tracker;

    /**
     * Constructor of CourseSearchController_unused
     *
     * @throws SQLException
     */
    public TaughtCourseController() throws SQLException {
        int courseSize;
        tracker = 0;
        checkChangeInside = 0;
        sectionTableContent.clear();
        studentTableContent.clear();
        int instructorID = CurrentUser.getUser().getID();
        checkChangeOutside = 0;
        Section.fetchByInstructor(instructorID, State.getCurrentTerm().getTermID());
        courseSize = Section.getAll().size();
        System.out.println(courseSize);
        if (courseSize > 0) {
            sectionTableContent.addAll(Section.getAll());
        }
        sectionFilterContent.addAll(sectionTableContent);

        //event listener for course filter 
        sectionTableContent.addListener(new ListChangeListener<Section>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Section> change) {
                sectionFilterRefresh();
            }
        });

        //event listener for course filter 
        studentTableContent.addListener(new ListChangeListener<EnrollSection>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends EnrollSection> change) {
                enrollSectionFilterRefresh();
            }
        });
    }

    /**
     * Handle the order for each course column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<Section, ?>> sortOrder = new ArrayList(sectionTable.getSortOrder());
        sectionTable.getSortOrder().clear();
        sectionTable.getSortOrder().addAll(sortOrder);
    }

    /**
     * Handle the order for each section column
     */
    private void sectionTableOrderAct() {
        ArrayList<TableColumn<EnrollSection, ?>> sectionSortOrder = new ArrayList(studentTable.getSortOrder());
        studentTable.getSortOrder().clear();
        studentTable.getSortOrder().addAll(sectionSortOrder);
    }

    /**
     * Refresh the content of the filter
     */
    private void sectionFilterRefresh() {
        sectionFilterContent.clear();
        for (Section section : sectionTableContent) {
            if (sectionFilterChecker(section)) {
                sectionFilterContent.add(section);
            }
        }
        tableOrderAct();
    }

    /**
     * Refresh the content of the filter
     */
    private void enrollSectionFilterRefresh() {
        studentFilterContent.clear();
        for (EnrollSection enrollsection : studentTableContent) {
            if (enrollSectionFilterChecker(enrollsection)) {
                studentFilterContent.add(enrollsection);
            }
        }
        sectionTableOrderAct();
    }

    /**
     * Control the course filter
     *
     * @param section the course object
     */
    private boolean sectionFilterChecker(Section section) {
        String filterString = sectionFilter.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (section.getSectionID().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (section.getCourseDept().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (section.getCourseNum().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (section.getTermID().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Control the section class filter
     *
     * @param SectionClass the course object
     */
    private boolean enrollSectionFilterChecker(EnrollSection enrollsection) {
        String filterString = studentFilter.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString;
        lowerCaseFilterString = filterString.toLowerCase();

        if (Integer.toString(enrollsection.getStudentID()).indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (enrollsection.getGrade().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //searchErrorLabel.setText("");
        /**
         * map the course table attributes
         */
        courseNum.setCellValueFactory(new PropertyValueFactory<Section, String>("courseNum"));
        courseDept.setCellValueFactory(new PropertyValueFactory<Section, String>("courseDept"));
        term.setCellValueFactory(new PropertyValueFactory<Section, String>("termID"));

        sectionTable.setItems(sectionFilterContent);

        /**
         * course filter event listener
         */
        sectionFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                sectionFilterRefresh();
            }
        });


        /**
         * section filter event listener
         */
        studentFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                enrollSectionFilterRefresh();
            }
        });
        /*
         * Event Handler to capture the selected course
         */
        sectionTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Section>() {
            @Override
            public void changed(ObservableValue<? extends Section> ov, Section t, Section t1) {
                int selectedIndex = sectionTable.getSelectionModel().getSelectedIndex();
                checkChangeOutside = 0;
                checkChangeInside = 0;
                tracker = selectedIndex;

                if (sectionFilterContent.size() > 0 && selectedIndex >= 0) {
                    System.out.println("Index : " + selectedIndex);

                    try {
                        String sectionID = sectionFilterContent.get(selectedIndex).getSectionID();
                        String courseNum = sectionFilterContent.get(selectedIndex).getCourseNum();
                        String courseDept = sectionFilterContent.get(selectedIndex).getCourseDept();
                        String currentTerm = sectionFilterContent.get(selectedIndex).getTermID();
                        EnrollSection.fetchAllValid(sectionID, courseDept, courseNum, currentTerm);

                        studentTableContent.clear();
                        studentTableContent.addAll(EnrollSection.getAll());
                        studentFilterContent.clear();
                        studentFilterContent.addAll(studentTableContent);
                    } catch (Exception ex) {
                        System.out.println("gotcha array out of bound 1");
                        Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        /*
         * Event Handler to capture the selected course
         */
        studentTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EnrollSection>() {
            @Override
            public void changed(ObservableValue<? extends EnrollSection> ov, EnrollSection t, EnrollSection t1) {
                int selectedIndex = studentTable.getSelectionModel().getSelectedIndex();
                EnrollSection currentEnrollSection = new EnrollSection();
                checkChangeOutside = 1;
                System.out.println("Index : " + selectedIndex);
                if (studentFilterContent.size() > 0 && selectedIndex >= 0) {
                    try {
                        State.setCurrentEnrollSection(studentFilterContent.get(selectedIndex));
                        currentEnrollSection.fetch(studentFilterContent.get(selectedIndex).getStudentID(),
                                studentFilterContent.get(selectedIndex).getSectionID(),
                                studentFilterContent.get(selectedIndex).getCourseDept(),
                                studentFilterContent.get(selectedIndex).getCourseNum(),
                                studentFilterContent.get(selectedIndex).getTermID());
                        State.setCurrentEnrollSection(currentEnrollSection);
                        gradeField.setText(currentEnrollSection.getGrade());
                    } catch (Exception ex) {
                        Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });


        /**
         * map the section table attributes
         */
        studentID.setCellValueFactory(new PropertyValueFactory<EnrollSection, String>("studentID"));

        grade.setCellValueFactory(new PropertyValueFactory<EnrollSection, String>("grade"));

        studentTable.setItems(studentFilterContent);
    }
    /**
    * @updateStuGrade
    * This method is to update the grade of a student in a particular section.
    */

    public void updateStuGrade() {
        Validator validator;
        validator = new Validator();

        if (checkChangeOutside == 1 || checkChangeInside == 1) {
            checkChangeInside = 1;
            EnrollSection se = new EnrollSection();
            se = State.getCurrentEnrollSection();
            String gradeToStu = gradeField.getText();
            if ((gradeToStu.compareTo("A") == 0
                    || gradeToStu.compareTo("B") == 0
                    || gradeToStu.compareTo("C") == 0
                    || gradeToStu.compareTo("D") == 0
                    || gradeToStu.compareTo("F") == 0)) {
                se.setGrade(gradeToStu);
                try {
                    System.out.println(se.getStudentID()+"___"+se.getGrade());
                    System.out.println("Tracker: "+tracker);
                    System.out.println("Index: "+studentTable.getSelectionModel().getSelectedIndex());
                    se.update();
                    studentFilterContent.set(studentTable.getSelectionModel().getSelectedIndex(), se);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(TaughtCourseController.class.getName()).log(Level.SEVERE, null, ex);
                }
                checkChangeOutside = 0;

                ViewManager.setStatus("Successfully Updated the Grade!");
            } else {
                ViewManager.setStatus("Please input a valid grade!");
            }
        } else {
            ViewManager.setStatus("Must choose someone!");
        }
    }
}

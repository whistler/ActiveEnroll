/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.controllers;

import com.mss.tuess.entity.Section;
import com.mss.tuess.entity.*;
import com.mss.tuess.entity.EnrollSection;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.State;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TaughtCourseController implements Initializable {

    //Section Table and fields
    @FXML
    private TableView<Section> sectionTable;
    @FXML
    private TableColumn<Section, String> courseNum;
    @FXML
    private TableColumn<Section, String> courseName;
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

    /**
     * Constructor of CourseSearchController_unused
     *
     * @throws SQLException
     */
    public TaughtCourseController() throws SQLException {
        int courseSize;

        sectionTableContent.clear();
        studentTableContent.clear();
        int instructorID = CurrentUser.getUser().getID();

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
    private void sectionClassTableOrderAct() {
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
        sectionClassTableOrderAct();
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
        String filterString = sectionFilter.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (Integer.toString(enrollsection.getStudentID()).indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (enrollsection.getGrade().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //searchErrorLabel.setText("");
        /**
         * map the course table attributes
         */
        courseNum.setCellValueFactory(new PropertyValueFactory<Section, String>("courseNum"));
        courseName.setCellValueFactory(new PropertyValueFactory<Section, String>("courseName"));
        courseDept.setCellValueFactory(new PropertyValueFactory<Section, String>("courseDept"));
        term.setCellValueFactory(new PropertyValueFactory<Section, String>("term"));
        credit.setCellValueFactory(new PropertyValueFactory<Section, String>("credit"));

        sectionTable.setItems(sectionFilterContent);

        /*
         * Event Handler to capture the selected course
         */
        sectionTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Section>() {
            @Override
            public void changed(ObservableValue<? extends Section> ov, Section t, Section t1) {
                int selectedIndex = sectionTable.getSelectionModel().getSelectedIndex();
                System.out.println("Index : " + selectedIndex);

                try {
                    String sectionID = sectionFilterContent.get(selectedIndex).getSectionID();
                    String courseNum = sectionFilterContent.get(selectedIndex).getCourseNum();
                    String courseDept = sectionFilterContent.get(selectedIndex).getCourseDept();
                    String currentTerm = State.getCurrentTerm().getTermID();
                    //Section.fetchStus(sectionID,courseDept, courseNum, currentTerm);
                    EnrollSection.fetchAll(sectionID, courseDept, courseNum, currentTerm);

                    studentTableContent.clear();
                    studentTableContent.addAll(EnrollSection.getAll());
                    studentFilterContent.clear();
                    studentFilterContent.addAll(studentTableContent);
                } catch (Exception ex) {
                    System.out.println("gotcha array out of bound 1");
                    Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
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

                System.out.println("Index : " + selectedIndex);

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
        });


        /**
         * map the section table attributes
         */
        studentID.setCellValueFactory(new PropertyValueFactory<EnrollSection, String>("studentID"));

        grade.setCellValueFactory(new PropertyValueFactory<EnrollSection, String>("grade"));

        studentTable.setItems(studentFilterContent);
    }

    public void updateStuGrade() {
        EnrollSection se = new EnrollSection();
        se = State.getCurrentEnrollSection();
        se.setGrade(gradeField.getText());
        try {
            se.update();
        } catch (SQLException ex) {
            Logger.getLogger(TaughtCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //studentTable.
    }
}
package com.mss.tuess.controllers;

import java.net.URL;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import com.mss.tuess.entity.Transcriptrecord;
import com.mss.tuess.util.ViewManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @TranscriptController
 * This method is to get all the grades information of a particular student.
 */

public class TranscriptController implements Initializable {

    
    @FXML private TextField filterText;
    @FXML private TableView<Transcriptrecord> transcriptTable;
    @FXML private TableColumn<Transcriptrecord, String> courseDept;
    @FXML private TableColumn<Transcriptrecord, String> courseNum;
    @FXML private TableColumn<Transcriptrecord, String> courseName;
    @FXML private TableColumn<Transcriptrecord, String> termID;
    @FXML private TableColumn<Transcriptrecord, Integer>credit;
    @FXML private TableColumn<Transcriptrecord, String> grade;
    @FXML private Label totalCredit;
    @FXML private Label GPA;

    private ObservableList<Transcriptrecord> tableContent = FXCollections.observableArrayList();
    private ObservableList<Transcriptrecord> filterContent = FXCollections.observableArrayList();
 

    /**
     * Constructor of TranscriptController
     *
     * @throws SQLException
     */
    public TranscriptController() throws SQLException {
        tableContent.clear();
        Transcriptrecord.fetch();
        int transcriptSize = Transcriptrecord.getAll().size();
        int courseCounter = 0;
        tableContent.clear();
        if (transcriptSize > 0) {
            tableContent.addAll(Transcriptrecord.getAll());
        }
        /**while (transcriptSize - 1 != courseCounter) {
            tableContent.add(TranscriptList.get(courseCounter));
            courseCounter++;
        }*/
        filterContent.addAll(tableContent);

        tableContent.addListener(new ListChangeListener<Transcriptrecord>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Transcriptrecord> change) {
                filterRefresh();
            }
        });
    }

    /**
     * Handle the order for each column
     */
    private void tableOrderAct() {
        ArrayList<TableColumn<Transcriptrecord, ?>> sortOrder = new ArrayList(transcriptTable.getSortOrder());
        transcriptTable.getSortOrder().clear();
        transcriptTable.getSortOrder().addAll(sortOrder);
    }

    /**
     * Refresh the content of the filter
     */
    private void filterRefresh() {
        filterContent.clear();
        for (Transcriptrecord transcript : tableContent) {
            if (filterChecker(transcript)) {
                filterContent.add(transcript);
            }
        }
        tableOrderAct();
    }

    private boolean filterChecker(Transcriptrecord transcript) {
        String filterString = filterText.getText();
        if (filterString == null || filterString.isEmpty()) {
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
        courseDept.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("courseDept"));
        courseNum.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("courseNum"));
        courseName.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("termID"));
        credit.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, Integer>("credit"));
        grade.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("grade"));
        totalCredit.setText(Integer.toString(Transcriptrecord.getAddCredit()));
        
        if(Transcriptrecord.getAddCredit()==0)
        {
            GPA.setText("0");
        }
        else
        {
           int addCmG = Transcriptrecord.getAddCreditMultipleGrade();
           int addC = Transcriptrecord.getAddCredit();
           double targetGPA = addCmG / (double) addC;
           GPA.setText(Double.toString(targetGPA));
        }
        
        transcriptTable.setItems(filterContent);

    }
}


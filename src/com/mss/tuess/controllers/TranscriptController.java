package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import com.mss.tuess.entity.Transcriptrecord;
import com.mss.tuess.util.ViewManager;
import com.mss.tuess.entitylist.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TranscriptController implements Initializable {

    
    @FXML
    Pane sidebar;
    @FXML
    private TextField filterText;
    @FXML
    private TableView<Transcriptrecord> transcriptTable;
    @FXML
    private TableColumn<Transcriptrecord, String> courseName;;
    @FXML
    private TableColumn<Transcriptrecord, Integer> credit;
    @FXML
    private TableColumn<Transcriptrecord, String> termID;
    @FXML
    private TableColumn<Transcriptrecord, String> grade;
    @FXML
    private TextField totalCredit;
    @FXML
    private TextField GPA;

    private ObservableList<Transcriptrecord> tableContent = FXCollections.observableArrayList();
    private ObservableList<Transcriptrecord> filterContent = FXCollections.observableArrayList();
 

    /**
     * Constructor of TranscriptController
     *
     * @throws SQLException
     */
    public TranscriptController() throws SQLException {
        tableContent.clear();
        TranscriptList.fetch();
        int transcriptSize = TranscriptList.getAll().size();
        int courseCounter = 0;
        tableContent.clear();
        while (transcriptSize - 1 != courseCounter) {
            tableContent.add(TranscriptList.get(courseCounter));
            courseCounter++;
        }
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ViewManager.loadSidebar(sidebar);
        courseName.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("courseName"));
        termID.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("termID"));
        grade.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, String>("grade"));
        credit.setCellValueFactory(new PropertyValueFactory<Transcriptrecord, Integer>("credit"));
        System.out.println(TranscriptList.getAddCredit()+"___in initilize___"+TranscriptList.getAddCreditMultipleGrade()/TranscriptList.getAddCredit());
        System.out.println(TranscriptList.getAddCreditMultipleGrade());
        System.out.println(TranscriptList.getAddCredit());
        totalCredit.setText(Integer.toString(TranscriptList.getAddCredit()));
        GPA.setText(Double.toString(TranscriptList.getAddCreditMultipleGrade()/TranscriptList.getAddCredit()));
        
        transcriptTable.setItems(filterContent);

    }
}


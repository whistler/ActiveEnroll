package com.mss.tuess.controllers;

import com.mss.tuess.util.SendEmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javax.mail.MessagingException;

public class MailGradesController implements Initializable {
    
    @FXML 
    Button mailGrades;
    
    @FXML 
    Label mailGradesStatus;
    
    @FXML
    ProgressIndicator mailGradeProgress;
    
    @FXML
    Label mailProgressLabel;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mailGradesStatus.setText("");
        mailGradeProgress.setVisible(false);
        mailProgressLabel.setText("");
    }   
    
    @FXML
    private void mailInstructions()throws MessagingException, InterruptedException{
        
        mailProgressLabel.setText("Please wait. Mailing Grades...");
        mailGradeProgress.setVisible(true);
        mailGradeProgress.setProgress(-0.59F);

        //These hardcodings are to be removed and replaced by data from the database
        String[] toEmails = {"karthik.gt90@gmail.com",
                            "ibmmmm@gmail.com",
                            "renxin.zju@gmail.com",
                            "prabalsharma39@gmail.com",
                            "jasonchenliang@gmail.com",
                            "wenhcn@gmail.com",
                            };
        String emailSubject = "Term Grades - TUESS Team";
        String emailBody = "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<body>\n" +
                            "\n" +
                            "<table border=\"1\" >\n" +
                            "  <tr bgcolor=\"#7A7584\">\n" +
                            "    <th>Course Code</th>\n" +
                            "    <th>Course Name</th>\n" +
                            "    <th>Credits</th>\n" +
                            "    <th>Grade</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <th>CICS 505</th>\n" +
                            "    <th>Introduction to Software Systems</th>\n" +
                            "    <th>6</th>\n" +
                            "    <th>P</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <th>CICS 520</th>\n" +
                            "    <th>Database Management Systems</th>\n" +
                            "    <th>3</th>\n" +
                            "    <th>P</th>\n" +
                            "  </tr>\n" +
                            "  <tr>\n" +
                            "    <th>CICS 511</th>\n" +
                            "    <th>Computational Structures</th>\n" +
                            "    <th>1.5</th>\n" +
                            "    <th>P</th>\n" +
                            "  </tr>\n" +
                            "</table>\n" +
                            "\n" +
                            "<p><b>Note:</b> Please contact your corresponding course coordinator in case of any discrepancy.</p>\n" +
                            "<BR><BR>\n" +
                            "Cheers!\n" +
                            "<BR><b>TUESS Team<b>\n" +
                            "\n" +
                            "</body>\n" +
                            "</html>";
        //End of hardcoding
        
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendMail(toEmails, emailSubject, emailBody);
        mailGradesStatus.setText("Grades successfully mailed!");
        mailProgressLabel.setText("");
        mailGradeProgress.setProgress(1.00F);
    }
}

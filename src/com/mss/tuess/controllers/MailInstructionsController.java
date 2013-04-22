package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.EmailData;
import com.mss.tuess.util.ViewManager;
import com.mss.tuess.util.SendEmail;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javax.mail.MessagingException;

public class MailInstructionsController implements Initializable {
    
    
    @FXML
    TextField emailSubjectText;
    @FXML 
    TextArea emailBodyText;
    @FXML
    Button sendInstructions;
    @FXML
    Label sendEmailStatus;
    @FXML
    ProgressIndicator mailInstructionsProgress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendEmailStatus.setText("");
        mailInstructionsProgress.setVisible(false);
    }

    @FXML
    private void mailInstructions() throws MessagingException, SQLException {
        
        EmailData emailData;
    
        ArrayList<EmailData> emailList;
        emailList = new ArrayList<>();
    
        String toEmails;
        String emailSubject = this.emailSubjectText.getText();
        String emailBody = this.emailBodyText.getText();
        
        mailInstructionsProgress.setVisible(true);
        mailInstructionsProgress.setProgress(-0.59F);
        sendEmailStatus.setText("Sending mails...");
        ResultSet mailrs;
        mailrs = Student.fetchCurrentTerm();

        while (mailrs.next()) {
            int currentID = mailrs.getInt("studentID");
            ResultSet rs;
            String sql;
            sql = "select distinct student.email from student, enrollSection where student.studentID=enrollSection.studentID and enrollSection.studentID='" + currentID + "'";
            rs = DatabaseConnector.returnQuery(sql);
            rs.first();
            toEmails = rs.getString("email");

            emailData = new EmailData(toEmails, emailSubject, emailBody);
            emailList.add(emailData);
        }
        /**
         * Send Email
         */
        try {
            if(emailList.size() <= 0){
            } else {
                SendEmail sendMail = new SendEmail();
                sendMail.sendMail(emailList);
                sendEmailStatus.setText("Instructions mailed successfully!");
                mailInstructionsProgress.setProgress(1.00F);
            }
        } catch (MessagingException ex) {
            Logger.getLogger(com.mss.tuess.controllers.MailGradesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

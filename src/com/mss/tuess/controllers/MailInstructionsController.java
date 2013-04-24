package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.EmailData;
import com.mss.tuess.util.SendEmail;
import com.mss.tuess.util.ViewManager;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javax.mail.MessagingException;

/**
 * @LoginController
 * This method is the controller of mailing instruction function of administrator
 */
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
    @FXML
    Region veil;
            
    private SendInstructionsMailService sendInstructionsMail = new SendInstructionsMailService();
    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendEmailStatus.setText("");
        veil.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
        mailInstructionsProgress.progressProperty().bind(sendInstructionsMail.progressProperty());
        veil.visibleProperty().bind(sendInstructionsMail.runningProperty());
        mailInstructionsProgress.visibleProperty().bind(sendInstructionsMail.runningProperty());
        sendEmailStatus.visibleProperty().bind(sendInstructionsMail.runningProperty());         
    }
    
    
    /**
     * @mailInstructions
     * This method gets result set from database operation, and then for each student, there is a whole loop to generate instuctions
     */

    @FXML
    private void mailInstructions() throws MessagingException, SQLException {
        if(emailSubjectText.getText().trim().isEmpty() || emailBodyText.getText().trim().isEmpty()){
            ViewManager.setStatus("Email Subject and Body is mandatory");
        }else{
            ViewManager.setStatus("");
            sendEmailStatus.setText("Please wait. Sending emails...");
            sendInstructionsMail.restart();
        }
        
    }
    
    public class SendInstructionsMailService extends Service <String>{

        @Override
        protected Task createTask() {
            return new TriggerInstructionMails();
        }
    }

    /**
     * This class implements the thread to trigger grade mails to the students
     */
    public class TriggerInstructionMails extends Task<String>{  

        /**
         *
         * @return
         * @throws Exception
         */
        @Override protected String call() throws Exception {
            
            String message = "Success";
            EmailData emailData;

            ArrayList<EmailData> emailList;
            emailList = new ArrayList<>();

            String toEmails;
            String emailSubject = emailSubjectText.getText();
            String emailBody = emailBodyText.getText();

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
                    ViewManager.setStatus("Instructions successfully mailed!!");
                }
            } catch (MessagingException ex) {
                Logger.getLogger(com.mss.tuess.controllers.MailGradesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return message;
        }
    }
}

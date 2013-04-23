package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.EmailData;
import com.mss.tuess.util.SendEmail;
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
import javax.mail.MessagingException;

/**
 * @MailGradesController
 * This method is the controller of mailing grades function of administrator
 */


public class MailGradesController implements Initializable {

    @FXML
    Button mailGrades;
    @FXML
    Label mailGradesStatus;
    @FXML
    ProgressIndicator mailGradeProgress;
    @FXML
    Label mailProgressLabel;
    
    final SendMailService service1 = new SendMailService();

    /**
     * Initializes the controller class.
     * @param url is the address, implements java.io.Serializable
     * @param rb is the resource boundary
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        mailGradesStatus.setText("");
        mailGradeProgress.setVisible(false);
        mailProgressLabel.setText("");
        
    }
    
/**
 * @mailInstructions
 * This method gets result set from database operation, and then for each student, there is a whole loop to generate his own transcript table
 */


    @FXML
    private void mailInstructions() throws MessagingException, InterruptedException, SQLException {
        
       service1.restart();
    }
    
    /**
     * A service for getting the DailySales data. This service exposes an
     * ObservableList for convenience when using the service. This
     * <code>results</code> list is final, though its contents are replaced when
     * a service call successfully concludes.
     */
    public class SendMailService extends Service <String>{

        @Override
        protected Task createTask() {
            return new TriggerMails();
        }
    }

    public class TriggerMails extends Task<String>{  

        /**
         *
         * @return
         * @throws Exception
         */
        @Override protected String call() throws Exception {
            
            ArrayList<EmailData> emailList;
            emailList = new ArrayList<>();
            EmailData emailData;
            String message = "Sucess";
            mailProgressLabel.setText("Please wait. Mailing Grades...");
            mailGradeProgress.setVisible(true);
            mailGradeProgress.setProgress(-0.59F);
            int studentnumber = 0;

            ResultSet mailrs;

            mailrs = Student.fetchCurrentTerm();

            System.out.println("intomailfuntion");

            while (mailrs.next()) {

                int currentID = mailrs.getInt("studentID");
                ResultSet rs;
                String sql;
                sql = "select distinct course.coursenum, course.coursename ,course.credit, enrollSection.termID, enrollSection.grade, student.email from student,course,enrollSection where enrollSection.coursedept=course.courseDept and enrollSection.studentID=student.studentID and enrollSection.courseNum=course.coursenum and enrollSection.studentID='" + currentID + "'";
                rs = DatabaseConnector.returnQuery(sql);


                rs.first();

                String toEmails = rs.getString("email");
                String emailSubject = "Term Grades for student ID: "+ currentID +"- TUESS Team";
                String emailBody1 = "<!DOCTYPE html>\n"
                        + "<html>\n"
                        + "<body>\n"
                        + "\n"
                        + "<table border=\"1\" >\n"
                        + "  <tr bgcolor=\"#7A7584\">\n"
                        + "    <th>Course Code</th>\n"
                        + "    <th>Course Name</th>\n"
                        + "    <th>Credits</th>\n"
                        + "    <th>Grade</th>\n"
                        + "  </tr>\n"
                        + "  <tr>\n";
                String emailBody2 = "\n";
                rs.beforeFirst();

                while (rs.next()) {
                    String mailCourseNumber = rs.getString("coursenum");
                    String mailCourseName = rs.getString("coursename");
                    String mailCredit = rs.getString("credit");
                    String mailGrade = rs.getString("grade");


                    emailBody2 += "    <th>" + mailCourseNumber + "</th>\n" + // !!!!!!!!
                            "    <th>" + mailCourseName + "</th>\n" + // !!!!!!!!
                            "    <th>" + mailCredit + "</th>\n" + // !!!!!!!!
                            "    <th>" + mailGrade + "</th>\n" + // !!!!!!!!
                            "  </tr>\n"
                            + "  <tr>\n";
                }

                String emailBody3 = "  </tr>\n"
                        + "</table>\n"
                        + "\n"
                        + "<p><b>Note:</b> Please contact your corresponding course coordinator in case of any discrepancy.</p>\n"
                        + "<BR><BR>\n"
                        + "Cheers!\n"
                        + "<BR><b>TUESS Team<b>\n"
                        + "\n"
                        + "</body>\n"
                        + "</html>";

                String emailBody = emailBody1 + emailBody2 + emailBody3;

                //build email list
                emailData = new EmailData(toEmails, emailSubject, emailBody);
                System.out.println("Adding student "+studentnumber);
                studentnumber++;
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
                    mailProgressLabel.setText("Grades mailed succesfully");
                    mailGradeProgress.setProgress(1.00F);
                }
            } catch (MessagingException ex) {
                Logger.getLogger(com.mss.tuess.controllers.MailGradesController.class.getName()).log(Level.SEVERE, null, ex);
            }

                return message;
            }
        }
}

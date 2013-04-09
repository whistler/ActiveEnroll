package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.entity.Transcriptrecord;
import com.mss.tuess.entitylist.StudentList;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.SendEmail;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private void mailInstructions() throws MessagingException, InterruptedException, SQLException {

        mailProgressLabel.setText("Please wait. Mailing Grades...");
        mailGradeProgress.setVisible(true);
        mailGradeProgress.setProgress(-0.59F);
        int studentnumber=0;
        ResultSet mailrs;

        mailrs = StudentList.fetchCurrentTerm();

        System.out.println("intomailfuntion");

        while (mailrs.next()) {

            int currentID = mailrs.getInt("studentID");
            ResultSet rs;
            String sql;
            sql = "select distinct course.coursenum, course.coursename ,course.credit, enrollSection.termID, enrollSection.grade, student.email from student,course,enrollSection where enrollSection.coursedept=course.courseDept and enrollSection.courseNum=course.coursenum and enrollSection.studentID='" + currentID + "'";

            //sql = "select distinct course.coursename ,course.credit, enrollSection.termID, enrollSection.grade, student.emaol from student,course,enrollSection where enrollSection.coursedept=course.courseDept and enrollSection.courseNum=course.coursenum and enrollSection.studentID=12345678";
            rs = DatabaseConnector.returnQuery(sql);
            System.out.println("into_student_loop_resultset");


            rs.first();
            //These hardcodings are to be removed and replaced by data from the database
            String toEmails = rs.getString("email");
            String emailSubject = "Term Grades - TUESS Team";
            //System.out.println("into_student_loop_resultset_after_passemail");
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
            //End of hardcoding
            //System.out.println(emailBody3);
            //System.out.println("outof_addcourse_loop");
            
            String emailBody = emailBody1 + emailBody2 + emailBody3;
            //System.out.println(toEmails);
            SendEmail sendEmail = new SendEmail();
            sendEmail.sendMail(toEmails, emailSubject, emailBody);
            
            studentnumber++;
            System.out.println(studentnumber);









            mailGradesStatus.setText("Grades successfully mailed!");
            mailProgressLabel.setText("");
            mailGradeProgress.setProgress(1.00F);
        }
    }
; // while loop for each student ends here
}

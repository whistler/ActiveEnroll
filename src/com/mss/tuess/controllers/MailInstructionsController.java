package com.mss.tuess.controllers;

import com.mss.tuess.entity.Student;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.ViewManager;
import com.mss.tuess.util.SendEmail;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.mail.MessagingException;

public class MailInstructionsController implements Initializable {

    @FXML
    Button sendInstructions;
    @FXML
    Label sendEmailStatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendEmailStatus.setText("");
    }

    @FXML
    private void mailInstructions() throws MessagingException, SQLException {

        //These hardcodings are to be removed and replaced by data from the database
        String toEmails;
        String emailSubject = "Pre enrolment Activities - TUESS Team";
        String emailBody = "Test Mail <BR><BR><BR>cheers!!!<BR><b>TUESS Team</b>";
        //End of hardcoding
        int studentnumber=0;

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
            System.out.println(toEmails);

            SendEmail sendEmail = new SendEmail();
            //sendEmail.sendMail(toEmails, emailSubject, emailBody);
            
            
            studentnumber++;
            System.out.println(studentnumber);

        }
    }
; // while loop for each student ends here
}

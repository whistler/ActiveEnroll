package com.mss.tuess.controllers;

import com.mss.tuess.util.ViewManager;
import com.mss.tuess.util.SendEmail;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javax.mail.MessagingException;

public class MailInstructionsController implements Initializable {
    
    @FXML Button sendInstructions;
    @FXML Label sendEmailStatus;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sendEmailStatus.setText("");
    }   
    
    @FXML
    private void mailInstructions()throws MessagingException{
        
        //These hardcodings are to be removed and replaced by data from the database
        String[] toEmails = {"karthik.gt90@gmail.com",
                            "ibmmmm@gmail.com",
                            "renxin.zju@gmail.com",
                            "prabalsharma39@gmail.com",
                            "jasonchenliang@gmail.com",
                            "wenhcn@gmail.com",
                            };
        String emailSubject = "Pre enrolment Activities - TUESS Team";
        String emailBody = "Test Mail <BR><BR><BR>cheers!!!<BR><b>TUESS Team</b>";
        //End of hardcoding
        
        SendEmail sendEmail = new SendEmail();
        sendEmail.sendMail(toEmails, emailSubject, emailBody);
        sendEmailStatus.setText("Mails successfully sent!");
    }
}

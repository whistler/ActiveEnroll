package com.mss.tuess.util;

import java.util.ArrayList;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

/**
 * SendEmail class. Used to send email to student.
 */
public class SendEmail {
    
    private Properties emailProperties;
    private Session mailSession;
    private MimeMessage emailMessage;
    private ArrayList<EmailData> emailList;

    public SendEmail(){
        setMailServerProperties();
    }

    /**
     *
     * @param emailList
     * @return
     * @throws MessagingException
     */
    public String sendMail(ArrayList<EmailData> emailList) throws MessagingException{
        
        String result;
        
        this.emailList = emailList;
        createEmailMessage();
        result = sendEmail();
        return result;
    }
 
    private void setMailServerProperties() {

          String emailPort = "587";//gmail's smtp port

          emailProperties = System.getProperties();
          emailProperties.put("mail.smtp.port", emailPort);
          emailProperties.put("mail.smtp.auth", "true");
          emailProperties.put("mail.smtp.starttls.enable", "true");
    }
 
    private void createEmailMessage() throws AddressException,MessagingException {

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
    }
 
    private String sendEmail() throws AddressException, MessagingException {

            String emailHost = "smtp.gmail.com";
            String fromUser = "tuessteam@gmail.com";
            String fromUserEmailPassword = "soonandsofors";

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, fromUser, fromUserEmailPassword);
          
            /**
             * loop through and send email
             */
            for(int i=0; i<emailList.size(); i++){
                
                emailMessage.setRecipients(Message.RecipientType.TO, emailList.get(i).getToAddress());
                emailMessage.setSubject(emailList.get(i).getEmailSubject());
                emailMessage.setContent(emailList.get(i).getEmailBody(), "text/html");

                transport.sendMessage(emailMessage, emailMessage.getAllRecipients());    
            }
            

            transport.close();
            System.out.println("Email sent successfully.");
            return "Email sent successfully.";
    }
 
}
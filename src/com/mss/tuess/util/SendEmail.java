/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class SendEmail {
    
    private Properties emailProperties;
    private Session mailSession;
    private MimeMessage emailMessage;

    public SendEmail(){
        setMailServerProperties();
    }

    public void sendMail(String[] toList, String emailSubject, String emailBody) throws MessagingException{

        createEmailMessage(toList,emailSubject,emailBody);
        sendEmail();
    }
 
    private void setMailServerProperties() {

          String emailPort = "587";//gmail's smtp port

          emailProperties = System.getProperties();
          emailProperties.put("mail.smtp.port", emailPort);
          emailProperties.put("mail.smtp.auth", "true");
          emailProperties.put("mail.smtp.starttls.enable", "true");
    }
 
    private void createEmailMessage(String[] toEmails, String emailSubject, String emailBody) 
            throws AddressException,MessagingException {

        InternetAddress[] mailToList = new javax.mail.internet.InternetAddress[toEmails.length];

        for (int i = 0; i < toEmails.length; i++){
                mailToList[i] = new javax.mail.internet.InternetAddress(toEmails[i]);
            }

        mailSession = Session.getDefaultInstance(emailProperties, null);
        emailMessage = new MimeMessage(mailSession);
        emailMessage.setRecipients(Message.RecipientType.TO, mailToList);
        emailMessage.setSubject(emailSubject);
        emailMessage.setContent(emailBody, "text/html");
    }
 
    private void sendEmail() throws AddressException, MessagingException {

          String emailHost = "smtp.gmail.com";
          String fromUser = "tuessteam@gmail.com";
          String fromUserEmailPassword = "soonandsofors";

          Transport transport = mailSession.getTransport("smtp");

          transport.connect(emailHost, fromUser, fromUserEmailPassword);
          transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
          transport.close();
          System.out.println("Email sent successfully.");
    }
 
}
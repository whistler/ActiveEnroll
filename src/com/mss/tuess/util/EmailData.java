/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.util;

/**
 *
 * @author Karthik Krish
 */
public class EmailData {
    
    private String toAddress;
    private String emailBody;
    private String emailSubject;
    
    /**
     *
     * @param toAddress
     * @param emailSubject
     * @param emailBody
     */
    public EmailData(String toAddress, String emailSubject, String emailBody){
        this.toAddress = toAddress;
        this.emailSubject = emailSubject;
        this.emailBody = emailBody;
    }
    
    /**
     * 
     * @return toAddress
     */
    public String getToAddress(){
        return this.toAddress;
    }
    
    /**
     * 
     * @return emailSubject
     */
    public String getEmailSubject(){
        return this.emailSubject;
    }
    
    /**
     * 
     * @return emailBody
     */
    public String getEmailBody(){
        return this.emailBody;
    }
   
}

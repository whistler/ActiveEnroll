/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ibrahim
 */
public abstract class User {
     /**
     * Returns Student if login and password match
     * 
     */
    
    public abstract void fetch(int id) throws SQLException;
    public abstract String getPassword();
    
    public static User login(int userID, String password, String userType) throws SQLException
    {
        User user;
        if(userType.equals("Student"))
        {
            user = new Student();
        } else if (userType.equals("Instructor"))
        {
            user = new Instructor();
        } else {
            user = new Administrator();
        }
                
        user.fetch(userID);
        if(user.getPassword().equals(password)) return user;
        else return null;
    }
}

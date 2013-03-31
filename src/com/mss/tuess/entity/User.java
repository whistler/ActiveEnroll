package com.mss.tuess.entity;

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

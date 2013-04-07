/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.RegisteredCourse;
import com.mss.tuess.entity.Term;
import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 *
 * @author chenliang
 */
public class RegisteredCoursesList {
    
    private static ArrayList<RegisteredCourse> registeredCoursesList = new ArrayList();
    private static int totalCredits=0;
    
    
    public static void fetch() throws SQLException {
        totalCredits=0;
        registeredCoursesList.clear();
        int currentID;
        Term currentTerm=State.getCurrentTerm();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        
        
        String sql = "es.courseDept,es.courseNum,c.courseName,es.sectionID,es.termID,c.credit " +
                     "from enrollSection es natural join course c " +
                     "where es.studentID="+currentID+ " and es.termID="+currentTerm.getTermID();
       

        
        rs = DatabaseConnector.returnQuery(sql);

        
        
        while (rs.next()) {
            RegisteredCourse registeredCourses = new RegisteredCourse();
            registeredCourses.setCourseDept(rs.getString("courseDept"));
            registeredCourses.setCourseNum(rs.getString("courseNum"));
            registeredCourses.setSectionID(rs.getString("sectionID"));
            registeredCourses.setCourseName(rs.getString("courseName"));
            registeredCourses.setTermID(rs.getString("termID"));
            registeredCourses.setCredit(rs.getInt("credit"));
           
            addTotalCredits(rs.getInt("credit"));
            

            registeredCoursesList.add(registeredCourses);
        }

    }
    
     /**
     * @param credit the addCredit to set
     */
    public static void addTotalCredits(int credits) 
    {
        totalCredits+=credits;
    }

     /**
     * @param index the index of registered course list
     * @return one registered course of index in the list
     */
    public static RegisteredCourse getRegisteredCourse(int index) 
    {
        return registeredCoursesList.get(index);
    }
    
    
     /**
     *
     * @return ArrayList<RegisteredCourses> Course ArrayList
     */
    public static ArrayList<RegisteredCourse> getAll() {
        //System.out.println(registeredCoursesList.get(1).getCourseName());
        return registeredCoursesList;
    }
    
     /**
     *
     * @return int the total credits registed current term
     */
    public static int getTotalCredits()
    {
        return totalCredits;
    }
    
    
}

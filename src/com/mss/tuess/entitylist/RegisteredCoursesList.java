/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.RegisteredCourses;
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
    
    private static ArrayList<RegisteredCourses> registeredCoursesList = new ArrayList();
    private static int totalCredits=0;
    
    
    public static void fetch() throws SQLException {
        int currentID;
        Term currentTerm=State.getCurrentTerm();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        String sql = "select e.courseDept,e.courseNum,e.sectionID,c.courseName,e.termID,s.day,e.type,c.credit " +
"from enrollSection as e natural join course as c natural join section as s " +
"where e.studentID="+currentID+ "and e.termID=20131";//+currentTerm.getTermID();
        rs = DatabaseConnector.returnQuery(sql);

        
        
        while (rs.next()) {
            RegisteredCourses registeredCourses = new RegisteredCourses();
            registeredCourses.setCourseDept(rs.getString("courseDept"));
            registeredCourses.setCourseNum(rs.getString("courseNum"));
            registeredCourses.setSectionID(rs.getString("sectionID"));
            registeredCourses.setCourseName(rs.getString("courseName"));
            registeredCourses.setTermID(rs.getString("termID"));
            registeredCourses.setDay(rs.getString("day"));
            registeredCourses.setType(rs.getString("type"));
           
            String lecture = "lecture";
            if(lecture.equals(rs.getString("type")))
            {
                registeredCourses.setCredit(0);
            }
            else
            {
                registeredCourses.setCredit(rs.getInt("credit"));
                addTotalCredits(rs.getInt("credit"));
            }
            

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
    public static RegisteredCourses getRegisteredCourse(int index) 
    {
        return registeredCoursesList.get(index);
    }
    
    
     /**
     *
     * @return ArrayList<RegisteredCourses> Course ArrayList
     */
    public static ArrayList<RegisteredCourses> getRegisteredCoursesList() {
        System.out.println(registeredCoursesList.get(1).getCourseName());
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

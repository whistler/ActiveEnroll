/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Term;
import com.mss.tuess.entity.WaivePrerequisite;
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
public class WaivePrerequisiteList {
       private static ArrayList<WaivePrerequisite> coursesToTeach= new ArrayList();
       
       /** 
        * fetch all data of courses one instructor to teach.
        * @throws SQLException 
        */
        public static void fetch() throws SQLException {
        coursesToTeach.clear();
        int instructorID;
        instructorID = CurrentUser.getUser().getID();
        Term currentTerm=State.getCurrentTerm();
        ResultSet rs;
        
        
        String sql = "select s.courseDept,s.courseNum,s.sectionID,c.courseName,s.termID,c.credit from section s "+
                "natural join course c where s.instructorID="+instructorID+ " and s.termID='"+currentTerm.getTermID()+"'";
       

        
        rs = DatabaseConnector.returnQuery(sql);

        
        while (rs.next()) {
            WaivePrerequisite waivePrerequisite = new WaivePrerequisite();
            waivePrerequisite.setCourseDept(rs.getString("courseDept"));
            waivePrerequisite.setCourseNum(rs.getString("courseNum"));
            waivePrerequisite.setSectionID(rs.getString("sectionID"));
            waivePrerequisite.setCourseName(rs.getString("courseName"));
            waivePrerequisite.setTermID(rs.getString("termID"));
            
            coursesToTeach.add(waivePrerequisite);
        }

    }
        
     /**
     * @param index the index of course to teach list
     * @return one course to waive prerequisite of index in the list
     */
    public static WaivePrerequisite getCoursesToTeach(int index) 
    {
        return coursesToTeach.get(index);
    }
    
    
     /**
     *
     * @return ArrayList<WaivePrerequisite> Course ArrayList
     */
    public static ArrayList<WaivePrerequisite> getAll() {
        //System.out.println(registeredCoursesList.get(1).getCourseName());
        return coursesToTeach;
    }
    
    
}

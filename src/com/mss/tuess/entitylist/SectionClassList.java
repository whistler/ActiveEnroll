/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.SectionClass;
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
public class SectionClassList {
    
        private static ArrayList<SectionClass> registeredSectionClassList = new ArrayList();

     public static void fetch() throws SQLException {

         
        registeredSectionClassList.clear();
        int currentID;
        Term currentTerm=State.getCurrentTerm();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        
        
        String sql="select sc.sectionID,sc.courseDept,sc.courseNum,sc.termID,sc.type,sc.classID,sc.day,sc.startTime,"+
                    "sc.endTime, sc.location from sectionClass sc natural join enrollSection es"+
                    "where es.studentID="+currentID+ "and es.termID="+currentTerm.getTermID();
        
        rs = DatabaseConnector.returnQuery(sql);

        
        
        while (rs.next()) {
            SectionClass registeredSectionClass = new SectionClass();
            
            registeredSectionClass.setSectionID(rs.getString("sectionID"));
            registeredSectionClass.setCourseDept(rs.getString("courseDept"));
            registeredSectionClass.setCourseNum(rs.getString("courseNum"));
            registeredSectionClass.setTermID(rs.getString("termID"));
            registeredSectionClass.setType(rs.getString("type"));
            registeredSectionClass.setClassID(rs.getString("classID"));
            
            registeredSectionClass.setDay(rs.getString("day"));
            
            registeredSectionClass.setStartTime(rs.getTimestamp("startTime"));
            registeredSectionClass.setEndTime(rs.getTimestamp("endTime"));
           
            registeredSectionClass.setLocation(rs.getString("location"));

        

            registeredSectionClassList.add(registeredSectionClass);
        }

    }
    
    
}

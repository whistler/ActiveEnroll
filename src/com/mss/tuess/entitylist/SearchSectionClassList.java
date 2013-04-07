/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.SectionClass;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Karthik
 */
public class SearchSectionClassList {
    
    private static ArrayList<SectionClass> sectionClassList = new ArrayList();

    /**
     * Loads all Section records from the database in to a list of Section
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
 
        String selectAll = "SELECT * FROM sectionClass";
        executeFetch(selectAll);
    }
    
    public static void fetch(String courseDept, String courseNum, String currentTerm) throws SQLException {
 
        
        String selectSectionClassListByCourse = "SELECT * FROM sectionClass"+
                                                    " where"+ 
                                                        " courseDept = '"+courseDept+
                                                        "' and courseNum = '"+courseNum+
                                                        "' and termID = '"+currentTerm+
                                                        "'";
        
        executeFetch(selectSectionClassListByCourse);
    }
        
    private static void executeFetch(String sql) throws SQLException {
            
            sectionClassList.clear();
            ResultSet rs = DatabaseConnector.returnQuery(sql);
            
            while (rs.next()) {
                
                SectionClass sectionClass = new SectionClass();
                sectionClass.setSectionID(rs.getString("sectionID"));
                sectionClass.setCourseDept(rs.getString("courseDept"));
                sectionClass.setCourseNum(rs.getString("courseNum"));
                sectionClass.setTermID(rs.getString("termID"));
                sectionClass.setType(rs.getString("type"));
                sectionClass.setClassID(rs.getString("classID"));
                sectionClass.setDay(rs.getString("day"));
                sectionClass.setStartTime(rs.getTimestamp("startTime"));
                sectionClass.setEndTime(rs.getTimestamp("endTime"));
                sectionClass.setLocation(rs.getString("location"));
                    
                sectionClassList.add(sectionClass);
            }
    }
    /**
     * Returns the section stored at the given index
     *
     * @param index index of the section to return
     * @return Section object at position index
     */
    public static SectionClass get(int index) {
        return sectionClassList.get(index);
    }
    
    /**
     * Returns the sections List
     *
     * @return ArrayList<Section> sections ArrayList
     */
    public static ArrayList<SectionClass> getAll() {
        return sectionClassList;
    }
}

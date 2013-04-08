/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Course;
import com.mss.tuess.entity.Section;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Prabal Sharma
 */
public class SectionList {

    private static ArrayList<Section> sections = new ArrayList();

    /**
     * Loads all Section records from the database in to a list of Section
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
 
        String selectAll = "SELECT * FROM section";
        executeFetch(selectAll);
    }
    
    public static void fetch(String courseNum) throws SQLException {
 
        String selectSectionsByCourse = "SELECT * FROM section where courseNum = "+courseNum;
        executeFetch(selectSectionsByCourse);
    }
        
    private static void executeFetch(String sql) throws SQLException {
            
            sections.clear();
            ResultSet rs = DatabaseConnector.returnQuery(sql);
            
            while (rs.next()) {
                Section section = new Section();
                section.setSectionID(rs.getString("sectionID"));
                section.setCourseDept(rs.getString("courseDept"));
                section.setCourseNum(rs.getString("courseNum"));
                section.setInstructorID(rs.getInt("instructorID"));
                section.setTermID(rs.getString("termID"));
                section.setCapacity(rs.getInt("capacity"));
                section.setRegistered(rs.getInt("registered"));
                section.setStatus(rs.getString("status"));

                sections.add(section);
            }
    }
    /**
     * Returns the section stored at the given index
     *
     * @param index index of the section to return
     * @return Section object at position index
     */
    public static Section get(int index) {
        return sections.get(index);
    }
    
        /**
     * Returns the sections List
     *
     * @return ArrayList<Section> sections ArrayList
     */
    public static ArrayList<Section> getAll() {
        return sections;
    }
}

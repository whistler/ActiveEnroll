/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entitylist;

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
        ResultSet rs;
        String sql = "SELECT * FROM section";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Section section = new Section();
            section.setSectionID(rs.getString("sectionID"));
            section.setCourseDept(rs.getString("courseDept"));
            section.setInstructorID(rs.getInt("instructorID"));
            section.setType(rs.getString("type"));
            section.setTextbook(rs.getString("textbook"));
            section.setTerm(rs.getString("term"));
            section.setTime(rs.getString("time"));
            section.setDay(rs.getString("day"));
            section.setCapacity(rs.getInt("capacity"));
            section.setRegistered(rs.getInt("registered"));
            section.setLocation(rs.getString("location"));
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
}

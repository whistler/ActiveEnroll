package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Prerequisite class. To keep constraint information about courses that should be taken before current term.
 * prereqGroup is the flag that needs to be computed to get the "AND" or "OR" relationship of each prerequsitie course pair.
 */
public class Prerequisite {

    private String courseDept;
    private String courseNum;
    private String prereqDept;
    private String prereqNum;
    private int prereqGroup;

    /**
     * @return prereqGroup
     */
    public int getPrereqGroup() {
        return prereqGroup;
    }

    /**
     * @param prereqGroup to set
     */
    public void setPrereqGroup(int prereqGroup) {
        this.prereqGroup = prereqGroup;
    }

    /**
     * @return the courseDept
     */
    public String getCourseDept() {
        return courseDept;
    }

    /**
     * @param courseDept the courseDept to set
     */
    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    /**
     * @return the courseNum
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * @param courseNum the courseNum to set
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * @return the prereqDept
     */
    public String getPrereqDept() {
        return prereqDept;
    }

    /**
     * @param prereqDept the prereqDept to set
     */
    public void setPrereqDept(String prereqDept) {
        this.prereqDept = prereqDept;
    }

    /**
     * @return the prereqNum
     */
    public String getPrereqNum() {
        return prereqNum;
    }

    /**
     * @param prereqNum the prereqNum to set
     */
    public void setPrereqNum(String prereqNum) {
        this.prereqNum = prereqNum;
    }

    /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     * @param courseDept the department of the course
     * @param courseNum the number of the course
     * @param prereqNum the number of the prerequisite course
     * @param prereqDept the department of the prerequisite course
     * @throws SQLException
     */
    public void fetch(String courseDept, String courseNum, String prereqNum, String prereqDept) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM prerequisite "
                + "WHERE  courseDept = " + courseDept + "AND courseNum = " + courseNum + "AND prereqNum = " + prereqNum + "AND prereqDept = " + prereqDept;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setPrereqNum(rs.getString("prereqNum"));
            this.setPrereqDept(rs.getString("prereqDept"));
            this.setPrereqGroup(rs.getInt("prereqGroup"));
        }

    }

    /**
     * Delete this Prerequisite record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM prerequisite WHERE courseDept=" + this.getCourseDept() + ", courseNum=" + this.getCourseNum() + ", prereqDept=" + this.getPrereqDept() + ", prereqNum=" + this.getPrereqNum();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Prerequisite into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO prereqisite  (courseDept, courseNum, prereqDept,"
                + " prereqNum, prereqGroup) values ("
                + this.getCourseDept() + ", '"
                + this.getCourseNum() + "', '"
                + this.getPrereqDept() + "', '"
                + this.getPrereqNum() + "', "
                + this.getPrereqGroup() + ")";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Fetches the prerequisites course from the database and stors in local arrayLit
     * @param course to find prerequisites for
     * @return ArrayList of prerequisites for the course ordered by group
     */
    public static ArrayList<Prerequisite> prerequisitesForCourse(Course course) {
        ArrayList<Prerequisite> prerequisites = new ArrayList();
        ResultSet rs;
        String sql = "SELECT * FROM prerequisite "
                + "WHERE  courseDept = '" + course.getCourseDept() + "' "
                + "AND courseNum = '" + course.getCourseNum() + "' "
                + "ORDER BY prereqGroup";
        try {
            rs = DatabaseConnector.returnQuery(sql);
            while (rs.next()) {
                Prerequisite prereq = new Prerequisite();
                prereq.setCourseDept(rs.getString("courseDept"));
                prereq.setCourseNum(rs.getString("courseNum"));
                prereq.setPrereqNum(rs.getString("prereqNum"));
                prereq.setPrereqDept(rs.getString("prereqDept"));
                prereq.setPrereqGroup(rs.getInt("prereqGroup"));
                prerequisites.add(prereq);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Prerequisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prerequisites;
    }
    
    /**
     * Parses the prerequisite courses info and displays in the panel.
     * @param prereqs the arrayList that stores the prerequisite info
     * @return str to be parsed from the arrayList and to be displayed
     */
    public static String getPrerequisitesString(ArrayList<Prerequisite> prereqs)
    {
        if (prereqs.size()==0) return "none";
        String str = "(";
        int size = prereqs.size();
        for(int i=0;i<size;i++)
        {
            String concat;
            if (i==size-1) concat = ")";
            else {
                if(prereqs.get(i+1).getPrereqGroup() == prereqs.get(i).getPrereqGroup()) concat = " OR ";
                else concat = ") AND (";
            }
            str = str + prereqs.get(i).getCourseDept() + " " + prereqs.get(i).getCourseNum() + concat;
        }
        return str;
    }
}

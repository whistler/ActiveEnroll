package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Corequisite class. To keep constraint information about courses that should be taken at the same term.
 * corerGroup is the flag that needs to be computed to get the "AND" or "OR" relationship of each corequsitie course pair.
 */
public class Corequisite {

    private String courseDept;
    private String courseNum;
    private String coreqDept;
    private String coreqNum;
    private int coreqGroup;

    /** 
     * Returns the group of corequisite.
     * @return coreqGroup the group of the corequisite
     */
    public int getCoreqGroup() {
        return coreqGroup;
    }

    /**
     * Sets the group of corequisite.
     * @param coreqGroup the corequisite group to set
     */
    public void setCoreqGroup(int coreqGroup) {
        this.coreqGroup = coreqGroup;
    }

    /**
     * Reutns the department that offers the course.
     * @return the courseDept
     */
    public String getCourseDept() {
        return courseDept;
    }

    /**
     * Sets the department that offers the course.
     * @param courseDept the courseDept to set
     */
    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    /**
     * Returns the number of the course.
     * @return the courseNum of the course
     */
    public String getCourseNum() {
        return courseNum;
    }

    /**
     * Sets the number of the course.
     * @param courseNum the courseNum to set
     */
    public void setCourseNum(String courseNum) {
        this.courseNum = courseNum;
    }

    /**
     * Returns the department that offers the corequisite course.
     * @return the coreqDept the department that offers the corequisite course
     */
    public String getCoreqDept() {
        return coreqDept;
    }

    /**
     * Sets the department that offers the corequisite course.
     * @param coreqDept the coreqDept to set
     */
    public void setCoreqDept(String coreqDept) {
        this.coreqDept = coreqDept;
    }

    /**
     * Returns the number of the corequisite course.
     * @return the coreqNum the course number of the corequisite course
     */
    public String getCoreqNum() {
        return coreqNum;
    }

    /**
     * Sets the number of the crequisite course.
     * @param coreqNum the course number to set
     */
    public void setCoreqNum(String coreqNum) {
        this.coreqNum = coreqNum;
    }

    /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     * @param courseDept the deparment that offers the course
     * @param courseNum the number of the course
     * @param coreqDept the department that offers the corequisite course
     * @param coreqNum  the number of the corequisite course
     * @throws SQLException
     */
    public void fetch(String courseDept, String courseNum, String coreqNum, String coreqDept) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM corequisite "
                + "WHERE  courseDept = " + courseDept + "AND courseNum = " + courseNum + "AND coreqNum = " + coreqNum + "AND coreqDept = " + coreqDept;
        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setCoreqNum(rs.getString("coreqNum"));
            this.setCoreqDept(rs.getString("coreqDept"));
            this.setCoreqGroup(rs.getInt("coreqGroup"));
        }

    }

    /**
     * Deletes this Corequisite record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM corequisite WHERE courseDept=" + this.getCourseDept() + ", courseNum=" + this.getCourseNum() + ", coreqDept=" + this.getCoreqDept() + ", coreqNum=" + this.getCoreqNum();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Inserts this Corequisite into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO coreqisite  (courseDept, courseNum, coreqDept,"
                + " coreqNum, coreqGroup) values ("
                + this.getCourseDept() + ", '"
                + this.getCourseNum() + "', '"
                + this.getCoreqDept() + "', '"
                + this.getCoreqNum() + "', "
                + this.getCoreqGroup() + ")";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Fetches the corequisite records in the database and store in the array list.
     * @param course to find corequisites for
     * @return ArrayList of corequisites for the course ordered by group
     */
    public static ArrayList<Corequisite> corequisitesForCourse(Course course) {
        ArrayList<Corequisite> corequisites = new ArrayList();
        ResultSet rs;
        String sql = "SELECT * FROM corequisite "
                + "WHERE  courseDept = '" + course.getCourseDept() + "' "
                + "AND courseNum = '" + course.getCourseNum() + "' "
                + "ORDER BY coreqGroup";
        try {
            rs = DatabaseConnector.returnQuery(sql);
            while (rs.next()) {
                Corequisite coreq = new Corequisite();
                coreq.setCourseDept(rs.getString("courseDept"));
                coreq.setCourseNum(rs.getString("courseNum"));
                coreq.setCoreqNum(rs.getString("coreqNum"));
                coreq.setCoreqDept(rs.getString("coreqDept"));
                coreq.setCoreqGroup(rs.getInt("coreqGroup"));
                corequisites.add(coreq);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Corequisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return corequisites;
    }
    
    /**
     * Get the corequisite courses information.
     * @param coreqs the array list that stores the all corequisite coures
     * @return str the string of course information of the corequisite to display in the panel.
     */
    public static String getCorequisitesString(ArrayList<Corequisite> coreqs)
    {
        if (coreqs.size()==0) return "none";
        String str = "(";
        int size = coreqs.size();
        for(int i=0;i<size;i++)
        {
            String concat;
            if (i==size-1) concat = ")";
            else {
                if(coreqs.get(i+1).getCoreqGroup() == coreqs.get(i).getCoreqGroup()) concat = " OR ";
                else concat = ") AND (";
            }
            str = str + coreqs.get(i).getCoreqDept() + " " + coreqs.get(i).getCoreqNum() + concat;
        }
        return str;
    }
}

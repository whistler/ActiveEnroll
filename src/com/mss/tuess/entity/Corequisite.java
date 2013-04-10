package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Corequisite {

    private String courseDept;
    private String courseNum;
    private String coreqDept;
    private String coreqNum;
    private int coreqGroup;

    /**
     * @return coreqGroup
     */
    public int getPrereqGroup() {
        return coreqGroup;
    }

    /**
     * @param coreqGroup to set
     */
    public void setPrereqGroup(int coreqGroup) {
        this.coreqGroup = coreqGroup;
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
     * @return the coreqDept
     */
    public String getPrereqDept() {
        return coreqDept;
    }

    /**
     * @param coreqDept the coreqDept to set
     */
    public void setPrereqDept(String coreqDept) {
        this.coreqDept = coreqDept;
    }

    /**
     * @return the coreqNum
     */
    public String getPrereqNum() {
        return coreqNum;
    }

    /**
     * @param coreqNum the coreqNum to set
     */
    public void setPrereqNum(String coreqNum) {
        this.coreqNum = coreqNum;
    }

    /**
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     *
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
            this.setPrereqNum(rs.getString("coreqNum"));
            this.setPrereqDept(rs.getString("coreqDept"));
            this.setPrereqGroup(rs.getInt("coreqGroup"));
        }

    }

    /**
     * Delete this Corequisite record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM corequisite WHERE courseDept=" + this.getCourseDept() + ", courseNum=" + this.getCourseNum() + ", coreqDept=" + this.getPrereqDept() + ", coreqNum=" + this.getPrereqNum();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Corequisite into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO coreqisite  (courseDept, courseNum, coreqDept,"
                + " coreqNum, coreqGroup) values ("
                + this.getCourseDept() + ", '"
                + this.getCourseNum() + "', '"
                + this.getPrereqDept() + "', '"
                + this.getPrereqNum() + "', "
                + this.getPrereqGroup() + ")";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

    /**
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
                coreq.setPrereqNum(rs.getString("coreqNum"));
                coreq.setPrereqDept(rs.getString("coreqDept"));
                coreq.setPrereqGroup(rs.getInt("coreqGroup"));
                corequisites.add(coreq);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Corequisite.class.getName()).log(Level.SEVERE, null, ex);
        }
        return corequisites;
    }
    
    /**
     * 
     * @param coreqs
     * @return 
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
                if(coreqs.get(i+1).getPrereqGroup() == coreqs.get(i).getPrereqGroup()) concat = " OR ";
                else concat = ") AND (";
            }
            str = str + coreqs.get(i).getCourseDept() + " " + coreqs.get(i).getCourseNum() + concat;
        }
        return str;
    }
}

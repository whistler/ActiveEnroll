package com.mss.tuess.entity;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *TaughtCourse class. Object used to store course taught by a instructor.
 */
public class TaughtCourse {

    private static ArrayList<TaughtCourse> taughtCoursesList = new ArrayList();
    private static int totalCredits = 0;
/**
 * Fetches TaughtCourse from database into arraylist
 * @throws SQLException 
 */
    public static void fetch() throws SQLException {
        totalCredits = 0;
        taughtCoursesList.clear();
        int currentID;
        Term currentTerm = State.getCurrentTerm();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        String sql = "select es.courseDept,es.courseNum,c.courseName,es.sectionID,es.termID,c.credit " + "from section es natural join course c " + "where es.instructorID=" + currentID + " and es.termID='" + currentTerm.getTermID() + "'";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            TaughtCourse taughtCourse = new TaughtCourse();
            taughtCourse.setCourseDept(rs.getString("courseDept"));
            taughtCourse.setCourseNum(rs.getString("courseNum"));
            taughtCourse.setSectionID(rs.getString("sectionID"));
            taughtCourse.setCourseName(rs.getString("courseName"));
            taughtCourse.setTermID(rs.getString("termID"));
            taughtCourse.setCredit(rs.getInt("credit"));
            addTotalCredits(rs.getInt("credit"));
            taughtCoursesList.add(taughtCourse);
        }
    }

    /**
     * @param index the index of registered course list
     * @return one registered course of index in the list
     */
    public static TaughtCourse getTaughtCourse(int index) {
        return taughtCoursesList.get(index);
    }

    /**
     * @param credits the addCredit to set
     */
    public static void addTotalCredits(int credits) {
        totalCredits += credits;
    }

    /**
     *
     * @return int the total credits registed current term
     */
    public static int getTotalCredits() {
        return totalCredits;
    }

    /**
     *
     * @return ArrayList<TaughtCourse> Course ArrayList
     */
    public static ArrayList<TaughtCourse> getAll() {
        return taughtCoursesList;
    }
    private String sectionID;
    private String courseDept;
    private String courseNum;
    private String courseName;
    private String termID;
    private int credit;

    /**
     * @return the sectionID
     */
    public String getSectionID() {
        return sectionID;
    }

    /**
     * @param sectionID the sectionID to set
     */
    public void setSectionID(String sectionID) {
        this.sectionID = sectionID;
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
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the termID
     */
    public String getTermID() {
        return termID;
    }

    /**
     * @param termID the termID to set
     */
    public void setTermID(String termID) {
        this.termID = termID;
    }

    /**
     * @return the credit
     */
    public int getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(int credit) {
        this.credit = credit;
    }
}

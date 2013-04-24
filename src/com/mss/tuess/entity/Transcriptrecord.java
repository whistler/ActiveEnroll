/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.tuess.entity;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Transcriptrecord class. Used for displaying transcript of a student.
 */
public class Transcriptrecord {
    private static ArrayList<Transcriptrecord> transcriptrecords = new ArrayList();
    private static int addCreditMultipleGrade;
    private static int addCredit;

    /**
     * @return the addCreditMultipleGrade
     */
    public static int getAddCreditMultipleGrade() {
        return addCreditMultipleGrade;
    }

    /**
     * Fetches Transcriptrecord info from the database into local array list.
     * @throws SQLException 
     */
    public static void fetch() throws SQLException {
        int currentID;
        int gradeNum = 0;
        setAddCredit(0);
        setAddCreditMultipleGrade(0);
        transcriptrecords.clear();
        currentID = CurrentUser.getUser().getID();
        ResultSet rs;
        Term ct = State.getCurrentTerm();
        String sql = "select es.courseDept,es.courseNum,c.courseName,es.termID,c.credit,es.grade from enrollSection es natural join course c where es.studentID=" + currentID + " and es.termID<>'" + ct.getTermID() + "'";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Transcriptrecord Transcriptrecord = new Transcriptrecord();
            Transcriptrecord.setCourseDept(rs.getString("courseDept"));
            Transcriptrecord.setCourseNum(rs.getString("courseNum"));
            Transcriptrecord.setCourseName(rs.getString("courseName"));
            Transcriptrecord.setTermID(rs.getString("termID"));
            Transcriptrecord.setCredit(rs.getInt("credit"));
            Transcriptrecord.setGrade(rs.getString("grade"));
            
            if (rs.getString("grade").equalsIgnoreCase("A")) {
                gradeNum = 4;
            } else if (rs.getString("grade").equalsIgnoreCase("B")) {
                gradeNum = 3;
            } else if (rs.getString("grade").equalsIgnoreCase("C")) {
                gradeNum = 2;
            } else if (rs.getString("grade").equalsIgnoreCase("D")) {
                gradeNum = 1;
            } else if (rs.getString("grade").equalsIgnoreCase("F")) {
                gradeNum = 0;
            } else if (rs.getString("grade").equalsIgnoreCase("W")) {
                gradeNum = 0;
            }
            if (rs.getString("grade").equalsIgnoreCase("W")) {
                //withdrawn course not counted into credit
            } else {
                setAddCredit(getAddCredit() + rs.getInt("credit"));
            }
            Transcriptrecord.addCreditMultipleGrade += (rs.getInt("credit") * gradeNum);
            transcriptrecords.add(Transcriptrecord);
        }
    }

    /**
     * @param aAddCredit the addCredit to set
     */
    public static void setAddCredit(int aAddCredit) {
        addCredit = aAddCredit;
    }

    /**
     * @return the addCredit
     */
    public static int getAddCredit() {
        return addCredit;
    }

    /**
     * @param aAddCreditMultipleGrade the addCreditMultipleGrade to set
     */
    public static void setAddCreditMultipleGrade(int aAddCreditMultipleGrade) {
        addCreditMultipleGrade = aAddCreditMultipleGrade;
    }

    /**
     * Returns one transcript record from the arraylist with given index
     * @param index the index to output
     * @return the transcript record of that index in arraylist
     */
    public static Transcriptrecord get(int index) {
        return transcriptrecords.get(index);
    }

    /**
     * Returns the course List
     *
     * @return ArrayList<Course> Course ArrayList
     */
    public static ArrayList<Transcriptrecord> getAll() {
        return transcriptrecords;
    }
    
    private String courseDept;
    private String courseNum;
    private String courseName;
    private String termID;
    private int credit;
    private String grade;

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

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    
}

package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollSection {

    private int studentID;
    private String sectionID;
    private String grade;
    private String type;
    private String courseDept;
    private String courseNum;
    private String term;

    /**
     * @return the studentID
     */
    public int getID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setID(int studentID) {
        this.studentID = studentID;
    }

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

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the term
     */
    public String getTerm() {
        return term;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * Loads the EnrollSection by the studentID from the database and encapsulates
     * into this EnrollSection objects
     *
     * @throws SQLException
     */
    public void fetch(int studentId, String sectionID, String courseDept, String courseNum, String term, String type ) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM enrollSection "
                + "WHERE studentID = " + studentId
                + "AND sectionID = " + sectionID + "AND courseDept = " + courseDept + "AND courseNum = " + courseNum + "AND term = " + term+ "AND type = " + type;
        rs = DatabaseConnector.returnQuery(sql);
        
        if (rs.next()) {
            this.setID(rs.getInt("studentID"));
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setType(rs.getString("type"));
            this.setTerm(rs.getString("term"));
            this.setGrade(rs.getString("grade"));
        }

    }

    /**
     * Uses the information of this EnrollSection to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE enrollSection SET "
                + " grade= ' " + this.getGrade()+"'"
                + "WHERE studentID=" + this.getID()+ ", sectionID=" + this.getSectionID()+ ", courseDept=" + this.getCourseDept()+ ", courseNum=" + this.getCourseNum()
                + ", type=" + this.getType()+ ", term=" + this.getTerm();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this EnrollSection record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE studentID=" + this.getID()+ ", sectionID=" + this.getSectionID()+ ", courseDept=" + this.getCourseDept()+ ", courseNum=" + this.getCourseNum()
                + ", type=" + this.getType()+ ", term=" + this.getTerm();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this EnrollSection into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO student  (studentID, sectionID, courseDept, courseNum, type, term, "
                + "grade) values "
                + "(" + this.getID() + ", '" + this.getSectionID()+ "', '" + this.getCourseDept() + "', '" + this.getCourseNum() + "', '" + this.getType() + "', '" + this.getTerm()
                + "', '" + this.getGrade()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }



 
}

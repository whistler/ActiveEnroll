package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Section class. Weak entity of a course of a certain term. 
 * Stores constraints like registered student number, capacity and status of a section.
 */
public class Section {

    private static ArrayList<Section> sections = new ArrayList();
    private String sectionID;
    private String courseDept;
    private String courseNum;
    private int instructorID;
    private String termID;
    private int capacity;
    private int registered;
    private String status;
    private Course course = new Course();
    private Term term = new Term();
    private Instructor instructor = new Instructor();

    /**
     * Fetches with course number.
     * @param courseNum the number of a course to fetch.
     * @throws SQLException 
     */
    public static void fetch(String courseNum) throws SQLException {
        String selectSectionsByCourse = "SELECT * FROM section where courseNum = " + courseNum;
        executeFetch(selectSectionsByCourse);
    }

    /**
     * Fetches with sectionID, courseDept, courseNum and currentTerm
     * @param sectionID "1"
     * @param courseDept "CICS"
     * @param courseNum "505"
     * @param currentTerm "20131"
     * @throws SQLException 
     */
    public static void fetch1(String sectionID, String courseDept, String courseNum, String currentTerm) throws SQLException {


        String selectSectionClassListByCourse = "SELECT * FROM sectionClass"
                + " where"
                + " sectionID = '" + sectionID
                + " courseDept = '" + courseDept
                + "' and courseNum = '" + courseNum
                + "' and termID = '" + currentTerm
                + "'";

        executeFetch(selectSectionClassListByCourse);
    }
    
    /**
     * Fetches with courseDept, courseNumber and currentTerm.
     * @param courseDept "CICS"
     * @param courseNum "505"
     * @param currentTerm "20131"
     * @throws SQLException 
     */
    public static void fetch(String courseDept, String courseNum, String currentTerm) throws SQLException {


        String selectSectionClassListByCourse = "SELECT * FROM sectionClass"
                + " where"
                + " courseDept = '" + courseDept
                + "' and courseNum = '" + courseNum
                + "' and termID = '" + currentTerm
                + "'";

        executeFetch(selectSectionClassListByCourse);
    }

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

    /**
     * Fetch section info from the database of one instructor's section
     *
     * @param instructorID the ID of instructor
     * @param termID the specific term to query
     * @throws SQLException
     */
    public static void fetchByInstructor(int instructorID, String termID) throws SQLException {
        String selectSectionByInstructor = "SELECT * FROM section natural join course where instructorID= " + instructorID + " and termID='" + termID + "'";
        executeFetch(selectSectionByInstructor);
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
     * Returns the sections List
     *
     * @return ArrayList<Section> sections ArrayList
     */
    public static ArrayList<Section> getAll() {
        return sections;
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
     * @return the instructorID
     */
    public int getInstructorID() {
        return instructorID;
    }

    /**
     * @param instructorID the instructorID to set
     */
    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    /**
     * @return the termID
     */
    public String getTermID() {
        return termID;
    }

    /**
     * @param term the termID to set
     */
    public void setTermID(String term) {
        this.termID = term;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * @return the registered
     */
    public int getRegistered() {
        return registered;
    }

    /**
     * @param registered the registered to set
     */
    public void setRegistered(int registered) {
        this.registered = registered;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Loads the Section by the sectionID from the database and encapsulates
     * into this Section objects
     *
     * @param sectionID "1"
     * @param courseDept "CICS"
     * @param courseNum "505"
     * @param termID "20131"
     * @throws SQLException
     */
    public void fetch(String sectionID, String courseDept, String courseNum, String termID) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM section  WHERE "
                + "sectionID = '" + sectionID + "' AND "
                + "courseDept = '" + courseDept + "' AND "
                + "courseNum = '" + courseNum + "' AND "
                + "termID = '" + termID + "'";

        rs = DatabaseConnector.returnQuery(sql);
        if (rs.next()) {
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setInstructorID(rs.getInt("instructorID"));
            this.setTermID(rs.getString("termID"));
            this.setCapacity(rs.getInt("capacity"));
            this.setRegistered(rs.getInt("registered"));
            this.setStatus(rs.getString("status"));
        }

    }

    /**
     * Uses the information of this Section to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE section SET "
                + "courseDept='" + this.getCourseDept() + "' AND "
                + "courseNum='" + this.getCourseNum() + "' AND "
                + "instructorID=" + this.getInstructorID() + " AND "
                + "termID='" + this.getTermID() + "' AND "
                + "capacity='" + this.getCapacity() + "' AND "
                + "registered='" + this.getRegistered() + "' AND "
                + "status='" + this.getStatus()
                + "' WHERE sectionID='" + this.getSectionID()+"'";
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Section record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM section WHERE sectionID=" + this.getSectionID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Section into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO section  (sectionID, courseDept, courseNum, instructorID, "
                + "termID, capacity, registered, status) values "
                + "(" + this.getSectionID() + ", '"
                + this.getCourseDept() + "', '"
                + this.getCourseNum() + "', '"
                + this.getInstructorID() + "', '"
                + this.getTermID() + "', '"
                + this.getCapacity() + "', '"
                + this.getRegistered() + "', '"
                + this.getStatus()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Fetches related entities ie. course, instructor and term from the
     * database into the object
     *
     * @throws SQLException
     */
    public void fetchAssociations() throws SQLException {
        course.fetch(courseDept, courseNum);
        instructor.fetch(instructorID);
        term.fetch(termID);
    }

    /**
     * Returns instructor precondition: fetchAssociations() should be called
     * before this method can be used
     *
     * @return instructor for this section
     */
    public Instructor getInstructor() {
        return instructor;
    }

    /**
     * Returns course precondition: fetchAssociations() should be called before
     * this method can be used
     *
     * @return course for this section
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Returns course precondition: fetchAssociations() should be called before
     * this method can be used
     *
     * @return course for this section
     */
    public Term getTerm() {
        return term;
    }
}

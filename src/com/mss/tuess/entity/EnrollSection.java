package com.mss.tuess.entity;

import com.mss.tuess.util.CurrentUser;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EnrollSection {

    private int studentID;
    private String sectionID;
    private String grade;
    private String courseDept;
    private String courseNum;
    private String termID;

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
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
     * Loads the EnrollSection by the studentID from the database and
     * encapsulates into this EnrollSection objects
     *
     * @throws SQLException
     */
    public void fetch(int studentID, String sectionID, String courseDept, String courseNum, String termID) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM enrollSection "
                + "WHERE studentID = " + studentID
                + "AND sectionID = " + sectionID + "AND courseDept = " + courseDept + "AND courseNum = " + courseNum + "AND termID = " + termID;
        rs = DatabaseConnector.returnQuery(sql);

        if (rs.next()) {
            this.setStudentID(rs.getInt("studentID"));
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setTermID(rs.getString("termID"));
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
                + " grade= ' " + this.getGrade() + "'"
                + "WHERE studentID=" + this.getStudentID() + ", sectionID=" + this.getSectionID() + 
                ", courseDept=" + this.getCourseDept() + ", courseNum=" + this.getCourseNum()
                + ", termID=" + this.getTermID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this EnrollSection record in the database.
     *
     * @throws SQLException
     */
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE studentID=" + this.getStudentID() + ", sectionID=" + this.getSectionID() + ", courseDept=" + this.getCourseDept() + ", courseNum=" + this.getCourseNum()
                 + ", term=" + this.getTermID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this EnrollSection into the database.
     *
     * @throws SQLException
     */
    public void insert() throws SQLException {
        String sql = "INSERT INTO student  (studentID, sectionID, courseDept, courseNum, termID, "
                + "grade) values "
                + "(" + this.getStudentID() + ", '" + this.getSectionID() + "', '" + this.getCourseDept() + "', '" + this.getCourseNum() + "', '"  + this.getTermID()
                + "', '" + this.getGrade()
                + "')";
        System.out.println(sql);
        DatabaseConnector.updateQuery(sql);
    }
 /**
     * Insert this EnrollSection into the database.
     *
     * @throws SQLException
     */
    public void insertBySecStu(Section section, int studentID) throws SQLException {
        String sql = "INSERT INTO enrollSection VALUES ("
                    + studentID + ", '"
                    + section.getSectionID() + "', '"
                    + section.getCourseDept() + "', '"
                    + section.getCourseNum() + "', '"
                    + section.getTermID()+"', ''"
                    + ")";
            DatabaseConnector.updateQuery(sql);
            System.out.println("\nCan!!!!!!!!   " + sql);
    }
    /**
     * Checks whether the student is enrolled in the section or not
     *
     * @param student student to check
     * @param section section to check
     * @return whether the student is enrolled in the section or not
     */
    public static boolean isEnrolled(Student student, Section section) {
        String sql = "SELECT * FROM enrollSection WHERE "
                + "studentID=" + student.getID() + " AND "
                + "sectionID='" + section.getSectionID() + "' AND "
                + "courseDept='" + section.getCourseDept() + "' AND "
                + "courseNum='" + section.getCourseNum() + "' AND "
                + "termID='" + section.getTermID() + "' ";

        ResultSet rs;
        try {
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EnrollSection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static ResultSet fetchEnrolledCourses(int studentID) throws SQLException {
        ResultSet rs;
        String sql = "select * from enrollSection where studentID=" + studentID;
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }

    public static ResultSet fetchPrereqCourses(Section section) throws SQLException {
        //CurrentUser.getUser().getID();
        ResultSet rs;
        String sql = "select * from prerequisite where courseNum='"
                + section.getCourseNum() + "' and courseDept='" + section.getCourseDept() + "'"
                + "ORDER by prereqGroup";
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }

    public static boolean isInSet(ResultSet rs_enrolled, String courseDept, String courseNum) throws SQLException {
        rs_enrolled.beforeFirst();
        while (rs_enrolled.next()) {
            if (courseDept.compareTo(rs_enrolled.getString("courseDept")) == 0
                    && courseNum.compareTo(rs_enrolled.getString("courseNum")) == 0
                    && (rs_enrolled.getString("grade").compareTo("A")==0
                    || rs_enrolled.getString("grade").compareTo("B")==0
                    || rs_enrolled.getString("grade").compareTo("C")==0
                    || rs_enrolled.getString("grade").compareTo("D")==0)) {
                System.out.println(courseDept + courseNum + "___is IN");
                return true;
            }
        }
        System.out.println(courseDept + courseNum + "___is NOT IN");
        return false;
    }

    public static boolean checkPrerequisite(Section section, int studentID) throws SQLException {
//        ResultSet rs;
//        String sql = " SELECT prereqDept, prereqNum FROM prerequisite pr"
//                + "	WHERE courseDept=" + section.getCourseDept()
//                + "		AND courseNum=" + section.getCourseNum()
//                + "		AND NOT EXISTS"
//                + "			( SELECT * FROM enrollSection e"
//                + "			WHERE  e.studentID=" + studentID
//                + "				AND e.courseDept=pr.prereqDept"
//                + "				AND e.courseNum=pr.prereqNum"
//                + "				AND (e.grade='A' OR e.grade='B' OR e.grade='C' OR e.grade='D')"
//                + "                )";
//        rs = DatabaseConnector.returnQuery(sql);
//        return rs;
        ResultSet rs_enrolled = fetchEnrolledCourses(studentID);
        ResultSet rs_pre = fetchPrereqCourses(section);
        rs_pre.last();
        int rowCount = rs_pre.getRow();
        rs_pre.beforeFirst();
        if (rowCount==0) {
            System.out.println("No prerequisite!!!!...!!!!");
            return true;
        }
        System.out.println("+------------------" + rowCount + "-------------------+");
        while (rs_enrolled.next()) {
            System.out.println(rs_enrolled.getString("courseDept") + "__" + rs_enrolled.getString("courseNum"));
        }
        System.out.println("=========");
        while (rs_pre.next()) {
            System.out.println(rs_pre.getString("prereqDept") + "__" + rs_pre.getString("prereqNum") + "__" + rs_pre.getString("prereqGroup"));
        }
        System.out.println("+-------------------------------------+");
        rs_enrolled.beforeFirst();
        rs_pre.beforeFirst();
        int flag = 0;
        int change = 0;
        while (rs_pre.next()) {
            flag = 0;
            if (isInSet(rs_enrolled, rs_pre.getString("prereqDept"), rs_pre.getString("prereqNum"))) {
                flag = 1;
            }
            String now = rs_pre.getString("prereqGroup");
            while (rs_pre.next()) {
                if (now.compareTo(rs_pre.getString("prereqGroup")) == 0) {
                    if (isInSet(rs_enrolled, rs_pre.getString("prereqDept"), rs_pre.getString("prereqNum"))) {
                        flag = 1;
                    }
                } else {
                    break;
                }
            }
            if (flag == 0) {
                return false;
            }
            rs_pre.previous();
        }
        if (flag == 1) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isAlreadyRegistered(Section section, int studentID) throws SQLException {

        ResultSet rs_stu_reg = fetchEnrolledCourses(CurrentUser.getUser().getID());
        while (rs_stu_reg.next()) {
            if (rs_stu_reg.getString("sectionID").compareTo(section.getSectionID()) == 0
                    && rs_stu_reg.getString("courseDept").compareTo(section.getCourseDept()) == 0
                    && rs_stu_reg.getString("courseNum").compareTo(section.getCourseNum()) == 0) {
                System.out.println("\nisAlreadyRegistered returns: true");
                return true;
            }
        }
        System.out.println("\nisAlreadyRegistered returns: false");
        return false;
    }

    public static boolean registrationEndNotPass(Section section) {
        Term currentTerm = State.getCurrentTerm();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println(now + "___" + currentTerm.getRegistrationEnd());
        if (now.compareTo(currentTerm.getRegistrationEnd()) < 0) {
            System.out.println("\nregistrationEndNotPass returns: true");
            return true;
        } else {
            System.out.println("\nregistrationEndNotPass returns: false");
            return false;
        }
    }
    public static boolean withdrawEndNotPass(Section section) {
        Term currentTerm = State.getCurrentTerm();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        System.out.println(now + "___" + currentTerm.getDropWithoutW());
        if (now.compareTo(currentTerm.getRegistrationEnd()) < 0) {
            System.out.println("\nwithdrawEndNotPass returns: true");
            return true;
        } else {
            System.out.println("\nwithdrawEndNotPass returns: false");
            return false;
        }
    }
    
    public static boolean isFull(Section section) {
        if (section.getStatus().compareTo("full") == 0) {
            System.out.println("\nisFull returns: true");
            return true;
        } else {
            System.out.println("\nisFull returns: false");
            return false;
        }
    }

    
}

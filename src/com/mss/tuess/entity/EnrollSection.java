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
    public int fetch(int studentID, String sectionID, String courseDept, String courseNum, String termID) throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM enrollSection "
                + "WHERE studentID = " + studentID
                + " AND sectionID = '" + sectionID + "' AND courseDept = '" + courseDept + "' AND courseNum = '" 
                + courseNum + "' AND termID = '" + termID+"'";
        rs = DatabaseConnector.returnQuery(sql);

        if (rs.next()) {
            this.setStudentID(rs.getInt("studentID"));
            this.setSectionID(rs.getString("sectionID"));
            this.setCourseDept(rs.getString("courseDept"));
            this.setCourseNum(rs.getString("courseNum"));
            this.setTermID(rs.getString("termID"));
            this.setGrade(rs.getString("grade"));
            return 1;
        }
        else return 0;

    }

    /**
     * Uses the information of this EnrollSection to update the record in the
     * database.
     *
     * @throws SQLException
     */
    public void update() throws SQLException {
        String sql = "UPDATE enrollSection SET "
                + " grade= '" + this.getGrade() + "'"
                + " WHERE studentID=" + this.getStudentID() + " AND sectionID='" + this.getSectionID()
                + "' AND courseDept='" + this.getCourseDept() + "' AND courseNum='" + this.getCourseNum()
                + "' AND termID='" + this.getTermID()+"'";
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
        String sql = "INSERT INTO enrollSection (studentID, sectionID, courseDept, courseNum, termID, "
                + "grade) values "
                + "(" + this.getStudentID() + ", '" + this.getSectionID() + "', '" + this.getCourseDept() + "', '" + this.getCourseNum() + "', '" + this.getTermID()
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
                + section.getTermID() + "', ''"
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

    /**
     * Get all sections which are enrolled by the student
     *
     * @param studentID is the studentID of the current student
     * @return rs the ResultSet of prerequisite information
     */
    public static ResultSet fetchEnrolledCourses(int studentID) throws SQLException {
        ResultSet rs;
        String sql = "select * from enrollSection where studentID=" + studentID;
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }

    /**
     * Get all prerequisite courses for the section
     *
     * @param section is the section student wants to enroll in
     * @return rs the ResultSet of prerequisite information
     */
    public static ResultSet fetchPrereqCourses(Section section) {
        //CurrentUser.getUser().getID();
        ResultSet rs = null;
        String sql = "select * from prerequisite where courseNum='"
                + section.getCourseNum() + "' and courseDept='" + section.getCourseDept() + "'"
                + "ORDER by prereqGroup";
        try {
            rs = DatabaseConnector.returnQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(EnrollSection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * Determines whether the section is in a section set
     *
     * @param rs_enrolled the ResultSet contains the sections information
     * @param courseDept the course department of the section
     * @param courseNum the course number of the section
     * @return true if in the set; false if not
     */
    public static boolean isInSet(ResultSet rs_enrolled, String courseDept, String courseNum) throws SQLException {
        rs_enrolled.beforeFirst();
        while (rs_enrolled.next()) {
            if (courseDept.compareTo(rs_enrolled.getString("courseDept")) == 0
                    && courseNum.compareTo(rs_enrolled.getString("courseNum")) == 0
                    && (rs_enrolled.getString("grade").compareTo("A") == 0
                    || rs_enrolled.getString("grade").compareTo("B") == 0
                    || rs_enrolled.getString("grade").compareTo("C") == 0
                    || rs_enrolled.getString("grade").compareTo("D") == 0)) {
                System.out.println(courseDept + courseNum + "___is IN");
                return true;
            }
        }
        System.out.println(courseDept + courseNum + "___is NOT IN");
        return false;
    }

    /**
     * Determines whether the student has met the prerequisites of the section
     *
     * @param section is the section student wants to enroll in
     * @param studentID the current student's studentID
     * @return true if the student has met the prerequisites of the section;
     * false if not
     */
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
        if (rowCount == 0) {
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

    /**
     * Determines whether the section is already been enrolled by the student
     *
     * @param section is the section student wants to enroll in
     * @param studentID the current student's studentID
     * @return true if the section is already been enrolled by the student;
     * false if not
     */
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

    /**
     * Determines whether the registration date is passed
     *
     * @param section is the section student wants to enroll in
     * @return true if the registration date is not passed; false if is passed
     */
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

    /**
     * Determines whether the withdraw date is passed
     *
     * @param section is the section student wants to enroll in
     * @return true if the withdraw date is not passed; false if is passed
     */
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

    /**
     * Determines whether the section is FULL
     *
     * @param section is the section student wants to enroll in
     * @return true if the section is already full; false if it is not full
     */
    public static boolean isFull(Section section) {
        if (section.getStatus().compareTo("full") == 0) {
            System.out.println("\nisFull returns: true");
            return true;
        } else {
            System.out.println("\nisFull returns: false");
            return false;
        }
    }

    /**
     * Determines whether enrolling a student in a new course section would have
     * a time conflict with already enrolled courses
     *
     * @param student is the student to enroll in the new section
     * @param newSection is the section student wants to enroll in
     * @return whether there is a time conflict or not
     */
    public static boolean isTimeConflict(Student student, Section newSection) {
        try {
            ResultSet rs;
            String sql = "SELECT * FROM \n"
                    + "(SELECT day, startTime, endTime, courseDept, courseNum FROM sectionClass WHERE "
                    + "sectionID='" + newSection.getSectionID() + "' AND "
                    + "courseDept='" + newSection.getCourseDept() + "' AND "
                    + "courseNum='" + newSection.getCourseNum() + "' AND "
                    + "termID='" + newSection.getTermID() + "'\n"
                    + "	UNION ALL\n"
                    + "	SELECT day, startTime, endTime, sc.courseDept, sc.courseNum "
                    + "FROM sectionClass sc, enrollSection es "
                    + "WHERE es.termID='" + newSection.getTermID() + "' AND "
                    + "es.studentID=" + student.getID() + ") AS T1 \n"
                    + "INNER JOIN \n"
                    + "(SELECT day, startTime, endTime, courseDept, courseNum FROM sectionClass WHERE "
                    + "sectionID='" + newSection.getSectionID() + "' AND "
                    + "courseDept='" + newSection.getCourseDept() + "' AND "
                    + "courseNum='" + newSection.getCourseNum() + "' AND "
                    + "termID='" + newSection.getTermID() + "'\n"
                    + "   UNION ALL\n"
                    + "   SELECT day, startTime, endTime, sc.courseDept, sc.courseNum "
                    + "FROM sectionClass sc, enrollSection es "
                    + "WHERE es.termID='" + newSection.getTermID() + "' AND "
                    + "es.studentID=" + student.getID() + ") AS T2 \n"
                    + "WHERE T1.day = T2.day AND\n"
                    + "	T1.startTime <= T2.endTime AND\n"
                    + "	T1.endTime >= T2.endTime AND\n"
                    + "	NOT (T1.courseDept = T2.courseDept AND T1.courseNum = T2.courseNum);\n"
                    + "	";
            rs = DatabaseConnector.returnQuery(sql);

            if (rs.next()) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(EnrollSection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}

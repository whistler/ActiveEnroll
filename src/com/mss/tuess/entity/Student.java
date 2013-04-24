package com.mss.tuess.entity;

import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Student class. Concrete object of abstract User class. Student object has additional attribute of registered date and status.
 * A student can search, enroll/drop a course, check timetable, registered courses and transcripts. 
 */
public class Student extends User {
    private static ArrayList<Student> students = new ArrayList();
    private int studentID =0;
    private String programID;
    private String registeredSince;
    private String status;
    /**
     * Loads all Student records from the database in to a list of Student
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM student";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Student student = new Student();
            student.setID(rs.getInt("studentID"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));
            student.setAddress(rs.getString("address"));
            student.setCity(rs.getString("city"));
            student.setCountry(rs.getString("country"));
            student.setZipcode(rs.getString("zipcode"));
            student.setPhone(rs.getString("phone"));
            student.setProgramID(rs.getString("programID"));
            student.setRegisteredSince(rs.getString("registeredSince"));
            student.setPassword(rs.getString("password"));
            students.add(student);
        }
    }

    /**
     * Returns the student stored at the given index
     *
     * @param index index of the student to return
     * @return Student object at position index
     */
    public static Student get(int index) {
        return students.get(index);
    }

    /**
     * Returns the student arreylist.
     * @return 
     */
    public static ArrayList<Student> getAll() {
        return students;
    }

    /**
     * Fetches current term from the database
     * @return result set 
     * @throws SQLException 
     */
    public static ResultSet fetchCurrentTerm() throws SQLException {
        ResultSet rs;
        int currentTerm = Integer.valueOf(State.getCurrentTerm().getTermID());
        if (currentTerm % 10 != 1) {
            currentTerm--;
        } else if (currentTerm % 10 == 1) {
            currentTerm = currentTerm - 10;
        }
        String sql = "SELECT * FROM enrollSection where termID='" + currentTerm + "'";
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }


    /**
     * @return the studentID
     */
    @Override
    public int getID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    @Override
    public void setID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the programID
     */
    public String getProgramID() {
        return programID;
    }

    /**
     * @param programID the programID to set
     */
    public void setProgramID(String programID) {
        this.programID = programID;
    }

    /**
     * @return the registeredSince
     */
    public String getRegisteredSince() {
        return registeredSince;
    }

    /**
     * @param registeredSince the registeredSince to set
     */
    public void setRegisteredSince(String registeredSince) {
        this.registeredSince = registeredSince;
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
     * Loads the Student by the studentID from the database and encapsulates
     * into this Student objects
     * @param stuId the student ID
     * @throws SQLException
     */
    @Override
    public void fetch(int stuId) {
        try {
            ResultSet rs;
            String sql = "SELECT * FROM student stu WHERE stu.studentID = " + stuId;
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                this.setID(rs.getInt("studentID"));
                this.setFirstName(rs.getString("firstName"));
                this.setLastName(rs.getString("lastName"));
                this.setAddress(rs.getString("address"));
                this.setCity(rs.getString("city"));
                this.setState(rs.getString("state"));
                this.setCountry(rs.getString("country"));
                this.setZipcode(rs.getString("zipcode"));
                this.setPhone(rs.getString("phone"));
                this.setProgramID(rs.getString("programID"));
                this.setRegisteredSince(rs.getString("registeredSince"));
                this.setStatus(rs.getString("status"));
                this.setPassword(rs.getString("password"));
                this.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Uses the information of this Student to update the record in the
     * database.
     *
     * @throws SQLException
     */
    @Override
    public void update() throws SQLException {
        String sql = "UPDATE student SET "
                + "firstName='" + this.getFirstName() + "', "
                + "lastName='" + this.getLastName() + "', "
                + "address='" + this.getAddress() + "', "
                + "city='" + this.getCity() + "', "
                + "country='" + this.getCountry() + "', "
                + "state='" + this.getState() + "', "
                + "zipcode='" + this.getZipcode() + "', "
                + "phone='" + this.getPhone() + "', "
                + "programID='" + this.getProgramID() + "', "
                + "registeredSince='" + this.getRegisteredSince() + "', "
                + "status='" + this.getStatus() + "', "
                + "password='" + this.getPassword() + "', "
                + "email='" + this.getEmail() + "' "
                + "WHERE studentID=" + this.getID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Delete this Student record in the database.
     *
     * @throws SQLException
     */
    @Override
    public void delete() throws SQLException {
        String sql = "DELETE FROM student WHERE studentID=" + this.getID();
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Insert this Student into the database.
     *
     * @throws SQLException
     */
    @Override
    public void insert() throws SQLException {
        String sql = "INSERT INTO student (studentID, firstName, lastName, "
                + "address, city, state, country, zipcode, phone, programID, "
                + "registeredSince, status, password, email) values " + "(" 
                + this.getID() + ", '" 
                + this.getFirstName() + "', '" 
                + this.getLastName() + "', '" 
                + this.getAddress() + "', '" 
                + this.getCity() + "', '" 
                + this.getState() + "', '" 
                + this.getCountry() + "', '" 
                + this.getZipcode() + "', '" 
                + this.getPhone() + "', '" 
                + this.getProgramID() + "', '" 
                + this.getRegisteredSince() + "', '" 
                + this.getStatus() + "', '" 
                + this.getPassword() + "', '" 
                + this.getEmail() + "')";
        DatabaseConnector.updateQuery(sql);
    }

    /**
     * Returns number of credits completed by the Student
     *
     * @return number of credits completed
     */
    public int getCreditsCompleted() {
        try {
            ResultSet rs;
            String sql = "SELECT SUM(credit) AS credits FROM "
                    + "(SELECT DISTINCT c.courseDept, c.courseNum, c.credit "
                    + "FROM enrollSection es, section s, course c "
                    + "WHERE c.courseDept = s.courseDept AND "
                    + "c.courseNum = s.courseNum AND "
                    + "es.courseDept = s.courseDept AND "
                    + "es.courseNum = s.courseNum AND "
                    + "es.termID = s.termID AND "
                    + "grade NOT IN ('W','F') AND "
                    + "es.studentID = '" + this.studentID + "'"
                    + ") AS T;";
            rs = DatabaseConnector.returnQuery(sql);
            if (rs.next()) {
                return rs.getInt("credits");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    /**
     * Returns required courses.
     * @return the arraylist of required courses
     */
    public ArrayList<Course> getRequiredCourses() {
        ArrayList list = new ArrayList();
        try {
            ResultSet rs;
            String sql = "SELECT courseNum, courseDept "
                    + "FROM degreeCourse, student\n"
                    + "WHERE student.studentID = '" + this.studentID + "' AND "
                    + "student.programID = degreeCourse.programID";
            rs = DatabaseConnector.returnQuery(sql);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseDept(rs.getString("courseDept"));
                course.setCourseNum(rs.getString("courseNum"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    /**
     * Returns incomplete required courses.
     * @return the arraylist of incomplete required courses
     */
    public ArrayList<Course> getIncompleteRequiredCourses() {
        ArrayList list = new ArrayList();
        try {
            ResultSet rs;
            String sql = "SELECT degreeCourse.courseDept, degreeCourse.courseNum "
                    + "FROM degreeCourse, student, enrollSection "
                    + "WHERE student.studentID = " + this.studentID + " AND "
                    + "student.programID = degreeCourse.programID AND "
                    + "enrollSection.studentID = student.studentID AND "
                    + "degreeCourse.courseDept = enrollSection.courseDept AND "
                    + "degreeCourse.courseNum = enrollSection.courseNum AND "
                    + "enrollSection.grade NOT IN ('F', 'W');";
            rs = DatabaseConnector.returnQuery(sql);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseDept(rs.getString("courseDept"));
                course.setCourseNum(rs.getString("courseNum"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    /**
     * Returns complete required courses.
     * @return the arraylist of complete required courses
     */
    public ArrayList<Course> getCompletedRequiredCourses() {
        ArrayList list = new ArrayList();
        try {
            ResultSet rs;
            String sql = "SELECT T1.courseNum, T1.courseDept FROM"
                    + "(SELECT courseNum, courseDept FROM degreeCourse, student "
                    + "WHERE student.studentID = '" + getID() + "' AND "
                    + "student.programID = degreeCourse.programID) T1 "
                    + "LEFT JOIN "
                    + "(SELECT degreeCourse.courseDept, degreeCourse.courseNum "
                    + "FROM degreeCourse, student, enrollSection "
                    + "WHERE student.studentID = '" + getID() + "' AND "
                    + "student.programID = degreeCourse.programID AND "
                    + "enrollSection.studentID = student.studentID AND "
                    + "degreeCourse.courseDept = enrollSection.courseDept AND "
                    + "degreeCourse.courseNum = enrollSection.courseNum AND "
                    + "enrollSection.grade NOT IN ('F', 'W')) T2 "
                    + "ON T1.courseNum = T2.courseNum AND T1.courseDept = T2.courseDept "
                    + "WHERE T2.courseNum IS NULL AND T2.courseDept IS NULL";
            rs = DatabaseConnector.returnQuery(sql);
            while (rs.next()) {
                Course course = new Course();
                course.setCourseDept(rs.getString("courseDept"));
                course.setCourseNum(rs.getString("courseNum"));
                list.add(course);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}

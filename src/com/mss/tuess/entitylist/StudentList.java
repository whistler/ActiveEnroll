package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Student;
import com.mss.tuess.util.DatabaseConnector;
import com.mss.tuess.util.State;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentList {

    private static ArrayList<Student> students = new ArrayList();

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
    public static ResultSet fetchCurrentTerm() throws SQLException {  // this function is for the mailgrade module
        ResultSet rs;
        String sql = "SELECT * FROM enrollSection where termID='"+State.getCurrentSection().getSectionID()+"'"; // termID should be changed
        rs = DatabaseConnector.returnQuery(sql);
        return rs;
    }
    public static ArrayList<Student> getAll() {
    return students;
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
}

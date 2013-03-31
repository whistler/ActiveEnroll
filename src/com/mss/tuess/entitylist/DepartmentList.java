package com.mss.tuess.entitylist;

import com.mss.tuess.entity.Department;
import com.mss.tuess.util.DatabaseConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DepartmentList {

    private static ArrayList<Department> departments = new ArrayList();

    /**
     * Loads all Department records from the database in to a list of Department
     * objects
     *
     * @throws SQLException
     */
    public static void fetch() throws SQLException {
        ResultSet rs;
        String sql = "SELECT * FROM department";
        rs = DatabaseConnector.returnQuery(sql);
        while (rs.next()) {
            Department department = new Department();
            department.setDeptID(rs.getString("deptID"));
            department.setDeptName(rs.getString("courseName"));
            department.setFacultyName(rs.getString("facultyName"));

            departments.add(department);
        }
    }

    /**
     * Returns the department stored at the given index
     *
     * @param index index of the department to return
     * @return Department object at position index
     */
    public static Department get(int index) {
        return departments.get(index);
    }
}

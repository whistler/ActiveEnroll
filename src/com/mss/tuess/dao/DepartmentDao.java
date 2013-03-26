package com.mss.dao;

import com.mss.entity.Department;

public class DepartmentDao {

    public void add(Department department) {
        saveOrUpdate(department);
    }

    public void delete(Department department) {
        delete(department);
    }

    public void update(Department department) {
        saveOrUpdate(department);
    }

    public Department findById(Department department) {
        return get(department.getClass(), department.getId());
    }
}

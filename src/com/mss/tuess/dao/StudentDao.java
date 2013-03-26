package com.mss.dao;

import com.mss.entity.Student;

public class StudentDao {

    public void add(Student student) {
        saveOrUpdate(student);
    }

    public void delete(Student student) {
        delete(student);
    }

    public void update(Student student) {
        saveOrUpdate(student);
    }

    public Student findById(Student student) {
        return get(student.getClass(), student.getId());
    }
}

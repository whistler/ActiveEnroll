package com.mss.dao;

import com.mss.entity.Instructor;

public class InstructorDao {

    public void add(Instructor instructor) {
        saveOrUpdate(instructor);
    }

    public void delete(Instructor instructor) {
        delete(instructor);
    }

    public void update(Instructor instructor) {
        saveOrUpdate(instructor);
    }

    public Instructor findById(Instructor instructor) {
        return get(instructor.getClass(), instructor.getId());
    }
}

package com.mss.dao;

import com.mss.entity.Enrollcourse;

public class EnrollcourseDao {

    public void add(Enrollcourse enrollcourse) {
        saveOrUpdate(enrollcourse);
    }

    public void delete(Enrollcourse enrollcourse) {
        delete(enrollcourse);
    }

    public void update(Enrollcourse enrollcourse) {
        saveOrUpdate(enrollcourse);
    }

    public Enrollcourse findById(Enrollcourse enrollcourse) {
        return get(enrollcourse.getClass(), enrollcourse.getId());
    }
}

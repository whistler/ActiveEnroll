package com.mss.dao;

import com.mss.entity.Course;

public class CourseDao {

    public void add(Course course) {
        saveOrUpdate(course);
    }

    public void delete(Course course) {
        delete(course);
    }

    public void update(Course course) {
        saveOrUpdate(course);
    }

    public Course findById(Course course) {
        return get(course.getClass(), course.getId());
    }
}

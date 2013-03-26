package com.mss.dao;

import com.mss.entity.Faculty;

public class FacultyDao {

    public void add(Faculty faculty) {
        saveOrUpdate(faculty);
    }

    public void delete(Faculty faculty) {
        delete(faculty);
    }

    public void update(Faculty faculty) {
        saveOrUpdate(faculty);
    }

    public Faculty findById(Faculty faculty) {
        return get(faculty.getClass(), faculty.getId());
    }
}

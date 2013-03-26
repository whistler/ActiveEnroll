package com.mss.dao;

import com.mss.entity.Prerequisite;

public class PrerequisiteDao {

    public void add(Prerequisite prerequisite) {
        saveOrUpdate(prerequisite);
    }

    public void delete(Prerequisite prerequisite) {
        delete(prerequisite);
    }

    public void update(Prerequisite prerequisite) {
        saveOrUpdate(prerequisite);
    }

    public Prerequisite findById(Prerequisite prerequisite) {
        return get(prerequisite.getClass(), prerequisite.getId());
    }
}

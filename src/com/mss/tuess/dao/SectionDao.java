package com.mss.dao;

import com.mss.entity.Section;

public class SectionDao {

    public void add(Section section) {
        saveOrUpdate(section);
    }

    public void delete(Section section) {
        delete(section);
    }

    public void update(Section section) {
        saveOrUpdate(section);
    }

    public Section findById(Section section) {
        return get(section.getClass(), section.getId());
    }
}

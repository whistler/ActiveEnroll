package com.mss.dao;

import com.mss.entity.Program;

public class ProgramDao {

    public void add(Program program) {
        saveOrUpdate(program);
    }

    public void delete(Program program) {
        delete(program);
    }

    public void update(Program program) {
        saveOrUpdate(program);
    }

    public Program findById(Program program) {
        return get(program.getClass(), program.getId());
    }
}

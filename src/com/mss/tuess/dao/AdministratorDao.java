package com.mss.tuess.dao;

import com.mss.entity.Administrator;

public class AdministratorDao {

    public void add(Administrator administrator) {
        saveOrUpdate(administrator);
    }

    public void delete(Administrator administrator) {
        delete(administrator);
    }

    public void update(Administrator administrator) {
        saveOrUpdate(administrator);
    }

    public Administrator findById(Administrator administrator) {
        return get(administrator.getClass(), administrator.getId());
    }
}

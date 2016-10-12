package ru.itis.dao;

import ru.itis.models.Owner;

import java.sql.Connection;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class DaojdbcOwnersImpl implements DaojdbcOwners {

    private Connection connection;

    public DaojdbcOwnersImpl() {
        connection = JdbcSupportFactory.getInstance().getConnection();
    }
    public List<Owner> getAll() {
        return null;
    }

    public Owner find(int id) {
        return null;
    }

    public void add(Owner owner) {

    }

    public void update(Owner owner) {

    }

    public void delete(int id) {

    }
}

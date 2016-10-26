package ru.itis.dao;

import ru.itis.models.Owner;

import java.util.List;


public interface OwnersDao {

    List<Owner> getAll();

    Owner find(int id);

    void add(Owner owner);

    void update(Owner owner);

    void delete(int id);
}

package ru.itis.dao;

import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public interface OwnersDao {
    List<Owner> getAll();

    Owner findId(int id);

    Owner findLogin(String login);

    void add(Owner owner);

    void update(Owner owner);

    void delete(int id);

    void setToken(String login, String password, String token);

    String getToken(String login);

}

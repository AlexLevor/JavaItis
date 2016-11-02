package ru.itis.services;

import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public interface OwnerService {
    List<Owner> getAllOwner();
    Owner findOwnerID(int id);
    Owner findOwnerLogin(String login);
    void updateOwner(Owner car);
    void addOwner(Owner car);
    void deleteOwner(int id);
    void setToken(String login, String password);
    String getToken(String login);
}

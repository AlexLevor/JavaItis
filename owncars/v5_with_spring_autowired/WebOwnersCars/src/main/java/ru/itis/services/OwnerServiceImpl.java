package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.dao.OwnersDao;
import ru.itis.models.Owner;
import ru.itis.utils.RandomString;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
@Service
public class OwnerServiceImpl implements OwnerService {
    private OwnersDao ownersDao;

    @Autowired
    public OwnerServiceImpl(OwnersDao ownersDao){
        this.ownersDao = ownersDao;
    }

    public List<Owner> getAllOwner() {
        return this.ownersDao.getAll();
    }

    public Owner findOwnerID(int id) {
        return ownersDao.findId(id);
    }

    public Owner findOwnerLogin(String login) {
        return ownersDao.findLogin(login);
    }

    public void updateOwner(Owner owner) {
        ownersDao.update(owner);
    }

    public void addOwner(Owner owner) {
        ownersDao.add(owner);
    }

    public void deleteOwner(int id) {
        ownersDao.delete(id);
    }

    public void setToken(String login, String password) {
       RandomString randomString = new RandomString(10);
       ownersDao.setToken(login, password, randomString.nextString());
    }

    public String getToken(String login) {
        return ownersDao.getToken(login);
    }

    public List<Owner> getAllOwnersOfAge(int age) {
        return ownersDao.getAllOwnersOfAge(age);
    }
}

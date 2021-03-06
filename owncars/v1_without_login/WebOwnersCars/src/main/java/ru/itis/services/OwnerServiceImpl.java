package ru.itis.services;

import ru.itis.dao.OwnersDao;
import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class OwnerServiceImpl implements OwnerService {
    private OwnersDao ownersDao;

    public OwnerServiceImpl(OwnersDao ownersDao){
        this.ownersDao = ownersDao;
    }

    public List<Owner> getAllOwner() {
        return this.ownersDao.getAll();
    }

    public Owner findOwner(int id) {
        return ownersDao.find(id);
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
}

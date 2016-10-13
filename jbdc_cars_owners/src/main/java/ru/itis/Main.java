package ru.itis;

import ru.itis.SuportFactories.DaoSupportFactory;
import ru.itis.dao.OwnersDao;
import ru.itis.models.Owner;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Main {
    public static void main(String[] args){


        OwnersDao ownerDao = DaoSupportFactory.getInstance().getDaoOwners();

        Owner owner = ownerDao.find(3);

        System.out.println(owner);

        Owner owner1 = new Owner(3, "BBBBB", 30, "AAAAAAAAAAAAA");

        ownerDao.update(owner1);

        owner = ownerDao.find(3);

        System.out.println(owner);
    }
}

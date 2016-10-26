package ru.itis;

import ru.itis.SuportFactories.DaoSupportFactory;
import ru.itis.dao.OwnersDao;

public class Main {
    public static void main(String[] args){


        OwnersDao ownerDao = DaoSupportFactory.getInstance().getDaoOwners();

       /* Owner owner = ownerDao.find(3);

        System.out.println(owner);

        Owner owner1 = new Owner("BBBBB", 30, "AAAAAAAAAAAAA");

        ownerDao.update(owner1);

        owner = ownerDao.find(3);

        System.out.println(owner);*/

        ownerDao.delete(3);
    }
}

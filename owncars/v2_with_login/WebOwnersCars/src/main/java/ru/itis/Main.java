package ru.itis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.itis.dao.OwnersDao;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;
import ru.itis.services.RandomString;

import java.io.*;
import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class Main {
    public static void main(String[] args) {


        //  OwnerService ownerDao = ServiceSupportFactory.getInstance().getServiceOwners();

        //  Owner owner = ownerDao.findId(3);

        //  System.out.println(owner);

        // Owner owner1 = new Owner(11,"AAAAA", 30, "AAAAAAAAAAAAA");

       /* List<Owner> s = ownerDao.getAllOwner();
        for (Owner i : s){
            System.out.println(i.toString());
        }*/

        //  Owner owner = ownerDao.findId(3);

        //  System.out.println(owner);

        // ownerDao.delete(3);
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("context.xml");
        OwnerService ownerService = (OwnerService) applicationContext.getBean("ownerService");
        System.out.println(ownerService.getToken("mit"));
        ownerService.setToken("ann","123");
        System.out.println(ownerService.findOwnerLogin("ann") + ownerService.getToken("ann"));

    }

}

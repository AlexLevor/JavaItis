package ru.itis.InterfaceOfClasses;

import ru.itis.dao.UsersDao;
import ru.itis.service.SimpleUsersService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by KFU-user on 07.10.2016.
 */
public class DaoServiceSupportFactory {
    private static DaoServiceSupportFactory instance;

    private Properties properties;
    private UsersDao dao;
    private SimpleUsersService service;

    private DaoServiceSupportFactory(){
        try{
            properties = new Properties();
            properties.load(
                    new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\src\\resources\\DaoServiceInterface.properties"));
            String daoClass = properties.getProperty("dao.class");
            String serviceClass = properties.getProperty("service.class");
            this.dao = (UsersDao) Class.forName(daoClass).newInstance();
            this.service = (SimpleUsersService) Class.forName(serviceClass).newInstance();
            service.setUsersDao(dao);
        }catch (IOException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException();
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException();
        }
    }

    static {
    instance = new DaoServiceSupportFactory();
    }
    public UsersDao getDao(){
        return dao;
    }
    public SimpleUsersService getService(){
        return service;
    }
    public static DaoServiceSupportFactory getInstance(){
        return instance;
    }
}

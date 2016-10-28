package ru.itis.supportFactories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.OwnersDao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class DaoSupportFactory {

    private static DaoSupportFactory instance;
    private OwnersDao daoOwners;
    private CarsDao daoCars;

    private Properties properties;

    private DaoSupportFactory (){
        try{
            properties = new Properties();
            //для maina
            //properties.load(new FileInputStream(".\\src\\main\\resources\\DaoProperties.properties"));
            // для tomcata через maven
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\apache-tomcat-8.5.6\\apache-tomcat-8.5.6\\webapps\\ROOT\\WEB-INF\\classes\\DaoProperties.properties"));

            String daoCarsClass=properties.getProperty("carsdao.class");
            String daoUsersClass=properties.getProperty("ownersdao.class");

            Class daoCarsClassConstructor = Class.forName(daoCarsClass);
            Constructor constructorCars = daoCarsClassConstructor.getConstructor(Connection.class);
            daoCars = (CarsDao)constructorCars.newInstance(JDBCConnectionFactory.getInstance().getConnection());

            Class daoOwnersClassConstructor = Class.forName(daoUsersClass);
            Constructor constructorOwners = daoOwnersClassConstructor.getConstructor(Connection.class);
            daoOwners = (OwnersDao) constructorOwners.newInstance(JDBCConnectionFactory.getInstance().getConnection());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    static {
        instance = new DaoSupportFactory();
    }

    public OwnersDao getDaoOwners(){
        return daoOwners;
    }
    public CarsDao getDaoCars(){
        return daoCars;
    }
    public static DaoSupportFactory getInstance(){
        return instance;
    }

}

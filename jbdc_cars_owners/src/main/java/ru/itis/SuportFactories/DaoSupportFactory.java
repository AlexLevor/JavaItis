package ru.itis.SuportFactories;

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
 * Created by KFU-user on 12.10.2016.
 */
public class DaoSupportFactory {

    private static DaoSupportFactory instance;
    private OwnersDao daoOwners;
    private CarsDao daoCars;

    private Properties properties;

    private DaoSupportFactory (){
        try{
            properties = new Properties();
            properties.load(new FileInputStream(".\\src\\main\\resources\\DaoProperties.properties"));
            String daoCarsClass=properties.getProperty("carsdao.class");
            String daoUsersClass=properties.getProperty("ownersdao.class");

            Class daoCarsClassConstructor = Class.forName(daoCarsClass);
            Constructor constructorCars = daoCarsClassConstructor.getConstructor(Connection.class);
            daoCars = (CarsDao)constructorCars.newInstance(JdbcConnectionFactory.getInstance().getConnection());

            Class daoOwnersClassConstructor = Class.forName(daoUsersClass);
            Constructor constructorOwners = daoOwnersClassConstructor.getConstructor(Connection.class);
            daoOwners = (OwnersDao) constructorOwners.newInstance(JdbcConnectionFactory.getInstance().getConnection());

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

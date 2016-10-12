package ru.itis.SuportFactories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.DaojdbcOwners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class DaoSupportFactory {

    private static DaoSupportFactory instance;
    private DaojdbcOwners daoOwners;
    private CarsDao daoCars;

    private Properties properties;

    private DaoSupportFactory (){
        try{
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\Desktop\\JavaItis\\jbdc_cars_owners\\src\\main\\resources\\DaoProperties.properties"));
            String daoCarsClass=properties.getProperty("daojdbccars.class");
            String daoUsersClass=properties.getProperty("daojdbcusers.class");
            this.daoCars = (CarsDao)Class.forName(daoCarsClass).newInstance();
            this.daoOwners = (DaojdbcOwners)Class.forName(daoUsersClass).newInstance();
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
        }
    }
    static {
        instance = new DaoSupportFactory();
    }

    public DaojdbcOwners getDaoOwners(){
        return daoOwners;
    }
    public CarsDao getDaoCars(){
        return daoCars;
    }
    public static DaoSupportFactory getInstance(){
        return instance;
    }
}

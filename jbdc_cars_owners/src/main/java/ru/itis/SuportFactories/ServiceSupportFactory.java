package ru.itis.SuportFactories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.OwnersDao;
import ru.itis.services.CarsService;
import ru.itis.services.OwnersService;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by AlexLevor on 21.10.2016.
 */
public class ServiceSupportFactory {
    private static ServiceSupportFactory instance;
    private OwnersService ownersService;
    private CarsService carsService;

    private Properties properties;

    private ServiceSupportFactory(){
        try{
            properties = new Properties();
            properties.load(new FileInputStream(".\\src\\main\\resources\\ServicesProperties.properties"));
            String serviceCarsClass=properties.getProperty("carsservice.class");
            String serviceOwnersClass=properties.getProperty("ownersservice.class");

            Class daoCarsClassConstructor = Class.forName(serviceCarsClass);
            Constructor constructorCars = daoCarsClassConstructor.getConstructor(CarsDao.class);
            carsService = (CarsService) constructorCars.newInstance(DaoSupportFactory.getInstance().getDaoCars());

            Class daoOwnersClassConstructor = Class.forName(serviceOwnersClass);
            Constructor constructorOwners = daoOwnersClassConstructor.getConstructor(OwnersDao.class);
            ownersService = (OwnersService) constructorOwners.newInstance(DaoSupportFactory.getInstance().getDaoOwners());

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public OwnersService getServiceOwners(){
            return ownersService;
        }
        public CarsService getServiceCars(){
            return carsService;
        }
        public static ServiceSupportFactory getInstance(){
            return instance;
        }
}

package ru.itis.supportFactories;

import ru.itis.dao.CarsDao;
import ru.itis.dao.OwnersDao;
import ru.itis.services.CarService;
import ru.itis.services.OwnerService;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class ServiceSupportFactory {

    private static ServiceSupportFactory instance;
    private OwnerService ownersService;
    private CarService carsService;

    private Properties properties;

    private ServiceSupportFactory(){
        try{
            properties = new Properties();
            //для maina
            //properties.load(new FileInputStream(".\\src\\main\\resources\\ServicesProperties.properties"));
            //для tomcata через maven
            properties.load(new FileInputStream("C:\\Users\\KFU-user\\apache-tomcat-8.5.6\\apache-tomcat-8.5.6\\webapps\\ROOT\\WEB-INF\\classes\\ServicesProperties.properties"));
/*
            File catalinaBase = new File( System.getProperty( "catalina.base" ) ).getAbsoluteFile();
            File propertyFile = new File( catalinaBase, "resources/ServiceProperty.properties" );
            InputStream inputStream = new FileInputStream( propertyFile );
            properties.load(inputStream);
*/
            String serviceCarsClass=properties.getProperty("carsservice.class");
            String serviceOwnersClass=properties.getProperty("ownersservice.class");

            Class daoCarsClassConstructor = Class.forName(serviceCarsClass);
            Constructor constructorCars = daoCarsClassConstructor.getConstructor(CarsDao.class);
            carsService = (CarService) constructorCars.newInstance(DaoSupportFactory.getInstance().getDaoCars());

            Class daoOwnersClassConstructor = Class.forName(serviceOwnersClass);
            Constructor constructorOwners = daoOwnersClassConstructor.getConstructor(OwnersDao.class);
            ownersService = (OwnerService) constructorOwners.newInstance(DaoSupportFactory.getInstance().getDaoOwners());

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

    static {
        instance = new ServiceSupportFactory();
    }

    public OwnerService getServiceOwners(){
        return ownersService;
    }
    public CarService getServiceCars(){
        return carsService;
    }
    public static ServiceSupportFactory getInstance(){
        return instance;
    }

}

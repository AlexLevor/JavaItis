package ru.itis.SuportFactories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by KFU-user on 13.10.2016.
 */

public class JdbcConnectionFactory {

    private static JdbcConnectionFactory instance;
    private Properties properties;
    private  Connection connection;


    private JdbcConnectionFactory(){
        try {
            this.properties = new Properties();
            this.properties.load(new FileInputStream(".\\src\\main\\resources\\JdbcProperties.properties"));
            String URL = this.properties.getProperty("jdbc.url");
            String name = this.properties.getProperty("jdbc.username");
            String password = this.properties.getProperty("jdbc.password");
            String classDriver = this.properties.getProperty("jdbc.driver");
            Class.forName(classDriver);
            this.connection = DriverManager.getConnection(URL, name, password);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    static {
        instance = new JdbcConnectionFactory();
    }

    public Connection getConnection(){
        return this.connection;
    }
    public static JdbcConnectionFactory getInstance(){
        return instance;
    }
}



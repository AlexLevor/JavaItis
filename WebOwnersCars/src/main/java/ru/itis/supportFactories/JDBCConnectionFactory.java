package ru.itis.supportFactories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public class JDBCConnectionFactory {

    private static JDBCConnectionFactory instance;
    private Properties properties;
    private Connection connection;


    private JDBCConnectionFactory(){
        try {
            this.properties = new Properties();

            //для maina
            //properties.load(new FileInputStream(".\\src\\main\\resources\\JdbcProperties.properties"));
            //для tomcata через maven
            this.properties.load(new FileInputStream("C:\\Users\\KFU-user\\apache-tomcat-8.5.6\\apache-tomcat-8.5.6\\webapps\\ROOT\\WEB-INF\\classes\\JdbcProperties.properties"));
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
        instance = new JDBCConnectionFactory();
    }

    public Connection getConnection(){
        return this.connection;
    }
    public static JDBCConnectionFactory getInstance(){
        return instance;
    }

}

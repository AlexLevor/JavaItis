package ru.itis.dao;

import ru.itis.models.Car;

import java.sql.Connection;
import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class CarsDaoImpl implements CarsDao {

    private Connection connection;

    public CarsDaoImpl(Connection connection){
        this.connection = connection;
        }
    public List<Car> getAll() {
        return null;
    }

    public Car find(int id) {
        return null;
    }

    public void add(Car car) {

    }

    public void update(Car car) {

    }

    public void delete(int id) {

    }
}

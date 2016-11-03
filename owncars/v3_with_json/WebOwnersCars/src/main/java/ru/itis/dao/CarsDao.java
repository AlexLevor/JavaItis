package ru.itis.dao;

import ru.itis.models.Car;

import java.util.List;

/**
 * Created by AlexLevor on 26.10.2016.
 */
public interface CarsDao {
    List<Car> getAllCarsOfOne(int id);

    List<Car> getAll();

    Car find(int id);

    void add(Car car);

    void update(Car car);

    void delete(int id);
}

package ru.itis.dao;

import ru.itis.models.Car;

import java.util.List;


public interface CarsDao {

    List<Car> getAll();

    Car find(int id);

    void add(Car car);

    void update(Car car);

    void delete(int id);
}

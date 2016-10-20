package ru.itis.services;

/**
 * Created by AlexLevor on 21.10.2016.
 */

import ru.itis.models.Car;

import java.util.List;

public interface CarsService {
    List<Car> getAllCars();
    Car findCar(int id);
    void updateCar(Car car);
    void addCar(Car car);
    void deleteCar(int id);
}